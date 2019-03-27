/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.combobox;

import model.bean.CargoBean;
import dao.CargoDAO;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vande
 */
public class CargoComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<CargoBean> listaCargo;
    private CargoBean cargoSelecionado;

    public CargoComboBoxModel() {
        this.listaCargo = CargoDAO.readCargo();
    }

    @Override
    public int getSize() {
        return listaCargo.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaCargo.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof CargoBean) {
            this.cargoSelecionado = (CargoBean) anItem;
            fireContentsChanged(this.listaCargo, 0, this.listaCargo.size());
        }
    }

    @Override
    public Object getSelectedItem() {
        return this.cargoSelecionado;
    }

    public void addEveTipo(CargoBean pro) {
        this.listaCargo.add(pro);
        fireContentsChanged(this.listaCargo, 0, this.listaCargo.size());
    }

    public void reset() {
        this.listaCargo.clear();
    }

}
