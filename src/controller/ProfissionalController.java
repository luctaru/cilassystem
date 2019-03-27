/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.ProfissionalBean;
import dao.ProfissionalDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.profissionais.GerenciarProfissionaisView;

/**
 *
 * @author vande
 */
public abstract class ProfissionalController {

    public static boolean createProfissional(ProfissionalBean pro) {
        try {
            if (ProfissionalDAO.createProfissional(pro)) {
                JOptionPane.showMessageDialog(null, "Profissional inserido(a) com sucesso!");
                GerenciarProfissionaisView.tablemodelmedico.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao inserir Profissional.");
        return false;
    }
    
    public static boolean createProfissionalRealizante(ProfissionalBean pro) {
        try {
            if (ProfissionalDAO.createProfissionalRealizante(pro)) {
                JOptionPane.showMessageDialog(null, "Profissional inserido(a) com sucesso!");
                GerenciarProfissionaisView.tablemodelmedico.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao inserir Profissional.");
        return false;
    }

    /**
     * Procurar por profissional a partir dos dados do conselho regional ao que
     * pertence.
     *
     * @param pro
     * @return
     */
    public static ProfissionalBean searchProfissionalByConselho(ProfissionalBean pro) {
        try {
            ProfissionalBean result = ProfissionalDAO.searchProfissionalByConselho(
                    pro.getConselho().getDescricao(),
                    pro.getNumConselho(),
                    pro.getUfConselho()
            );
            if (result != null) {
                return result;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Nenhum(a) profissional encontrado a partir dos dados para edição.");
        return null;
    }

    /**
     * Atualizar dados de profissional;
     * @param pro
     * @return 
     */
    public static boolean updateProfissional(ProfissionalBean pro){
        try {
            if(ProfissionalDAO.updateProfissional(pro)){
                JOptionPane.showMessageDialog(null, "Profissional atulizado(a) com sucesso.");
                GerenciarProfissionaisView.tablemodelmedico.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao atualizar profissional.");
        return false;
    }
    
    /**
     * Deletar profissional.
     * @param pro
     * @return 
     */
    public static boolean deleteProfissional(ProfissionalBean pro){
        try {
            if(ProfissionalDAO.deleteFuncionarioByLogin(
                    pro.getConselho().getDescricao(), 
                    pro.getNumConselho()+"", 
                    pro.getUfConselho())){
                JOptionPane.showMessageDialog(null, "Profissional excluído(a) com sucesso.");
                GerenciarProfissionaisView.tablemodelmedico.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao excluir profissional.");
        return false;
    }
    
}
