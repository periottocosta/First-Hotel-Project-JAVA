/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.EstatusQuarto;
import entidadesDoBanco.Quarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuartoDaoImp implements QuartoDao {

    @Override
    public Object salvar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Quarto quarto = (Quarto) obj;

        try {
            String sql = "insert into quarto (numero, capacidade, descricao, idEstutusQuarto) values (?, ?, ?, ?)";
            conn = FabricaConexao.abreConexao();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, quarto.getNumero());
            psmt.setString(2, quarto.getCapacidade());
            psmt.setString(3, quarto.getDescricao());
            psmt.setInt(4, quarto.getIdEstatusQuarto().getId());
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
        return null;
    }

    @Override
    public void alterar(Object obj) throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        Quarto quarto = (Quarto) obj;
        try {
            conn = FabricaConexao.abreConexao();
            String banco2 = "update quarto set idEstutusQuarto where id = ?";
            psmt = conn.prepareStatement(banco2);
            psmt.setInt(1, quarto.getIdEstatusQuarto().getId());
            psmt.setInt(2, quarto.getId());
            psmt.executeUpdate();
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object procurar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List listar() throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        List quartos = new ArrayList();
        Quarto quarto = null;
        int estatus = 2;
        try {
            conn = FabricaConexao.abreConexao();
            String BANCO = "SELECT * FROM quarto where idEstutusQuarto = ?";
            psmt = conn.prepareStatement(BANCO);
            psmt.setInt(1, estatus);
            rs = psmt.executeQuery();
            while ((rs.next())) {
                quarto = new Quarto();
                quarto.setId(rs.getInt("id"));
                quarto.setNumero(rs.getString("numero"));
                quarto.setDescricao(rs.getString("descricao"));
                quarto.setCapacidade(rs.getString("capacidade"));

                quartos.add(quarto);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return quartos;
    }

    @Override
    public List listarQuarto() throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        List quartos = new ArrayList();
        Quarto quarto = null;
        EstatusQuarto eQ = null;
        try {
            conn = FabricaConexao.abreConexao();
            String BANCO = "SELECT q.*,e.* FROM quarto q join estausquarto e on q.idEstutusQuarto = e.id";
            psmt = conn.prepareStatement(BANCO);
            rs = psmt.executeQuery();
            while ((rs.next())) {
                quarto = new Quarto();
                eQ = new EstatusQuarto();
                eQ.setId(rs.getInt("e.id"));
                eQ.setStatus(rs.getString("status"));
                quarto.setIdEstatusQuarto(eQ);
                quarto.setId(rs.getInt("id"));
                quarto.setNumero(rs.getString("numero"));
                quarto.setDescricao(rs.getString("descricao"));
                quarto.setCapacidade(rs.getString("capacidade"));

                quartos.add(quarto);
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return quartos;
    }
}