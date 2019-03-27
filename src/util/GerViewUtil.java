/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.bean.ConvenioBean;
import model.bean.ProfissionalBean;
import javax.swing.JFrame;
import view.MenuPrincipalView;
import view.agenda.AgendaView;
import view.caixa.FluxoDeCaixaView;
import view.configuracoes.ConfiguracoesView;
import view.consulta.GerConsultasView;
import view.convenio.CadastrarConvenioView;
import view.convenio.EditarConvenioView;
import view.convenio.GerenciarConveniosView;
import view.funcionario.CadastrarFuncionarioView;
import view.funcionario.EditarFuncionarioView;
import view.funcionario.GerenciarFuncionariosView;
import view.laudo.GerenciadorLaudoView;
import view.profissionais.CadastrarProfissionalView;
import view.profissionais.EditarProfissionalView;
import view.profissionais.GerenciarProfissionaisView;

/**
 *
 * @author vande
 */
public abstract class GerViewUtil {

    private static MenuPrincipalView menuprincipal = null;
    private static AgendaView agenda = null;
    private static GerenciarProfissionaisView profissional = null;
    private static CadastrarProfissionalView cadPro = null;
    private static EditarProfissionalView editPro = null;
    private static GerenciarConveniosView convenio = null;
    private static CadastrarConvenioView cadCon = null;
    private static EditarConvenioView editCon = null;
    private static GerenciarFuncionariosView funcionario = null;
    private static CadastrarFuncionarioView cadFun = null;
    private static EditarFuncionarioView editFun = null;
    private static GerConsultasView consulta = null;
    private static ConfiguracoesView configuracoes = null;
    private static FluxoDeCaixaView caixa = null;
    private static GerenciadorLaudoView laudo = null;

    /**
     * Fechar todos as views abertas.
     */
    public static void closeAllView() {
        if (menuprincipal != null) {
            menuprincipal.setVisible(false);
        }
        if (agenda != null) {
            agenda.setVisible(false);
        }
        if (profissional != null) {
            profissional.setVisible(false);
        }
        if (cadPro != null) {
            cadPro.setVisible(false);
        }
        if (editPro != null) {
            editPro.setVisible(false);
        }
        if (convenio != null) {
            convenio.setVisible(false);
        }
        if (cadCon != null) {
            cadCon.setVisible(false);
        }
        if (editCon != null) {
            editCon.setVisible(false);
        }
        if (funcionario != null) {
            funcionario.setVisible(false);
        }
        if (cadFun != null) {
            cadFun.setVisible(false);
        }
        if (editFun != null) {
            editFun.setVisible(false);
        }
        if (consulta != null) {
            consulta.setVisible(false);
        }
        if (configuracoes != null) {
            configuracoes.setVisible(false);
        }
        if (caixa != null) {
            caixa.setVisible(false);
        }
        if (laudo != null) {
            laudo.setVisible(false);
        }
    }

    /**
     * Gerenciar instancias da view de Menu Principal.
     */
    public static void getViewMenuPrincipal() {
        SessaoUtil.verificarSessao(menuprincipal);
        if (menuprincipal == null) {
            menuprincipal = new MenuPrincipalView();
            menuprincipal.setVisible(true);
        } else {
            menuprincipal.setVisible(true);
            menuprincipal.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Gerenciar instancias da view de AgendaView.
     */
    public static void getViewAgenda() {
        SessaoUtil.verificarSessao(agenda);
        if (agenda == null) {
            agenda = new AgendaView();
            agenda.setVisible(true);
        } else {
            agenda.setVisible(true);
            agenda.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * "Atualiza o frame quando aberto novamente após fechado pelo botão close
     * do sistema "X".
     */
    public static void refleshAgendaView() {
        SessaoUtil.verificarSessao(agenda);
        agenda = new AgendaView();
    }
    
    public static void refleshCaixaView() {
        SessaoUtil.verificarSessao(caixa);
        caixa = new FluxoDeCaixaView();
    }

    /**
     * Gerenciar instancias da view de profissional.
     */
    public static void getViewProfissional() {
        SessaoUtil.verificarSessao(profissional);
        if (profissional == null) {
            profissional = new GerenciarProfissionaisView();
            profissional.setVisible(true);
        } else {
            profissional.setVisible(true);
            profissional.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Gerenciar instancias da view de profissional.
     */
    public static void getViewConvenio() {
        SessaoUtil.verificarSessao(convenio);
        if (convenio == null) {
            convenio = new GerenciarConveniosView();
            convenio.setVisible(true);
        } else {
            convenio.setVisible(true);
            convenio.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Gerenciar instancias da view de funcionário.
     */
    public static void getViewFuncionaio() {
        SessaoUtil.verificarSessao(funcionario);
        if (funcionario == null) {
            funcionario = new GerenciarFuncionariosView();
            funcionario.setVisible(true);
        } else {
            funcionario.setVisible(true);
            funcionario.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Gerenciar instancias da view de configuração.
     */
    public static void getViewConfiguracao() {
        SessaoUtil.verificarSessao(configuracoes);
        if (configuracoes == null) {
            configuracoes = new ConfiguracoesView();
            configuracoes.setVisible(true);
        } else {
            configuracoes.setVisible(true);
            configuracoes.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Gerenciar instancias da view de consulta.
     */
    public static void getViewConsulta() {
        SessaoUtil.verificarSessao(consulta);
        if (consulta == null) {
            consulta = new GerConsultasView();
            consulta.setVisible(true);
        } else {
            consulta.setVisible(true);
            consulta.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Gerenciar instancias da view de caixa.
     */
    public static void getViewCaixa() {
        SessaoUtil.verificarSessao(caixa);
        if (caixa == null) {
            caixa = new FluxoDeCaixaView();
            caixa.setVisible(true);
        } else {
            caixa.setVisible(true);
            caixa.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Gerenciar instancias da view de gerenciador de laudo.
     */
    public static void getViewLaudo() {
        SessaoUtil.verificarSessao(laudo);
        if (laudo == null) {
            laudo = new GerenciadorLaudoView();
            laudo.setVisible(true);
        } else {
            laudo.setVisible(true);
            laudo.setExtendedState(JFrame.NORMAL);
        }
    }

    /**
     * Chega se o gerenciador de funcionários está aberto.
     *
     * @return
     */
    public static boolean checkIfGerFunOpen() {
        if (funcionario == null) {
            return false;
        }
        return true;
    }
}
