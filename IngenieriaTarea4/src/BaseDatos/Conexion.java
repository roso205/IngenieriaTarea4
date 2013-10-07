package BaseDatos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roso
 */
import java.sql.*;
//creamos la clase
public class Conexion {
   
    private  String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String pass = "postgres";
    private Connection cn;
     
    public Connection getConexion() {
        try {
    
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
               e.printStackTrace();
        }
        return cn;
    }
    

}
