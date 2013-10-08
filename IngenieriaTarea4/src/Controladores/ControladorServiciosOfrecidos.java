/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import BaseDatos.Conexion;
import BaseDatos.gestionarBaseDatos;
import ObjetosBase.Paquete;
import ObjetosBase.Plan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arturo
 */
public class ControladorServiciosOfrecidos {
    
    public ControladorServiciosOfrecidos() {
        
    }
    
    public boolean agregarPlan(Plan p) {
        
        int codigoP = p.getCodigo();
        double tarifaP = p.getTarifa();
        String nombreP = p.getNombre();
        String tipoP = p.getTipo();
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        String insertPlan = "INSERT INTO PLAN VALUES (" +
                                Integer.toString(codigoP)+ ", '"+
                                nombreP + "', " +
                                Double.toString(tarifaP)+ ");";
               
        try {
        
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(insertPlan);
            stmt.close();
            gestorBD.cerrarConexion(conexion);
            return resultado > 0;
            

        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        return false;
        
    }
    
    public void mostrarClientesAfiliados (int cop){
        
        try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
   ResultSet.CONCUR_READ_ONLY); 
        
        ResultSet rs = stm.executeQuery("SELECT * FROM CLIENTE WHERE"
                + " RIF IN (SELECT RIF_C FROM AFILIA WHERE CODIGO_P ="+cop+")");
       
        rs.last();
        
        if(0<rs.getRow()){
            rs.beforeFirst();
            System.out.println("Clientes Asociados al plan "+ cop+
             " \n Nombre                   |  Documento ");
            while(rs.next()){
                
                System.out.println(rs.getString("NOMBRE")+"             "+rs.getString("RIF"));

            }
        }else {
            System.out.println("No existen clientes Asociados al plan "+ cop);
        }
     cn.close();
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
    
        
    }
    
    //Utilizar con cuidado, porque queda aun el producto afiliado
    public boolean desafiliarClientesAfiliados(int cop){
    
        try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(); 
        
         stm.executeQuery("DELETE * FROM AFILIA WHERE CODIGO_P ="+cop);
       
      
     cn.close();
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public boolean agregarPaquete(int co_plan,int co_paq){
        
                try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY); 
        
        ResultSet rs = stm.executeQuery("SELECT * FROM CONTIENE WHERE"
                + " CODIGO_P ="+co_plan+" AND CODIGO_PQ="+co_paq);
       rs.last();
       if(0==rs.getRow()){
          stm.executeQuery("INSERT INTO CONTIENE (CODIGO_P, CODIGO_PQ) "
                  + "VALUES ("+co_plan+","+co_paq+")" );
       
       }else{
           System.out.println("Ya el paquete:"+co_paq+" esta asociado al plan: "+co_plan);
           return false;
       }
      
     cn.close();
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
    
        return true;
    }
    
    public boolean borrarPaquete(int co_plan,int co_paq){
        
         try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(); 
        
        stm.executeQuery("DELETE * FROM CONTIENE WHERE"
                + " CODIGO_P ="+co_plan+" AND CODIGO_PQ="+co_paq);
      
      
         cn.close();
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
        return true;
    }
    
    public boolean modificarProducto(int co_pro, int new_plan){
        try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(); 
        
        stm.executeQuery("UPDATE PRODUCTO SET CODIGO_P="+new_plan+" WHERE"
                + " CODIGO="+co_pro);
      
      
         cn.close();
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
        
        return true;
    }
    
    public void mostrarProductos( int cop){
        
          try{
            
        Connection cn = new Conexion().getConexion();
        
        Statement stm = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
   ResultSet.CONCUR_READ_ONLY); 
        
        ResultSet rs = stm.executeQuery("SELECT * FROM PRODUCTO WHERE"
                + "CODIGO_P="+cop);
       
        rs.last();
        
        if(0<rs.getRow()){
            rs.beforeFirst();
            System.out.println("Productos Asociados al plan "+ cop+
             " \n Codigo      |        Nombre     |    Desc     ");
            while(rs.next()){
                
                System.out.println(rs.getString("CODIGO")+"   |    "+
                        rs.getString("NOMBRE")+"        "+rs.getString("DESCRIPCION"));

            }
        }else { System.out.println("No existen productos Asociados al plan "+ cop); 
        }
     cn.close();
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
    
        
        
    }
    
    // Agrega un servicio al paquete.
    public boolean agregarServicio(int codigoServ, int codigoPaquete,
                                   int cantidad) {
        
        // Crear el gestor para la base de datos
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        // Verificar si existe el servicio
        
        String consultaServ = "SELECT * FROM SERVICIO WHERE"+
                "(CODIGO = "+Integer.toString(codigoServ)+");";
        
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaServ);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close();
                return false;
            }
                      
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Verificar si existe el paquete
        
        String consultaPaquete = "SELECT * FROM PAQUETE WHERE"+
                "(CODIGO = "+Integer.toString(codigoPaquete)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaPaquete);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close();
                return false;
            }
                       
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        String interrelacion = "INSERT INTO CONFORMA VALUES ("
                                +Integer.toString(codigoPaquete)+","+
                                Integer.toString(codigoServ)+","+
                                Integer.toString(cantidad)+")";
        
        // Se agrega la interrelacion entre el servicio y paquete.
        
        try {
            
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(interrelacion);
            gestorBD.cerrarConexion(conexion);
            return resultado > 0;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        
        return false;
        
    }
    
    public boolean eliminarServicio(int codigoServ, int codigoPaquete) {
        
        // Crear el gestor para la base de datos
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        // Verificar si existe el servicio
        
        String consultaServ = "SELECT * FROM SERVICIO WHERE"+
                "(CODIGO = "+Integer.toString(codigoServ)+");";
        
        try {
            
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaServ);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close();
                return false;
            }
                    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Verificar si existe el paquete
        
        String consultaPaquete = "SELECT * FROM PAQUETE WHERE"+
                "(CODIGO = "+Integer.toString(codigoPaquete)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaPaquete);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close(); 
                return false;                
            }
                                  
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        String eliminar = "DELETE FROM CONFORMA WHERE "+
                                "(CODIGO_PQ = "+Integer.toString(codigoPaquete)+") AND "+
                                "(CODIGO_S = "+Integer.toString(codigoServ)+");";
        
        // Se agrega la interrelacion entre el servicio y paquete.
        
        try {
            
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(eliminar);
            gestorBD.cerrarConexion(conexion);
            return resultado > 0;
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        
        return false;
    }
    
    public ArrayList<String> obtenerServicios(int codigoPaquete) {
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        

        
        // Verificar si existe el paquete
        
        String consultaPaquete = "SELECT * FROM PAQUETE WHERE"+
                "(CODIGO = "+Integer.toString(codigoPaquete)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultaPaquete);

            if (!rs.next()) {
                gestorBD.cerrarConexion(conexion);
                stmt.close();
                return null;
            }
                                  
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Obtener los codigos de los servicios 
        
        String consultarCodigos = "SELECT CODIGO_S FROM CONFORMA WHERE "+
                                  "CODIGO_PQ = "+Integer.toString(codigoPaquete)+
                                  ";";
        
        ArrayList<String> listaCod = new ArrayList();
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consultarCodigos);
            
            while (rs.next()) {
                listaCod.add(rs.getString(1));
            }
            
            stmt.close();            
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
                                          
        return listaCod;         
        
    }
    
    public Paquete buscarPaquete(int codigoPaquete) {
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        

        
        // Verificar si existe el paquete
        
        String busquedaPaquete = "SELECT * FROM PAQUETE WHERE "+
                "(CODIGO = "+Integer.toString(codigoPaquete)+");";
        
        try {
        
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(busquedaPaquete);

            if (rs.next()) {
                int codigoPQ = rs.getInt(1);
                int tarifaPQ = rs.getInt(3);
                String nombrePQ = rs.getString(4);
                String tipoPQ = rs.getString(2);
                Paquete pq = new Paquete(codigoPQ,tarifaPQ,nombrePQ,tipoPQ);
                stmt.close();
                gestorBD.cerrarConexion(conexion);
                return pq;
            }
            

        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        
        return null;
    }
    
    public boolean agregarPaquete(Paquete pq) {
        
        int codigoPQ = pq.getCodigo();
        int tarifaPQ = pq.getTarifa();
        String nombrePQ = pq.getNombre();
        String tipoPQ = pq.getTipo();
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        String insertPaquete = "INSERT INTO PAQUETE VALUES (" +
                                Integer.toString(codigoPQ)+ ", '"+
                                tipoPQ + "', " + 
                                Integer.toString(tarifaPQ)+ ", '"+
                                nombrePQ + "');";
        System.out.println(insertPaquete);
        
        try {
        
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(insertPaquete);
            stmt.close();
            gestorBD.cerrarConexion(conexion);
            return resultado > 0;
            

        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        return false;
    }
    
    public boolean borrarPaquete(int codigoPaquete) {
        
        gestionarBaseDatos gestorBD = new gestionarBaseDatos();
        
        Connection conexion = gestorBD.establecerConexion();
        
        String borrarPaquete = "DELETE FROM PAQUETE WHERE "+
                                "CODIGO = "+Integer.toString(codigoPaquete)+";";
        System.out.println(borrarPaquete);
        
        try {
        
            Statement stmt = conexion.createStatement();
            int resultado = stmt.executeUpdate(borrarPaquete);
            gestorBD.cerrarConexion(conexion);
            stmt.close();
            return resultado > 0;
            

        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        gestorBD.cerrarConexion(conexion);
        return false;
    }
    
    public int TarifaTotalPaquetesAdicionales(List<String> Paquetes) {
        
        
        try{

        gestionarBaseDatos BaseDatos = new gestionarBaseDatos();
        
        Connection connection = BaseDatos.establecerConexion();
                
        Statement stmt = connection.createStatement();    
        int resultado = 0; 
        
        for (int j = 0; j < Paquetes.size(); j++) {
            
         
            String consulta = "SELECT TARIFA FROM PAQUETE "
                     + " WHERE (PAQUETE.CODIGO = " +Paquetes.get(j)+") ";

            
        
             ResultSet rs = stmt.executeQuery(consulta);


             while (rs.next()){

                resultado+= rs.getInt(1);

            }
             
        }
        

        
        stmt.close();
        BaseDatos.cerrarConexion(connection); 
        return resultado;
        }

        catch ( Exception e ) {
            System.out.println(e.getMessage());
            int resultado = 5;
            return resultado;
        } 
        

    
    }
    
}
