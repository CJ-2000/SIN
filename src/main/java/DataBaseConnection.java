
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Notes
 */
public class DataBaseConnection {
    
    protected static Connection initializeDatabase()
            throws SQLException,ClassNotFoundException {
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sin_bd?user=root&password=");
        return con;
    }
    

}
