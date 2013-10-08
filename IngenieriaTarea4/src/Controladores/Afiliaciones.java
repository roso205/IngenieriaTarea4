package Controladores;

import BaseDatos.*;
import ObjetosBase.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arturo
 */
public class Afiliaciones {
    
    //Constructor
    public Afiliaciones() {
        
    }
    
      public Plan buscarPlan(int Codigo) {
        

        try{

        gestionarBaseDatos BaseDatos = new gestionarBaseDatos();
        
        Connection connection = BaseDatos.establecerConexion();
                
        Statement stmt = connection.createStatement();
        String consulta = "SELECT * FROM PLAN "
                  + " WHERE (PLAN.CODIGO = " +Integer.toString(Codigo)+") ";

                    
        ResultSet rs = stmt.executeQuery(consulta);

        List<String> resultado = new ArrayList<String>();
        Afiliaciones tipo = new Afiliaciones();

        while (rs.next()){

            for (int i = 1; i < 4; i++) {

                resultado.add(rs.getString(i));

            }
        }

        Plan Plan = new Plan(Integer.parseInt(resultado.get(0)),
                resultado.get(1),tipo.buscarTipoPlan(Codigo),
                                      Double.parseDouble(resultado.get(2)));
       
        
        
        BaseDatos.cerrarConexion(connection); 
        return Plan;
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());
            Plan Plan = null;
            return Plan;
        } 

  
    }
    
    
    //afiliar y desafiliar plan y paquete y consultar afiliaciones
    
    public boolean modificarPlan(int codigoProducto, int codigoPlan) {
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        // Verificar si existe el producto
        
        String consultaProd = "SELECT * FROM PRODUCTO WHERE"+
                "(CODIGO = "+Integer.toString(codigoProducto)+");";
        
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaProd);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close();
                return false;
            }
            
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Verificar si existe el plan
        
        String consultaPlan = "SELECT * FROM PLAN WHERE"+
                "(CODIGO = "+Integer.toString(codigoPlan)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaPlan);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close(); 
                return false;
            }      
                 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fecha_a = (dateFormat.format(date)).toString();
        
        ControladorConsumidores Consulta = new ControladorConsumidores();
        Producto Producto = Consulta.buscarProducto(codigoProducto);
        
        Afiliaciones Consulta2 = new Afiliaciones();
        
        String TipoPlanProducto =
                            Consulta2.buscarTipoPlan(Producto.getCodigoPlan());
        String TipoPlan = Consulta2.buscarTipoPlan(codigoPlan);
        
        if (!TipoPlanProducto.equalsIgnoreCase(TipoPlan)) {
            System.out.println("No se puede afiliar un plan "+TipoPlan+
                                            " a un Producto "+TipoPlanProducto);
          return false;  
        }
        
        String afiliacion;
        
        if (TipoPlanProducto.equalsIgnoreCase("POSTPAGO")) {
            afiliacion = "UPDATE PRODUCTO SET CODIGO_P = "+
                                Integer.toString(codigoPlan) + ", FECHA_A = '"+
                                fecha_a + "' WHERE CODIGO = " +
                                Integer.toString(codigoProducto) +";";
        
        } else {

              
            Plan Plan = Consulta2.buscarPlan(codigoPlan);   
            
            if ((Producto.getSaldo()-Plan.getTarifa()) >= 0 ) {
                afiliacion = "UPDATE PRODUCTO SET CODIGO_P = "+
                                Integer.toString(codigoPlan) + ", FECHA_A = '"+
                                fecha_a + "' SALDO = "+
                                (Producto.getSaldo()-Plan.getTarifa())+
                                " WHERE CODIGO = " +
                                Integer.toString(codigoProducto) +";"; 
            } else {
                System.out.println("Saldo Insuficiente para adquirir el plan");
                return false;     
            }
        }
        
        
        try {
            
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(afiliacion);
            gestorBD.cerrarConexion(conexion);
            return resultado > 0;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        
        return false;
    }
    
    public boolean afiliarPaquete(int codigoProducto, int codigoPq, String tipo) {
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        // Verificar si existe el producto
        
        String consultaProd = "SELECT * FROM PRODUCTO WHERE"+
                "(CODIGO = "+Integer.toString(codigoProducto)+");";
        
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaProd);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close();
                return false;
            }
            
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Verificar si existe el paquete
        
        String consultaPq = "SELECT * FROM PAQUETE WHERE"+
                "(CODIGO = "+Integer.toString(codigoPq)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaPq);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close(); 
                return false;
            }      
                 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        if (!tipo.equalsIgnoreCase("MENSUAL") && 
            !tipo.equalsIgnoreCase("UNICO")) 
        {
            return false;
        }
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fecha_a = (dateFormat.format(date)).toString();
        
        // Obtener los datos para agregar la nueva instancia de "contrata"
        
        String rifCliente = "SELECT RIF_C FROM PRODUCTO WHERE CODIGO = "+
                            Integer.toString(codigoProducto)+";";
        
        // Obtener el codigo del cliente
        String rifC = "";
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(rifCliente);
            rs.next();
            
            rifC = Integer.toString(rs.getInt(1));
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //String para insertar la afiliacion
        
        String afiliacion = "INSERT INTO CONTRATA VALUES (" +
                            rifC + ", " +
                            Integer.toString(codigoPq) + ", " +
                            Integer.toString(codigoProducto) + ", '" +
                            fecha_a + "', '" + tipo + "');";
        
        
        // Ejecutar la consulta
        try {
            
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(afiliacion);
            gestorBD.cerrarConexion(conexion);
            return resultado > 0;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        
        return false;
    }
    
    
    // Este metodo desafilia TODOS los paquetes iguales del producto
    public boolean desafiliarPaquete(int codigoProducto, int codigoPq) {
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        // Verificar si existe el producto
        
        String consultaProd = "SELECT * FROM PRODUCTO WHERE"+
                "(CODIGO = "+Integer.toString(codigoProducto)+");";
        
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaProd);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close();
                return false;
            }
            
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Verificar si existe el paquete
        
        String consultaPq = "SELECT * FROM PAQUETE WHERE"+
                "(CODIGO = "+Integer.toString(codigoPq)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaPq);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close(); 
                return false;
            }      
                 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
               
        // Obtener los datos para agregar la nueva instancia de "contrata"
        
        String rifCliente = "SELECT RIF_C FROM PRODUCTO WHERE CODIGO = "+
                            Integer.toString(codigoProducto)+";";
        
        // Obtener el codigo del cliente
        String rifC = "";
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(rifCliente);
            rs.next();
            
            rifC = Integer.toString(rs.getInt(1));
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //String para desafiliar
        
        String desafiliacion = "DELETE FROM CONTRATA WHERE RIF_C = " +
                            rifC + " AND CODIGO_PQ = " +
                            Integer.toString(codigoPq) + " AND CODIGO_PR = " +
                            Integer.toString(codigoProducto) +";";
        
        System.out.println(desafiliacion);
        
        
        // Ejecutar la consulta
        try {
            
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(desafiliacion);
            gestorBD.cerrarConexion(conexion);
            return resultado > 0;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        
        return false;
    }
    
    
     public List<String> BuscarPaquete(int CodigoPlan) {
   
       try{

        gestionarBaseDatos BaseDatos = new gestionarBaseDatos();
        
        Connection connection = BaseDatos.establecerConexion();
                
        Statement stmt = connection.createStatement();
        String consulta = "SELECT CODIGO_PQ FROM CONTIENE "
                  + " WHERE (CONTIENE.CODIGO_P = " +Integer.toString(CodigoPlan)+") ";

                    
        ResultSet rs = stmt.executeQuery(consulta);

        List<String> resultado = new ArrayList<String>();


        while (rs.next()){

            resultado.add(rs.getString(1));
        }


        stmt.close();
        BaseDatos.cerrarConexion(connection); 
        return resultado;
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());   
             List<String> resultado = new ArrayList<String>();
             return resultado;
        } 
   
   }
    
    

    public List<String> listarPaquetesAfiliados(int codigoProducto) {
        

        try{

            gestionarBaseDatos BaseDatos = new gestionarBaseDatos();

            Connection connection = BaseDatos.establecerConexion();

            Statement stmt = connection.createStatement();
            String consulta = "SELECT CODIGO_PQ FROM CONTRATA "
                              + " WHERE (CONTRATA.CODIGO_PR = "
                              +Integer.toString(codigoProducto)+") ";


            ResultSet rs = stmt.executeQuery(consulta);

            List<String> resultado = new ArrayList<String>();


            while (rs.next()){
                resultado.add(rs.getString(1));
            }


            stmt.close();
            BaseDatos.cerrarConexion(connection); 
            return resultado;
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());   
            List<String> resultado = new ArrayList<String>();
            return resultado;
        } 
    
    }
   
    
    public String buscarTipoPlan(int CodigoPlan){
        
          try{

        String resultado = "POSTPAGO";
        gestionarBaseDatos BaseDatos = new gestionarBaseDatos();
        
        Connection connection = BaseDatos.establecerConexion();
                
        Statement stmt = connection.createStatement();
        String consulta = "SELECT * FROM PREPAGO "
             + " WHERE (PREPAGO.CODIGO_P = " +Integer.toString(CodigoPlan)+") ";

                    
        ResultSet rs = stmt.executeQuery(consulta);


        if (rs.next()){
            resultado = "PREPAGO";
        }


        BaseDatos.cerrarConexion(connection); 
        return resultado;
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());
            String resultado = "";
            return resultado;
        } 
        
    }
}
