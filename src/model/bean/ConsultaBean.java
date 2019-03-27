/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import dao.ConsultaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vande
 */
public class ConsultaBean {
    
    private int consultaCodigo;
    private String descricao;

    public ConsultaBean() {
    }

    public ConsultaBean(int procedimentoCodigo) {
        this.consultaCodigo = procedimentoCodigo;
    }

    public ConsultaBean(int consultaCodigo, String descricao) {
        this.consultaCodigo = consultaCodigo;
        this.descricao = descricao;
    }

    public ConsultaBean(String descricao){
        this.descricao = descricao;
        try {
            this.consultaCodigo = ConsultaDAO.searchCodConsultaByDesc(descricao);
        } catch (Exception ex) {
            Logger.getLogger(ConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getConsultaCodigo() {
        return consultaCodigo;
    }

    public void setConsultaCodigo(int procedimentoCodigo) {
        this.consultaCodigo = procedimentoCodigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
}
