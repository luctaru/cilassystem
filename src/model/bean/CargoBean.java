/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import dao.CargoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vande
 */
public class CargoBean {
    private int codigoCargo;
    private String descricao;

    public CargoBean() {
    }

    public CargoBean(int codigoCargo, String descricao) {
        this.codigoCargo = codigoCargo;
        this.descricao = descricao;
    }

    public CargoBean(int codigoCargo) {
        this.codigoCargo = codigoCargo;
        try {
            this.descricao = CargoDAO.searchDescCargo(codigoCargo);
        } catch (Exception ex) {
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CargoBean(String descricao){
        try {
            this.codigoCargo = CargoDAO.searchCodigoCargo(descricao);
        } catch (Exception ex) {
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.descricao = descricao;
    }

    public int getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
