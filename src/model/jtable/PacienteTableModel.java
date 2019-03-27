/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import dao.PacienteDAO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import util.MaskFormatTextUtil;

/**
 * Classe table model de pacientes.
 *
 * @author vande
 */
public class PacienteTableModel extends AbstractTableModel {

    private String[] colunas = {"Código", "Nome do(a) paciente", "Telefone", "Celular"};
    private String search;

    /**
     * Construtor da classe que recebe como parametro um valor de busca
     * referente ao nome do paciente para preencher a lista da JTable.
     *
     * @param search
     */
    public PacienteTableModel(String search) {
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
            return PacienteDAO.searchPacienteByNome(search).size();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir os pacientes.");
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
                    return PacienteDAO.searchPacienteByNome(search).get(linha).getPacienteCodigo();
                case 1:
                    return PacienteDAO.searchPacienteByNome(search).get(linha).getNome();
                case 2:
                    return MaskFormatTextUtil.telefone(PacienteDAO.searchPacienteByNome(search).get(linha).getTelefone());
                case 3:
                    return MaskFormatTextUtil.telefone(PacienteDAO.searchPacienteByNome(search).get(linha).getCelular());
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
                    PacienteDAO.searchPacienteByNome(search).get(linha).setPacienteCodigo((int) valor);
                    break;
                case 1:
                    PacienteDAO.searchPacienteByNome(search).get(linha).setNome((String) valor);
                    break;
                case 2:
                    PacienteDAO.searchPacienteByNome(search).get(linha).setTelefone((String) valor);
                    break;
                case 3:
                    PacienteDAO.searchPacienteByNome(search).get(linha).setCelular((String) valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
