/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jlist;

import model.bean.FormaPagamentoBean;
import dao.FormaPagamentoDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author vande
 */
public class FormaPagamentoJListModel extends AbstractListModel {
    
    private List<FormaPagamentoBean> lista;
    private FormaPagamentoBean itemselecionado;
    private String search;

    public FormaPagamentoJListModel(String search) {
        try {
            this.lista = FormaPagamentoDAO.searchFormaPagByDesc(search);
        } catch (Exception ex) {
            Logger.getLogger(FormaPagamentoJListModel.class.getName()).log(Level.SEVERE, null, ex);
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
