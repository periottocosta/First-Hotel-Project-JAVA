/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Endereco;
import entidadesDoBanco.Funcao;
import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Pessoa;
import entidadesDoBanco.Turno;
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
public class FuncionarioDaoImp implements FuncionarioDao {

    private PessoaDao pDao;

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        pDao = new PessoaDaoImp();
        Funcionario funcionario = null;

        try {

            funcionario = (Funcionario) pDao.salvar(obj);
            conn = FabricaConexao.abreConexao();
            String BANCO = "INSERT INTO funcionario(cracha, salario, idPessoa, idTurno, idFuncao) VALUES(?, ?, ?, ?, ?)";
            psmt = conn.prepareStatement(BANCO);
            psmt.setString(1, funcionario.getCracha());
            psmt.setDouble(2, funcionario.getSalario());
            psmt.setInt(3, funcionario.getId());
            psmt.setInt(4, funcionario.getIdTurno().getId());
            psmt.setInt(5, funcionario.getIdFuncao().getId());
            psmt.executeUpdate();

        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
        return funcionario;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Funcionario funcionario = (Funcionario) (Pessoa) obj;

        try {

            conn = FabricaConexao.abreConexao();
            String sql1 = "update pessoa set cpf = ?, rg = ?, email = ?, telefone = ?, celular = ?, sexo = ?, "
                    + "nascimento = ?, nome = ? where id = ?";
            psmt = conn.prepareStatement(sql1);
            psmt.setString(1, funcionario.getCpf());
            psmt.setString(2, funcionario.getRg());
            psmt.setString(3, funcionario.getEmail());
            psmt.setString(4, funcionario.getTelefone());
            psmt.setString(5, funcionario.getCelular());
            psmt.setString(6, funcionario.getSexo());
            psmt.setDate(7, new java.sql.Date(funcionario.getNascimento().getTime()));
            psmt.setString(8, funcionario.getNome());
            psmt.setInt(9, funcionario.getId());
            psmt.executeUpdate();

            ///

            String sql = "update funcionario set cracha = ?, salario = ?, idTurno = ?, idFuncao = ? where idPessoa = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, funcionario.getCracha());
            psmt.setDouble(2, funcionario.getSalario());
            psmt.setInt(3, funcionario.getIdTurno().getId());
            psmt.setInt(4, funcionario.getIdFuncao().getId());
            psmt.setInt(5, funcionario.getId());
            psmt.executeUpdate();

            ///////
            String sql3 = "update endereco set rua = ?, bairro = ?, cidade = ?, estado = ?, sigla = ?, pais = ?, cep = ?, idPessoa = ? where id = ? ";

            psmt = conn.prepareStatement(sql3);
            psmt.setString(1, funcionario.getIdEndereco().getRua());
            psmt.setString(2, funcionario.getIdEndereco().getBairro());
            psmt.setString(3, funcionario.getIdEndereco().getCidade());
            psmt.setString(4, funcionario.getIdEndereco().getEstado());
            psmt.setString(5, funcionario.getIdEndereco().getSigla());
            psmt.setString(6, funcionario.getIdEndereco().getPais());
            psmt.setString(7, funcionario.getIdEndereco().getCep());
            psmt.setInt(8, funcionario.getIdEndereco().getId());
            psmt.setInt(9, funcionario.getIdEndereco().getId());
            psmt.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro ao alterar funcionario " + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;
        Endereco endereco;
        Turno turno;
        Funcao funcao;
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select p.*,f.*,e.*,t.*,fu.* from pessoa p join funcionario f on f.idPessoa = p.id "
                    + "join endereco e on e.idPessoa = p.id join turno t on t.id = f.idTurno "
                    + "join funcao fu on f.idFuncao = fu.id where p.id = ? ";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if ((rs.next())) {
                funcionario = new Funcionario();
                turno = new Turno();
                funcao = new Funcao();
                endereco = new Endereco();
                funcionario.setId(rs.getInt("p.id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setEmail(rs.getString("email"));

                funcionario.setNascimento(rs.getDate("nascimento"));
                funcionario.setCracha(rs.getString("cracha"));
                funcionario.setSalario(rs.getDouble("salario"));

                funcao.setNome(rs.getString("fu.nomee"));
                funcao.setId(rs.getInt("fu.id"));
                funcionario.setIdFuncao(funcao);

                turno.setId(rs.getInt("t.id"));
                turno.setPerfio(rs.getString("perfiodo"));
                funcionario.setIdTurno(turno);
                endereco.setId(rs.getInt("e.id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setSigla(rs.getString("sigla"));
                endereco.setPais(rs.getString("pais"));
                funcionario.setIdEndereco(endereco);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcionario;

    }

    @Override
    public List listar() throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List funcionarios = new ArrayList();
        Funcionario funcionario;
        try {

            conn = FabricaConexao.abreConexao();
            String BANCO = "SELECT f.*,p.* FROM pessoa p join funcionario f on p.id = f.idPessoa";
            psmt = conn.prepareStatement(BANCO);
            rs = psmt.executeQuery();


            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("idPessoa"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionarios.add(funcionario);
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcionarios;
    }

    @Override
    public List<Funcionario> pesquisa(String nome) throws Exception {
        Connection conn = null;
        Funcionario funcionario;
        Turno turno = null;
        Funcao funcao = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList();
        try {
            conn = FabricaConexao.abreConexao();
            String sql = "select f.*,p.* from funcionario f join pessoa p on f.idPessoa = p.id where (nome) like ?";//"join turno t on f.idTurno = t.id join funcao fu on f.idFuncao = fu.id where (nome) like ?";
//            select f.*,p.* from funcionario f left join pessoa p on f.idPessoa = p.id where (nome) like ?
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + nome + "%");
            rs = psmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();

                funcionario.setId(rs.getInt("idPessoa"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao procurar funcionario" + e.getMessage());
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return funcionarios;
    }
//    @Override
//    public void alterarFuncionario(Object obj, int idfuncionario, int idEndereco) throws Exception {
//        Connection conn = null;
//        PreparedStatement psmt = null;
//        //pDao = new PessoaDaoImp();
//        ResultSet rs = null;
//        Funcionario funcionario = null;
//        Pessoa pessoa = null;
//        try {
//            //  pDao.alterar(obj);
//            ///
//            FabricaConexao.abreConexao();
//            String sql1 = "update pessoa set cpf = ?, rg = ?, email = ?, telefone = ?, celular = ?, sexo = ?, "
//                    + "nascimento = ?, nome = ? where id = ?";
//            psmt = conn.prepareStatement(sql1);
//            psmt.setString(1, pessoa.getCpf());
//            psmt.setString(2, pessoa.getRg());
//            psmt.setString(3, pessoa.getEmail());
//            psmt.setString(4, pessoa.getTelefone());
//            psmt.setString(5, pessoa.getCelular());
//            psmt.setString(6, pessoa.getSexo());
//            psmt.setDate(7, new java.sql.Date(pessoa.getNascimento().getTime()));
//            psmt.setString(8, pessoa.getNome());
//            psmt.setInt(9, idfuncionario);
//            psmt.executeUpdate();
//            
//            ///
//           // conn = FabricaConexao.abreConexao();
//            String sql = "update funcionario set cracha = ?, salario = ?, idTurno = ?, idFuncao = ? where idPessoa = ?";
//            psmt = conn.prepareStatement(sql);
//            psmt.setString(1, funcionario.getCracha());
//            psmt.setDouble(2, funcionario.getSalario());
//            psmt.setInt(3, funcionario.getIdTurno().getId());
//            psmt.setInt(4, funcionario.getIdFuncao().getId());
//            psmt.setInt(5, idfuncionario);
//            psmt.executeUpdate();
//            
//            ///////
//             String sql3 = "update endereo set rua = ?, bairro = ?, cidade = ?, estado = ?, sigla = ?, pais = ?, cep = ? where id = ? ";
//           
//            psmt = conn.prepareStatement(sql3);
//            psmt.setString(1, pessoa.getIdEndereco().getRua());
//            psmt.setString(2, pessoa.getIdEndereco().getBairro());
//            psmt.setString(3, pessoa.getIdEndereco().getCidade());
//            psmt.setString(4, pessoa.getIdEndereco().getEstado());
//            psmt.setString(5, pessoa.getIdEndereco().getSigla());
//            psmt.setString(6, pessoa.getIdEndereco().getPais());
//            psmt.setString(7, pessoa.getIdEndereco().getCep());
//            psmt.setInt(8,  idEndereco);
//            psmt.executeUpdate();
//        } catch (Exception e) {
//        }finally{
//         FabricaConexao.fechaConexao(conn, psmt, rs);   
//        }
//    }
}
