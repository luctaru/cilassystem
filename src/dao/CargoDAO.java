/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.CargoBean;
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
public abstract class CargoDAO {

    /**
     * Retorna todos os cargos do banco de dados.
     * @return 
     */
    public static List<CargoBean> readCargo() {
        List<CargoBean> cargos = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("select * from cargo order by descricao");
            rs = stmt.executeQuery();
            while(rs.next()){
                CargoBean cargo = new CargoBean(
                        rs.getInt("cargoCodigo"), 
                        rs.getString("descricao")
                );
                cargos.add(cargo);
            }
            return cargos;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    /**
     * Retorna o código de um cargo a a partir da busca de sua descrição.
     * @param desc
     * @return
     * @throws Exception 
     */
    public static int searchCodigoCargo(String desc) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from cargo "
                    + "where descricao like ? ");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("cargoCodigo");
            }
            return 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    public static String searchDescCargo(int codigo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from cargo "
                    + "where cargoCodigo = ? ");
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
    
}
