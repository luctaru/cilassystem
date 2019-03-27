/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.combobox;

import model.bean.ConsultaBean;
import dao.ConsultaPorConvenioDAO;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vande
 */
public class ConsultaPorConvenioComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<ConsultaBean> listaConsultas;
    private ConsultaBean consultaSelecionada;
    private int convenioCodigo;
    
    public ConsultaPorConvenioComboBoxModel(int convenioCodigo) {
        this.convenioCodigo = convenioCodigo;
        this.listaConsultas = ConsultaPorConvenioDAO.searchConWithoutCon(this.convenioCodigo);
    }

    @Override
    public int getSize() {
        return listaConsultas.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaConsultas.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof ConsultaBean) {
            this.consultaSelecionada = (ConsultaBean) anItem;
            fireContentsChanged(this.listaConsultas, 0, this.listaConsultas.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.consultaSelecionada;
    }

    public void addEveTipo(ConsultaBean pro) {
        this.listaConsultas.add(pro);
        fireContentsChanged(this.listaConsultas, 0, this.listaConsultas.size());
    }

    public void reset() {
        this.listaConsultas.clear();
    }

}
