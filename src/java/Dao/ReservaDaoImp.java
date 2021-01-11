/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.Cliente;
import entidadesDoBanco.EstatusReserva;
import entidadesDoBanco.Quarto;
import entidadesDoBanco.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservaDaoImp implements ReservaDao {

    @Override
    public Object salvar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void alterar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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
        Cliente cliente;
        Reserva reserva = null;
        EstatusReserva Er;
        Quarto quarto;

        try {
            conn = FabricaConexao.abreConexao();
            String banco = "select c.*,p.*,r.*,q.*,er.* from cliente c join pessoa p on c.idPessoa = p.id "
                    + "join reserva r on r.idCliente = c.idPessoa join quarto q on q.id = r.idQuarto "
                    + "join estatusReserva er on r.idEstatusReserva = er.id where r.id = ? ";

            psmt = conn.prepareStatement(banco);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                reserva = new Reserva();
                quarto = new Quarto();
                Er = new EstatusReserva();
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setId(rs.getInt("p.id"));
                reserva.setIdCliente(cliente);
                Er.setId(rs.getInt("er.id"));
                Er.setStatus(rs.getString("status"));
                reserva.setIdEstatusReser(Er);
                quarto.setId(rs.getInt("q.id"));
                quarto.setNumero(rs.getString("numero"));
                reserva.setIdQuarto(quarto);

                reserva.setDataEntrada(rs.getDate("entrada"));
                reserva.setDataReserva(rs.getDate("dataReserva"));
                reserva.setDataSaida(rs.getDate("saida"));
                reserva.setId(rs.getInt("r.id"));

            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return reserva;
    }

    @Override
    public List listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object salvarRserva(Object obj, int idQuarto) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Reserva reserva = (Reserva) obj;
        int estatus = 1;
        try {
            conn = FabricaConexao.abreConexao();
            String banco2 = "update quarto set idEstutusQuarto = ? where id = ?";
            psmt = conn.prepareStatement(banco2);
            psmt.setInt(1, estatus);
            psmt.setInt(2, idQuarto);
            psmt.executeUpdate();
            ///////////////////////////
            String banco = "insert into reserva (idQuarto, idCliente, dataReserva, entrada, saida, idEstatusReserva) "
                    + "values(?, ?, ?, ?, ?, ?)";

            psmt = conn.prepareStatement(banco);
            psmt.setInt(1, reserva.getIdQuarto().getId());
            psmt.setInt(2, reserva.getIdCliente().getId());
            psmt.setDate(3, new java.sql.Date(reserva.getDataReserva().getTime()));
            psmt.setDate(4, new java.sql.Date(reserva.getDataEntrada().getTime()));
            psmt.setDate(5, new java.sql.Date(reserva.getDataSaida().getTime()));
            psmt.setInt(6, reserva.getIdEstatusReser().getId());
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
        return null;
    }

    @Override
    public List<Reserva> pesquisa(String nome) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Cliente cliente;
        Reserva reserva;
        EstatusReserva Er;
        Quarto quarto;
        List<Reserva> reservas = new ArrayList();
        try {
            conn = FabricaConexao.abreConexao();
            String banco = "select c.*,p.*,r.*,q.*,er.* from cliente c "
                    + "join pessoa p on c.idPessoa = p.id join reserva r on r.idCliente = c.idPessoa "
                    + "join quarto q on q.id = r.idQuarto "
                    + "join estatusReserva er on r.idEstatusReserva = er.id where (p.nome) like ?";

            psmt = conn.prepareStatement(banco);
            psmt.setString(1, "%" + nome + "%");
            rs = psmt.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
                reserva = new Reserva();
                quarto = new Quarto();
                Er = new EstatusReserva();
                cliente.setNome(rs.getString("nome"));
                cliente.setId(rs.getInt("p.id"));
                reserva.setIdCliente(cliente);
                Er.setId(rs.getInt("er.id"));
                Er.setStatus(rs.getString("status"));
                reserva.setIdEstatusReser(Er);
                quarto.setId(rs.getInt("q.id"));
                quarto.setNumero(rs.getString("numero"));
                reserva.setIdQuarto(quarto);

                reserva.setDataEntrada(rs.getDate("entrada"));
                reserva.setDataReserva(rs.getDate("dataReserva"));
                reserva.setDataSaida(rs.getDate("saida"));
                reserva.setId(rs.getInt("r.id"));
                reservas.add(reserva);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return reservas;

    }

    @Override
    public void excluirRserva(int id, int idQuarto) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = FabricaConexao.abreConexao();
            String banco = "update quarto set idEstutusQuarto = 2 where id = ?";
            psmt = conn.prepareStatement(banco);
            psmt.setInt(1, idQuarto);
            psmt.executeUpdate();

            String banco2 = "delete from reserva where id = ?";
            psmt = conn.prepareStatement(banco2);
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
    }

    @Override
    public void alterarReserva(Object obj, int idQuarto) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Reserva reserva = (Reserva) obj;
        ResultSet rs = null;
        Quarto quarto = null;
        int estatus = 1;
        int estatus2 = 2;
        try {
            conn = FabricaConexao.abreConexao();   
            if (reserva.getIdQuarto().getId() == idQuarto) {
                String banco5 = "update reserva set dataReserva = ?, entrada = ?, saida = ? where id = ?";
                psmt = conn.prepareStatement(banco5);
                psmt.setDate(1, new java.sql.Date(reserva.getDataReserva().getTime()));
                psmt.setDate(2, new java.sql.Date(reserva.getDataEntrada().getTime()));
                psmt.setDate(3, new java.sql.Date(reserva.getDataSaida().getTime()));
                psmt.setInt(4, reserva.getId());
                psmt.executeUpdate();
            } else {
                String banco = "update reserva set idQuarto = ?,dataReserva =?, entrada = ?, saida = ? where id = ?";
                psmt = conn.prepareStatement(banco);
                psmt.setInt(1, reserva.getIdQuarto().getId());
                psmt.setDate(2, new java.sql.Date(reserva.getDataReserva().getTime()));
                psmt.setDate(3, new java.sql.Date(reserva.getDataEntrada().getTime()));
                psmt.setDate(4, new java.sql.Date(reserva.getDataSaida().getTime()));
                psmt.setInt(5, reserva.getId());
                psmt.executeUpdate();
                /// Novo quarto
                String banco2 = "update quarto set idEstutusQuarto = ? where id = ?";
                psmt = conn.prepareStatement(banco2);
                psmt.setInt(1, estatus);
                psmt.setInt(2, reserva.getIdQuarto().getId());
                psmt.executeUpdate();
                /// Quarto antigo
                String banco4 = "update quarto set idEstutusQuarto = ? where id = ?";
                psmt = conn.prepareStatement(banco2);
                psmt.setInt(1, estatus2);
                psmt.setInt(2, idQuarto);
                psmt.executeUpdate();
            }


        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
    }
}
