package Dao;

import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Perfil;
import entidadesDoBanco.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDaoImp implements UsuarioDao {

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Funcionario funcionario = (Funcionario) obj;
        try {
            String sql = "insert into usuario (login, senha, idPerfil) values (?, ?, ?)";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, funcionario.getUsuario().getLogin());
            psmt.setString(2, funcionario.getUsuario().getSenha());
            psmt.setInt(3, funcionario.getUsuario().getIdPerfil().getId());
            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();
            rs.next();
            int idUsuario = rs.getInt(1);
            String sql2 = "update pessoa set idUsuario = ? where id = ?";
            psmt = conn.prepareStatement(sql2);
            psmt.setInt(1, idUsuario);
            psmt.setInt(2, funcionario.getId());
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return null;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Funcionario funcionario = (Funcionario) obj;
        try {
            String banco = "update usuario set login = ?, senha = ? where id = ?";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(banco);
            psmt.setString(1, funcionario.getUsuario().getLogin());
            psmt.setString(2, funcionario.getUsuario().getSenha());
            psmt.setInt(3, funcionario.getUsuario().getId());

            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Funcionario> pesquisa(String nome) throws Exception {

        Connection conn = null;
        Funcionario funcionario;
        Usuario usuario = null;
        Perfil perfil = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList();
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select * from pessoa p  join usuario u on p.idUsuario = u.id "
                    + "join perfil pe on u.idPerfil = pe.id where nome like ?";

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + nome + "%");
            rs = psmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                usuario = new Usuario();
                perfil = new Perfil();
                // funcionario.setId(rs.getInt("id"));
                perfil.setTipo(rs.getString("pe.tipo"));
                usuario.setIdPerfil(perfil);
                usuario.setLogin(rs.getString("login"));
                usuario.setId(rs.getInt("u.id"));
                funcionario.setUsuario(usuario);
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setNome(rs.getString("p.nome"));

                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao procurar funcionario" + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcionarios;


    }

    @Override
    public Funcionario logar(String login, String senha) throws Exception {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Funcionario funcionario = new Funcionario();
        Usuario usuario = new Usuario();
        funcionario.setUsuario(usuario);
        try {
            conn = FabricaConexao.abreConexao();
            String consulta = "select p.*,u.* from pessoa p join usuario u on p.idUsuario = u.id where login = ? and senha = ?";
            psmt = conn.prepareStatement(consulta);
            psmt.setString(1, login);
            psmt.setString(2, senha);
            rs = psmt.executeQuery();

            if (rs.next()) {
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setId(rs.getInt("u.id"));
                usuario.setLogado(true);
                funcionario.setNome(rs.getString("p.nome"));
                funcionario.setEmail(rs.getString("email"));
                ultimoacesso(usuario);

            }
        } catch (Exception e) {
            System.out.println("erro ao logar usuario" + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> usuarioN(String nome) throws Exception {
        Connection conn = null;
        Funcionario funcionario;
        Usuario usuario = null;
        Perfil perfil = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList();
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select p.*,f.* from pessoa p join funcionario f on f.idPessoa = p.id where p.idUsuario is null and (nome) like ?";

//            "select f.*,p.* from funcionario f left join pessoa p "
//                    +"on f.idPessoa = p.id where (nome) like ?"
//                    +"and p.idUsuario is not null"
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + nome + "%");
            rs = psmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                usuario = new Usuario();
                perfil = new Perfil();
                funcionario.setId(rs.getInt("id"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setNome(rs.getString("p.nome"));

                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao procurar funcionario" + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcionarios;
    }

    @Override
    public void excluirUsuario(int id, int idUsuario) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = FabricaConexao.abreConexao();
            String banco = "update pessoa set idUsuario = null where id = ?";
            psmt = conn.prepareStatement(banco);
            psmt.setInt(1, id);
            psmt.executeUpdate();

            String banco2 = "delete from usuario where id = ?";
            psmt = conn.prepareStatement(banco2);
            psmt.setInt(1, idUsuario);
            psmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro a deletar usuario " + e.getMessage());
        } finally {

            FabricaConexao.fechaConexao(conn, psmt);
        }
    }

    private void ultimoacesso(Usuario usuario) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            conn = FabricaConexao.abreConexao();
            String banco = "update usuario set ultimoAcesso = now() where id = ?";
            psmt = conn.prepareStatement(banco);
            psmt.setInt(1, usuario.getId());
           

            psmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro a deletar usuario " + e.getMessage());
        } finally {

            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
    }
}