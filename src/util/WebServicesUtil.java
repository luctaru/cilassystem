/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.bean.CepBean;
import webservices.WebServiceCepAPI;

/**
 *
 * @author vande
 */
public abstract class WebServicesUtil {
    
    public static CepBean searchCepUtil(String cep) throws Exception {
        //Faz a busca para o cep 58043-280
        WebServiceCepAPI webServiceCep = WebServiceCepAPI.searchCep(cep);
        //A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) {
            return new CepBean(
                    webServiceCep.getLogradouroFull(),
                    webServiceCep.getBairro(),
                    webServiceCep.getCidade(),
                    webServiceCep.getUf()
            );
            //caso haja problemas imprime as exce??es.
        } else {
            System.err.println("Erro numero: " + webServiceCep.getResulCode());
            System.err.println("Descrição do erro: " + webServiceCep.getResultText());
        }
        return null;
    }
    
}
