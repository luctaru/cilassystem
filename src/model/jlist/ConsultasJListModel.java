/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jlist;

import model.bean.ConsultaBean;
import dao.ConsultaDAO;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author vande
 */
public class ConsultasJListModel extends AbstractListModel {
    
    private List<ConsultaBean> listaConsultas;
    private ConsultaBean consultaSelecionada;
    private String search;

    public ConsultasJListModel(String search) {
        this.listaConsultas = ConsultaDAO.searchConsultasByDescricao(search);
    }
    
    @Override
    public int getSize() {
        return listaConsultas.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaConsultas.get(index);
    }
    
    public void add(String desc){
        this.listaConsultas.add(new ConsultaBean(desc));
    }
    
}
