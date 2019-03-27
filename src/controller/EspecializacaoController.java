/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EspecializacaoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vande
 */
public abstract class EspecializacaoController {
    
    /**
     * Cadastrar nova especialização.
     * @param descricao
     * @return 
     */
    public static boolean createEspecializacao(String descricao){
        try {
            if(EspecializacaoDAO.createEspecializacao(descricao)){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecializacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar especialização.");
        return false;
    }

    /**
     * Atualizar descrição da especialização.
     * @param oldValue
     * @param newValue
     * @return 
     */
    public static boolean updateEspecializacao(String oldValue, String newValue) {
        try {
            if(EspecializacaoDAO.updateEspecializacao(oldValue, newValue)){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecializacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao editar especialização.");
        return false;
    }
    
}
