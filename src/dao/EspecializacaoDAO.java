/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.EspecializacaoBean;
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
public abstract class EspecializacaoDAO {

    public static boolean createEspecializacao(String descricao) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("insert into especializacao values(null,?)");
            stmt.setString(1, descricao);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    public static List<EspecializacaoBean> readEspecializacao() {
        List<EspecializacaoBean> especializacoes = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from especializacao order by descricao ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                EspecializacaoBean espec = new EspecializacaoBean(
                        rs.getInt("especializacaoCodigo"),
                        rs.getString("descricao"));
                especializacoes.add(espec);
            }
            return especializacoes;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }

    /**
     * Retorna a descrição da especialização de acordo com o código.
     *
     * @param codigo
     * @return
     * @throws Exception
     */
    public static String searchDescEspecializacao(int codigo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from especializacao "
                    + "where especializacaoCodigo = ? ");
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
     * Retorna o código da especialização de acordo com a descrição.
     *
     * @param desc
     * @return
     * @throws Exception
     */
    public static int searchCodigoEspecializacao(String desc) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from especializacao "
                    + "where descricao = ? ");
            stmt.setString(1, desc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("especializacaoCodigo");
            }
            return 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procura especialização pelo descrição
     * @param desc
     * @return 
     */
    public static List<EspecializacaoBean> searchEspecializacaoByDesc(String desc) {
        List<EspecializacaoBean> especializacoes = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from especializacao where descricao like ? order by descricao ASC");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                EspecializacaoBean espec = new EspecializacaoBean(
                        rs.getInt("especializacaoCodigo"),
                        rs.getString("descricao"));
                especializacoes.add(espec);
            }
            return especializacoes;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }

    public static boolean updateEspecializacao(String oldValue, String newValue) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("update especializacao set descricao = ? where descricao = ?");
            stmt.setString(1, newValue);
            stmt.setString(2, oldValue);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
}
