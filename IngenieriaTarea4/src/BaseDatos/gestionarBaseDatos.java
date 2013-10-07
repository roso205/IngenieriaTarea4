package BaseDatos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author arturo
 */
public class gestionarBaseDatos {
    
    public gestionarBaseDatos() {
        
    }
    
    public ResultSet ejecutarConsulta(String consulta) {

        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectString, user, password);
            Statement stmt = connection.createStatement();
            

            if (connection != null) {
                System.out.println("...Conectando a la base de datos...");
            }

            ResultSet rs = stmt.executeQuery(consulta);

            stmt.close();
            connection.close();
            return rs;
            }

        catch ( Exception e ) {
            System.out.println("EXCEPCION");
            System.out.println(e.getMessage());
            return null;
        } 

    }
    
    public boolean ejecutarInsert(String insert) {

        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try{

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectString, user, password);
            Statement stmt = connection.createStatement();

            if (connection != null) {
                System.out.println("...Conectando a la base de datos...");
            }

            int resultado = stmt.executeUpdate(insert);

            stmt.close();
            connection.close();
            return resultado > 0;
            
            }

        catch ( Exception e ) {
            System.out.println(e.getMessage());
            return false;
        } 

    }
    
    public Connection establecerConexion() {
             
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";   
        
        try{

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(connectString, user, password);

            if (connection != null) {
                System.out.println("...Conectando a la base de datos...");
            }
            return connection;          
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());
            return null;

        } 
           
    }
    
    public void cerrarConexion(Connection conexion) {
         
        try {
            conexion.close();       

        }
          catch(Exception e) {
          System.out.println("Problema al desconectarse de la base de datos");
        }
   
    }
    
    
}
