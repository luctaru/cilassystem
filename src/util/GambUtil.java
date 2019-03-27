/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JComboBox;

/**
 *
 * @author vande
 */
public abstract class GambUtil {
    
    /**
     * Gambiarra para quando a função setSelectedItem não estiver funcionando
     * para setar um valor diretamento do banco de dados. Foi definido uma model
     * para JComboBox, porém quando tentava setar um item da mesma a partir de
     * um valor do banco de dados pelo método setSelectedItem, nada acontecia.
     *
     * @param jcb
     * @param value
     */
    public static void setValueOfJComboBoxModel(JComboBox jcb, String value) {
        int tam = jcb.getItemCount();
        for (int i = 0; i < tam; i++) {
            Object item = jcb.getItemAt(i);
            if (item.toString().equals(value)) {
                jcb.setSelectedIndex(i);
            }
        }
    }
    
}
