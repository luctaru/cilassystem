/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import model.bean.FluxoDeCaixaBean;
import model.bean.PacienteBean;
import dao.AgendaDAO;
import dao.FaturamentoDAO;
import dao.LaudoDAO;
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
public class LaudoTableModel extends AbstractTableModel {

    private String[] colunas = {"Código", "Data / Horário", "Profissional realizante", "Convênio", "Tipo da consulta"};
    private PacienteBean paciente;

    /**
     * Construtor da classe que recebe como parametro um valor de busca.
     *
     * @param search
     */
    public LaudoTableModel(PacienteBean paciente) {
        this.paciente = paciente;
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
            return LaudoDAO.searchLaudoByPaciente(paciente).size();
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
                    return LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getFaturamentoCodigo();
                case 1:
                    String dataTime = new SimpleDateFormat("dd/MM/yyyy H:mm").format(LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().getDataAgendamento());
                    return dataTime;
                case 2:
                    return LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().getProfissional().getNome();
                case 3:
                    return LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().getConvenio().getNome();
                case 4:
                    return LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().getConsulta().getDescricao();
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
                    LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().setFaturamentoCodigo((int) valor);
                    break;
                case 1:
                    LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().setDataAgendamento((Date) valor);
                    break;
                case 2:
                    LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().getProfissional().setNome((String) valor);
                    break;
                case 3:
                    LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().getConvenio().setNome((String) valor);
                    break;
                case 4:
                    LaudoDAO.searchLaudoByPaciente(paciente).get(linha).getFaturamento().getAgenda().getConsulta().setDescricao((String) valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
