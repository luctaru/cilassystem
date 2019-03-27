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
public class LaudoBean {
    
    private int laudoCodigo;
    private FaturamentoBean faturamento;
    private String laudo;

    public LaudoBean() {
    }

    public LaudoBean(int laudoCodigo, FaturamentoBean faturamento, String laudo) {
        this.laudoCodigo = laudoCodigo;
        this.faturamento = faturamento;
        this.laudo = laudo;
    }

    public int getLaudoCodigo() {
        return laudoCodigo;
    }

    public void setLaudoCodigo(int laudoCodigo) {
        this.laudoCodigo = laudoCodigo;
    }

    public FaturamentoBean getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(FaturamentoBean faturamento) {
        this.faturamento = faturamento;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    @Override
    public String toString() {
        return "LaudoBean{" + "laudoCodigo=" + laudoCodigo + ", faturamento=" + faturamento + ", laudo=" + laudo + '}';
    }
        
}
