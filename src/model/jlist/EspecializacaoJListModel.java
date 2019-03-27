/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jlist;

import model.bean.EspecializacaoBean;
import dao.EspecializacaoDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author vande
 */
public class EspecializacaoJListModel extends AbstractListModel {
    
    private List<EspecializacaoBean> lista;
    private EspecializacaoBean itemselecionado;
    private String search;

    public EspecializacaoJListModel(String search) {
        try {
            this.lista = EspecializacaoDAO.searchEspecializacaoByDesc(search);
        } catch (Exception ex) {
            Logger.getLogger(EspecializacaoJListModel.class.getName()).log(Level.SEVERE, null, ex);
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
