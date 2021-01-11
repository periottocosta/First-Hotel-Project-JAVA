/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Perfil;
import entidadesDoBanco.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PerfilDaoImp implements PerfilDao {

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Perfil perfil = (Perfil) obj;
        try {
            String sql = "insert into perfil (tipo, descricao) values (?, ?)";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, perfil.getTipo());
            psmt.setString(2, perfil.getDescricao());
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
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
        List perfis = new ArrayList();
        Perfil perfil = null;

        try {
            conn = FabricaConexao.abreConexao();
            String BANCO = "SELECT * FROM perfil";
            psmt = conn.prepareStatement(BANCO);
            rs = psmt.executeQuery();
            while ((rs.next())) {
                perfil = new Perfil();
                perfil.setId(rs.getInt("id"));
                perfil.setTipo(rs.getString("tipo"));
                perfil.setDescricao(rs.getString("descricao"));
                perfis.add(perfil);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return perfis;
    }

    @Override
    public List listarPesq() throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Funcionario> perfis = new ArrayList();
        Perfil perfil = null;
        Usuario usuario;
        Funcionario funcionario;
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select p.*,u.*,pe.* from pessoa p  join usuario u on p.idUsuario = u.id "
                    + " join perfil pe on u.idPerfil = pe.id";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while ((rs.next())) {
                funcionario = new Funcionario();
                usuario = new Usuario();
                perfil = new Perfil();             
                perfil.setTipo(rs.getString("pe.tipo"));
                usuario.setIdPerfil(perfil);
                usuario.setLogin(rs.getString("login"));
                usuario.setId(rs.getInt("u.id"));
                funcionario.setUsuario(usuario);
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setNome(rs.getString("p.nome"));
                perfis.add(funcionario);

            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
          return  perfis;
    }
}