/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.ConsultaPorConvenioBean;
import dao.ConsultaPorConvenioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.convenio.EditarConvenioView;

/**
 *
 * @author vande
 */
public abstract class ConsultaPorConvenioController {

    /**
     * Adiciona uma consulta para um convênio.
     * @param conBycon
     * @return 
     */
    public static boolean addConsulta(ConsultaPorConvenioBean conBycon) {
        try {
            if (ConsultaPorConvenioDAO.createConsultaPorConvenio(conBycon)) {
                JOptionPane.showMessageDialog(null, "Consulta adicionada com sucesso");
                EditarConvenioView.tablemodelConPorCon.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultaPorConvenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao adicionar consulta para o convênio");
        return false;
    }
    
    public static boolean changeValueOfConsulta(ConsultaPorConvenioBean conBycon) {
        try {
            if (ConsultaPorConvenioDAO.changeValueOfConsulta(conBycon)) {
                EditarConvenioView.tablemodelConPorCon.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultaPorConvenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao adicionar consulta para o convênio");
        return false;
    }

    public static boolean deleteConsultaPorConvenio(ConsultaPorConvenioBean conBycon) {
        try {
            if (ConsultaPorConvenioDAO.deleteConsultaPorConvenio(conBycon)) {
                EditarConvenioView.tablemodelConPorCon.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConsultaPorConvenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao excluir consulta.");
        return false;
    }
    
    public static ConsultaPorConvenioBean searchValorConsulta(ConsultaPorConvenioBean conBycon){
        return conBycon = ConsultaPorConvenioDAO.searchValorConsulta(conBycon);
    }
}
