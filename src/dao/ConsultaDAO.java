/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.ConsultaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vande
 */
public abstract class ConsultaDAO {

    public static List<ConsultaBean> readProcedimentos() {
        List<ConsultaBean> procedimentos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from consulta order by descricao ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConsultaBean consulta = new ConsultaBean(
                        rs.getInt("consultaCodigo"),
                        rs.getString("descricao")
                );
                procedimentos.add(consulta);
            }
            return procedimentos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    public static List<ConsultaBean> searchConsultasByDescricao(String descricao){
        List<ConsultaBean> procedimentos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from consulta where descricao like ? order by descricao ASC");
            stmt.setString(1, "%"+descricao+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConsultaBean consulta = new ConsultaBean(
                        rs.getInt("consultaCodigo"),
                        rs.getString("descricao")
                );
                procedimentos.add(consulta);
            }
            return procedimentos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Inserir nova consulta no banco de dados.
     * @param desc
     * @return 
     */
    public static boolean createConsulta(String desc) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("insert into consulta values (null, ?)");
            stmt.setString(1, desc);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            new RuntimeException(ex);
        } finally{
            ConectaBanco.closeConnection(con, stmt);
        }
        return false;
    }
    
    /**
     * Atualizar descrição de uma consulta no banco de dados.
     * @param oldValue
     * @param newValue
     * @return 
     */
    public static boolean updateConsulta(String oldValue, String newValue) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("update consulta set descricao = ? where descricao = ?");
            stmt.setString(1, newValue);
            stmt.setString(2, oldValue);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    /**
     * Deletar uma consulta pela descrição.
     * @param descricao
     * @return
     * @throws Exception 
     */
    public static boolean deleteConsulta(String descricao) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("delete from consulta where descricao = ?");
            stmt.setString(1, descricao);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    /**
     * Procura pelo código da consulta a partir de sua descrição.
     * @param descricao
     * @return
     * @throws Exception 
     */
    public static int searchCodConsultaByDesc(String descricao) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("select consultaCodigo from consulta where descricao like ?");
            stmt.setString(1, descricao);
            rs = stmt.executeQuery();
            while(rs.next()){
                return rs.getInt("consultaCodigo");
            }
            return 0;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
}
