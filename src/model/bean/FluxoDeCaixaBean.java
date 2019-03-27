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
public class FluxoDeCaixaBean {
    
    private Date dataInicial;
    private Date dataFinal;
    private FormaPagamentoBean formaPagamento;
    private ProfissionalBean profissional;
    private ConvenioBean convenio;
    private ConsultaBean consulta;

    public FluxoDeCaixaBean() {
    }

    public FluxoDeCaixaBean(Date dataInicial, Date dataFinal, FormaPagamentoBean formaPagamento, ProfissionalBean profissional, ConvenioBean convenio, ConsultaBean consulta) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.formaPagamento = formaPagamento;
        this.profissional = profissional;
        this.convenio = convenio;
        this.consulta = consulta;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public FormaPagamentoBean getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoBean formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public ProfissionalBean getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalBean profissional) {
        this.profissional = profissional;
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
    
    @Override
    public String toString() {
        return "FluxoDeCaixa{" + "dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", formaPagamento=" + formaPagamento + ", profissional=" + profissional + ", convenio=" + convenio + ", consulta=" + consulta + '}';
    }
    
}
