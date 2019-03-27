/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.combobox;

import model.bean.CargoBean;
import model.bean.FormaPagamentoBean;
import dao.FormaPagamentoDAO;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vande
 */
public class ForPagamentoComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<FormaPagamentoBean> listaForPag;
    private FormaPagamentoBean forPagSelecionado;

    public ForPagamentoComboBoxModel() {
        this.listaForPag = FormaPagamentoDAO.readForPagamento();
    }

    @Override
    public int getSize() {
        return listaForPag.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaForPag.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof FormaPagamentoBean) {
            this.forPagSelecionado = (FormaPagamentoBean) anItem;
            fireContentsChanged(this.listaForPag, 0, this.listaForPag.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.forPagSelecionado;
    }

    public void addEveTipo(FormaPagamentoBean value) {
        this.listaForPag.add(value);
        fireContentsChanged(this.listaForPag, 0, this.listaForPag.size());
    }

    public void reset() {
        this.listaForPag.clear();
    }

}
