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
public class PacienteBean extends PessoaBean {

    private int pacienteCodigo;
    private Double altura;
    private Double peso;
    private String cirurgia;
    private String gestacao;
    private String alergia;
    private String pressaoArterial;
    private String tipoSanguineo;
    private String deficiencia;
    private Date dum;
    private String observacao;
    
    public PacienteBean() {
    }   

    public PacienteBean(int pacienteCodigo, String nome, Date dataNascimento, String sexo, String profissao, String telefone, String celular, String logradouro, String numeroResidencia, String bairro, String cidade, String uf, String cep, String email, Double altura, Double peso, String cirurgia, String gestacao, String alergia, String pressaoArterial, String tipoSanguineo, String deficiencia, Date dum, String observacao) {
        super(pacienteCodigo, nome, dataNascimento, sexo, profissao, telefone, celular, logradouro, numeroResidencia, bairro, cidade, uf, cep, email);
        this.pacienteCodigo = pacienteCodigo;
        this.altura = altura;
        this.peso = peso;
        this.cirurgia = cirurgia;
        this.gestacao = gestacao;
        this.alergia = alergia;
        this.pressaoArterial = pressaoArterial;
        this.tipoSanguineo = tipoSanguineo;
        this.deficiencia = deficiencia;
        this.dum = dum;
        this.observacao = observacao;
    }

    public PacienteBean(int pacienteCodigo, Double altura, Double peso, String cirurgia, String gestacao, String alergia, String pressaoArterial, String tipoSanguineo, String deficiencia, Date dum, String observacao) {
        super.setPessoaCodigo(pacienteCodigo);
        this.pacienteCodigo = pacienteCodigo;
        this.altura = altura;
        this.peso = peso;
        this.cirurgia = cirurgia;
        this.gestacao = gestacao;
        this.alergia = alergia;
        this.pressaoArterial = pressaoArterial;
        this.tipoSanguineo = tipoSanguineo;
        this.deficiencia = deficiencia;
        this.dum = dum;
        this.observacao = observacao;
    }

    public int getPacienteCodigo() {
        return pacienteCodigo;
    }

    public void setPacienteCodigo(int pacienteCodigo) {
        this.pacienteCodigo = pacienteCodigo;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(String cirurgia) {
        this.cirurgia = cirurgia;
    }

    public String getGestacao() {
        return gestacao;
    }

    public void setGestacao(String gestacao) {
        this.gestacao = gestacao;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public Date getDum() {
        return dum;
    }

    public void setDum(Date dum) {
        this.dum = dum;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return super.toString()+"\nPaciente{" + "pacienteCodigo=" + pacienteCodigo + ", altura=" + altura + ", peso=" + peso + ", cirurgia=" + cirurgia + ", gestacao=" + gestacao + ", alergia=" + alergia + ", pressaoArterial=" + pressaoArterial + ", tipoSanguineo=" + tipoSanguineo + ", deficiencia=" + deficiencia + ", dum=" + dum + ", observacao=" + observacao + '}';
    }

    
    
}
