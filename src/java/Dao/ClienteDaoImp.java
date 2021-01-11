/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Cliente;
import entidadesDoBanco.Endereco;
import entidadesDoBanco.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ClienteDaoImp implements ClienteDao {

    private PessoaDao pDao;

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        pDao = new PessoaDaoImp();
        Cliente cliente = null;

        try {

            cliente = (Cliente) pDao.salvar(obj);
            conn = FabricaConexao.abreConexao();
            String BANCO = "INSERT INTO cliente(codigo, depedentes, idPessoa) VALUES(?, ?, ?)";
            psmt = conn.prepareStatement(BANCO);

            psmt.setString(1, cliente.getCodigo());
            psmt.setInt(2, cliente.getDepedentes());
            psmt.setInt(3, cliente.getId());

            psmt.executeUpdate();

        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
        return cliente;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Cliente cliente = (Cliente) (Pessoa) obj;
        
        try {
            conn = FabricaConexao.abreConexao();
              String sql1 = "update pessoa set cpf = ?, rg = ?, email = ?, telefone = ?, celular = ?, sexo = ?, "
                    + "nascimento = ?, nome = ? where id = ?";
            psmt = conn.prepareStatement(sql1);
            psmt.setString(1, cliente.getCpf());
            psmt.setString(2, cliente.getRg());
            psmt.setString(3, cliente.getEmail());
            psmt.setString(4,cliente.getTelefone());
            psmt.setString(5, cliente.getCelular());
            psmt.setString(6,cliente.getSexo());
            psmt.setDate(7, new java.sql.Date(cliente.getNascimento().getTime()));
            psmt.setString(8,cliente.getNome());
            psmt.setInt(9, cliente.getId());
            psmt.executeUpdate();
            ////CLIENTE
            String sql = "update cliente set codigo = ?, depedentes = ? where idPessoa = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, cliente.getCodigo());
            psmt.setInt(2, cliente.getDepedentes());
            psmt.setInt(3, cliente.getId());
            psmt.executeUpdate();
            //////ENDEREÇO
              String sql3 = "update endereco "
                      + "set rua = ?, bairro = ?, cidade = ?, estado = ?, sigla = ?, pais = ?, cep = ?, idPessoa = ? where id = ? ";
           
            psmt = conn.prepareStatement(sql3);
            psmt.setString(1, cliente.getIdEndereco().getRua());
            psmt.setString(2, cliente.getIdEndereco().getBairro());
            psmt.setString(3, cliente.getIdEndereco().getCidade());
            psmt.setString(4, cliente.getIdEndereco().getEstado());
            psmt.setString(5, cliente.getIdEndereco().getSigla());
            psmt.setString(6, cliente.getIdEndereco().getPais());
            psmt.setString(7, cliente.getIdEndereco().getCep());
            psmt.setInt(8, cliente.getIdEndereco().getId());
            psmt.setInt(9, cliente.getIdEndereco().getId());
            psmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro ao alterar cliente " + e.getMessage());
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
        Pessoa pessoa = null;
//        EnderecoDao enderecoDao = new EnderecoDaoImp();
        Endereco endereco = null;
        Cliente cliente = null;
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select p.*,c.*,e.* from pessoa p join cliente c on c.idPessoa = p.id  join endereco e on e.idPessoa = p.id where p.id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if ((rs.next())) {
                cliente = new Cliente();
                endereco = new Endereco();
                cliente.setId(rs.getInt("p.id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNascimento(rs.getDate("nascimento"));
                cliente.setDepedentes(rs.getInt("depedentes"));
                cliente.setCodigo(rs.getString("codigo"));
                endereco.setId(rs.getInt("e.id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade("cidade");
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setSigla(rs.getString("sigla"));
                endereco.setPais(rs.getString("pais"));
                cliente.setIdEndereco(endereco);
            }
//              enderecoDao.procurar(id);
        } catch (Exception e) {
        }finally{
           FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return cliente;
    }

    @Override
    public List listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Cliente> pesquisa(String nome) throws Exception {
        Connection conn = null;
        Cliente cliente;

        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList();
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select c.*,p.* from cliente c join pessoa p on c.idPessoa = p.id where (nome) like ?";//"join turno t on f.idTurno = t.id join funcao fu on f.idFuncao = fu.id where (nome) like ?";
//            select f.*,p.* from funcionario f left join pessoa p on f.idPessoa = p.id where (nome) like ?
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + nome + "%");
            rs = psmt.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
//                funcao = new Funcao();
//                turno = new Turno();
//                funcao.setNome(rs.getString("nomee"));
//                funcionario.setIdFuncao(funcao);
//                turno.setPerfio(rs.getString("perfiodo"));
//                funcionario.setIdTurno(turno);
                cliente.setId(rs.getInt("idPessoa"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro ao procurar funcionario" + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return clientes;
    }
}
