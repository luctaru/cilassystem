/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import dao.EspecializacaoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vande
 */
public class EspecializacaoBean {
    
    private int especializacaoCodigo;
    private String descricao;

    public EspecializacaoBean(int especializacaoCodigo, String descricao) {
        this.especializacaoCodigo = especializacaoCodigo;
        this.descricao = descricao;
    }

    public EspecializacaoBean() {
    }

    public EspecializacaoBean(int especializacaoCodigo) {
        this.especializacaoCodigo = especializacaoCodigo;
        try {
            this.descricao = EspecializacaoDAO.searchDescEspecializacao(especializacaoCodigo);
        } catch (Exception ex) {
            Logger.getLogger(EspecializacaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EspecializacaoBean(String descricao) {
        try {
            this.especializacaoCodigo = EspecializacaoDAO.searchCodigoEspecializacao(descricao);
        } catch (Exception ex) {
            Logger.getLogger(EspecializacaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.descricao = descricao;
    }
    
    public int getEspecializacaoCodigo() {
        return especializacaoCodigo;
    }

    public void setEspecializacaoCodigo(int especializacaoCodigo) {
        this.especializacaoCodigo = especializacaoCodigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
