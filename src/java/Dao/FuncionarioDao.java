/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Funcionario;
import java.util.List;

/**
 *
 * @author User
 */
public interface FuncionarioDao extends BaseDao{
    
    List<Funcionario> pesquisa(String nome) throws Exception;
    
   //  void alterarFuncionario(Object obj, int idfuncionario , int idEndereco) throws Exception;
   
   
}
