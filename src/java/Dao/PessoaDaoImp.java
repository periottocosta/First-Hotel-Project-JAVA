/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;

public class PessoaDaoImp implements PessoaDao {

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Pessoa pessoa = (Pessoa) obj;
        EnderecoDao enderecoDao = new EnderecoDaoImp();
        try {

            String BANCO = "INSERT INTO pessoa (cpf, rg, email, telefone, celular, sexo, nascimento, nome) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(BANCO, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, pessoa.getCpf());
            psmt.setString(2, pessoa.getRg());
            psmt.setString(3, pessoa.getEmail());
            psmt.setString(4, pessoa.getTelefone());
            psmt.setString(5, pessoa.getCelular());
            psmt.setString(6, pessoa.getSexo());
            psmt.setDate(7, new java.sql.Date(pessoa.getNascimento().getTime()));
            psmt.setString(8, pessoa.getNome());
            psmt.executeUpdate();

            rs = psmt.getGeneratedKeys();
            rs.next();
            pessoa.setId(rs.getInt(1));
            enderecoDao.salvar(pessoa);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pessoa - Erro ao tentar inserir dados "
                    + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }

        return pessoa;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Pessoa pessoa = (Pessoa) obj;
        EnderecoDao enderecoDao = new EnderecoDaoImp();
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "update pessoa set cpf = ?, rg = ?, email = ?, telefone = ?, celular = ?, sexo = ?, nascimento = ?, nome = ? where id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, pessoa.getCpf());
            psmt.setString(2, pessoa.getRg());
            psmt.setString(3, pessoa.getEmail());
            psmt.setString(4, pessoa.getTelefone());
            psmt.setString(5, pessoa.getCelular());
            psmt.setString(6, pessoa.getSexo());
            psmt.setDate(7, new java.sql.Date(pessoa.getNascimento().getTime()));
            psmt.setString(8, pessoa.getNome());
            psmt.setInt(9, pessoa.getId());
            psmt.executeUpdate();

            rs = psmt.getGeneratedKeys();
            rs.next();
            
            enderecoDao.alterar(pessoa);
        } catch (Exception e) {
        }finally{
           FabricaConexao.fechaConexao(conn, psmt, rs);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "DELETE FROM pessoa WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}