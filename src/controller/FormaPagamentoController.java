/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FormaPagamentoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author vande
 */
public abstract class FormaPagamentoController {
 
    /**
     * Cadastrar nova forma de pagamento.
     * @param descricao
     * @return 
     */
    public static boolean createFormaPagamento(String descricao){
        try {
            if(FormaPagamentoDAO.createFormaPagamento(descricao)){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecializacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar forma de pagamento.");
        return false;
    }

    /**
     * Atualizar descrição da forma de pagamento.
     * @param oldValue
     * @param newValue
     * @return 
     */
    public static boolean updateFormaPagamento(String oldValue, String newValue) {
        try {
            if(FormaPagamentoDAO.updateFormaPagamento(oldValue, newValue)){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(EspecializacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao editar forma de pagamento.");
        return false;
    }
    
}
