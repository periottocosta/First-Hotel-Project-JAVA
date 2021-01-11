/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FabricaConexao {
    public static Connection abreConexao() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "");
    }

    private static void fecha(Connection conn, Statement stmt,
            ResultSet rs) throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void fechaConexao(Connection conn, Statement stmt,
            ResultSet rs) throws Exception {
        fecha(conn, stmt, rs);
    }

    public static void fechaConexao(Connection conn, Statement stmt)
            throws Exception {
        fecha(conn, stmt, null);
    }

    public static void fechaConexao(Connection conn)
            throws Exception {
        fecha(conn, null, null);
    }
}
