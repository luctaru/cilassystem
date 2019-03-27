/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.ConvenioBean;
import dao.ConvenioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.convenio.GerenciarConveniosView;

/**
 *
 * @author vande
 */
public abstract class ConvenioController {
    
    /**
     * Criar novo convênio.
     * @param con
     * @return 
     */
    public static boolean createConvenio(ConvenioBean con){
        try {
            if(ConvenioDAO.createConvenio(con)){
                JOptionPane.showMessageDialog(null, "Convênio cadastrado com sucesso.");
                GerenciarConveniosView.tablemodelConvenio.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConvenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar convênio.");
        return false;
    }
    
    public static boolean updateConvenio(ConvenioBean con){
        try {
            if(ConvenioDAO.updateConvenio(con)){
                JOptionPane.showMessageDialog(null, "Convênio atualizado com sucesso.");
                GerenciarConveniosView.tablemodelConvenio.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConvenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao atualizar convênio.");
        return false;
    }

    public static boolean deleteFuncionario(ConvenioBean conv) {
        try {
            if(ConvenioDAO.deleteConvenioByCnpj(conv.getCnpj())){
                JOptionPane.showMessageDialog(null, "Convênio removido com sucesso.");
                GerenciarConveniosView.tablemodelConvenio.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConvenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao remover convênio.");
        return false;
    }
    
    /**
     * Procura profissional a partir dos dados do conselho regional ao que
     * pertence.
     *
     * @param conv
     * @return
     */
    public static ConvenioBean searchConvenioByCnpj(ConvenioBean conv) {
        try {
            ConvenioBean result = ConvenioDAO.searchConvenioByCnpj(conv.getCnpj());
            if (result != null) {
                return result;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Nenhum convênio encontrado a partir dos dados para edição.");
        return null;
    }
    
}
