/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.ConsultaPorConvenioBean;
import dao.ConsultaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vande
 */
public abstract class ConsultaController {
    
    /**
     * Cadastrar consulta no banco de dados.
     * @param desc
     * @return 
     */
    public static boolean createConsulta(String desc){
        try {
            if(ConsultaDAO.createConsulta(desc)){
                JOptionPane.showMessageDialog(null, "Consulta cadastrado com sucesso.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta.");
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean updateConsulta(String oldValue, String newValue){
        try {
            if(ConsultaDAO.updateConsulta(oldValue, newValue)){
                JOptionPane.showMessageDialog(null, "Consulta atualizada com sucesso.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar consulta.");
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean deleteConsulta(String descricao){
        try {
            if(ConsultaDAO.deleteConsulta(descricao)){
                JOptionPane.showMessageDialog(null, "Consulta excluida com sucesso.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir consulta.");
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static int searchCodConvenioByDesc(ConsultaPorConvenioBean conPorCon){
        try {
            int codigo = ConsultaDAO.searchCodConsultaByDesc(conPorCon.getDescricao());
            if(codigo != 0) return codigo;
        } catch (Exception ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao procurar pelo codigo da consulta.");
        return 0;
    }
    
    public static int searchCodConvenioByDesc(String descricao){
        try {
            int codigo = ConsultaDAO.searchCodConsultaByDesc(descricao);
            if(codigo != 0) return codigo;
        } catch (Exception ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao procurar pelo codigo da consulta.");
        return 0;
    }
}
