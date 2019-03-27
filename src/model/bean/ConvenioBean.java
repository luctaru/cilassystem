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
public class ConvenioBean {

    private int convenioCodigo;
    private String nome;
    private String cnpj;
    private String oldCnpj;
    private String razaoSocial;
    private String telefone;
    private String logradouro;
    private String numeroResidencial;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String email;

    public ConvenioBean() {
    }

    public ConvenioBean(int convenioCodigo) {
        this.convenioCodigo = convenioCodigo;
    }

    public ConvenioBean(int convenioCodigo, String nome, String cnpj, String razaoSocial, String telefone, String logradouro, String numeroResidencial, String bairro, String cidade, String uf, String cep, String email) {
        this.convenioCodigo = convenioCodigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.numeroResidencial = numeroResidencial;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.email = email;
    }

    public ConvenioBean(int convenioCodigo, String nome) {
        this.convenioCodigo = convenioCodigo;
        this.nome = nome;
    }

    /**
     * Usado para definir um valor antigo de CNJP antes de ser editado. 
     * @return 
     */
    private void setOldCodigoConvenio(String oldCnpj){
        this.oldCnpj = oldCnpj;
    }

    /**
     * Usado para resgatar um valor antigo de CNJP antes de ser editado. 
     * @return 
     */
    public String getOldCodigoConvenio(){
        return oldCnpj;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConvenioCodigo() {
        return convenioCodigo;
    }

    public void setConvenioCodigo(int convenioCodigo) {
        this.convenioCodigo = convenioCodigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        setOldCodigoConvenio(this.cnpj);
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroResidencial() {
        return numeroResidencial;
    }

    public void setNumeroResidencial(String numeroResidencial) {
        this.numeroResidencial = numeroResidencial;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
