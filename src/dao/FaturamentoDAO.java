/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.AgendaBean;
import model.bean.ConsultaBean;
import model.bean.ConvenioBean;
import model.bean.FaturamentoBean;
import model.bean.FluxoDeCaixaBean;
import model.bean.FormaPagamentoBean;
import model.bean.FuncionarioBean;
import model.bean.LaudoBean;
import model.bean.PacienteBean;
import model.bean.ProfissionalBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import util.DbMysqlUtil;
import util.MaskFormatTextUtil;
import view.laudo.LaudoView;

/**
 *
 * @author vande
 */
public abstract class FaturamentoDAO {

    /**
     * Faturar um agendamento
     *
     * @param faturamento
     * @return
     * @throws Exception
     */
    public static FaturamentoBean createFaturamento(FaturamentoBean faturamento) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into faturamento values (null, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, faturamento.getAgenda().getAgendamentoCodigo());
            stmt.setInt(2, faturamento.getPorcentagemDesconto());
            stmt.setDouble(3, faturamento.getValorTotal());
            stmt.setInt(4, faturamento.getFormaPagamento().getFormaPagCodigo());
            stmt.setInt(5, faturamento.getFaturadoPor().getFuncionarioCodigo());
            stmt.setString(6, faturamento.getDataFaturamento());
            stmt.executeUpdate();
            faturamento.setFaturamentoCodigo(DbMysqlUtil.getValueIndex(stmt.getGeneratedKeys()));
            return faturamento;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Procurar um faturamento pelo código do agendamento.
     *
     * @param faturamento
     * @return
     * @throws Exception
     */
    public static FaturamentoBean searchFaturamentoByAgendamento(FaturamentoBean faturamento) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from faturamento, formaPagamento \n"
                    + "where agendamentoCodigo = ? and faturamento.formaPagCodigo = formaPagamento.formaPagCodigo");
            stmt.setInt(1, faturamento.getAgenda().getAgendamentoCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                faturamento.setFaturamentoCodigo(rs.getInt("faturamentoCodigo"));
                faturamento.getAgenda().setAgendamentoCodigo(rs.getInt("agendamentoCodigo"));
                faturamento.setFormaPagamento(new FormaPagamentoBean());
                faturamento.getFormaPagamento().setFormaPagCodigo(rs.getInt("formaPagCodigo"));
                faturamento.getFormaPagamento().setDescricao(rs.getString("formaPagamento.descricao"));
                faturamento.setPorcentagemDesconto(Integer.parseInt(String.format("%.0f", rs.getDouble("porcentagemDesconto"))));
                faturamento.setValorTotal(rs.getDouble("valorTotal"));
                faturamento.setFaturadoPor(new FuncionarioBean(rs.getInt("faturadoPor")));
                faturamento.setDataFaturamento(rs.getString("dataFaturamento"));
                return faturamento;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }

    public static List<FaturamentoBean> searchFaturamentos(FluxoDeCaixaBean caixa) throws Exception {
        List<FaturamentoBean> faturamentos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sqlProfissional = "";
            String sqlConvenio = "";
            String sqlConsulta = "";
            String sqlForPagamento = "";
            
            if(caixa.getProfissional()!=null){
                sqlProfissional = "and ag.profissionalCodigo = "+caixa.getProfissional().getProfissionalCodigo();
            }
            if(caixa.getConvenio()!=null){
                sqlConvenio = "and conv.convenioCodigo = "+caixa.getConvenio().getConvenioCodigo();
            }
            if(caixa.getConsulta()!=null){
                sqlConsulta = "and cons.consultaCodigo = "+caixa.getConsulta().getConsultaCodigo();
            }
            if(caixa.getFormaPagamento()!=null){
                sqlForPagamento = "and form.formaPagCodigo = "+caixa.getFormaPagamento().getFormaPagCodigo();
            }
            
            String consulta = ""
                    + "select faturamentoCodigo, DATE_FORMAT(ag.dataAgendamento, '%Y-%m-%d') as dataAgenda, "
                    + "     DATE_FORMAT(ag.dataAgendamento, '%H:%i') as horaAgenda, profissionalCodigo, "
                    + "     p2.nome as profissionalNome, pacienteCodigo, p1.nome as nomePaciente, "
                    + "     conv.convenioCodigo, conv.nome, cons.consultaCodigo, cons.descricao, "
                    + "     valorTotal, form.formaPagCodigo, form.descricao \n"
                    + "from faturamento as fat, agenda as ag, pessoa as p1, pessoa as p2, convenio as conv, consulta as cons, formapagamento as form \n"
                    + "where fat.agendamentoCodigo = ag.agendamentoCodigo \n"
                    + "and ag.profissionalCodigo = p2.pessoaCodigo "+sqlProfissional+"\n"
                    + "and ag.pacienteCodigo = p1.pessoaCodigo \n"
                    + "and ag.convenioCodigo = conv.convenioCodigo "+sqlConvenio+"\n"
                    + "and ag.consultaCodigo = cons.consultaCodigo "+sqlConsulta+" \n"
                    + "and fat.formaPagCodigo = form.formaPagCodigo "+sqlForPagamento+" \n"
                    + "and DATE_FORMAT(ag.dataAgendamento, '%Y-%m-%d') between ? and ? \n"
                    + "order by dataAgenda ASC";

            stmt = con.prepareStatement(consulta);
            stmt.setString(1, MaskFormatTextUtil.dataUs(caixa.getDataInicial()));
            stmt.setString(2, MaskFormatTextUtil.dataUs(caixa.getDataFinal()));
            rs = stmt.executeQuery();
            while(rs.next()){
                FaturamentoBean item = new FaturamentoBean();
                item.setFaturamentoCodigo(rs.getInt("faturamentoCodigo"));
                item.setAgenda(new AgendaBean());
                item.getAgenda().setDataAgendamento(new SimpleDateFormat("yyyy-MM-dd H:m").parse(rs.getString("dataAgenda")+" "+rs.getString("horaAgenda")));
                item.getAgenda().setProfissional(new ProfissionalBean());
                item.getAgenda().getProfissional().setProfissionalCodigo(rs.getInt("profissionalCodigo"));
                item.getAgenda().getProfissional().setNome(rs.getString("profissionalNome"));
                item.getAgenda().setPaciente(new PacienteBean());
                item.getAgenda().getPaciente().setNome(rs.getString("nomePaciente"));
                item.getAgenda().getPaciente().setPacienteCodigo(rs.getInt("pacienteCodigo"));
                item.getAgenda().setConvenio(new ConvenioBean(rs.getInt("conv.convenioCodigo"), rs.getString("conv.nome")));
                item.getAgenda().setConsulta(new ConsultaBean(rs.getInt("cons.consultaCodigo"), rs.getString("cons.descricao")));
                item.setValorTotal(rs.getDouble("valorTotal"));
                item.setFormaPagamento(new FormaPagamentoBean(rs.getInt("form.formaPagCodigo"), rs.getString("form.descricao")));
                faturamentos.add(item);
            }
            return faturamentos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Alterar dados do faturamento
     *
     * @param faturamento
     * @return
     * @throws Exception
     */
    public static boolean updateFaturamento(FaturamentoBean faturamento) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update faturamento set porcentagemDesconto = ?, valorTotal = ?, formaPagCodigo = ?, faturadoPor = ?, dataFaturamento = ? where faturamentoCodigo = ?");
            stmt.setInt(1, faturamento.getPorcentagemDesconto());
            stmt.setDouble(2, faturamento.getValorTotal());
            stmt.setInt(3, faturamento.getFormaPagamento().getFormaPagCodigo());
            stmt.setInt(4, faturamento.getFaturadoPor().getFuncionarioCodigo());
            stmt.setString(5, faturamento.getDataFaturamento());
            stmt.setInt(6, faturamento.getFaturamentoCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.getConnection();
        }
    }

    /**
     * Excluir faturamento do agendamento
     *
     * @param faturamento
     * @return
     * @throws Exception
     */
    public static boolean deleteFaturamento(FaturamentoBean faturamento) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from faturamento where faturamentoCodigo = ?");
            stmt.setInt(1, faturamento.getFaturamentoCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.getConnection();
        }
    }
}
