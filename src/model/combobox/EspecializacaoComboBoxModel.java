/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.combobox;

import model.bean.EspecializacaoBean;
import dao.EspecializacaoDAO;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vande
 */
public class EspecializacaoComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private List<EspecializacaoBean> listaEspecializacao;
    private EspecializacaoBean especializacaoSelecionada;

    public EspecializacaoComboBoxModel() {
        this.listaEspecializacao = EspecializacaoDAO.readEspecializacao();
    }

    @Override
    public int getSize() {
        return listaEspecializacao.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaEspecializacao.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof EspecializacaoBean) {
            this.especializacaoSelecionada = (EspecializacaoBean) anItem;
            fireContentsChanged(this.listaEspecializacao, 0, this.listaEspecializacao.size());
            
        }
    }
    
    @Override
    public Object getSelectedItem() {
        return this.especializacaoSelecionada;
    }

}
