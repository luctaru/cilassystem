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
public class FormaPagamentoBean {
    
    private int formaPagCodigo;
    private String descricao;

    public FormaPagamentoBean() {
    }

    public FormaPagamentoBean(int formaPagCodigo, String descricao) {
        this.formaPagCodigo = formaPagCodigo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getFormaPagCodigo() {
        return formaPagCodigo;
    }

    public void setFormaPagCodigo(int formaPagCodigo) {
        this.formaPagCodigo = formaPagCodigo;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
}
