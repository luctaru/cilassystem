/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.AgendaBean;
import dao.AgendaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.agenda.AgendaView;

/**
 *
 * @author vande
 */
public abstract class AgendaController {

    public static boolean createAgendamento(AgendaBean agendamento) {
        try {
            if (AgendaDAO.createAgendamento(agendamento)) {
                JOptionPane.showMessageDialog(null, "Agendamento realizado com sucesso.");
                AgendaView.tmAgenda.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao realizar agendamento.");
        return false;
    }

    public static boolean deleteAgendamento(AgendaBean agendamento) {
        try {
            if (AgendaDAO.deleteAgendamento(agendamento)) {
                JOptionPane.showMessageDialog(null, "Agendamento cancelado com sucesso.");
                AgendaView.tmAgenda.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao cancelar agendamento.");
        return false;
    }

    /**
     * Busca dados complementares do agendamento.
     * @param agendamento
     * @return 
     */
    public static AgendaBean searchAgendamento(AgendaBean agendamento){
        try {
            agendamento = AgendaDAO.searchAgendamento(agendamento);
            if(agendamento != null){
                return agendamento;
            }
        } catch (Exception ex) {
            Logger.getLogger(AgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao resgatar dados do agendamento.");
        return null;
    }
    
}
