/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import model.bean.CargoBean;
import dao.FuncionarioDAO;
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
public class FuncionarioTableModel extends AbstractTableModel {

    private String[] colunas = {"Nome do(a) funcionário(a)", "Data de nascimento", "Sexo", "Login", "Cargo"};
    private String search;

    /**
     * Construtor da classe que recebe como parametro um valor de busca
     * referente ao nome do funcionario para preencher a lista da JTable.
     *
     * @param search
     */
    public FuncionarioTableModel(String search) {
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
            return FuncionarioDAO.searchFuncionarioByNome(search).size();
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
                    return FuncionarioDAO.searchFuncionarioByNome(search).get(linha).getNome();
                case 1:
                    return MaskFormatTextUtil.dataBr(FuncionarioDAO.searchFuncionarioByNome(search).get(linha).getDataNascimento());
                case 2:
                    return MaskFormatTextUtil.sexoBySigla(FuncionarioDAO.searchFuncionarioByNome(search).get(linha).getSexo());
                case 3:
                    return FuncionarioDAO.searchFuncionarioByNome(search).get(linha).getLogin();
                case 4:
                    return FuncionarioDAO.searchFuncionarioByNome(search).get(linha).getCargo().getDescricao();
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
                    FuncionarioDAO.searchFuncionarioByNome(search).get(linha).setNome((String) valor);
                    break;
                case 1:
                    FuncionarioDAO.searchFuncionarioByNome(search).get(linha).setDataNascimento((Date) valor);
                    break;
                case 2:
                    FuncionarioDAO.searchFuncionarioByNome(search).get(linha).setSexo((String) valor);
                    break;
                case 3:
                    FuncionarioDAO.searchFuncionarioByNome(search).get(linha).setLogin((String) valor);
                    break;
                case 4:
                    FuncionarioDAO.searchFuncionarioByNome(search).get(linha).setCargo(new CargoBean((String) valor));
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
