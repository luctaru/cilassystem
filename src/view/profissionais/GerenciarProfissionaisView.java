/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.profissionais;

import model.bean.ConselhoRegionalBean;
import model.bean.EspecializacaoBean;
import model.bean.ProfissionalBean;
import controller.ProfissionalController;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import model.jtable.ProfissionalTableModel;
import util.GerViewUtil;

/**
 *
 * @author vande
 */
public class GerenciarProfissionaisView extends javax.swing.JFrame {

    public static ProfissionalTableModel tablemodelmedico = null;
    private ProfissionalBean pro = null;

    /**
     * Creates new form GerenciarMedicos
     */
    public GerenciarProfissionaisView() {
        initComponents();
        jtMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defConvenioTableModelBySearch();
        defColumnFuncionarioTable();
    }

    /**
     * Método que define o tamanho das colunas da tabela de médicos.
     */
    private void defColumnFuncionarioTable() {
        jtMedicos.getColumnModel().getColumn(0).setPreferredWidth(20);
        jtMedicos.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtMedicos.getColumnModel().getColumn(2).setPreferredWidth(200);
        jtMedicos.getColumnModel().getColumn(3).setPreferredWidth(30);
        jtMedicos.getColumnModel().getColumn(4).setPreferredWidth(10);
    }

    /**
     * Método usado pelo listener quando digitado um valor no campo de busca de
     * médicos, além de setar um model para tabela.
     */
    private void defConvenioTableModelBySearch() {
        tablemodelmedico = new ProfissionalTableModel(txtSearchMedico.getText());
        jtMedicos.setModel(tablemodelmedico);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpGerMedicos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCadMedico = new javax.swing.JButton();
        txtSearchMedico = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMedicos = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnExcluirMedico = new javax.swing.JButton();
        jmbGerMedicos = new javax.swing.JMenuBar();
        jmSobre = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cila's System - Gerenciar profissionais");
        setResizable(false);

        jLabel1.setText("Nome:");

        btnCadMedico.setText("CADASTRAR NOVO");
        btnCadMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadMedicoActionPerformed(evt);
            }
        });

        txtSearchMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchMedicoKeyPressed(evt);
            }
        });

        jtMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMedicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtMedicos);

        btnExcluir.setText("EDITAR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnExcluirMedico.setText("EXCLUIR");
        btnExcluirMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpGerMedicosLayout = new javax.swing.GroupLayout(jpGerMedicos);
        jpGerMedicos.setLayout(jpGerMedicosLayout);
        jpGerMedicosLayout.setHorizontalGroup(
            jpGerMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGerMedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGerMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addGroup(jpGerMedicosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCadMedico))
                    .addGroup(jpGerMedicosLayout.createSequentialGroup()
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluirMedico)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpGerMedicosLayout.setVerticalGroup(
            jpGerMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGerMedicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGerMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCadMedico)
                    .addComponent(txtSearchMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGerMedicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnExcluirMedico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jmSobre.setText("Sobre");
        jmbGerMedicos.add(jmSobre);

        setJMenuBar(jmbGerMedicos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGerMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGerMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchMedicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMedicoKeyPressed
        // TODO add your handling code here:
        defConvenioTableModelBySearch();
        defColumnFuncionarioTable();
    }//GEN-LAST:event_txtSearchMedicoKeyPressed

    private void btnCadMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadMedicoActionPerformed
        // TODO add your handling code here:
        CadastrarProfissionalView cad = new CadastrarProfissionalView(this, true);
        cad.setVisible(true);
    }//GEN-LAST:event_btnCadMedicoActionPerformed

    private void jtMedicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMedicosMouseClicked
        // TODO add your handling code here:
        pro = new ProfissionalBean();
        pro.setNumConselho((int)jtMedicos.getValueAt(jtMedicos.getSelectedRow(), 0));
        pro.setNome((String)jtMedicos.getValueAt(jtMedicos.getSelectedRow(), 1));
        pro.setEspecializacao(new EspecializacaoBean((String)jtMedicos.getValueAt(jtMedicos.getSelectedRow(), 2)));
        pro.setConselho(new ConselhoRegionalBean((String)jtMedicos.getValueAt(jtMedicos.getSelectedRow(), 3)));
        pro.setUfConselho((String)jtMedicos.getValueAt(jtMedicos.getSelectedRow(), 4));
    }//GEN-LAST:event_jtMedicosMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if(jtMedicos.getSelectedRow() != -1){
            EditarProfissionalView edi = new EditarProfissionalView(this, true, pro);
            edi.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum(a) profissional foi selecionado(a).");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMedicoActionPerformed
        // TODO add your handling code here:
        if(jtMedicos.getSelectedRow() != -1){
            ProfissionalController.deleteProfissional(pro);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum(a) profissional foi selecionado(a).");
        }
    }//GEN-LAST:event_btnExcluirMedicoActionPerformed

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
            java.util.logging.Logger.getLogger(GerenciarProfissionaisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarProfissionaisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarProfissionaisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarProfissionaisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciarProfissionaisView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadMedico;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluirMedico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu jmSobre;
    private javax.swing.JMenuBar jmbGerMedicos;
    private javax.swing.JPanel jpGerMedicos;
    private javax.swing.JTable jtMedicos;
    private javax.swing.JTextField txtSearchMedico;
    // End of variables declaration//GEN-END:variables
}
