/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.combobox;

import model.bean.ConselhoRegionalBean;
import dao.ConselhoRegionalDAO;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vande
 */
public class ConselhoComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<ConselhoRegionalBean> listaConselho;
    private ConselhoRegionalBean conselhoSelecionado;

    public ConselhoComboBoxModel() {
        this.listaConselho = ConselhoRegionalDAO.readConselhoRegional();
    }

    @Override
    public int getSize() {
        return listaConselho.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaConselho.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof ConselhoRegionalBean) {
            this.conselhoSelecionado = (ConselhoRegionalBean) anItem;
            fireContentsChanged(this.listaConselho, 0, this.listaConselho.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.conselhoSelecionado;
    }

}
