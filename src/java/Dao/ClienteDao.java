/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Cliente;
import java.util.List;

/**
 *
 * @author User
 */
public interface ClienteDao extends BaseDao{
    
    List<Cliente> pesquisa(String nome) throws Exception;
    
}
