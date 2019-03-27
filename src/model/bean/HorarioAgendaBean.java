/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.List;

/**
 *
 * @author vande
 */
public class HorarioAgendaBean {
    
    private ProfissionalBean profissional;
    private List<String> horarios;

    public HorarioAgendaBean() {
    }
    
    public HorarioAgendaBean(ProfissionalBean profissional, List horarios) {
        this.profissional = profissional;
        this.horarios = horarios;
    }

    public ProfissionalBean getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalBean profissional) {
        this.profissional = profissional;
    }

    public List getHorarios() {
        return horarios;
    }

    public void setHorarios(List horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "HorarioAgenda{" + "profissional=" + profissional + ", horarios=" + horarios + '}';
    }    
}
