/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.bancodedados.ConectaBanco;
import model.bean.CargoBean;
import model.bean.ConselhoRegionalBean;
import model.bean.EspecializacaoBean;
import model.bean.ProfissionalBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import util.DbMysqlUtil;

/**
 *
 * @author vande
 */
public abstract class ProfissionalDAO {

    public static boolean createProfissional(ProfissionalBean pro) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            con.setAutoCommit(false);

            //Inserindo em pessoas
            stmt = con.prepareStatement("insert into pessoa values (null,?,null,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pro.getNome());
            stmt.setString(2, pro.getSexo());
            stmt.setString(3, pro.getProfissao());
            stmt.setString(4, pro.getTelefone());
            stmt.setString(5, pro.getCelular());
            stmt.setString(6, pro.getLogradouro());
            stmt.setString(7, pro.getNumeroResidencia());
            stmt.setString(8, pro.getBairro());
            stmt.setString(9, pro.getCidade());
            stmt.setString(10, pro.getUf());
            stmt.setString(11, pro.getCep());
            stmt.setString(12, pro.getEmail());
            stmt.executeUpdate();

            //Descobrindo indice da operação anterior
            int index = DbMysqlUtil.getValueIndex(stmt.getGeneratedKeys());

            System.out.println(index);

            //Inserindo em profissionais
            stmt = con.prepareStatement("insert into profissional values (?,?,?,?,?)");
            stmt.setInt(1, index);
            stmt.setInt(2, pro.getConselho().getConselhoCodigo());
            stmt.setInt(3, pro.getNumConselho());
            stmt.setString(4, pro.getUfConselho());
            stmt.setInt(5, pro.getEspecializacao().getEspecializacaoCodigo());
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

    /**
     * Quando muda o cargo de um funconário para profissional realizante,
     * utiliza-se está função para inserir o funcionário na tabela de
     * profissionais.
     *
     * @param pro
     * @return
     * @throws Exception
     */
    public static boolean createProfissionalRealizante(ProfissionalBean pro) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into profissional values (?,?,?,?,?)");
            stmt.setInt(1, pro.getPessoaCodigo());
            stmt.setInt(2, pro.getConselho().getConselhoCodigo());
            stmt.setInt(3, pro.getNumConselho());
            stmt.setString(4, pro.getUfConselho());
            stmt.setInt(5, pro.getEspecializacao().getEspecializacaoCodigo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Retorna todos os médicos realizantes.
     *
     * @return
     * @throws java.lang.Exception
     */
    public static List<ProfissionalBean> readProfissionalRealizantes() throws Exception {
        List<ProfissionalBean> profissionais = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select profissionalCodigo, nome, dataNascimento, sexo, profissao, telefone, celular, logradouro, \n"
                    + "numeroResidencia, bairro, cidade, uf, cep, email, login, senha, cargo.cargoCodigo, conselhoCodigo, \n"
                    + "numConselho, ufConselho, especializacaoCodigo \n"
                    + "from pessoa, funcionario, profissional, cargo \n"
                    + "where pessoaCodigo = funcionarioCodigo \n"
                    + "and funcionarioCodigo = profissionalCodigo \n"
                    + "and cargo.cargoCodigo = funcionario.cargoCodigo \n"
                    + "and cargo.descricao like 'Profissional realizante' \n"
                    + "order by nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProfissionalBean pro = new ProfissionalBean(
                        rs.getInt("profissionalCodigo"),
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
                        new CargoBean(rs.getInt("cargoCodigo")),
                        new ConselhoRegionalBean(rs.getInt("conselhoCodigo")),
                        rs.getInt("numConselho"),
                        rs.getString("ufConselho"),
                        new EspecializacaoBean(rs.getInt("especializacaoCodigo"))
                );
                profissionais.add(pro);
            }
            return profissionais;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Retorna todos os tipos de médicos, realizantes ou solicitantes, de acordo
     * com valor da busca.
     *
     * @param nome
     * @return
     * @throws Exception
     */
    public static List<ProfissionalBean> searchProfissionalByName(String nome) throws Exception {
        List<ProfissionalBean> profissionais = new ArrayList<>();
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select *\n"
                    + "from pessoa as pe, profissional as pr, especializacao as e, conselhoregional as co\n"
                    + "where pe.pessoaCodigo = pr.profissionalCodigo\n"
                    + "and e.especializacaoCodigo = pr.especializacaoCodigo \n"
                    + "and co.conselhoCodigo = pr.conselhoCodigo\n"
                    + "and nome like ?\n"
                    + "order by nome ASC");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProfissionalBean pro = new ProfissionalBean(
                        rs.getInt("profissionalCodigo"),
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
                        "",
                        "",
                        null,
                        new ConselhoRegionalBean(rs.getInt("conselhoCodigo")),
                        rs.getInt("numConselho"),
                        rs.getString("ufConselho"),
                        new EspecializacaoBean(rs.getInt("especializacaoCodigo"))
                );
                profissionais.add(pro);
            }
            return profissionais;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Procura profissional a partir dos dados do conselho regional ao que
     * pertence.
     *
     * @param conselho
     * @param numConselho
     * @param Uf
     * @return
     * @throws Exception
     */
    public static ProfissionalBean searchProfissionalByConselho(String conselho, int numConselho, String Uf) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * \n"
                    + "from pessoa as pe, profissional as pr, especializacao as e, conselhoregional as co \n"
                    + "where pe.pessoaCodigo = pr.profissionalCodigo \n "
                    + "and e.especializacaoCodigo = pr.especializacaoCodigo \n"
                    + "and co.conselhoCodigo = pr.conselhoCodigo \n"
                    + "and co.descricao like ? \n"
                    + "and pr.ufConselho like ? \n"
                    + "and pr.numConselho = ?");
            stmt.setString(1, conselho);
            stmt.setString(2, Uf);
            stmt.setInt(3, numConselho);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return new ProfissionalBean(
                        rs.getInt("profissionalCodigo"),
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
                        "",
                        "",
                        null,
                        new ConselhoRegionalBean(rs.getInt("conselhoCodigo")),
                        rs.getInt("numConselho"),
                        rs.getString("ufConselho"),
                        new EspecializacaoBean(rs.getInt("especializacaoCodigo"))
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
     * Procura atraves do código, se o profissional existe.
     *
     * @param profissionalCodigo
     * @return
     * @throws Exception
     */
    public static boolean ifExistProfissional(int profissionalCodigo) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from profissional where profissionalCodigo = ?");
            stmt.setInt(1, profissionalCodigo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt, rs);
        }
        return false;
    }

    /**
     * Atualiza dados de um profissional.
     *
     * @param pro
     * @return
     * @throws Exception
     */
    public static boolean updateProfissional(ProfissionalBean pro) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        try {
            pro.setPessoaCodigo(ProfissionalDAO.searchProfissionalByConselho(
                    pro.getOldInf()[0],
                    Integer.parseInt(pro.getOldInf()[1]),
                    pro.getOldInf()[2]).getPessoaCodigo()
            );
            pro.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse("00-00-0000"));
            stmt = con.prepareStatement("update profissional set conselhoCodigo = ?, numConselho = ?, ufConselho = ?, especializacaoCodigo = ? where profissionalCodigo = ?");
            stmt.setInt(1, pro.getConselho().getConselhoCodigo());
            stmt.setInt(2, pro.getNumConselho());
            stmt.setString(3, pro.getUfConselho());
            stmt.setInt(4, pro.getEspecializacao().getEspecializacaoCodigo());
            stmt.setInt(5, pro.getPessoaCodigo());
            stmt.executeUpdate();
            PessoaDAO.updatePessoaByObject(pro);
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
    }

    /**
     * Deletar profissional a partir dos atributos conselho.descricao,
     * numConselho e ufConselho.
     *
     * @param login
     * @return
     * @throws Exception
     */
    public static boolean deleteFuncionarioByLogin(String conselho, String numConselho, String ufConselho) throws Exception {
        Connection con = ConectaBanco.getConnection();
        PreparedStatement stmt = null;
        int codigoProfissional = ProfissionalDAO.searchProfissionalByConselho(
                conselho,
                Integer.parseInt(numConselho),
                ufConselho).getPessoaCodigo();
        try {
            stmt = con.prepareStatement("delete from profissional where profissionalCodigo like ?");
            stmt.setInt(1, codigoProfissional);
            stmt.executeUpdate();
            //PessoaDAO.deletePessoaById(codigoProfissional);
            return true;
        } catch (SQLException ex) {
            new RuntimeException(ex);
        } finally {
            ConectaBanco.closeConnection(con, stmt);
        }
        return false;
    }

}
