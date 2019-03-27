/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jlist;

import model.bean.HorarioAgendaBean;
import model.bean.ProfissionalBean;
import dao.HorarioAgendaDAO;
import dao.ProfissionalDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author vande
 */
public class ProfissionalJListModel extends AbstractListModel {
    
    private List<ProfissionalBean> listaprofissional;
    private HorarioAgendaBean profissionalselecionado;
    private String search;

    public ProfissionalJListModel(String search) {
        try {
            this.listaprofissional = ProfissionalDAO.searchProfissionalByName(search);
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalJListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int getSize() {
        return listaprofissional.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaprofissional.get(index);
    }
    
}
