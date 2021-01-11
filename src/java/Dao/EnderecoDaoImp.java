/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Endereco;
import entidadesDoBanco.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class EnderecoDaoImp implements EnderecoDao {

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Pessoa pessoa = (Pessoa) obj;

        try {
            String BANCO = "INSERT INTO endereco(rua, bairro, cidade, estado, sigla, pais, cep, idPessoa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(BANCO);
            psmt.setString(1, pessoa.getIdEndereco().getRua());
            psmt.setString(2, pessoa.getIdEndereco().getBairro());
            psmt.setString(3, pessoa.getIdEndereco().getCidade());
            psmt.setString(4, pessoa.getIdEndereco().getEstado());
            psmt.setString(5, pessoa.getIdEndereco().getSigla());
            psmt.setString(6, pessoa.getIdEndereco().getPais());
            psmt.setString(7, pessoa.getIdEndereco().getCep());
            psmt.setInt(8, pessoa.getId());
            psmt.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar endereco " + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
        return null;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Pessoa pessoa = (Pessoa) obj;
        try {
            String sql = "update endereo set rua = ?, bairro = ?, cidade = ?, estado = ?, sigla = ?, pais = ?, cep = ? where idPessoa = ?";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, pessoa.getIdEndereco().getRua());
            psmt.setString(2, pessoa.getIdEndereco().getBairro());
            psmt.setString(3, pessoa.getIdEndereco().getCidade());
            psmt.setString(4, pessoa.getIdEndereco().getEstado());
            psmt.setString(5, pessoa.getIdEndereco().getSigla());
            psmt.setString(6, pessoa.getIdEndereco().getPais());
            psmt.setString(7, pessoa.getIdEndereco().getCep());
            psmt.setInt(8, pessoa.getId());
            psmt.executeUpdate();
        } catch (Exception e) {
        }finally{
          FabricaConexao.fechaConexao(conn, psmt);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Endereco endereco = null;
        return null;
    }

    @Override
    public List listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
