/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.PacienteBean;
import dao.PacienteDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.agenda.AgendaView;

/**
 *
 * @author vande
 */
public abstract class PacienteController {
    
    public static boolean createPaciente(PacienteBean pac){
        try {
            if(PacienteDAO.createPaciente(pac)){
                JOptionPane.showMessageDialog(null, "Paciente cadastrado(a) com sucesso.");
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente.");
        AgendaView.tmPaciente.fireTableDataChanged();
        return false;
    }
    
    public static PacienteBean searchPacienteByCodigo(int pacienteCodigo){
        try {
            PacienteBean pac = PacienteDAO.searchPacienteByCodigo(pacienteCodigo);
            if(pac != null){
                return pac;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao procurar paciente.");
        return null;
    }
    
    public static boolean deletePaciente(int pacienteCodigo){
        try {
            if(PacienteDAO.deletePaciente(pacienteCodigo)){
                JOptionPane.showMessageDialog(null, "Paciente excluido(a) com sucesso.");
                AgendaView.tmPaciente.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao excluir paciente.");
        return false;
    }
    
    public static boolean updatePaciente(PacienteBean pac){
        try {
            if(PacienteDAO.updatePaciente(pac)){
                JOptionPane.showMessageDialog(null, "Paciente atualizado(a) com sucesso.");
                AgendaView.tmPaciente.fireTableDataChanged();
                AgendaView.tmAgenda.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente.");
        return false;
    }
    
}
