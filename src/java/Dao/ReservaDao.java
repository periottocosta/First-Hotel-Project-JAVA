/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Reserva;
import java.util.List;

public interface ReservaDao extends BaseDao {

    Object salvarRserva(Object obj, int idQuarto) throws Exception;

    List<Reserva> pesquisa(String nome) throws Exception;

    void excluirRserva(int id, int idQuarto) throws Exception;

    void alterarReserva(Object obj, int idQuarto) throws Exception;
}