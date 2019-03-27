/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author vande
 */
public class AgendaBean {
    
    private int agendamentoCodigo;
    private ProfissionalBean profissional;
    private Date dataAgendamento;
    private PacienteBean paciente;
    private ConvenioBean convenio;
    private ConsultaBean consulta;
    private FuncionarioBean agendadoPor;
    private Date agendadoEm;
    private String observacao;

    public AgendaBean() {
    }

    public AgendaBean(int agendamentoCodigo, ProfissionalBean profissional, Date dataAgendamento, PacienteBean paciente, ConvenioBean convenio, ConsultaBean consulta, FuncionarioBean agendadoPor, Date agendadoEm, String observacao) {
        this.agendamentoCodigo = agendamentoCodigo;
        this.profissional = profissional;
        this.dataAgendamento = dataAgendamento;
        this.paciente = paciente;
        this.convenio = convenio;
        this.consulta = consulta;
        this.agendadoPor = agendadoPor;
        this.agendadoEm = agendadoEm;
        this.observacao = observacao;
    }

    public Date getAgendadoEm() {
        return agendadoEm;
    }

    public void setAgendadoEm(Date agendadoEm) {
        this.agendadoEm = agendadoEm;
    }

    public int getAgendamentoCodigo() {
        return agendamentoCodigo;
    }

    public void setAgendamentoCodigo(int agendamentoCodigo) {
        this.agendamentoCodigo = agendamentoCodigo;
    }

    public ProfissionalBean getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalBean profissional) {
        this.profissional = profissional;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public PacienteBean getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteBean paciente) {
        this.paciente = paciente;
    }

    public ConvenioBean getConvenio() {
        return convenio;
    }

    public void setConvenio(ConvenioBean convenio) {
        this.convenio = convenio;
    }

    public ConsultaBean getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaBean consulta) {
        this.consulta = consulta;
    }

    public FuncionarioBean getAgendadoPor() {
        return agendadoPor;
    }

    public void setAgendadoPor(FuncionarioBean agendadoPor) {
        this.agendadoPor = agendadoPor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Agenda{" + "agendamentoCodigo=" + agendamentoCodigo + "\nprofissional=" + profissional + "\ndataAgendamento=" + dataAgendamento + "\npaciente=" + paciente + "\nconvenio=" + convenio + "\nconsulta=" + consulta + ", agendadoPor=" + agendadoPor + ", agendadoEm=" + agendadoEm + ", observacao=" + observacao + '}';
    }
    
}
