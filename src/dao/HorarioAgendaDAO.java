/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.HorarioAgendaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.MaskFormatTextUtil;

/**
 *
 * @author vande
 */
public abstract class HorarioAgendaDAO {

    /**
     * Cria novos horários para o profissionalCodigo passado como parametro.
     *
     * @param profissionalCodigo
     * @param newHorario
     * @return
     * @throws Exception
     */
    public static boolean createHorario(int profissionalCodigo, String novoHorario) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into horarioAgenda values (?, ?)");
            stmt.setInt(1, profissionalCodigo);
            stmt.setString(2, novoHorario);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Procura a partir do código do profissional, os horários da agenda do
     * mesmo.
     *
     * @param profissionalCodigo
     * @return
     * @throws Exception
     */
    public static HorarioAgendaBean searchHorarios(int profissionalCodigo) throws Exception {
        HorarioAgendaBean agenda = new HorarioAgendaBean();
        List<String> horarios = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select horario from horarioAgenda where profissionalCodigo = ? order by horario asc");
            stmt.setInt(1, profissionalCodigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                horarios.add(rs.getString("horario"));
            }
            agenda.setHorarios(horarios);
            return agenda;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Excluir horário do profissional.
     * @param profissionalCodigo
     * @param novoHorario
     * @return
     * @throws Exception 
     */
    public static boolean deleteHorario(int profissionalCodigo, String novoHorario) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from horarioAgenda where profissionalCodigo = ? and horario like ?");
            stmt.setInt(1, profissionalCodigo);
            stmt.setString(2, novoHorario);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
}
