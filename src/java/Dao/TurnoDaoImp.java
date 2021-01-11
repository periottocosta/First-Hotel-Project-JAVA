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

public class TurnoDaoImp implements TurnoDao {

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Turno turno = (Turno) obj;
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "insert into turno (perfiodo, descricao) values (?, ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, turno.getPerfio());
            psmt.setString(2, turno.getDescricao());
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
        return turno;
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

        List turnos = new ArrayList();
        Turno turno = null;

        try {
            conn = FabricaConexao.abreConexao();
            String BANCO = "SELECT * FROM turno";
            psmt = conn.prepareStatement(BANCO);
            rs = psmt.executeQuery();
            while ((rs.next())) {
                turno = new Turno();
                turno.setId(rs.getInt("id"));
                turno.setPerfio(rs.getString("perfiodo"));
                turno.setDescricao(rs.getString("descricao"));

                turnos.add(turno);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return turnos;
    }

    @Override
    public List<Funcionario> pesquisa(int turno) throws Exception {
        Connection conn = null;
        Funcionario funcionario;
        Turno turno1;
        Funcao funcao;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Funcionario> turnos = new ArrayList();
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select f.*,p.*,t.*,fu.* from funcionario f "
                    + "join pessoa p on f.idPessoa = p.id join turno t on f.idTurno = t.id join funcao fu on f.idFuncao = fu.id where t.id = ? ";//"join turno t on f.idTurno = t.id
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, turno);
            rs = psmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcao = new Funcao();
                turno1 = new Turno();
                funcao.setNome(rs.getString("nomee"));
                funcao.setId(rs.getInt("fu.id"));
                funcionario.setIdFuncao(funcao);
                turno1.setId(rs.getInt("t.id"));
                turno1.setPerfio(rs.getString("perfiodo"));
                funcionario.setIdTurno(turno1);
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                turnos.add(funcionario);
            }
        } catch (Exception e) {
        } finally {

            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return turnos;

    }

    @Override
    public void excluirTruno(int id, int idTurno, String mens) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;
        Turno turno;
        try {
            conn = FabricaConexao.abreConexao();
            String banco = "select f.idTurno, t.id from funcionario f join turno t  on t.id = f.idTurno where t.id = ?";
            psmt = conn.prepareStatement(banco);
            psmt.setInt(1, idTurno);
           rs = psmt.executeQuery();
            if (rs.next()) {
                turno = new Turno();
                funcionario = new Funcionario();
                turno.setId(rs.getInt("t.id"));
                funcionario.setIdTurno(turno);
                if (funcionario.getIdTurno() != null) {
//                   mens = "Existe um ou mais funcionarios nesse turno \n para excluir um turno não pode ter nenhum \n funcionario cadastrado no mesmo";
                } else {
                    String banco2 = "delete from turno where id = ?";
                    psmt = conn.prepareStatement(banco);
                    psmt.setInt(1, idTurno);
                    psmt.executeUpdate();
                }
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
    }
}
