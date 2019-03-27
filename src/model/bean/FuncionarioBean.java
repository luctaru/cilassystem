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
public class FuncionarioBean extends PessoaBean {

    private int funcionarioCodigo;
    private String login;
    private String senha;
    private CargoBean cargo;

    public FuncionarioBean() {
    }

    public FuncionarioBean(int funcionarioCodigo) {
        this.funcionarioCodigo = funcionarioCodigo;
    }

    public FuncionarioBean(CargoBean cargo) {
        this.cargo = cargo;
    }

    public FuncionarioBean(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public FuncionarioBean(int codigo, String login, String senha, CargoBean cargoCodigo) {
        this.funcionarioCodigo = codigo;
        this.login = login;
        this.senha = senha;
        this.cargo = cargoCodigo;
    }

    public FuncionarioBean(String nome, Date dataNascimento, String sexo, String profissao, String telefone, String celular, String logradouro, String numeroResidencia, String bairro, String cidade, String uf, String cep, String email, String login, String senha, CargoBean cargo) {
        super(nome, dataNascimento, sexo, profissao, telefone, celular, logradouro, numeroResidencia, bairro, cidade, uf, cep, email);
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    public FuncionarioBean(int pessoaCodigo, String nome, Date dataNascimento, String sexo, String profissao, String telefone, String celular, String logradouro, String numeroResidencia, String bairro, String cidade, String uf, String cep, String email, String login, String senha, CargoBean cargo) {
        super(pessoaCodigo, nome, dataNascimento, sexo, profissao, telefone, celular, logradouro, numeroResidencia, bairro, cidade, uf, cep, email);
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
        this.funcionarioCodigo = pessoaCodigo;
    }
    
    public int getFuncionarioCodigo() {
        return funcionarioCodigo;
    }

    public void setFuncionarioCodigo(int codigo) {
        this.funcionarioCodigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CargoBean getCargo() {
        return cargo;
    }

    public void setCargo(CargoBean cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return super.toString()+"\nFuncionario{" + "codigo=" + funcionarioCodigo + ", login=" + login + ", senha=" + senha + ", cargo=" + cargo + '}';
    }

}
