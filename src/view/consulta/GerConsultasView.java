/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.consulta;

import controller.ConsultaController;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import model.jlist.ConsultasJListModel;

/**
 *
 * @author vande
 */
public class GerConsultasView extends javax.swing.JFrame {

    public static ConsultasJListModel listmodelConsultas = null;
    
    /**
     * Creates new form Teste
     */
    public GerConsultasView() {
        initComponents();
        defConsultasModel();
        jlConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void defConsultasModel() {
        listmodelConsultas = new ConsultasJListModel(txtSearchCon.getText());
        jlConsultas.setModel(listmodelConsultas);
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
        jLabel1 = new javax.swing.JLabel();
        txtSearchCon = new javax.swing.JTextField();
        btnCadConsulta = new javax.swing.JButton();
        btnEditarCon = new javax.swing.JButton();
        btnExcluirCon = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlConsultas = new javax.swing.JList<>();
        jmbGerConsultas = new javax.swing.JMenuBar();
        jmSobre = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cila's System - Gerenciar consultas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Nome:");

        txtSearchCon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchConKeyReleased(evt);
            }
        });

        btnCadConsulta.setText("CADASTRAR NOVA");
        btnCadConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadConsultaActionPerformed(evt);
            }
        });

        btnEditarCon.setText("EDITAR");
        btnEditarCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarConActionPerformed(evt);
            }
        });

        btnExcluirCon.setText("EXCLUIR");
        btnExcluirCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirConActionPerformed(evt);
            }
        });

        jlConsultas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlConsultasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jlConsultas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnEditarCon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluirCon))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchCon, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCadConsulta)))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchCon)
                    .addComponent(btnCadConsulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarCon)
                    .addComponent(btnExcluirCon)))
        );

        jmSobre.setText("Sobre");
        jmbGerConsultas.add(jmSobre);

        setJMenuBar(jmbGerConsultas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadConsultaActionPerformed
        // TODO add your handling code here:
        CadastrarConsultaView cadCon = new CadastrarConsultaView(this, true);
        cadCon.setVisible(true);
        defConsultasModel();
    }//GEN-LAST:event_btnCadConsultaActionPerformed

    private void btnEditarConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarConActionPerformed
        // TODO add your handling code here:
        if (jlConsultas.getSelectedIndex() != -1) {
            Object[] con = jlConsultas.getSelectedValues();
            EditarConsultaView ediCon = new EditarConsultaView(this, true, con[0].toString());
            ediCon.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para editar.");
        }
        defConsultasModel();
    }//GEN-LAST:event_btnEditarConActionPerformed

    private void jlConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlConsultasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlConsultasMouseClicked

    private void txtSearchConKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchConKeyReleased
        // TODO add your handling code here:
        defConsultasModel();
    }//GEN-LAST:event_txtSearchConKeyReleased

    private void btnExcluirConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirConActionPerformed
        // TODO add your handling code here:
        if (jlConsultas.getSelectedIndex() != -1) {
            Object[] con = jlConsultas.getSelectedValues();
            ConsultaController.deleteConsulta(con[0].toString());
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para excluir.");
        }
        defConsultasModel();
    }//GEN-LAST:event_btnExcluirConActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened
    
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
            java.util.logging.Logger.getLogger(GerConsultasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerConsultasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerConsultasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerConsultasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerConsultasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadConsulta;
    private javax.swing.JButton btnEditarCon;
    private javax.swing.JButton btnExcluirCon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlConsultas;
    private javax.swing.JMenu jmSobre;
    private javax.swing.JMenuBar jmbGerConsultas;
    private javax.swing.JTextField txtSearchCon;
    // End of variables declaration//GEN-END:variables
}
