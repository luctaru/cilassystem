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
public class FaturamentoBean {
    
    private int faturamentoCodigo;
    private AgendaBean agenda;
    private int porcentagemDesconto;
    private double valorTotal;
    private FormaPagamentoBean formaPagamento;
    private FuncionarioBean faturadoPor;
    private String dataFaturamento;

    public FaturamentoBean() {
    }

    public FaturamentoBean(int faturamentoCodigo, AgendaBean agenda, int porcentagemDesconto, double valorTotal, FormaPagamentoBean formaPagamento, FuncionarioBean faturadoPor, String dataFaturamento) {
        this.faturamentoCodigo = faturamentoCodigo;
        this.agenda = agenda;
        this.porcentagemDesconto = porcentagemDesconto;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.faturadoPor = faturadoPor;
        this.dataFaturamento = dataFaturamento;
    }

    public String getDataFaturamento() {
        return dataFaturamento;
    }

    public void setDataFaturamento(String dataFaturamento) {
        this.dataFaturamento = dataFaturamento;
    }

    public int getFaturamentoCodigo() {
        return faturamentoCodigo;
    }

    public void setFaturamentoCodigo(int faturamentoCodigo) {
        this.faturamentoCodigo = faturamentoCodigo;
    }

    public AgendaBean getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaBean agenda) {
        this.agenda = agenda;
    }

    public int getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(int porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public FormaPagamentoBean getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoBean formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public FuncionarioBean getFaturadoPor() {
        return faturadoPor;
    }

    public void setFaturadoPor(FuncionarioBean faturadoPor) {
        this.faturadoPor = faturadoPor;
    }

    @Override
    public String toString() {
        return "Faturamento{" + "faturamentoCodigo=" + faturamentoCodigo + ", agenda=" + agenda + ", porcentagemDesconto=" + porcentagemDesconto + ", valorTotal=" + valorTotal + ", formaPagamento=" + formaPagamento + ", faturadoPor=" + faturadoPor + ", dataFaturamento=" + dataFaturamento + '}';
    }
    
}
