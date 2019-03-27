/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.PessoaBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author vande
 */
public abstract class PessoaDAO {

    /**
     * Método cria no banco uma pessoa.
     *
     * @param pes
     * @return true or false.
     * @throws java.lang.Exception
     */
    public static boolean createPessoa(PessoaBean pes) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into pessoa values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, pes.getNome());
            //Exemplo: new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01")
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(pes.getDataNascimento()));
            stmt.setString(3, pes.getSexo());
            stmt.setString(4, pes.getProfissao());
            stmt.setString(5, pes.getTelefone());
            stmt.setString(6, pes.getCelular());
            stmt.setString(7, pes.getLogradouro());
            stmt.setString(8, pes.getNumeroResidencia());
            stmt.setString(9, pes.getBairro());
            stmt.setString(10, pes.getCidade());
            stmt.setString(11, pes.getUf());
            stmt.setString(12, pes.getCep());
            stmt.setString(13, pes.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
            //throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Cria uma pessoa no banco e retorna um objeto do mesmo já com o código da
     * tupla gerado pelo banco.
     *
     * @param pes
     * @return PessoaBean
     * @throws java.lang.Exception
     */
    public static PessoaBean createPessoaReturnObject(PessoaBean pes) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into pessoa values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, pes.getNome());
            //Exemplo: new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01")
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(pes.getDataNascimento()));
            stmt.setString(3, pes.getSexo());
            stmt.setString(4, pes.getProfissao());
            stmt.setString(5, pes.getTelefone());
            stmt.setString(6, pes.getCelular());
            stmt.setString(7, pes.getLogradouro());
            stmt.setString(8, pes.getNumeroResidencia());
            stmt.setString(9, pes.getBairro());
            stmt.setString(10, pes.getCidade());
            stmt.setString(11, pes.getUf());
            stmt.setString(12, pes.getCep());
            stmt.setString(13, pes.getEmail());
            stmt.executeUpdate();
            return searchPessoaByObject(pes);
        } catch (SQLException ex) {
            //return null;
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Retorna uma lista de todos as pessoas cadastradas no banco de dados.
     *
     * @return Lista de objeto PessoaBean.
     * @throws java.lang.Exception
     */
    public static List<PessoaBean> readPessoas() throws Exception {
        List<PessoaBean> pessoas = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa order by nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                PessoaBean pes = new PessoaBean(
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
                        rs.getString("email")
                );
                pessoas.add(pes);
            }
            return pessoas;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro durante a consulta de pessoas");
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna uma lista de pessoas do banco de dados de acordo com a String
     * passada.
     *
     * @param nome
     * @return Lista de objeto PessoaBean.
     * @throws java.lang.Exception
     */
    public static List<PessoaBean> searchPessoasByName(String nome) throws Exception {
        List<PessoaBean> pessoas = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa where nome like ? order by nome ASC");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                PessoaBean pes = new PessoaBean(
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
                        rs.getString("email")
                );
                pessoas.add(pes);
            }
            return pessoas;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro durante a pesquisa de pessoas pelo nome.");
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna uma pessoa do banco de dados de acordo com o código passado.
     *
     * @param codigo
     * @return PessoaBean
     * @throws java.lang.Exception
     */
    public static PessoaBean searchPessoaById(int codigo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa where pessoaCodigo like ?");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                PessoaBean pes = new PessoaBean(
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
                        rs.getString("email")
                );
                return pes;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro durante a pesquisa de pessoas pelo código.");
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }

    /**
     * Busca no banco de dados uma pessoa a partir de um objeto PessoaBean pelo
 paramêtro.
     *
     * @param pes
     * @return PessoaBean
     * @throws java.lang.Exception
     */
    public static PessoaBean searchPessoaByObject(PessoaBean pes) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa "
                    + "where nome like ? "
                    + "and dataNascimento like ? "
                    + "and sexo like ? "
                    + "and profissao like ? "
                    + "and telefone like ? "
                    + "and celular like ? "
                    + "and logradouro like ? "
                    + "and numeroResidencia like ? "
                    + "and bairro like ? "
                    + "and cidade like ? "
                    + "and uf like ? "
                    + "and cep like ? "
                    + "and email like ?");
            stmt.setString(1, "%" + pes.getNome() + "%");
            //Exemplo: new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01")
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(pes.getDataNascimento()));
            stmt.setString(3, pes.getSexo());
            stmt.setString(4, pes.getProfissao());
            stmt.setString(5, pes.getTelefone());
            stmt.setString(6, pes.getCelular());
            stmt.setString(7, "%" + pes.getLogradouro() + "%");
            stmt.setString(8, "%" + pes.getNumeroResidencia() + "%");
            stmt.setString(9, "%" + pes.getBairro() + "%");
            stmt.setString(10, pes.getCidade());
            stmt.setString(11, pes.getUf());
            stmt.setString(12, pes.getCep());
            stmt.setString(13, pes.getEmail());
            rs = stmt.executeQuery();
            while (rs.next()) {
                return new PessoaBean(
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
                        rs.getString("email")
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return null;
    }

    /**
     * Deleta uma pessoa do banco de dados.
     *
     * @param codigo
     * @return true or false.
     * @throws java.lang.Exception
     */
    public static boolean deletePessoaById(int codigo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from pessoa where pessoaCodigo = ?");
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
            //throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Atualiza dados de uma pessoa no banco de dados.
     *
     * @param pes
     * @return true or false.
     * @throws java.lang.Exception
     */
    public static boolean updatePessoaByObject(PessoaBean pes) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE pessoa SET "
                    + "nome = ?, "
                    + "dataNascimento = ?, "
                    + "sexo = ?, "
                    + "profissao = ?, "
                    + "telefone = ?, "
                    + "celular = ?, "
                    + "logradouro = ?, "
                    + "numeroResidencia = ?, "
                    + "bairro = ?, "
                    + "cidade = ?, "
                    + "uf = ?, "
                    + "cep = ?, "
                    + "email = ? WHERE pessoaCodigo = ?");
            stmt.setString(1, pes.getNome());
            //Exemplo: new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01")
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(pes.getDataNascimento()));
            stmt.setString(3, pes.getSexo());
            stmt.setString(4, pes.getProfissao());
            stmt.setString(5, pes.getTelefone());
            stmt.setString(6, pes.getCelular());
            stmt.setString(7, pes.getLogradouro());
            stmt.setString(8, pes.getNumeroResidencia());
            stmt.setString(9, pes.getBairro());
            stmt.setString(10, pes.getCidade());
            stmt.setString(11, pes.getUf());
            stmt.setString(12, pes.getCep());
            stmt.setString(13, pes.getEmail());
            stmt.setInt(14, pes.getPessoaCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        //return false;
    }

}
