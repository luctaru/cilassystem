/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.FormaPagamentoBean;
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
public abstract class FormaPagamentoDAO {
    
    public static boolean createFormaPagamento(String descricao) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("insert into formaPagamento values(null,?)");
            stmt.setString(1, descricao);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    /**
     *  Busca no banco as formas de pagamentos
     * @return 
     */
    public static List<FormaPagamentoBean> readForPagamento() {
        List<FormaPagamentoBean> formas = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from formaPagamento order by descricao ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                FormaPagamentoBean forma = new FormaPagamentoBean(
                        rs.getInt("formaPagCodigo"),
                        rs.getString("descricao"));
                formas.add(forma);
            }
            return formas;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Procurar forma de pagamento pela descrição.
     * @param desc
     * @return 
     */
    public static List<FormaPagamentoBean> searchFormaPagByDesc(String desc) {
        List<FormaPagamentoBean> formas = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from formaPagamento where descricao like ? order by descricao ASC");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                FormaPagamentoBean forma = new FormaPagamentoBean(
                        rs.getInt("formaPagCodigo"),
                        rs.getString("descricao"));
                formas.add(forma);
            }
            return formas;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    public static boolean updateFormaPagamento(String oldValue, String newValue) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("update formaPagamento set descricao = ? where descricao = ?");
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
