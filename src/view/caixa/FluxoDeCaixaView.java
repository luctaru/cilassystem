/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.caixa;

import model.bean.AgendaBean;
import model.bean.ConsultaBean;
import model.bean.ConvenioBean;
import model.bean.FaturamentoBean;
import model.bean.FluxoDeCaixaBean;
import model.bean.FormaPagamentoBean;
import model.bean.ProfissionalBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.combobox.ConvenioComboBoxModel;
import model.combobox.ForPagamentoComboBoxModel;
import model.combobox.ProfissionalComboBoxModel;
import model.jlist.ConsultasByConvenioJListModel;
import model.jtable.FluxoDeCaixaTableModel;
import util.GerViewUtil;

/**
 *
 * @author vande
 */
public class FluxoDeCaixaView extends javax.swing.JFrame {

    private final FluxoDeCaixaBean CAIXA = new FluxoDeCaixaBean();

    /**
     * Creates new form FluxoDeCaixa
     */
    public FluxoDeCaixaView() {
        initComponents();
        preset();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtFaturamento = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdcInicial = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jdcFinal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jcbForPagamento = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbProfissional = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jcbConvenio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlConsulta = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jlValorTotal = new javax.swing.JLabel();
        jlTotalConsultas = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cila's System - Fluxo de Caixa");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setViewportView(jtFaturamento);

        jLabel1.setText("FILTROS DA CONSULTA");

        jLabel2.setText("Data inicial:");

        jLabel3.setText("Data final:");

        jLabel4.setText("Forma de pagamento:");

        jcbForPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbForPagamentoActionPerformed(evt);
            }
        });

        jLabel5.setText("Profissional realizante:");

        jcbProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProfissionalActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel6.setText("Convênio:");

        jcbConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbConvenioActionPerformed(evt);
            }
        });

        jLabel7.setText("Consulta por convênio:");

        jlConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlConsultaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jlConsulta);

        jLabel8.setText("RESUMO DA CONSULTA:");

        jlValorTotal.setText("Valor total: R$");

        jlTotalConsultas.setText("Total de consultas:");

        jButton1.setText("LIMPAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbProfissional, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jcbConvenio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(jcbForPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlValorTotal)
                            .addComponent(jlTotalConsultas)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlValorTotal)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbForPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(jButton1))
                    .addComponent(jlTotalConsultas))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        setModelFatTable();
        countResults();
        countValorPeriodo();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jcbConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbConvenioActionPerformed
        // TODO add your handling code here:
        if (jcbConvenio.getSelectedIndex() != -1) {
            CAIXA.setConvenio(((ConvenioBean) jcbConvenio.getSelectedItem()));
            jlConsulta.setModel(new ConsultasByConvenioJListModel(((ConvenioBean) jcbConvenio.getSelectedItem()).getNome()));
        }
    }//GEN-LAST:event_jcbConvenioActionPerformed

    private void jcbForPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbForPagamentoActionPerformed
        // TODO add your handling code here:
        if (jcbForPagamento.getSelectedIndex() != -1) {
            CAIXA.setFormaPagamento((FormaPagamentoBean) jcbForPagamento.getSelectedItem());
        }
    }//GEN-LAST:event_jcbForPagamentoActionPerformed

    private void jcbProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbProfissionalActionPerformed
        // TODO add your handling code here:
        if (jcbProfissional.getSelectedIndex() != -1) {
            CAIXA.setProfissional((ProfissionalBean) jcbProfissional.getSelectedItem());
        }
    }//GEN-LAST:event_jcbProfissionalActionPerformed

    private void jlConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlConsultaMouseClicked
        // TODO add your handling code here:
        if (jlConsulta.getSelectedIndex() != -1) {
            CAIXA.setConsulta((ConsultaBean) jlConsulta.getSelectedValues()[0]);
        }
    }//GEN-LAST:event_jlConsultaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setModelsComBox();
        jlConsulta.setModel(new ConsultasByConvenioJListModel("null"));
        CAIXA.setFormaPagamento(null);
        CAIXA.setProfissional(null);
        CAIXA.setConvenio(null);
        CAIXA.setConsulta(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        setModelsComBox();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        GerViewUtil.refleshCaixaView();
    }//GEN-LAST:event_formWindowClosing

    /**
     * Preenche o formulário e instancia abributos de CAIXA.
     */
    private void preset() {
        setModelsComBox();
        Calendar data = Calendar.getInstance();
        int mes = data.get(Calendar.MONTH) + 1;
        int diaFinal = data.getActualMaximum(Calendar.DAY_OF_MONTH);
        int ano = data.get(Calendar.YEAR);
        try {
            jdcInicial.setDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/" + mes + "/" + ano));
            jdcFinal.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(diaFinal + "/" + mes + "/" + ano));
            CAIXA.setDataInicial(jdcInicial.getDate());
            CAIXA.setDataFinal(jdcFinal.getDate());
            setModelFatTable();
        } catch (ParseException ex) {
            Logger.getLogger(FluxoDeCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        countResults();
        countValorPeriodo();
    }

    /**
     * Definir modelos para as ComboBoxes.
     */
    private void setModelsComBox() {
        jcbProfissional.setModel(new ProfissionalComboBoxModel());
        jcbForPagamento.setModel(new ForPagamentoComboBoxModel());
        jcbConvenio.setModel(new ConvenioComboBoxModel());
    }
    
    /**
     * Define a busca no modelo da tabela.
     */
    private void setModelFatTable() {
        jtFaturamento.setModel(new FluxoDeCaixaTableModel(CAIXA));
    }

    /**
     * Quantidade de resultados da tabela.
     *
     * @return
     */
    private void countResults() {
        jlTotalConsultas.setText("Total de consultas: " + jtFaturamento.getRowCount());
    }

    /**
     * Valor o valor total do período.
     */
    private void countValorPeriodo() {
        int totalLinhas = jtFaturamento.getRowCount() - 1;
        double valorTotal = 0;
        for (int i = 0; i <= totalLinhas; i++) {
            double valor = (double) jtFaturamento.getValueAt(i, 6);
            valorTotal = valorTotal + valor;
        }
        jlValorTotal.setText("Valor total: R$" + String.format("%.2f", valorTotal));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FluxoDeCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FluxoDeCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FluxoDeCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FluxoDeCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FluxoDeCaixaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbConvenio;
    private javax.swing.JComboBox<String> jcbForPagamento;
    private javax.swing.JComboBox<String> jcbProfissional;
    private com.toedter.calendar.JDateChooser jdcFinal;
    private com.toedter.calendar.JDateChooser jdcInicial;
    private javax.swing.JList<String> jlConsulta;
    private javax.swing.JLabel jlTotalConsultas;
    private javax.swing.JLabel jlValorTotal;
    private javax.swing.JTable jtFaturamento;
    // End of variables declaration//GEN-END:variables
}
