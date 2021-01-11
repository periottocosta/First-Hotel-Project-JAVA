/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Funcionario;
import java.util.List;

public interface TurnoDao extends BaseDao{
     List<Funcionario> pesquisa(int turno) throws Exception;
      void excluirTruno(int id, int idTurno, String mens) throws Exception;
}