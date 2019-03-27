/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jlist;

import model.bean.HorarioAgendaBean;
import dao.HorarioAgendaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author vande
 */
public class HorarioAgendaJListModel extends AbstractListModel {

    private HorarioAgendaBean listahorarios;

    public HorarioAgendaJListModel(int search) {
        try {
            this.listahorarios = HorarioAgendaDAO.searchHorarios(search);
        } catch (Exception ex) {
            Logger.getLogger(HorarioAgendaJListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSize() {
        return listahorarios.getHorarios().size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listahorarios.getHorarios().get(index);
    }

    public void add(String horario) {
        this.listahorarios.getHorarios().add(horario);
    }

}
