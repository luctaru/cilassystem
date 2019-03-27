/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.FaturamentoBean;
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
public abstract class FaturamentoController {

    /**
     * Faturar agendamento
     *
     * @param faturamento
     * @return
     */
    public static boolean createFaturamento(FaturamentoBean faturamento) {
        try {
            faturamento = FaturamentoDAO.createFaturamento(faturamento);
            if (faturamento != null) {
                LaudoBean laudo = new LaudoBean();
                laudo.setFaturamento(faturamento);
                if (LaudoController.createLaudo(laudo)) {
                    JOptionPane.showMessageDialog(null, "Agendamento faturado com sucesso.");
                    return true;
                } else {
                    deleteFaturamento(faturamento);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FaturamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao faturar agendamento.");
        return false;
    }

    /**
     * Procurar um faturamento pelo código do agendamento.
     *
     * @param faturamento
     * @return
     */
    public static FaturamentoBean searchFaturamentoByAgendamento(FaturamentoBean faturamento) {
        try {
            faturamento = FaturamentoDAO.searchFaturamentoByAgendamento(faturamento);
            if (faturamento != null) {
                return faturamento;
            }
        } catch (Exception ex) {
            Logger.getLogger(FaturamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Alterar dados do faturamento
     *
     * @param faturamento
     * @return
     */
    public static boolean updateFaturamento(FaturamentoBean faturamento) {
        try {
            if (FaturamentoDAO.updateFaturamento(faturamento)) {
                JOptionPane.showMessageDialog(null, "Faturamento alterado com sucesso.");
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(FaturamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao alterar faturamento.");
        return false;
    }

    /**
     * Excluir faturamento do agendamento
     *
     * @param faturamento
     * @return
     */
    public static boolean deleteFaturamento(FaturamentoBean faturamento) {
        try {
            if (LaudoDAO.deleteLaudo(faturamento.getFaturamentoCodigo())) {
                if (FaturamentoDAO.deleteFaturamento(faturamento)) {
                    JOptionPane.showMessageDialog(null, "Faturamento removido com sucesso.");
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FaturamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao remover faturamento.");
        return false;
    }
}
