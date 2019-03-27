/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.CargoBean;
import model.bean.FuncionarioBean;
import model.bean.ProfissionalBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.DbMysqlUtil;

/**
 *
 * @author vande
 */
public abstract class FuncionarioDAO {

    /**
     * Criar um funcionário a partir no obeto de um profissional, pois o mesmo
     * extende todos os tipos da hierarquia de pessoas (pessoas <- funcionarios
     * <- profissionais).
     *
     * @param pro
     * @return
     * @throws Exception
     */
    public static boolean createFuncionario(ProfissionalBean pro) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {

            //Desligando autoCommit
            con.setAutoCommit(false);

            //Inserindo em pessoas antes de inserir em funcionários
            stmt = con.prepareStatement("insert into pessoa values (null,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pro.getNome());
            //Exemplo: new SimpleDateFormat("yyyy-MM-dd").parse("1998-01-01")
            stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(pro.getDataNascimento()));
            stmt.setString(3, pro.getSexo());
            stmt.setString(4, pro.getProfissao());
            stmt.setString(5, pro.getTelefone());
            stmt.setString(6, pro.getCelular());
            stmt.setString(7, pro.getLogradouro());
            stmt.setString(8, pro.getNumeroResidencia());
            stmt.setString(9, pro.getBairro());
            stmt.setString(10, pro.getCidade());
            stmt.setString(11, pro.getUf());
            stmt.setString(12, pro.getCep());
            stmt.setString(13, pro.getEmail());
            stmt.executeUpdate();

            //Descobrindo indice da operação anterior
            int index = DbMysqlUtil.getValueIndex(stmt.getGeneratedKeys());

            //Inserindo em funcionários, após ter inserido em pessoas
            stmt = con.prepareStatement("insert into funcionario values (?, ?, ?, ?)");
            stmt.setInt(1, index);
            stmt.setString(2, pro.getLogin());
            stmt.setString(3, pro.getSenha());
            stmt.setInt(4, pro.getCargo().getCodigoCargo());
            stmt.executeUpdate();

            //Inserindo em profissionais, se o funcionário for do tipo Profissional realizante.
            if (pro.getCargo().getDescricao().equals("Profissional realizante")) {
                stmt = con.prepareStatement("insert into profissional values (?,?,?,?,?)");
                stmt.setInt(1, index);
                stmt.setInt(2, pro.getConselho().getConselhoCodigo());
                stmt.setInt(3, pro.getNumConselho());
                stmt.setString(4, pro.getUfConselho());
                stmt.setInt(5, pro.getEspecializacao().getEspecializacaoCodigo());
                stmt.executeUpdate();
            }

            //Dando commit manualmente
            con.commit();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        return true;
    }

    /**
     * Retorna funcionários do banco de dados.
     *
     * @return
     * @throws Exception
     */
    public static List<FuncionarioBean> readFuncionarios() throws Exception {
        List<FuncionarioBean> funcionarios = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from funcionario");
            rs = stmt.executeQuery();
            while (rs.next()) {
                FuncionarioBean fun = new FuncionarioBean(
                        rs.getInt("funcionarioCodigo"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        new CargoBean(rs.getInt("cargoCodigo")));
                funcionarios.add(fun);
            }
            return funcionarios;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna um funcionário a partir dos dados de um objeto do tipo
     * funcionário.
     *
     * @param fun
     * @return
     * @throws Exception
     */
    public static FuncionarioBean searchFuncionarioByObject(FuncionarioBean fun) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FuncionarioBean resultado = null;
        try {
            stmt = con.prepareStatement("select * from pessoa, funcionario "
                    + "where pessoa.pessoaCodigo = funcionario.funcionarioCodigo "
                    + "and login like ? and senha like ?");
            stmt.setString(1, "%" + fun.getLogin() + "%");
            stmt.setString(2, "%" + fun.getSenha() + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                resultado = new FuncionarioBean(
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
                        rs.getString("login"),
                        rs.getString("senha"),
                        new CargoBean(rs.getInt("cargoCodigo"))
                );
            }
            return resultado;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    public static List<FuncionarioBean> searchFuncionarioByNome(String nome) throws Exception {
        List<FuncionarioBean> funcionarios = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from pessoa, funcionario "
                    + "where pessoa.pessoaCodigo = funcionario.funcionarioCodigo "
                    + "and nome like ?"
                    + " order by nome ASC");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                FuncionarioBean fun = new FuncionarioBean(
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
                        rs.getString("login"),
                        rs.getString("senha"),
                        new CargoBean(rs.getInt("cargoCodigo"))
                );
                funcionarios.add(fun);
            }
            return funcionarios;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procura funcionário a partir do código do cargo.
     *
     * @param cargo
     * @return
     * @throws Exception
     */
    public static List<FuncionarioBean> searchFuncionariosByCargo(int cargo) throws Exception {
        List<FuncionarioBean> funcionarios = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from funcionario where cargoCodigo = ?");
            stmt.setInt(1, cargo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                FuncionarioBean fun = new FuncionarioBean(
                        rs.getInt("funcionarioCodigo"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        new CargoBean(rs.getInt("cargoCodigo")));
                funcionarios.add(fun);
            }
            return funcionarios;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro durante a consulta de funcionário(a)");
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procura o código do funcionário a partir do login.
     *
     * @param login
     * @return
     * @throws Exception
     */
    public static int searchCodigoFunByLogin(String login) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from funcionario where login like ?");
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt("funcionarioCodigo");
            }
            return 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro durante a consulta de funcionário(a)");
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procurar senha do funcionário a partir do login.
     *
     * @param login
     * @return
     * @throws Exception
     */
    public static String searchSenhaFunByLogin(String login) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from funcionario where login like ?");
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("senha");
            }
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro durante a consulta de funcionário(a)");
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Deleta um funcionário a partir de um objeto de funcionário.
     *
     * @param fun
     * @return
     * @throws Exception
     */
    public static boolean deleteFuncionarioByObject(FuncionarioBean fun) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from funcionario where funcionarioCodigo = ?");
            stmt.setInt(1, fun.getFuncionarioCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Deletar funcionário a partir do login do mesmo.
     *
     * @param login
     * @return
     * @throws Exception
     */
    public static boolean deleteFuncionarioByLogin(String login) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        int codigoPessoa = searchCodigoFunByLogin(login);
        try {
            stmt = con.prepareStatement("delete from funcionario where login like ?");
            stmt.setString(1, login);
            stmt.executeUpdate();
            //PessoaDAO.deletePessoaById(codigoPessoa);
            return true;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        return false;
    }

    /**
     * Atualiza dados de um funcionário a partir de um objeto de funcionário.
     *
     * @param fun
     * @return
     * @throws Exception
     */
    public static boolean updateFuncionario(FuncionarioBean fun) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update funcionario set senha = ?, cargoCodigo = ? where login = ?");
            stmt.setString(1, fun.getSenha());
            stmt.setInt(2, fun.getCargo().getCodigoCargo());
            stmt.setString(3, fun.getLogin());
            stmt.executeUpdate();
            fun.setPessoaCodigo(FuncionarioDAO.searchCodigoFunByLogin(fun.getLogin()));
            PessoaDAO.updatePessoaByObject(fun);
            return true;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        return false;
    }

}
