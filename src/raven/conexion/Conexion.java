/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Conexion {
    public static Connection con;
    private static final String driver ="com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/produzcaproject";
    private static final String user = "root";
    private static final String password = "";
    
    public Connection conectar(){
        con=null;
        try {
            con=(Connection)DriverManager.getConnection(URL, user, password);
            if (con!=null) {
                
            }
            
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error de conexion", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}

