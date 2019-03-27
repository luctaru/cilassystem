/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jlist;

import model.bean.ConsultaPorConvenioBean;
import dao.ConsultaPorConvenioDAO;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author vande
 */
public class ConsultasByConvenioJListModel extends AbstractListModel {
    
    private List<ConsultaPorConvenioBean> listaconsultas;
    private ConsultaPorConvenioBean consultaselecionada;
    private String search;

    public ConsultasByConvenioJListModel(String search) {
        this.listaconsultas = ConsultaPorConvenioDAO.searchConsultasByConNome(search);
    }
    
    @Override
    public int getSize() {
        return listaconsultas.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaconsultas.get(index);
    }
    
}
