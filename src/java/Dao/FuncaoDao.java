/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Funcionario;
import java.util.List;

public interface FuncaoDao extends BaseDao{
     List<Funcionario> pesquisa(int funcao) throws Exception;
}