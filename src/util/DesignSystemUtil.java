/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;

/**
 *
 * @author vande
 */
public abstract class DesignSystemUtil {
    
    public static void defLogo(JFrame frame){
        URL url = frame.getClass().getResource("../img/logo.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        frame.setIconImage(imagemTitulo);
    }
    
}
