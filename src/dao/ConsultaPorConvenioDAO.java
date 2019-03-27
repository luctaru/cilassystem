/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.ConsultaBean;
import model.bean.ConvenioBean;
import model.bean.ConsultaPorConvenioBean;
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
public abstract class ConsultaPorConvenioDAO {

    /**
     * Inserir nova consulta pelo convênio.
     * @param conPorCon
     * @return
     * @throws Exception 
     */
    public static boolean createConsultaPorConvenio(ConsultaPorConvenioBean conPorCon) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("insert into consultaporconvenio values (?, ?, ?)");
            stmt.setInt(1, conPorCon.getConvenio().getConvenioCodigo());
            stmt.setInt(2, conPorCon.getConsultaCodigo());
            stmt.setString(3, conPorCon.getValor());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    /**
     * Procurar consulta pelo nome do convênio a qual se é realizada a mesma.
     *
     * @param nomeConvenio
     * @return
     */
    public static List<ConsultaPorConvenioBean> searchConsultasByConNome(String nomeConvenio) {
        List<ConsultaPorConvenioBean> consultas = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select b.consultaCodigo, p.descricao, b.convenioCodigo, c.nome, b.valor \n"
                    + "from consultaporconvenio as b, convenio as c, consulta as p\n"
                    + "where b.convenioCodigo = c.convenioCodigo and b.consultaCodigo = p.consultaCodigo\n"
                    + "and c.nome like ?"
                    + "order by p.descricao ASC");
            stmt.setString(1, "%" + nomeConvenio + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConsultaPorConvenioBean consulta = new ConsultaPorConvenioBean(
                        rs.getInt("b.consultaCodigo"),
                        rs.getString("p.descricao"),
                        new ConvenioBean(rs.getInt("b.convenioCodigo"), rs.getString("c.nome")),
                        rs.getString("valor")
                );
                consultas.add(consulta);
            }
            return consultas;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procura pelas consultas que não são realizadas pelo o convênio.
     *
     * @param codigoConvenio
     * @return
     */
    public static List<ConsultaBean> searchConWithoutCon(int codigoConvenio) {
        List<ConsultaBean> consultas = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from consulta\n"
                    + "where consulta.consultaCodigo not in (select consultaCodigo \n"
                    + "from consultaporconvenio where convenioCodigo = ?) "
                    + "order by descricao ASC");
            stmt.setInt(1, codigoConvenio);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ConsultaBean consulta = new ConsultaBean(
                        rs.getInt("consultaCodigo"),
                        rs.getString("descricao")
                );
                consultas.add(consulta);
            }
            return consultas;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    public static boolean changeValueOfConsulta(ConsultaPorConvenioBean conBycon) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("update consultaporconvenio set valor = ? where consultaCodigo = ? and convenioCodigo = ?");
            stmt.setString(1, conBycon.getValor());
            stmt.setInt(2, conBycon.getConsultaCodigo());
            stmt.setInt(3, conBycon.getConvenio().getConvenioCodigo());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    public static double searchValueOfConsulta(ConsultaPorConvenioBean conBycon) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("select * from ConsultaPorConvenio where convenioCodigo = ? and consultaCodigo = ?");
            stmt.setInt(1, conBycon.getConsultaCodigo());
            stmt.setInt(2, conBycon.getConvenio().getConvenioCodigo());
            rs = stmt.executeQuery();
            while(rs.next()){
                return rs.getDouble("valor");
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        return -1;
    }

    public static boolean deleteConsultaPorConvenio(ConsultaPorConvenioBean conBycon) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("delete from consultaporconvenio where consultaCodigo = ? and convenioCodigo = ?");
            stmt.setInt(1, conBycon.getConsultaCodigo());
            stmt.setInt(2, conBycon.getConvenio().getConvenioCodigo());
            stmt.executeUpdate();
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * pega o valor da consulta pelo convênio.
     * @param conByCon
     * @return 
     */
    public static ConsultaPorConvenioBean searchValorConsulta(ConsultaPorConvenioBean conByCon) {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("select * from ConsultaPorConvenio where convenioCodigo = ? and consultaCodigo = ?");
            stmt.setInt(1, conByCon.getConvenio().getConvenioCodigo());
            stmt.setInt(2, conByCon.getConsultaCodigo());
            rs = stmt.executeQuery();
            while(rs.next()){
                conByCon.setValor(rs.getString("valor"));
            }
            return conByCon;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
}
