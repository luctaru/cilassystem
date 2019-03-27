/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import model.bean.ConsultaBean;
import model.bean.ConvenioBean;
import model.bean.PacienteBean;
import model.bean.ProfissionalBean;
import dao.AgendaDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vande
 */
public class EditarHistoricoPacienteTableModel extends AbstractTableModel {

    private String[] colunas = {"Data", "Horário", "Profissional realizante", "Convênio", "Tipo da consulta"};
    private PacienteBean paciente;
;
    /**
     * Construtor da classe que recebe como parametro um valor de busca.
     *
     * @param search
     */
    public EditarHistoricoPacienteTableModel(PacienteBean paciente) {
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
            return AgendaDAO.historicoAgendamento(paciente).size();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir o histórico de agendamentos.");
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
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(AgendaDAO.historicoAgendamento(paciente).get(linha).getDataAgendamento());
                    return data;
                case 1:
                    String hora = new SimpleDateFormat("H:mm").format(AgendaDAO.historicoAgendamento(paciente).get(linha).getDataAgendamento());
                    return hora;
                case 2:
                    return AgendaDAO.historicoAgendamento(paciente).get(linha).getProfissional().getNome();
                case 3:
                    return AgendaDAO.historicoAgendamento(paciente).get(linha).getConvenio().getNome();
                case 4:
                    return AgendaDAO.historicoAgendamento(paciente).get(linha).getConsulta().getDescricao();
                
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
                    AgendaDAO.historicoAgendamento(paciente).get(linha).setDataAgendamento((Date) valor);
                    break;
                case 1:
                    AgendaDAO.historicoAgendamento(paciente).get(linha).setDataAgendamento((Date) valor);
                    break;
                case 2:
                    AgendaDAO.historicoAgendamento(paciente).get(linha).setProfissional((ProfissionalBean) valor);
                    break;
                case 3:
                    AgendaDAO.historicoAgendamento(paciente).get(linha).setConvenio((ConvenioBean) valor);
                    break;
                case 4:
                    AgendaDAO.historicoAgendamento(paciente).get(linha).setConsulta((ConsultaBean) valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
