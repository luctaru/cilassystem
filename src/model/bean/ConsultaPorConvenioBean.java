/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author vande
 */
public class ConsultaPorConvenioBean extends ConsultaBean {
    
    private ConvenioBean convenio;
    private String valor;

    public ConsultaPorConvenioBean() {
    }

    public ConsultaPorConvenioBean(int consultaCodigo, String descricao, ConvenioBean convenio, String valor) {
        super(consultaCodigo, descricao);
        this.convenio = convenio;
        this.valor = valor;
    }
    
    public ConsultaPorConvenioBean(ConvenioBean convenio, String valor) {
        this.convenio = convenio;
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ConvenioBean getConvenio() {
        return convenio;
    }

    public void setConvenio(ConvenioBean convenio) {
        this.convenio = convenio;
    }

    @Override
    public String toString() {
        return super.getDescricao();
    }
    
}
