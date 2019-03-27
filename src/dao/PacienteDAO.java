/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.PacienteBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import util.DbMysqlUtil;
import util.MaskFormatTextUtil;

/**
 *
 * @author vande
 */
public abstract class PacienteDAO {

    public static boolean createPaciente(PacienteBean pac) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {

            //Desligando autoCommit
            con.setAutoCommit(false);

            //Inserindo em pessoas antes de inserir em pacientes.
            stmt = con.prepareStatement("insert into pessoa values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pac.getNome());
            //Exemplo: new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01")
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(pac.getDataNascimento()));
            stmt.setString(3, pac.getSexo());
            stmt.setString(4, pac.getProfissao());
            stmt.setString(5, pac.getTelefone());
            stmt.setString(6, pac.getCelular());
            stmt.setString(7, pac.getLogradouro());
            stmt.setString(8, pac.getNumeroResidencia());
            stmt.setString(9, pac.getBairro());
            stmt.setString(10, pac.getCidade());
            stmt.setString(11, pac.getUf());
            stmt.setString(12, pac.getCep());
            stmt.setString(13, pac.getEmail());
            stmt.executeUpdate();

            //Descobrindo indice da operação anterior
            int index = DbMysqlUtil.getValueIndex(stmt.getGeneratedKeys());

            //Inserindo em pacientes, após ter inserido em pessoas
            stmt = con.prepareStatement("insert into paciente values (?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, index);
            stmt.setDouble(2, pac.getAltura());
            stmt.setDouble(3, pac.getPeso());
            stmt.setString(4, pac.getCirurgia());
            stmt.setString(5, pac.getGestacao());
            stmt.setString(6, pac.getAlergia());
            stmt.setString(7, pac.getPressaoArterial());
            stmt.setString(8, pac.getTipoSanguineo());
            stmt.setString(9, pac.getDeficiencia());
            if(pac.getDum() != null){
                stmt.setString(10, new SimpleDateFormat("yyyy-MM-dd").format(pac.getDum()));
            } else {
                stmt.setString(10, null);
            }
            stmt.setString(11, pac.getObservacao());
            stmt.executeUpdate();

            //Dando commit manualmente
            con.commit();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }
    
    public static List<PacienteBean> searchPacienteByNome(String nome) throws Exception {
        List<PacienteBean> pacientes = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa, paciente "
                    + "where nome like ? "
                    + "and pessoa.pessoaCodigo = paciente.pacienteCodigo"
                    + " order by nome ASC");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                PacienteBean pac = new PacienteBean(
                        rs.getInt("pessoaCodigo"),
                        rs.getString("nome"),
                        rs.getDate("dataNascimento"),
                        rs.getString("sexo"),
                        rs.getString("profissao"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("logradouro"),
                        rs.getString("numeroResidencia"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("email"),
                        rs.getDouble("altura"),
                        rs.getDouble("peso"),
                        rs.getString("cirurgia"),
                        rs.getString("gestacao"),
                        rs.getString("alergia"),
                        rs.getString("pressaoArterial"),
                        rs.getString("tipoSanguineo"),
                        rs.getString("deficiencia"),
                        rs.getDate("dum"),
                        rs.getString("observacao")
                );
                pacientes.add(pac);
            }
            return pacientes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }
    
    public static PacienteBean searchPacienteByCodigo(int pacienteCodigo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa, paciente "
                    + "where pacienteCodigo = ? "
                    + "and pessoa.pessoaCodigo = paciente.pacienteCodigo"
                    + " order by nome ASC");
            stmt.setInt(1, pacienteCodigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return new PacienteBean(
                        rs.getInt("pessoaCodigo"),
                        rs.getString("nome"),
                        rs.getDate("dataNascimento"),
                        rs.getString("sexo"),
                        rs.getString("profissao"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("logradouro"),
                        rs.getString("numeroResidencia"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("email"),
                        rs.getDouble("altura"),
                        rs.getDouble("peso"),
                        rs.getString("cirurgia"),
                        rs.getString("gestacao"),
                        rs.getString("alergia"),
                        rs.getString("pressaoArterial"),
                        rs.getString("tipoSanguineo"),
                        rs.getString("deficiencia"),
                        rs.getDate("dum"),
                        rs.getString("observacao")
                );
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    public static List<PacienteBean> readPacientes() throws Exception {
        List<PacienteBean> pacientes = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa as pes, paciente as pac where pessoaCodigo = pacienteCodigo order by nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                PacienteBean pac = new PacienteBean(
                        rs.getInt("pessoaCodigo"),
                        rs.getString("nome"),
                        rs.getDate("dataNascimento"),
                        rs.getString("sexo"),
                        rs.getString("profissao"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("logradouro"),
                        rs.getString("numeroResidencia"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("email"),
                        rs.getDouble("altura"),
                        rs.getDouble("peso"),
                        rs.getString("cirurgia"),
                        rs.getString("gestacao"),
                        rs.getString("alergia"),
                        rs.getString("pressaoArterial"),
                        rs.getString("tipoSanguineo"),
                        rs.getString("deficiencia"),
                        rs.getDate("dum"),
                        rs.getString("observacao")
                );
                pacientes.add(pac);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return pacientes;
    }
    
    public static boolean updatePaciente(PacienteBean pac) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("update paciente "
                    + "set altura = ?, "
                    + "peso = ?, "
                    + "cirurgia = ?, "
                    + "gestacao = ?, "
                    + "alergia = ?, "
                    + "pressaoArterial = ?, "
                    + "tipoSanguineo = ?, "
                    + "deficiencia = ?, "
                    + "dum = ?, "
                    + "observacao = ? "
                    + "where pacienteCodigo = ?");
            stmt.setDouble(1, pac.getAltura());
            stmt.setDouble(2, pac.getPeso());
            stmt.setString(3, pac.getCirurgia());
            stmt.setString(4, pac.getGestacao());
            stmt.setString(5, pac.getAlergia());
            stmt.setString(6, pac.getPressaoArterial());
            stmt.setString(7, pac.getTipoSanguineo());
            stmt.setString(8, pac.getDeficiencia());
            if(pac.getDum() != null){
                stmt.setString(9, MaskFormatTextUtil.dataUs(pac.getDum()));
            } else {
                stmt.setString(9, null);
            }
            stmt.setString(10, pac.getObservacao());
            stmt.setInt(11, pac.getPacienteCodigo());
            stmt.executeUpdate();
            PessoaDAO.updatePessoaByObject(pac);
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    public static boolean deletePaciente(int pacienteCodigo) throws Exception{
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("delete from paciente where pacienteCodigo = ?");
            stmt.setInt(1, pacienteCodigo);
            stmt.executeUpdate();
            PessoaDAO.deletePessoaById(pacienteCodigo);
            return true;
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

}
