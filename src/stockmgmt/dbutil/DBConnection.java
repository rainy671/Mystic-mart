/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmgmt.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class DBConnection {
    private static Connection conn;
    static
    {
        try
            
    
    {
        Class.forName("oracle.jdbc.OracleDriver");
        conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-H34P21T:1521/xe","advjavabatch","mystudents");
        JOptionPane.showMessageDialog(null,"Connection Opened Successfully!","Success",JOptionPane.INFORMATION_MESSAGE);        
    }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(null,"Could not load the driver","Error",JOptionPane.ERROR_MESSAGE);
        cnfe.printStackTrace();
        System.exit(1);
        
        }
    catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null,"Could not open the Connection","Error",JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        System.exit(1);
    }
    
    }
   
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            conn.close();
            JOptionPane.showMessageDialog(null,"Connection Closed Successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(SQLException ex)
                {
                 JOptionPane.showMessageDialog(null,"Could not close the Connection","Error",JOptionPane.ERROR_MESSAGE);  
                 ex.printStackTrace();
                }
    }
}
    

