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
public class ModelosDeLaudosBean {
    
    private int modeloCodigo;
    private String nome;
    private String modelo;
    private ProfissionalBean criadoPor;
    private String criadoEm;

    public ModelosDeLaudosBean(int modeloCodigo, String nome, String modelo, ProfissionalBean criadoPor, String criadoEm) {
        this.modeloCodigo = modeloCodigo;
        this.nome = nome;
        this.modelo = modelo;
        this.criadoPor = criadoPor;
        this.criadoEm = criadoEm;
    }

    public ModelosDeLaudosBean() {
    }

    public int getModeloCodigo() {
        return modeloCodigo;
    }

    public void setModeloCodigo(int modeloCodigo) {
        this.modeloCodigo = modeloCodigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public ProfissionalBean getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(ProfissionalBean criadoPor) {
        this.criadoPor = criadoPor;
    }

    public String getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(String criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Override
    public String toString() {
        return "ModelosDeLaudos{" + "modeloCodigo=" + modeloCodigo + ", nome=" + nome + ", modelo=" + modelo + ", criadoPor=" + criadoPor + ", criadoEm=" + criadoEm + '}';
    }
    
}
