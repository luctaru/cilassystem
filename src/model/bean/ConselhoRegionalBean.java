/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import dao.ConselhoRegionalDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vande
 */
public class ConselhoRegionalBean {
    
    private int conselhoCodigo;
    private String descricao;

    public ConselhoRegionalBean(int conselhoCodigo, String descricao) {
        this.conselhoCodigo = conselhoCodigo;
        this.descricao = descricao;
    }

    public ConselhoRegionalBean() {
    }

    public ConselhoRegionalBean(int conselhoCodigo) {
        this.conselhoCodigo = conselhoCodigo;
        try {
            this.descricao = ConselhoRegionalDAO.searchDescConselho(conselhoCodigo);
        } catch (Exception ex) {
            Logger.getLogger(ConselhoRegionalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ConselhoRegionalBean(String descricao) {
        try {
            this.conselhoCodigo = ConselhoRegionalDAO.searchCodigoConselho(descricao);
        } catch (Exception ex) {
            Logger.getLogger(ConselhoRegionalBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.descricao = descricao;
    }
    
    public int getConselhoCodigo() {
        return conselhoCodigo;
    }

    public void setConselhoCodigo(int conselhoCodigo) {
        this.conselhoCodigo = conselhoCodigo;
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
