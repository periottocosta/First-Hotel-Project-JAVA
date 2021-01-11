/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Funcao;
import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncaoDaoImp implements FuncaoDao{
    
    @Override
    public Object salvar(Object obj) throws Exception {
       Connection conn = null;
        PreparedStatement psmt = null;
        Funcao funcao = (Funcao) obj;
        try {
            String sql = "insert into funcao (nomee, descricao) values (?, ?)";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, funcao.getNome());
            psmt.setString(2, funcao.getDescricao());
            psmt.executeUpdate();
        } catch (Exception e) {
        }finally{
            FabricaConexao.fechaConexao(conn, psmt);
          }
        return null;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List listar() throws Exception {

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List funcoes = new ArrayList();
        Funcao funcao = null;

        try {
            conn = FabricaConexao.abreConexao();
            String BANCO = "SELECT * FROM funcao";
            psmt = conn.prepareStatement(BANCO);
            rs = psmt.executeQuery();
            while ((rs.next())) {
                funcao = new Funcao();
                funcao.setId(rs.getInt("id"));
                funcao.setNome(rs.getString("nomee"));
                funcao.setDescricao(rs.getString("descricao"));
                funcoes.add(funcao);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcoes;
    }

    @Override
    public List<Funcionario> pesquisa(int funcao) throws Exception {
       Connection conn = null;
        Funcionario funcionario;
        Turno turno;
        Funcao funcao1;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Funcionario> funcoes = new ArrayList();
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select f.*,p.*,t.*,fu.* from funcionario f "
                    + "join pessoa p on f.idPessoa = p.id join turno t on f.idTurno = t.id join funcao fu on "
                    +"f.idFuncao = fu.id where fu.id = ? ";//"join turno t on f.idTurno = t.id
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, funcao);
            rs = psmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcao1 = new Funcao();
                turno = new Turno();
                funcao1.setNome(rs.getString("nomee"));
                funcao1.setId(rs.getInt("fu.id"));
                funcionario.setIdFuncao(funcao1);
                turno.setId(rs.getInt("t.id"));
                turno.setPerfio(rs.getString("perfiodo"));
                funcionario.setIdTurno(turno);
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
               funcoes.add(funcionario);
            }
        } catch (Exception e) {
        } finally {

            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcoes;

    }
}