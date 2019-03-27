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
public class PessoaBean {

    private int pessoaCodigo;
    private String nome;
    private Date dataNascimento;
    private String sexo;
    private String profissao;
    private String telefone;
    private String celular;
    private String logradouro;
    private String numeroResidencia;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String email;

    public PessoaBean() {
    }

    public PessoaBean(int pessoaCodigo) {
        this.pessoaCodigo = pessoaCodigo;
    }
    
    public PessoaBean(String nome, Date dataNascimento, String sexo, String profissao, String telefone, String celular, String logradouro, String numeroResidencia, String bairro, String cidade, String uf, String cep, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.profissao = profissao;
        this.telefone = telefone;
        this.celular = celular;
        this.logradouro = logradouro;
        this.numeroResidencia = numeroResidencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.email = email;
    }

    public PessoaBean(int pessoaCodigo, String nome, Date dataNascimento, String sexo, String profissao, String telefone, String celular, String logradouro, String numeroResidencia, String bairro, String cidade, String uf, String cep, String email) {
        this.pessoaCodigo = pessoaCodigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.profissao = profissao;
        this.telefone = telefone;
        this.celular = celular;
        this.logradouro = logradouro;
        this.numeroResidencia = numeroResidencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.email = email;
    }

    public int getPessoaCodigo() {
        return this.pessoaCodigo;
    }

    public void setPessoaCodigo(int codigo) {
        this.pessoaCodigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroResidencia() {
        return numeroResidencia;
    }

    public void setNumeroResidencia(String numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "pessoaCodigo=" + pessoaCodigo + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", profissao=" + profissao + ", telefone=" + telefone + ", celular=" + celular + ", logradouro=" + logradouro + ", numeroResidencia=" + numeroResidencia + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep + ", email=" + email + '}';
    }

}
