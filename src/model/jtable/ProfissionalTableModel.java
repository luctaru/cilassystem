/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import dao.ProfissionalDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * Classe table model de médico.
 *
 * @author vande
 */
public class ProfissionalTableModel extends AbstractTableModel {

    private String[] colunas = {"Nº do registro", "Nome do(a) médico(a)", "Especialização", "Conselho", "Estado"};
    private String search;

    /**
     * Construtor da classe que recebe como parametro um valor de busca
     * referente ao nome do médico para preencher a lista da JTable.
     *
     * @param search
     */
    public ProfissionalTableModel(String search) {
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
            return ProfissionalDAO.searchProfissionalByName(search).size();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir os médicos.");
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
                    return ProfissionalDAO.searchProfissionalByName(search).get(linha).getNumConselho();
                case 1:
                    return ProfissionalDAO.searchProfissionalByName(search).get(linha).getNome();
                case 2:
                    return ProfissionalDAO.searchProfissionalByName(search).get(linha).getEspecializacao().getDescricao();
                case 3:
                    return ProfissionalDAO.searchProfissionalByName(search).get(linha).getConselho().getDescricao();
                case 4:
                    return ProfissionalDAO.searchProfissionalByName(search).get(linha).getUfConselho();
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalTableModel.class.getName()).log(Level.SEVERE, null, ex);
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
                    ProfissionalDAO.searchProfissionalByName(search).get(linha).setNumConselho((int) valor);
                    break;
                case 1:
                    ProfissionalDAO.searchProfissionalByName(search).get(linha).setNome((String) valor);
                    break;
                case 2:
                    ProfissionalDAO.searchProfissionalByName(search).get(linha).getEspecializacao().setDescricao((String) valor);
                    break;
                case 3:
                    ProfissionalDAO.searchProfissionalByName(search).get(linha).getConselho().setDescricao((String) valor);
                    break;
                case 4:
                    ProfissionalDAO.searchProfissionalByName(search).get(linha).setUfConselho((String) valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProfissionalTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
