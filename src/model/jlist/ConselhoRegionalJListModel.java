/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jlist;

import model.bean.ConselhoRegionalBean;
import dao.ConselhoRegionalDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author vande
 */
public class ConselhoRegionalJListModel extends AbstractListModel {
    
    private List<ConselhoRegionalBean> lista;
    private ConselhoRegionalBean itemselecionado;
    private String search;

    public ConselhoRegionalJListModel(String search) {
        try {
            this.lista = ConselhoRegionalDAO.searchConselhoByDesc(search);
        } catch (Exception ex) {
            Logger.getLogger(ConselhoRegionalJListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.lista.get(index);
    }
    
}
