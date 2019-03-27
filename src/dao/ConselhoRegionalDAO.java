/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.ConselhoRegionalBean;
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
public abstract class ConselhoRegionalDAO {

    public static boolean createConRegional(String descricao) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("insert into conselhoRegional values (null,?)");
            stmt.setString(1, descricao);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    public static List<ConselhoRegionalBean> readConselhoRegional() {
        List<ConselhoRegionalBean> conselhos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from conselhoRegional order by descricao ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConselhoRegionalBean cons = new ConselhoRegionalBean(
                        rs.getInt("conselhoCodigo"),
                        rs.getString("descricao"));
                conselhos.add(cons);
            }
            return conselhos;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }

    /**
     * Retorna a descrição conforme codigo do conselho.
     *
     * @param codigo
     * @return
     * @throws Exception
     */
    public static String searchDescConselho(int codigo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from conselhoregional "
                    + "where conselhoCodigo = ? ");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("descricao");
            }
            return "";
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna o código conforme descrição do conselho.
     * @param desc
     * @return
     * @throws Exception 
     */
    public static int searchCodigoConselho(String desc) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from conselhoregional "
                    + "where descricao = ? ");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("conselhoCodigo");
            }
            return 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procurar conselho regional pela descrição.
     * @param desc
     * @return 
     */
    public static List<ConselhoRegionalBean> searchConselhoByDesc(String desc) {
        List<ConselhoRegionalBean> conselhos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from conselhoRegional where descricao like ? order by descricao ASC");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConselhoRegionalBean cons = new ConselhoRegionalBean(
                        rs.getInt("conselhoCodigo"),
                        rs.getString("descricao"));
                conselhos.add(cons);
            }
            return conselhos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    public static boolean updateConRegional(String oldValue, String newValue) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("update conselhoRegional set descricao = ? where descricao = ?");
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
    
}
