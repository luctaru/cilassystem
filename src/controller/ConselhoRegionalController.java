/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ConselhoRegionalDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vande
 */
public abstract class ConselhoRegionalController {
    
    /**
     * Cadastrar novo conselho regional.
     * @param descricao
     * @return 
     */
    public static boolean createConRegional(String descricao){
        try {
            if(ConselhoRegionalDAO.createConRegional(descricao)){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConselhoRegionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar conselho regional.");
        return false;
    }
    
    /**
     * Editar descrição de conselho regional.
     * @param oldValue
     * @param newValue
     * @return 
     */
    public static boolean updateConRegional(String oldValue, String newValue){
        try {
            if(ConselhoRegionalDAO.updateConRegional(oldValue, newValue)){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConselhoRegionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao editar conselho regional.");
        return false;
    }
}
