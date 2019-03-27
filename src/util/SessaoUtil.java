/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.bean.CargoBean;
import model.bean.FuncionarioBean;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.AutenticarAcessoView;

/**
 * Classe utilitária para ser usada no que diz respeito a sessão de usuário.
 *
 * @author vande
 */
public abstract class SessaoUtil {

    /**
     * Abributo static para definir sessão de usuário.
     */
    private static FuncionarioBean funcionario = null;

    public static void setFuncionario(FuncionarioBean fun) {
        SessaoUtil.funcionario = fun;
    }

    public static FuncionarioBean getFuncionario() {
        return SessaoUtil.funcionario;
    }

    public static void redefinirFuncionario() {
        SessaoUtil.funcionario = null;
    }

    /**
     * Verificar se a sessão de um usuário foi aberta. Se não foi, abrirá a tela
     * de login.
     *
     * @param frame
     */
    public static void verificarSessao(JFrame frame) {
        if (funcionario == null) {
            JOptionPane.showMessageDialog(frame, "Faça login para poder ter acesso aos recursos do sistema.");
            GerViewUtil.closeAllView();
            frame.setVisible(false);
            AutenticarAcessoView login = new AutenticarAcessoView();
            login.setVisible(true);
        }
    }

}
