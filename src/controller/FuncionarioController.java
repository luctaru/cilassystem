/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.bean.FuncionarioBean;
import model.bean.ProfissionalBean;
import dao.FuncionarioDAO;
import dao.ProfissionalDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import util.SessaoUtil;
import view.funcionario.CadastrarFunProRealizanteView;
import view.funcionario.GerenciarFuncionariosView;

/**
 *
 * @author vande
 */
public abstract class FuncionarioController {

    /**
     * Autenticar acesso ao sistema por meio de login e senha.
     *
     * @param login
     * @return
     */
    public static boolean autenticarAcesso(FuncionarioBean login) {
        FuncionarioBean funcionario;
        try {
            funcionario = FuncionarioDAO.searchFuncionarioByObject(login);
            if (funcionario == null) {
                return false;
            }
            SessaoUtil.setFuncionario(funcionario);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar funcionário(a) no banco de dados.");
            throw new RuntimeException(ex);
        }
        return true;
    }

    /**
     * Criar um funcionário a partir no obeto de um profissional, pois o mesmo
     * extende todos os tipos da hierarquia de pessoas (pessoas <- funcionarios
     * <- profissionais).
     *
     * @param pro
     * @return
     */
    public static boolean createFuncionario(ProfissionalBean pro) {
        try {
            if (FuncionarioDAO.createFuncionario(pro)) {
                JOptionPane.showMessageDialog(null, "Funcionário(a) cadastrado(a) com sucesso!");
                if (GerenciarFuncionariosView.tablemodelFuncionario != null) {
                    GerenciarFuncionariosView.tablemodelFuncionario.fireTableDataChanged();
                }
                return true;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário(a).");
        return false;
    }

    /**
     * Deletar um funcionário.
     *
     * @param fun
     * @return
     */
    public static boolean deleteFuncionario(FuncionarioBean fun) {
        try {
            if (FuncionarioDAO.deleteFuncionarioByLogin(fun.getLogin())) {
                JOptionPane.showMessageDialog(null, "Funcionário(a) deletado(a) com sucesso.");
                GerenciarFuncionariosView.tablemodelFuncionario.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao deletar funcionário(a).");
        return false;
    }

    /**
     * Atualizar dados de um funcionário.
     * @param fun
     * @return 
     */
    public static boolean updateFuncionario(FuncionarioBean fun) {
        try {
            if (FuncionarioDAO.updateFuncionario(fun)) {
                //JOptionPane.showMessageDialog(null, "Funcionário(a) atualizado(a) com sucesso.");
                GerenciarFuncionariosView.tablemodelFuncionario.fireTableDataChanged();
                return true;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do(a) funcionário(a).");
        return false;
    }
    
    /**
     * Atualizar dados de um funcionário e verificar se o cargo mudou para profissional realizante.
     *
     * @param fun
     * @return
     */
    public static boolean updateFuncionario(JDialog parent, ProfissionalBean fun) {
        try {
            if (fun.getCargo().getDescricao().equalsIgnoreCase("Profissional realizante")) {
                fun.setPessoaCodigo(FuncionarioDAO.searchCodigoFunByLogin(fun.getLogin()));
                if (!ProfissionalDAO.ifExistProfissional(fun.getPessoaCodigo())) {
                    JOptionPane.showMessageDialog(null, "Este funcionário não é um profissional ainda. "
                            + "Informe os dados de profissional realizante para realizar a alteração de cargo.");
                    CadastrarFunProRealizanteView cadastrar = new CadastrarFunProRealizanteView(parent, true, fun);
                    cadastrar.setVisible(true);
                } else {
                    FuncionarioDAO.updateFuncionario(fun);
                }
            } else {
                FuncionarioDAO.updateFuncionario(fun);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do(a) funcionário(a).");
            System.err.println(ex.toString());
            return false;
        }
        GerenciarFuncionariosView.tablemodelFuncionario.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Dados do(a) funcionário(a) atualizados com sucesso.");
        return true;
    }

    /**
     * Procurar funcionário a partir de um cargo.
     *
     * @param cargo
     * @return
     */
    public static boolean existFuncionarioByCargo(int cargo) {
        try {
            List<FuncionarioBean> users = FuncionarioDAO.searchFuncionariosByCargo(cargo);
            if (users.isEmpty()) {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
