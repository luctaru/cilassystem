/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import model.bancodedados.ConectaBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe utilitária para métodos staticos usados em conjunto ao banco de dados.
 *
 * @author vande
 */
public abstract class DbMysqlUtil {

    /**
     * Descobre o indice (chave) do registro inseriado anteriormente na operação
     * para usa-lo dentro de outra operação da transação.
     *
     * @param stmt
     * @return int.
     * @throws SQLException
     */
    public static int getValueIndex(ResultSet rsIndex) throws SQLException {
        int index = -1;
        while (rsIndex.next()) {
            index = rsIndex.getInt(1);
        }
        return index;
    }

    /**
     * Verifica se o banco de dados existe.
     *
     * @return
     */
    public static boolean checkIfDBExists() {
        ConectaBanco.setUrlRootConnection();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            stmt = con.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'clinica_v1'");
            rs = stmt.executeQuery();
            while (rs.next()) {
                count++;
            }
            if (count > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbMysqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
            ConectaBanco.setUrlClinicaConnection();
        }
        return false;
    }

    public static boolean createDBClinica() {
        if (!checkIfDBExists()) {
            int confirm = JOptionPane.showConfirmDialog(null, "Será necessário "
                    + "criar o banco de dados do sistema.Certifique-se\nde que tem "
                    + "o MySQL server instalado e informe sua senha no\nprompt de "
                    + "comando (CMD) quando pedido.\nDeseja fazer isso agora?");
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Desktop.getDesktop().open(new File("DB.bat"));
                    System.exit(1);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Arquivos de configuração não foram encontrados!\n"
                            + "Deixe o arquivo no mesmo diretório que o\narquivo de execução do sistema e tente\n"
                            + "novamente.");
                    Logger.getLogger(DbMysqlUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.exit(1);
            }
        }
        return true;
    }

    public static boolean insertValuesDBClinica() {
        String sqlCargo = "INSERT INTO clinica_v1.cargo (cargoCodigo, descricao) VALUES \n"
                + "	(null, 'Administrador'), \n"
                + "	(null, 'Funcionário(a)'), \n"
                + "	(null, 'Profissional realizante');";

        String sqlEspecializacao = "INSERT INTO clinica_v1.especializacao (especializacaoCodigo, descricao) VALUES \n"
                + "(null, 'Alergia e Imunologia'),(null, 'Angiologia'),(null, 'Cancerologia'),\n"
                + "(null, 'Cardiologia'),(null, 'Clínica Médica '),(null, 'Coloproctologia'),\n"
                + "(null, 'Dermatologia'),(null, 'Endocrinologia e Metabologia'),(null, 'Infectologia'),\n"
                + "(null, 'Endoscopia'),(null, 'Gastroenterologia'),(null, 'Geriatria'),\n"
                + "(null, 'Ginecologia e obstetrícia'),(null, 'Mastologia'),(null, 'Medicina do Trabalho'),\n"
                + "(null, 'Medicina Esportiva'),(null, 'Nefrologia'),(null, 'Neurologia'),(null, 'Nutrologia'),\n"
                + "(null, 'Obstetrícia'),(null, 'Oftalmologia'),(null, 'Ortopedia e Traumatologia'),\n"
                + "(null, 'Otorrinolaringologia'),(null, 'Patologia'),(null, 'Pediatria'),(null, 'Pneumologia'),\n"
                + "(null, 'Psiquiatria'),(null, 'Radiologia'),(null, 'Radioterapia'),(null, 'Reumatologia'),\n"
                + "(null, 'Urologia'),(null, 'Ultrassonografia');";

        String sqlConselho = "INSERT INTO clinica_v1.conselhoRegional (conselhoCodigo, descricao) \n"
                + "VALUES (null, 'CRM'),(null, 'COREN'),(null, 'CRO'),(null, 'CRV');";

        String sqlForPagamento = "INSERT INTO clinica_v1.formaPagamento (formaPagCodigo, descricao) \n"
                + "VALUES (null, 'Dinheiro'),(null, 'Débito'),(null, 'Crédito a vista'),\n"
                + "(null, 'Crédito parcelado'),(null, 'Cheque a vista'),(null, 'Cheque pré-datado'),\n"
                + "(null, 'Pendente'),(null, 'Gentileza');";

        String sqlConvenio = "INSERT INTO convenio "
                + "VALUES (1,'Unimed','12312312312312','Unimed Norte do Paraná','4335237777','Rua III','345','Centro','Cornélio Procópio','PR','86300000','unimed@unimednp.com.br'),"
                + "(2,'Sanepar','54354354354353','Ass Social Fundação Sanepar','4323234443','Rua Joao Padinha','123','Centro','Cornélio Procópio','PR','86300000','fundacao@sanepar.com.br'),"
                + "(3,'Fundação Copel','98450498539485','Ass Social Fundação Copel','4365432232','Rua XV de março','43','Centro','Cornélio Procópio','PR','86300000','fundacao@copel.com.br'),"
                + "(4,'SAS','90834583095480','Serviço de Atendimento do Servidor','4390809898','Avenida São Paulo','456','centro','Cornélio Procópio','PR','86300000','contato@sas.com.br'),"
                + "(5,'Particular','00000000000000','Cilas System','4388754578','','','','','AC','','');";

        String sqlConsulta = "INSERT INTO consulta "
                + "VALUES (3,'Dermatológica'),"
                + "(6,'Geriatrica'),"
                + "(1,'Ginecológica'),"
                + "(2,'Obstetrícia'),"
                + "(4,'Oftalmológica'),"
                + "(5,'Pediatrica'),"
                + "(7,'Psicológica');";
        
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sqlCargo);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sqlConselho);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sqlEspecializacao);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sqlForPagamento);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sqlConvenio);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sqlConsulta);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DbMysqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
