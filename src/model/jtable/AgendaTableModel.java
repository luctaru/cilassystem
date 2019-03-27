/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.jtable;

import model.bean.AgendaBean;
import dao.AgendaDAO;
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
public class AgendaTableModel extends AbstractTableModel {

    private String[] colunas = {"Horário", "Nome do(a) paciente", "Contato", "Convênio", "Tipo da consulta", "Observação"};
    private AgendaBean agenda;

    /**
     * Construtor da classe que recebe como parametro um valor de busca.
     *
     * @param search
     */
    public AgendaTableModel(AgendaBean agenda) {
        this.agenda = agenda;
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
            return AgendaDAO.searchAgendamentos(agenda).size();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível exibir os agendamentos.");
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
                    String hora = new SimpleDateFormat("H:mm").format(AgendaDAO.searchAgendamentos(agenda).get(linha).getDataAgendamento());
                    return hora;
                case 1:
                    return AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().getNome();
                case 2:
                    if (AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().getTelefone() != null) {
                        return MaskFormatTextUtil.telefone(AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().getTelefone());
                    } else {
                        if (AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().getCelular() != null) {
                            return MaskFormatTextUtil.telefone(AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().getCelular());
                        }
                    }
                    return null;
                case 3:
                    return AgendaDAO.searchAgendamentos(agenda).get(linha).getConvenio().getNome();
                case 4:
                    return AgendaDAO.searchAgendamentos(agenda).get(linha).getConsulta().getDescricao();
                case 5:
                    return AgendaDAO.searchAgendamentos(agenda).get(linha).getObservacao();
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
                    AgendaDAO.searchAgendamentos(agenda).get(linha).setDataAgendamento((Date) valor);
                    break;
                case 1:
                    AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().setNome((String) valor);
                    break;
                case 2:
                    if (AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().getTelefone() != null) {
                        AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().setTelefone((String) valor);
                    } else {
                        if (AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().getCelular() != null) {
                            AgendaDAO.searchAgendamentos(agenda).get(linha).getPaciente().setCelular((String) valor);
                        }
                    }
                    break;
                case 3:
                    AgendaDAO.searchAgendamentos(agenda).get(linha).getConvenio().setNome((String) valor);
                    break;
                case 4:
                    AgendaDAO.searchAgendamentos(agenda).get(linha).getConsulta().setDescricao((String) valor);
                    break;
                case 5:
                    AgendaDAO.searchAgendamentos(agenda).get(linha).setObservacao((String) valor);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
