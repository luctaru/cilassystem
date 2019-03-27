/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.AgendaBean;
import model.bean.ConsultaBean;
import model.bean.ConvenioBean;
import model.bean.FuncionarioBean;
import model.bean.PacienteBean;
import model.bean.ProfissionalBean;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import util.MaskFormatTextUtil;
import util.SessaoUtil;

/**
 *
 * @author vande
 */
public abstract class AgendaDAO {

    public static boolean createAgendamento(AgendaBean agenda) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into agenda values(null,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, agenda.getProfissional().getProfissionalCodigo());
            stmt.setString(2, MaskFormatTextUtil.dataTimeUs(agenda.getDataAgendamento()));
            stmt.setInt(3, agenda.getPaciente().getPacienteCodigo());
            stmt.setInt(4, agenda.getConvenio().getConvenioCodigo());
            stmt.setInt(5, agenda.getConsulta().getConsultaCodigo());
            stmt.setInt(6, SessaoUtil.getFuncionario().getPessoaCodigo());
            stmt.setString(7, MaskFormatTextUtil.dataTimeUs(new Date(System.currentTimeMillis())));
            stmt.setString(8, agenda.getObservacao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    public static List<AgendaBean> searchAgendamentos(AgendaBean agenda) throws Exception {
        List<AgendaBean> agendamentos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT DISTINCT horarios.horario, age.agendamentoCodigo, age.profissionalCodigo, age.dataAgendamento AS data, \n"
                    + "pes.pessoaCodigo, pes.nome, pes.telefone, pes.celular, \n"
                    + "conv.convenioCodigo, conv.nome, \n"
                    + "cons.consultaCodigo, cons.descricao, \n"
                    + "age.observacao \n"
                    + "FROM (SELECT horPro.* FROM profissional AS pro, horarioagenda AS horPro WHERE pro.profissionalCodigo = horPro.profissionalCodigo AND horPro.profissionalCodigo = ?) AS horarios \n"
                    + "LEFT JOIN agenda AS age ON DATE_FORMAT(age.dataAgendamento, '%H:%i') = horarios.horario AND DATE_FORMAT(age.dataAgendamento, '%Y-%m-%d') = ? and age.profissionalCodigo = ? \n"
                    + "LEFT JOIN profissional AS pro ON age.profissionalCodigo = pro.profissionalCodigo \n"
                    + "LEFT JOIN convenio AS conv ON age.convenioCodigo = conv.convenioCodigo \n"
                    + "LEFT JOIN consulta AS cons ON age.consultaCodigo = cons.consultaCodigo \n"
                    + "LEFT JOIN pessoa AS pes ON age.pacienteCodigo = pes.pessoaCodigo \n"
                    + "ORDER BY horarios.horario");
            stmt.setInt(1, agenda.getProfissional().getProfissionalCodigo());
            stmt.setString(2, MaskFormatTextUtil.dataUs(agenda.getDataAgendamento()));
            stmt.setInt(3, agenda.getProfissional().getProfissionalCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                AgendaBean agendamento = new AgendaBean();
                agendamento.setAgendamentoCodigo(rs.getInt("age.agendamentoCodigo"));
                if (rs.getString("data") != null) {
                    agendamento.setDataAgendamento(new SimpleDateFormat("yyyy-MM-dd H:m").parse(rs.getString("data")));
                } else {
                    agendamento.setDataAgendamento(new SimpleDateFormat("yyyy-MM-dd H:m").parse("0000-00-00 " + rs.getString("horario")));
                }
                agendamento.setProfissional(new ProfissionalBean(rs.getInt("age.profissionalCodigo")));
                agendamento.setPaciente(new PacienteBean());
                agendamento.getPaciente().setPacienteCodigo(rs.getInt("pes.pessoaCodigo"));
                agendamento.getPaciente().setNome(rs.getString("pes.nome"));
                agendamento.getPaciente().setTelefone(rs.getString("pes.telefone"));
                agendamento.getPaciente().setCelular(rs.getString("pes.celular"));
                agendamento.setConvenio(new ConvenioBean(rs.getInt("conv.convenioCodigo"), rs.getString("conv.nome")));
                agendamento.setConsulta(new ConsultaBean(rs.getInt("cons.consultaCodigo"), rs.getString("cons.descricao")));
                agendamento.setObservacao(rs.getString("age.observacao"));
                agendamentos.add(agendamento);
            }
            return agendamentos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Buscar histórico de agendamentos por paciente.
     *
     * @param paciente
     * @return
     * @throws Exception
     */
    public static List<AgendaBean> historicoAgendamento(PacienteBean paciente) throws Exception {
        List<AgendaBean> agendamentos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from agenda as age, pessoa as pes, convenio as conv, consulta as cons \n"
                    + "where age.profissionalCodigo = pes.pessoaCodigo \n"
                    + "and age.convenioCodigo = conv.convenioCodigo \n"
                    + "and age.consultaCodigo = cons.consultaCodigo \n"
                    + "and age.pacienteCodigo = ? \n"
                    + "order by age.agendamentoCodigo DESC");
            stmt.setInt(1, paciente.getPacienteCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                AgendaBean agendamento = new AgendaBean();
                agendamento.setAgendamentoCodigo(rs.getInt("age.agendamentoCodigo"));
                agendamento.setDataAgendamento(new SimpleDateFormat("yyyy-MM-dd H:m").parse(rs.getString("dataAgendamento")));
                agendamento.setProfissional(new ProfissionalBean(rs.getInt("age.profissionalCodigo")));
                agendamento.getProfissional().setNome(rs.getString("pes.nome"));
                agendamento.setPaciente(new PacienteBean());
                agendamento.getPaciente().setPacienteCodigo(rs.getInt("pes.pessoaCodigo"));
                agendamento.setConvenio(new ConvenioBean(rs.getInt("conv.convenioCodigo"), rs.getString("conv.nome")));
                agendamento.setConsulta(new ConsultaBean(rs.getInt("cons.consultaCodigo"), rs.getString("cons.descricao")));
                agendamentos.add(agendamento);
            }
            return agendamentos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procurar um agendamento pelo código do profissional e data de
     * agendamento.
     *
     * @param agendamento
     * @return
     * @throws Exception
     */
    public static AgendaBean searchAgendamento(AgendaBean agendamento) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * \n"
                    + "from agenda as ag, pessoa as pm, pessoa as pp, pessoa as pf, convenio as conv, consulta as cons \n"
                    + "where profissionalCodigo = ? and dataAgendamento = ? \n"
                    + "and ag.pacienteCodigo = pp.pessoaCodigo \n"
                    + "and ag.profissionalCodigo = pm.pessoaCodigo \n"
                    + "and ag.agendadoPor = pf.pessoaCodigo \n"
                    + "and ag.convenioCodigo = conv.convenioCodigo \n"
                    + "and ag.consultaCodigo = cons.consultaCodigo");
            stmt.setInt(1, agendamento.getProfissional().getProfissionalCodigo());
            stmt.setString(2, MaskFormatTextUtil.dataTimeUs(agendamento.getDataAgendamento()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                agendamento.setAgendamentoCodigo(rs.getInt("agendamentoCodigo"));
                agendamento.setPaciente(new PacienteBean());
                agendamento.getPaciente().setPacienteCodigo(rs.getInt("pacienteCodigo"));
                agendamento.getPaciente().setNome(rs.getString("pp.nome"));
                agendamento.getProfissional().setNome(rs.getString("pm.nome"));
                agendamento.setConvenio(new ConvenioBean(rs.getInt("convenioCodigo")));
                agendamento.getConvenio().setNome(rs.getString("conv.nome"));
                agendamento.setConsulta(new ConsultaBean(rs.getInt("consultaCodigo")));
                agendamento.getConsulta().setDescricao(rs.getString("descricao"));
                agendamento.setAgendadoPor(new FuncionarioBean(rs.getInt("agendadoPor")));
                agendamento.getAgendadoPor().setNome(rs.getString("pf.nome"));
            }
            return agendamento;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Cancelar agendamento
     *
     * @param agenda
     * @return
     * @throws Exception
     */
    public static boolean deleteAgendamento(AgendaBean agenda) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from agenda where profissionalCodigo = ? and dataAgendamento = ?");
            stmt.setInt(1, agenda.getProfissional().getProfissionalCodigo());
            stmt.setString(2, MaskFormatTextUtil.dataTimeUs(agenda.getDataAgendamento()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }
}
