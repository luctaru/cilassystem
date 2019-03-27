/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.combobox;

import model.bean.ProfissionalBean;
import dao.ProfissionalDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vande
 */
public class ProfissionalComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<ProfissionalBean> listaProfissional;
    private ProfissionalBean profissionalSelecionado;

    public ProfissionalComboBoxModel() {
        try {
            this.listaProfissional = ProfissionalDAO.readProfissionalRealizantes();
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalComboBoxModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSize() {
        return listaProfissional.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaProfissional.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof ProfissionalBean) {
            this.profissionalSelecionado = (ProfissionalBean) anItem;
            fireContentsChanged(this.listaProfissional, 0, this.listaProfissional.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.profissionalSelecionado;
    }

    public void addEveTipo(ProfissionalBean pro) {
        this.listaProfissional.add(pro);
        fireContentsChanged(this.listaProfissional, 0, this.listaProfissional.size());
    }

    public void reset() {
        this.listaProfissional.clear();
    }

}
