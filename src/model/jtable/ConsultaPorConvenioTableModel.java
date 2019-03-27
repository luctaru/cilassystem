/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import dao.ConsultaPorConvenioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vande
 */
public class ConsultaPorConvenioTableModel extends AbstractTableModel {

    private String[] colunas = {"Nome da consulta", "Valor"};
    private String search;

    /**
     * Construtor da classe que recebe como parametro um valor de busca
     * referente ao nome da consulta para preencher a lista da JTable.
     *
     * @param search
     */
    public ConsultaPorConvenioTableModel(String search) {
        this.search = search;
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
            return ConsultaPorConvenioDAO.searchConsultasByConNome(search).size();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir as consultas pelo convênio.");
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
                    return ConsultaPorConvenioDAO.searchConsultasByConNome(search).get(linha).getDescricao();
                case 1:
                    return ConsultaPorConvenioDAO.searchConsultasByConNome(search).get(linha).getValor();
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Define o valor na célula em columnIndexe rowIndexpara aValue.
     *
     * @param valor
     * @param linha
     * @param coluna
     */
    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        try {
            switch (coluna) {
                case 0:
                    ConsultaPorConvenioDAO.searchConsultasByConNome(search).get(linha).setDescricao((String)valor);
                    break;
                case 1:
                    ConsultaPorConvenioDAO.searchConsultasByConNome(search).get(linha).setValor((String)valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
