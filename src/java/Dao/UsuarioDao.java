/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Funcionario;
import java.util.List;

public interface UsuarioDao extends BaseDao {

    List<Funcionario> pesquisa(String nome) throws Exception;
    
    List<Funcionario> usuarioN(String nome) throws Exception;

    Funcionario logar(String login, String senha) throws Exception;
    
    void excluirUsuario(int id, int idUsuario) throws Exception;
}
