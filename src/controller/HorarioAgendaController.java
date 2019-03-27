/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HorarioAgendaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.MaskFormatTextUtil;

/**
 *
 * @author vande
 */
public abstract class HorarioAgendaController {

    /**
     * Criar novo horário para agenda do profissional.
     *
     * @param profissionalCodigo
     * @param novoHorario
     * @return
     */
    public static boolean createNovoHorario(int profissionalCodigo, String novoHorario) {
        if (MaskFormatTextUtil.checkHora(novoHorario)) {
            try {
                if (HorarioAgendaDAO.createHorario(profissionalCodigo, novoHorario)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo horário.");
                }
            } catch (Exception ex) {
                Logger.getLogger(HorarioAgendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Horario inválido.");
        }
        return false;
    }

    /**
     * Excluir horario do profissional.
     *
     * @param profissionalCodigo
     * @param novoHorario
     * @return
     */
    public static boolean deleteHorario(int profissionalCodigo, String novoHorario) {
        try {
            if (HorarioAgendaDAO.deleteHorario(profissionalCodigo, novoHorario)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir horário.");
            }
        } catch (Exception ex) {
            Logger.getLogger(HorarioAgendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
