/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import model.bean.FluxoDeCaixaBean;
import dao.AgendaDAO;
import dao.FaturamentoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import util.MaskFormatTextUtil;

/**
 *
 * @author vande
 */
public class FluxoDeCaixaTableModel extends AbstractTableModel {

    private String[] colunas = {"Data / Horário", "Profissional realizante", "Nome do paciente", "Convênio", "Tipo da consulta", "Forma de pagamento", "Valor da consulta"};
    private FluxoDeCaixaBean caixa;

    /**
     * Construtor da classe que recebe como parametro um valor de busca.
     *
     * @param search
     */
    public FluxoDeCaixaTableModel(FluxoDeCaixaBean caixa) {
        this.caixa = caixa;
    }

    /**
     * Retorna o nome da coluna.
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    /**
     * Retorna o número de linhas do modelo.
     *
     * @return int
     */
    @Override
    public int getRowCount() {
        try {
            return FaturamentoDAO.searchFaturamentos(caixa).size();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir o faturamento do período.");
            throw new RuntimeException(ex);
        }
    }

    /**
     * Retorna o número de colunas do modelo.
     *
     * @return int
     */
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    /**
     * Retorna o valor para a célula em columnIndex e rowIndex.
     *
     * @param linha
     * @param coluna
     * @return
     */
    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            switch (coluna) {
                case 0:
                    String dataTime = new SimpleDateFormat("dd/MM/yyyy H:mm").format(FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getDataAgendamento());
                    return dataTime;
                case 1:
                    return FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getProfissional().getNome();
                case 2:
                    return FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getPaciente().getNome();
                case 3:
                    return FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getConvenio().getNome();
                case 4:
                    return FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getConsulta().getDescricao();
                case 5:
                    return FaturamentoDAO.searchFaturamentos(caixa).get(linha).getFormaPagamento().getDescricao();
                case 6:
                    return FaturamentoDAO.searchFaturamentos(caixa).get(linha).getValorTotal();
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        try {
            switch (coluna) {
                case 0:
                    FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().setDataAgendamento((Date) valor);
                    break;
                case 1:
                    FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getProfissional().setNome((String) valor);
                    break;
                case 2:
                    FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getPaciente().setNome((String) valor);
                    break;
                case 3:
                    FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getConvenio().setNome((String) valor);
                    break;
                case 4:
                    FaturamentoDAO.searchFaturamentos(caixa).get(linha).getAgenda().getConsulta().setDescricao((String) valor);
                    break;
                case 5:
                    FaturamentoDAO.searchFaturamentos(caixa).get(linha).getFormaPagamento().setDescricao((String) valor);
                    break;
                case 6:
                    FaturamentoDAO.searchFaturamentos(caixa).get(linha).setValorTotal((Double) valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
