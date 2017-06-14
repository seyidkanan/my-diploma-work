/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Kanan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {

    public Connection getConnection() {
        Connection myConn = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/qida?useSSL=false", "xxxx", "xxxxx");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
           // System.out.println("SQLState: " + ex.getSQLState());
           // System.out.println("VendorError: " + ex.getErrorCode());
        }
        return myConn;
    }

    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (con.isClosed() == false) {
            con.close();
        }
        if (ps.isClosed() == false) {
            ps.close();
        }
        if (rs.isClosed() == false) {
            rs.close();
        }
    }

}
