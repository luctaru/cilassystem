/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.combobox;

import model.bean.ConvenioBean;
import dao.ConvenioDAO;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vande
 */
public class ConvenioComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<ConvenioBean> listaConvenios;
    private ConvenioBean convenioSelecionado;

    public ConvenioComboBoxModel() {
        this.listaConvenios = ConvenioDAO.readConvenio();
    }

    @Override
    public int getSize() {
        return listaConvenios.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaConvenios.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof ConvenioBean) {
            this.convenioSelecionado = (ConvenioBean) anItem;
            fireContentsChanged(this.listaConvenios, 0, this.listaConvenios.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.convenioSelecionado;
    }

    public void addEveTipo(ConvenioBean pro) {
        this.listaConvenios.add(pro);
        fireContentsChanged(this.listaConvenios, 0, this.listaConvenios.size());
    }

    public void reset() {
        this.listaConvenios.clear();
    }

}
