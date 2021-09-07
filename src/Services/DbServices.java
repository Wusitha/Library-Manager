/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DbConnection;

/**
 *
 * @author Wsuitha
 */
public class DbServices {
    /*
        *Method:executeStatement
        *Return: void
        *Parameters: PreparedStatement object
        
        *Used to execute insert/update and delete queries
    */
    public static void executeStatement(PreparedStatement statement) throws SQLException {
        statement.execute();
    }
    
    public static ResultSet getData(PreparedStatement statement) throws SQLException {
        
        return statement.executeQuery();
    }
    
    public static PreparedStatement getStatement(String sql){
        
        PreparedStatement statement = null;
        Connection con = DbConnection.getConnection(); 
        
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DbServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statement;
    }
}
