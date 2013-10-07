/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import BaseDatos.Conexion;
import BaseDatos.gestionarBaseDatos;
import ObjetosBase.Cliente;
import ObjetosBase.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arturo
 */
public class ControladorConsumidores {
    
    public ControladorConsumidores() {
        
    }
    
    public boolean agregarCliente(Cliente cli){
        
         try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(); 
        
         stm.executeQuery("INSERT INTO CLIENTE (RIF,NOMBRE,DIRECCION)"
          + "VALUES ('"+ cli.getRif() +"','"+cli.getNombre()+"','"+cli.getDirec()+"') ");
        
         cn.close();
         
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
        
        return true;
    } 

    public Cliente buscarCliente(int rif){
        Cliente cli = new Cliente();
         try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(); 
        
        ResultSet rs = stm.executeQuery("SELECT * FROM CLIENTE WHERE RIF="+rif);
        rs.next();
        cli.SetNombre(rs.getString("NOMBRE"));
        cli.setRif(rs.getInt("RIF"));
        cli.setDirec(rs.getString("DIRECCION"));
      
         cn.close();
         
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
        
        return cli;
    }

    public void mostrarCliente(Cliente cli ){
       
        System.out.println("Nombre: "+ cli.getNombre()+" Rif: "+cli.getRif()
                +" Direccion: "+cli.getDirec());
        
    }
    
    public Producto buscarProducto(int Codigo) {
        

        try{

        gestionarBaseDatos BaseDatos = new gestionarBaseDatos();
        
        Connection connection = BaseDatos.establecerConexion();
                
        Statement stmt = connection.createStatement();
        String consulta = "SELECT * FROM PRODUCTO "
                  + " WHERE (PRODUCTO.CODIGO = " +Integer.toString(Codigo)+") ";

                    
        ResultSet rs = stmt.executeQuery(consulta);

        List<String> resultado = new ArrayList<String>();


        while (rs.next()){

            for (int i = 1; i < 8; i++) {

                resultado.add(rs.getString(i));

            }
        }

        Producto Producto = new Producto(
                                Integer.parseInt(resultado.get(0)),
                                resultado.get(1),resultado.get(2),
                                Integer.parseInt(resultado.get(3)),
                                Integer.parseInt(resultado.get(4)), 
                                resultado.get(5),
                                Integer.parseInt(resultado.get(6)));
        stmt.close();
        BaseDatos.cerrarConexion(connection); 
        return Producto;
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());
            Producto Producto = null;
            return Producto;
        } 

  
    }
    
    public boolean agregarProducto(int Codigo, String Nombre, 
                   String Descripcion, int CodigoPlan, int RifCliente, 
                                        String FechaAfiliacion, int Saldo) {
       

         // Crear el gestor para la base de datos
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        // Verificar si existe el producto
        
        String consultaProducto = "SELECT * FROM PRODUCTO WHERE"+
                "(CODIGO = "+Integer.toString(Codigo)+");";
        
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaProducto);

            if (rs.next()) {
             System.out.println("El producto ya existe");
                return false;

            }
            
            stmt.close();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Verificar si existe el cliente
        
        String consultaCliente = "SELECT * FROM CLIENTE WHERE"+
                "(RIF = "+Integer.toString(RifCliente)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaCliente);

            if (!rs.next()) {
                System.out.println("No existe el cliente asociado al producto");
                return false;
            }
            
            stmt.close();            
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Verificar si existe el plan
        
        String consultaPlan = "SELECT * FROM PLAN WHERE"+
                "(CODIGO = "+Integer.toString(CodigoPlan)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaPlan);

            if (!rs.next()) {
                System.out.println("No existe el plan ingresado");
                return false;
            }
            
            stmt.close();            
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
  
        String producto = "INSERT INTO PRODUCTO VALUES ("
                               +Integer.toString(Codigo)+","+Nombre+","
                               +Descripcion+","+Integer.toString(CodigoPlan)+","
                           +Integer.toString(RifCliente)+","+FechaAfiliacion+","
                               +Integer.toString(Saldo)+")";

        // Se agrega el producto
        
        try {
            
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(producto);
            return resultado > 0;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        
        return false;
        

    }
    
    public void mostrarProducto(int codigoPaquete) {
        

        //Creamos las listas para guardar las consultas

        Producto prod = buscarProducto(codigoPaquete);
        
        System.out.println(prod.getCodigo());
        System.out.println(prod.getCodigoPlan());
        System.out.println(prod.getDescripcion());
        System.out.println(prod.getFechaAfiliacion());
        System.out.println(prod.getNombre());
        System.out.println(prod.getRifCliente());
        System.out.println(prod.getSaldo());
        
    }
    
    
    public List<String> BuscarPaquetesAdicionales(int CodigoProducto) {
    
             try{

        gestionarBaseDatos BaseDatos = new gestionarBaseDatos();
        
        Connection connection = BaseDatos.establecerConexion();
                
        Statement stmt = connection.createStatement();
        String consulta = "SELECT CODIGO_PQ FROM CONTRATA "
                          + " WHERE (CONTRATA.CODIGO_PR = "
                                        +Integer.toString(CodigoProducto)+") ";

                    
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
    
    public ArrayList<String> buscarNombreServicios(ArrayList<String> Servicios) {
     
        
        try{

        gestionarBaseDatos BaseDatos = new gestionarBaseDatos();
        
        Connection connection = BaseDatos.establecerConexion();
                
        Statement stmt = connection.createStatement();    
        ArrayList<String> resultado = new ArrayList<String>();   
        
        for (int j = 0; j < Servicios.size(); j++) {
            
         
            String consulta = "SELECT NOMBRE FROM SERVICIO "
                     + " WHERE (SERVICIO.CODIGO = " +Servicios.get(j)+") ";

            
        
             ResultSet rs = stmt.executeQuery(consulta);


             while (rs.next()){

                resultado.add(rs.getString(1));

            }
             
        }
        

        
        stmt.close();
        BaseDatos.cerrarConexion(connection); 
        return resultado;
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());
            ArrayList<String> resultado = new ArrayList<String>();
            return resultado;
        } 
    
        
    }
 
}


