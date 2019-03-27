/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DbMysqlUtil;

/**
 *
 * @author vande
 */
public abstract class ConectaBanco {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/clinica_v1?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "flordodia";
    private static Connection con;
    
    public static void setUrlRootConnection(){
        url = "jdbc:mysql://localhost:3306";
    }
    
    public static void setUrlClinicaConnection(){
        url = "jdbc:mysql://localhost:3306/clinica_v1";
    }
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(url, USER, PASS);
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            DbMysqlUtil.createDBClinica();
            throw new RuntimeException("Erro na conecao: ", ex);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if(stmt!=null){
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
