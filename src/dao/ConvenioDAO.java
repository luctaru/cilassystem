/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.ConvenioBean;
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
public abstract class ConvenioDAO {

    /**
     * Retornar todos os convênios do banco de dados.
     * @return 
     */
    public static List<ConvenioBean> readConvenio() {
        List<ConvenioBean> convenios = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from convenio order by nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConvenioBean conv = new ConvenioBean(
                        rs.getInt("convenioCodigo"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("razaoSocial"),
                        rs.getString("logradouro"),
                        rs.getString("telefone"),
                        rs.getString("numeroResidencia"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("email")
                );
                convenios.add(conv);
            }
            return convenios;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Criar novo convênio.
     * @param conv
     * @return
     * @throws Exception 
     */
    public static boolean createConvenio(ConvenioBean conv) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into convenio values (null,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, conv.getNome());
            stmt.setString(2, conv.getCnpj());
            stmt.setString(3, conv.getRazaoSocial());
            stmt.setString(4, conv.getTelefone());
            stmt.setString(5, conv.getLogradouro());
            stmt.setString(6, conv.getNumeroResidencial());
            stmt.setString(7, conv.getBairro());
            stmt.setString(8, conv.getCidade());
            stmt.setString(9, conv.getUf());
            stmt.setString(10, conv.getCep());
            stmt.setString(11, conv.getEmail());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        return true;
    }

    /**
     * Pesquisar por convênios a partir do nome.
     * @param nome
     * @return
     * @throws Exception 
     */
    public static List<ConvenioBean> searchConvenioByNome(String nome) throws Exception {
        List<ConvenioBean> convenios = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from convenio where nome like ? order by nome ASC");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConvenioBean conv = new ConvenioBean(
                        rs.getInt("convenioCodigo"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("razaosocial"),
                        rs.getString("telefone"),
                        rs.getString("logradouro"),
                        rs.getString("numeroresidencia"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("email"));
                convenios.add(conv);
            }
            return convenios;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retornar dados do convênio a partir do CNPJ.
     * @param cnpj
     * @return
     * @throws Exception 
     */
    public static ConvenioBean searchConvenioByCnpj(String cnpj) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from convenio where cnpj like ?");
            stmt.setString(1, "%" + cnpj + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                return new ConvenioBean(
                        rs.getInt("convenioCodigo"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("razaosocial"),
                        rs.getString("telefone"),
                        rs.getString("logradouro"),
                        rs.getString("numeroresidencia"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("email"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }
    
    /**
     * Procura o código do convênio pelo seu CNPJ.
     * @param cnpj
     * @return
     * @throws Exception 
     */
    public static int searchCodigoByCnpj(String cnpj) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from convenio where cnpj like ?");
            stmt.setString(1, "%" + cnpj + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("convenioCodigo");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return 0;
    }
    
    public static boolean updateConvenio(ConvenioBean conv) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update convenio set cnpj = ?, nome = ?, razaosocial = ?, telefone = ?, logradouro = ?, numeroResidencia = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, email = ? where convenioCodigo = ?");
            stmt.setString(1, conv.getCnpj());
            stmt.setString(2, conv.getNome());
            stmt.setString(3, conv.getRazaoSocial());
            stmt.setString(4, conv.getTelefone());
            stmt.setString(5, conv.getLogradouro());
            stmt.setString(6, conv.getNumeroResidencial());
            stmt.setString(7, conv.getBairro());
            stmt.setString(8, conv.getCidade());
            stmt.setString(9, conv.getUf());
            stmt.setString(10, conv.getCep());
            stmt.setString(11, conv.getEmail());
            stmt.setInt(12, ConvenioDAO.searchCodigoByCnpj(conv.getOldCodigoConvenio()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        return false;
    }

    public static boolean deleteConvenioByCnpj(String cnpj) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            int convenioCodigo = ConvenioDAO.searchCodigoByCnpj(cnpj);
            stmt = con.prepareStatement("delete from convenio where convenioCodigo = ?");
            stmt.setInt(1, convenioCodigo);
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt);
        }
        return false;
    }
    
}
