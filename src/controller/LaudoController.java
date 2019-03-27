/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.LaudoBean;
import dao.FaturamentoDAO;
import dao.LaudoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vande
 */
public abstract class LaudoController {
    
    public static boolean createLaudo(LaudoBean laudo){
        try {
            laudo = LaudoDAO.createLaudo(laudo);
            if(laudo==null){
                JOptionPane.showMessageDialog(null, "Erro ao criar o laudo após o faturamento. Tente refazer a operação.");
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(LaudoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public static LaudoBean searchLaudo(LaudoBean laudo){
        try {
            laudo = LaudoDAO.searchLaudo(laudo);
            if(laudo!=null){
                return laudo;
            }
        } catch (Exception ex) {
            Logger.getLogger(LaudoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao consultar laudo.");
        return null;
    }
    
    public static boolean updateLaudo(LaudoBean laudo){
        try {
            if(LaudoDAO.updateLaudo(laudo)){
                JOptionPane.showMessageDialog(null, "Laudo salvo com sucesso");
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(LaudoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao salvar laudo.");
        return false;
    }
}
