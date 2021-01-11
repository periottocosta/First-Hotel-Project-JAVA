/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entidadesDoBanco.EstatusQuarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class EstatusQuartoDaoImp implements EstatusQuartoDao {
    
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List listar() throws Exception {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List estatus = new ArrayList();
        EstatusQuarto estatusq = null;
        
        try {
            conn = FabricaConexao.abreConexao();
            String BANCO = "SELECT * FROM estausquarto";
            psmt = conn.prepareStatement(BANCO);
            rs = psmt.executeQuery();
            while ((rs.next())) {
                estatusq = new EstatusQuarto();
                estatusq.setId(rs.getInt("id"));
                estatusq.setStatus(rs.getString("status"));
                estatus.add(estatusq);
                
            }
        } catch (Exception e) {
        } finally {
            FabricaConexao.fechaConexao(conn, psmt, rs);
        }
        return estatus;
    }
}
