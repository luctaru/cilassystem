/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import dao.ConvenioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import util.MaskFormatTextUtil;

/**
 *
 * @author vande
 */
public class ConvenioTableModel extends AbstractTableModel {

    private String[] colunas = {"Nome do convênio", "CNPJ", "Telefone", "E-mail"};
    private String search;

    /**
     * Construtor da classe que recebe como parametro um valor de busca
     * referente ao nome do convênio para preencher a lista da JTable.
     *
     * @param search
     */
    public ConvenioTableModel(String search) {
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
            return ConvenioDAO.searchConvenioByNome(search).size();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir os convênios.");
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
                    return ConvenioDAO.searchConvenioByNome(search).get(linha).getNome();
                case 1:
                    return MaskFormatTextUtil.cnpj(ConvenioDAO.searchConvenioByNome(search).get(linha).getCnpj());
                case 2:
                    return MaskFormatTextUtil.telefone(ConvenioDAO.searchConvenioByNome(search).get(linha).getTelefone());
                case 3:
                    return ConvenioDAO.searchConvenioByNome(search).get(linha).getEmail();
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
                    ConvenioDAO.searchConvenioByNome(search).get(linha).setNome((String) valor);
                    break;
                case 1:
                    ConvenioDAO.searchConvenioByNome(search).get(linha).setCnpj((String) valor);
                    break;
                case 2:
                    ConvenioDAO.searchConvenioByNome(search).get(linha).setTelefone((String) valor);
                    break;
                case 3:
                    ConvenioDAO.searchConvenioByNome(search).get(linha).setEmail((String) valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
