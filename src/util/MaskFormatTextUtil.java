/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.PacienteDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 * Classe utilitária para métodos staticos que manipulam máscaras de dados.
 *
 * @author vande
 */
public abstract class MaskFormatTextUtil {

    /**
     * Mascara para número celular de 11 digitos formato (##) #####-#### ou
     * telefone de 8 digitos no formato (##) ####-####.
     *
     * @param tel
     * @return String
     */
    public static String telefone(String tel) {
        MaskFormatter telFormater = null;
        JFormattedTextField txtTel = null;
        try {
            if (tel.length() == 11) {
                telFormater = new MaskFormatter("(##) #####-####");
            } else {
                telFormater = new MaskFormatter("(##) ####-####");
            }
            txtTel = new JFormattedTextField(telFormater);
            txtTel.setText(tel);
        } catch (ParseException ex) {
            Logger.getLogger(MaskFormatTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return txtTel.getText();
    }

    /**
     * Mascara para CNPJ.
     *
     * @param cnpj
     * @return
     */
    public static String cnpj(String cnpj) {
        MaskFormatter cnpjFormater = null;
        JFormattedTextField txtCnpj = null;

        try {
            cnpjFormater = new MaskFormatter("##.###.###.####/##");
            txtCnpj = new JFormattedTextField(cnpjFormater);
            txtCnpj.setText(cnpj);
        } catch (ParseException ex) {
            Logger.getLogger(MaskFormatTextUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return txtCnpj.getText();
    }

    /**
     * Mascara de data para padrão brasileiro dd/MM/yyyy.
     *
     * @param data
     * @return String
     */
    public static String dataBr(Date data) {
        if (data == null) {
            return null;
        }
        String dataBr = new SimpleDateFormat("dd/MM/yyyy").format(data);
        return dataBr;
    }

    /**
     * Mascara de data para padrão americado yyyy-MM-dd.
     *
     * @param data
     * @return String
     */
    public static String dataUs(Date data) {
        if (data == null) {
            return null;
        }
        String dataBr = new SimpleDateFormat("yyyy-MM-dd").format(data);
        return dataBr;
    }

    /**
     * Mascara de sexoBySigla, onde substituirá uma sigla para uma palavra o
     * gereno correspondente.
     *
     * @param sigla
     * @return
     */
    public static String sexoBySigla(String sigla) {
        if (sigla.equalsIgnoreCase("m")) {
            return "Masculino";
        } else if (sigla.equalsIgnoreCase("f")) {
            return "Feminino";
        } else {
            return "Não definido";
        }
    }

    /**
     * Mascara de sexoBySigla, onde substituirá uma genero para uma sigla do
     * mesmo.
     *
     * @param genero
     * @return
     */
    public static String sexoByGenero(String genero) {
        if (genero.equalsIgnoreCase("Masculino")) {
            return "M";
        } else if (genero.equalsIgnoreCase("Feminino")) {
            return "F";
        } else {
            return "Não definido";
        }
    }

    /**
     * Remover de uma string os caracteres não númericos.
     *
     * @param texto
     * @return
     */
    public static String onlyNumbers(String texto) {
        String numbers = "0123456789";
        String resultado = "";
        for (int i = 0; i < texto.length(); i++) {
            for (int j = 0; j < numbers.length(); j++) {
                if (texto.charAt(i) == numbers.charAt(j)) {
                    resultado = resultado + texto.charAt(i);
                }
            }
        }
        return resultado;
    }

    /**
     * Checa se a string contem somente números.
     *
     * @param texto
     * @return
     */
    public static boolean checkOnlyNumbers(String texto) {
        try {
            Long.parseLong(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * Validar e-mail.
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                for (int j = i; j < email.length(); j++) {
                    if (email.charAt(j) == '.') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Válida formato de horário em hh:mm.
     *
     * @param hora
     * @return
     */
    public static boolean checkHora(String hora) {
        if (hora.length() != 5 || hora.charAt(2) != ':') {
            System.err.println("Tamanho da String de horas > 5 ou o caracter ':' não encontrado na posição 2.");
            return false;
        } else {
            String hh = hora.substring(0, 2);
            String mm = hora.substring(3);
            if (checkOnlyNumbers(hh) && checkOnlyNumbers(mm)) {
                if (Integer.parseInt(hh) > 23 || Integer.parseInt(hh) < 0
                        || Integer.parseInt(mm) > 59 || Integer.parseInt(mm) < 0) {
                    System.err.println("Horas ou minutos fora dos limites.");
                    return false;
                }
            } else {
                System.err.println("Contém caracteres não númericos da String de hora. HH = " + hh + ", MM = " + mm);
                return false;
            }
        }
        return true;
    }

    /**
     * Válidar data.
     *
     * @param data
     * @return
     */
    public static boolean validarData(String data) {
        try {
            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(3, 5));
            int ano = Integer.parseInt(data.substring(6));

            if (dia < 1 || dia > 31) {
                return false;
            }
            if (mes > 12 || mes < 1) {
                return false;
            }
            if (ano < 1) {
                return false;
            }
            Date teste = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(MaskFormatTextUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Mudar a virgula(,) por ponto(.).
     *
     * @param valor
     * @return
     */
    public static Double changeCommaByDot(String valor) {
        if (!valor.isEmpty()) {
            return Double.parseDouble(valor.replace(',', '.'));
        }
        return 0.0;
    }

    /**
     * Mudar o ponto(.) pela virgula(,).
     *
     * @param valor
     * @return
     */
    public static String changeDotByComma(String valor) {
        if (!valor.isEmpty()) {
            return valor.replace('.', ',');
        }
        return null;
    }

    /**
     * Mascara de data e hora com saída igual a yyyy-MM-dd H:m.
     * @param dataTime
     * @return 
     */
    public static String dataTimeUs(Date dataTime) {
        if (dataTime == null) {
            return null;
        }
        String data = new SimpleDateFormat("yyyy-MM-dd").format(dataTime);
        String hora = new SimpleDateFormat("H:m").format(dataTime);
        return data+" "+hora;
    }
}
