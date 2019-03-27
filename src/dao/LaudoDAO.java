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

/**
 *
 * @author vande
 */
public abstract class LaudoDAO {

    /**
     * Cria um laudo para o agendamento faturado.
     *
     * @param laudo
     * @return
     * @throws Exception
     */
    public static LaudoBean createLaudo(LaudoBean laudo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into laudo values (null, ?, ?)");
            stmt.setInt(1, laudo.getFaturamento().getFaturamentoCodigo());
            stmt.setString(2, laudo.getLaudo());
            stmt.executeUpdate();
            return laudo;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Procura os agendamentos faturados por paciente
     *
     * @param pac
     * @return
     * @throws Exception
     */
    public static List<LaudoBean> searchLaudoByPaciente(PacienteBean pac) throws Exception {
        List<LaudoBean> laudos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * \n"
                    + "from agenda as ag, faturamento as fat, convenio as conv, consulta as cons, pessoa as pes \n"
                    + "where ag.agendamentoCodigo = fat.agendamentoCodigo \n"
                    + "and ag.convenioCodigo = conv.convenioCodigo \n"
                    + "and ag.consultaCodigo = cons.consultaCodigo \n"
                    + "and ag.profissionalCodigo = pes.pessoaCodigo \n"
                    + "and ag.pacienteCodigo = ? \n"
                    + "order by dataAgendamento DESC");
            stmt.setInt(1, pac.getPacienteCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                LaudoBean item = new LaudoBean();
                item.setFaturamento(new FaturamentoBean());
                item.getFaturamento().setFaturamentoCodigo(rs.getInt("fat.faturamentoCodigo"));
                item.getFaturamento().setAgenda(new AgendaBean());
                item.getFaturamento().getAgenda().setDataAgendamento(new SimpleDateFormat("yyyy-MM-dd H:m").parse(rs.getString("dataAgendamento")));
                item.getFaturamento().getAgenda().setProfissional(new ProfissionalBean());
                item.getFaturamento().getAgenda().getProfissional().setProfissionalCodigo(rs.getInt("ag.profissionalCodigo"));
                item.getFaturamento().getAgenda().getProfissional().setNome(rs.getString("pes.nome"));
                item.getFaturamento().getAgenda().setConvenio(new ConvenioBean(rs.getInt("conv.convenioCodigo"), rs.getString("conv.nome")));
                item.getFaturamento().getAgenda().setConsulta(new ConsultaBean(rs.getInt("cons.consultaCodigo"), rs.getString("cons.descricao")));
                laudos.add(item);
            }
            return laudos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procura no banco de dados um laudo pelo código do faturamento.
     * @param laudo
     * @return
     * @throws Exception 
     */
    public static LaudoBean searchLaudo(LaudoBean laudo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from faturamento as fat, laudo, agenda \n"
                    + "where fat.agendamentoCodigo = agenda.agendamentoCodigo \n"
                    + "and fat.faturamentoCodigo = laudo.faturamentoCodigo \n"
                    + "and fat.faturamentoCodigo = ? ");
            stmt.setInt(1, laudo.getFaturamento().getFaturamentoCodigo());
            rs = stmt.executeQuery();
            while(rs.next()){
                FaturamentoBean faturamento = new FaturamentoBean();
                faturamento.setFaturamentoCodigo(rs.getInt("laudo.faturamentoCodigo"));
                return new LaudoBean(
                        rs.getInt("laudo.laudoCodigo"), 
                        faturamento, 
                        rs.getString("laudo.laudo")
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }

    /**
     * Editar o laudo na consulta.
     * @param laudo
     * @return
     * @throws Exception 
     */
    public static boolean updateLaudo(LaudoBean laudo)throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("update laudo set laudo = ? where faturamentoCodigo = ?");
            stmt.setString(1, laudo.getLaudo());
            stmt.setInt(2, laudo.getFaturamento().getFaturamentoCodigo());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
 
    /**
     * Exclui um laudo pelo  codigo do faturamento.
     * @param codigoFaturamento
     * @return
     * @throws Exception 
     */
    public static boolean deleteLaudo(int codigoFaturamento)throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("delete from laudo where faturamentoCodigo = ?");
            stmt.setInt(1, codigoFaturamento);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
}
