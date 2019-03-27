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
public class ProfissionalBean extends FuncionarioBean {

    private int profissionalCodigo;
    private ConselhoRegionalBean conselho;
    private int numConselho;
    private String ufConselho;
    private EspecializacaoBean especializacao;
    private String oldInfoOfConselho;
    private String oldInfoOfNumConselho;
    private String oldInfoOfUf;
    
    public ProfissionalBean() {
    }

    public ProfissionalBean(int profissionalCodigo, ConselhoRegionalBean conselhoCodigo, int numConselho, String ufConselho, EspecializacaoBean especializacao) {
        this.profissionalCodigo = profissionalCodigo;
        this.conselho = conselhoCodigo;
        this.numConselho = numConselho;
        this.ufConselho = ufConselho;
        this.especializacao = especializacao;
    }

    public ProfissionalBean(int pessoaCodigo, String nome, Date dataNascimento, String sexo, String profissao, String telefone, String celular, String logradouro, String numeroResidencia, String bairro, String cidade, String uf, String cep, String email, String login, String senha, CargoBean cargo, ConselhoRegionalBean conselhoCodigo, int numConselho, String ufConselho, EspecializacaoBean especializacao) {
        super(pessoaCodigo, nome, dataNascimento, sexo, profissao, telefone, celular, logradouro, numeroResidencia, bairro, cidade, uf, cep, email, login, senha, cargo);
        this.conselho = conselhoCodigo;
        this.numConselho = numConselho;
        this.ufConselho = ufConselho;
        this.especializacao = especializacao;
        this.profissionalCodigo = pessoaCodigo;
    }

    public ProfissionalBean(int profissionalCodigo) {
        this.profissionalCodigo = profissionalCodigo;
    }
    
    /**
     * Dados anteriores de profissionais.
     * @return 
     */
    public String[] getOldInf(){
        String[] args = new String[3];
        args[0] = this.oldInfoOfConselho;
        args[1] = this.oldInfoOfNumConselho;
        args[2] = this.oldInfoOfUf;
        return args;
    }

    public int getProfissionalCodigo() {
        return profissionalCodigo;
    }

    public void setProfissionalCodigo(int profissionalCodigo) {
        this.profissionalCodigo = profissionalCodigo;
        super.setPessoaCodigo(numConselho);
    }

    public ConselhoRegionalBean getConselho() {
        return conselho;
    }

    public void setConselho(ConselhoRegionalBean conselho) {
        if(this.conselho != null){
            this.oldInfoOfConselho = this.conselho.getDescricao();
        }
        this.conselho = conselho;
    }

    public int getNumConselho() {
        return numConselho;
    }

    public void setNumConselho(int numConselho) {
        this.oldInfoOfNumConselho = ""+this.numConselho;
        this.numConselho = numConselho;
    }

    public String getUfConselho() {
        return ufConselho;
    }

    public void setUfConselho(String ufConselho) {
        this.oldInfoOfUf = this.ufConselho;
        this.ufConselho = ufConselho;
    }

    public EspecializacaoBean getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(EspecializacaoBean especializacao) {
        this.especializacao = especializacao;
    }

    @Override
    public String toString() {
        return super.getNome();
    }
    
}
