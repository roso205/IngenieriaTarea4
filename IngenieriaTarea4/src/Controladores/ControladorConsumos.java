package Controladores;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import BaseDatos.Conexion;
import ObjetosBase.*;
import Interface.*;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author nilver
 */
public class ControladorConsumos{
    
      
    public boolean agregarConsumo(int cantidad,int codServicio,Date fecha,int codProducto){
        
        Connection conectar = new Conexion().getConexion();
            
        try{
            
            Statement  s = conectar.createStatement() ; 
            
            
            String validarServicio="SELECT * FROM SERVICIO"
                                    +" WHERE CODIGO ="+codServicio;
          
                                   
            ResultSet resultado = s.executeQuery(validarServicio);
                                                          
            if(!resultado.next()){  
                System.out.println("El servicio no existe");
                return false;
            }    
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        try{
            
            Statement  s = conectar.createStatement() ; 
            
            String validarProducto=" SELECT * FROM PRODUCTO "
                                     +"WHERE CODIGO ="+codProducto;
              
            ResultSet resultado = s.executeQuery(validarProducto);
                                                          
            if(!resultado.next()){  
                System.out.println("El producto no existe");
                return false;
            }    
         
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
            
        
        
        ControladorConsumidores Consulta = new ControladorConsumidores();
        Producto Producto = Consulta.buscarProducto(codProducto);
        
        Afiliaciones Consulta2 = new Afiliaciones();   
        String TipoPlanProducto =
                            Consulta2.buscarTipoPlan(Producto.getCodigoPlan());

        String agregar;
        
        if (TipoPlanProducto.equalsIgnoreCase("POSTPAGO")) {
            

            agregar = "INSERT INTO CONSUMO (CODIGO_S,CODIGO_PR,FECHA,CANTIDAD)"
                    + "VALUES ("+Integer.toString(codServicio)+","
                    +Integer.toString(codProducto)+","+fecha+","
                                                                 +cantidad+")";
          
        } else {
            System.out.println("");
                  
            
     
            ControladorConsumos Consulta3 = new ControladorConsumos();
            ArrayList<Consumo> Consumos=Consulta3.consultarConsumo(codProducto);
            Plan Plan =  Consulta2.buscarPlan(Producto.getCodigoPlan());
            ControladorServiciosOfrecidos Consulta4 =
                                            new ControladorServiciosOfrecidos();
            
            ArrayList<String> ServiciosPlan = new ArrayList<String>();
            ArrayList<Servicio> ServiciosPlanTotal = new ArrayList<Servicio>();
          
            List<String> Paquetes = Consulta2.BuscarPaquete(Plan.getCodigo());
                     
            for (int i = 0; i < Paquetes.size(); i++) {

                ServiciosPlan = Consulta4.obtenerServicios(
                                            Integer.parseInt(Paquetes.get(i)));

                for (int j = 0; j < ServiciosPlan.size(); j++) {

                 ServiciosPlanTotal.add(Consulta4.buscarServicioPaquete(
                            Integer.parseInt(ServiciosPlan.get(j)), 
                            Integer.parseInt(Paquetes.get(i))));
                 


                }
            }
            
   
            int segMOCEL = 0;
            int segOtras = 0;
            int msj = 0;
           
            //Creamos las variables con las cantidades de los servicios
            //que obtienen del plan
            for (int i = 0; i < ServiciosPlanTotal.size(); i++) {

               if (ServiciosPlanTotal.get(i).getCodigo() == 1014 ){
                    segMOCEL += ServiciosPlanTotal.get(i).getCantidad();
               } else if (ServiciosPlanTotal.get(i).getCodigo() == 1054 ){
                    segOtras += ServiciosPlanTotal.get(i).getCantidad();
               } else if (ServiciosPlanTotal.get(i).getCodigo() == 1075 ){
                    msj += ServiciosPlanTotal.get(i).getCantidad();
               }
            }         
            
   
            //Buscamos los Servicios adicionales del Prodcuto
           List<String> PaquetesAdicionales;
           PaquetesAdicionales =
                       Consulta.BuscarPaquetesAdicionales(Producto.getCodigo());
           ArrayList<String> ServiciosAdicionales= new ArrayList<String>();
           ArrayList<Servicio> ServiciosAdicionalesTotal =
                                                 new ArrayList<Servicio>(); 

           for (int i = 0; i < PaquetesAdicionales.size(); i++) {

               ServiciosAdicionales = Consulta4.obtenerServicios(
                                  Integer.parseInt(PaquetesAdicionales.get(i)));

               for (int j = 0; j < ServiciosAdicionales.size(); j++) {

                 ServiciosAdicionalesTotal.add(Consulta4.buscarServicioPaquete(
                            Integer.parseInt(ServiciosPlan.get(j)), 
                            Integer.parseInt(Paquetes.get(i))));

               }
            }            
                     
            for (int i = 0; i < ServiciosAdicionalesTotal.size(); i++) {

               if (ServiciosAdicionalesTotal.get(i).getCodigo() == 1014 ){
                    segMOCEL += ServiciosAdicionalesTotal.get(i).getCantidad();
               } else if (ServiciosAdicionalesTotal.get(i).getCodigo() == 1054){
                    segOtras += ServiciosAdicionalesTotal.get(i).getCantidad();
               } else if (ServiciosAdicionalesTotal.get(i).getCodigo() == 1075){
                    msj += ServiciosAdicionalesTotal.get(i).getCantidad();
               }
            }    
            
            System.out.println(segMOCEL);   
                System.out.println(segOtras);   
                            System.out.println(msj);   
            //Variables 
            //int SegMOCEL 
            
            for (int i= 0 ; i < Consumos.size(); i++) {
    
               
            }
            
        }
        
        
        agregar= null;

        
         
        try{
            
           Statement  s = conectar.createStatement() ; 
            
           int resultado = s.executeUpdate(agregar);
            
           return resultado>0;                
         
             
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
         
         return false;
    }
    
        
      public ArrayList<Consumo> consultarConsumo(int codProducto){
        
        Connection conectar = new Conexion().getConexion();
            
         try{
     
        Statement stm = conectar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY); 
        String consulta= "SELECT C.CODIGO_PR,S.NOMBRE AS NOMBRE_SERVICIO,"
                + "S.CODIGO AS COD_SERVICIO,C.CANTIDAD,C.FECHA "
                + "FROM SERVICIO S,CONSUMO C,PRODUCTO P WHERE "
                + "C.CODIGO_PR= P.CODIGO AND CODIGO_S= S.CODIGO AND C.CODIGO_PR ="+codProducto;
        
        ArrayList<Consumo> ConsumosProducto= new ArrayList<Consumo>();
        ResultSet resultado = stm.executeQuery(consulta);
       
         resultado.last();
        
        if(0<resultado.getRow()){
            resultado.beforeFirst();
            while(resultado.next()){
                Consumo Consumo = new Consumo();
                
                Consumo.setcodProducto(Integer.parseInt(
                                             resultado.getString("CODIGO_PR")));  
              Consumo.setNombreServicio(resultado.getString("NOMBRE_SERVICIO"));             
                Consumo.setcodServicio(Integer.parseInt(
                                          resultado.getString("COD_SERVICIO")));
                Consumo.setcCantidad(Integer.parseInt(
                                              resultado.getString("CANTIDAD")));
                Consumo.setFecha(resultado.getDate("FECHA"));

                ConsumosProducto.add(Consumo);
                
            }

        }else {
            System.out.println("No existen consumos  asociados al producto "
                                                                 + codProducto);

        }
     conectar.close();
     return ConsumosProducto;
        } catch (Exception e ) { 
           System.out.println("Se ha producido un error en la  base de datos!");
            System.out.println(e.getMessage());
            return null;
        }
         
               
    }
    
    
    public void listarConsumos(){
        
           Connection conectar = new Conexion().getConexion();
            
         try{
     
        Statement stm = conectar.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY); 
       
        String consulta1 =" SELECT C.CODIGO_PR,P.NOMBRE,S.CODIGO,S.NOMBRE,C.CANTIDAD,C.FECHA "
               + "FROM CONSUMO C "
               +"JOIN PRODUCTO P (C.CODIGO_PR=P.CODIGO) "
               + "JOIN SERVICIO S(C.CODIGO_S= S.CODIGO)";
        
        ResultSet resultado = stm.executeQuery(consulta1);
       
        System.out.print(consulta1);
        
        resultado.last();
        
        if(0<resultado.getRow()){
            resultado.beforeFirst();
            System.out.println("Consumos ");
            while(resultado.next()){
                
                System.out.println(resultado.getString("CODIGO_ṔR")+"  "+
                        resultado.getString("CODIGO_S")+" "+
                        resultado.getString("NOMPRO")+" "+
                        resultado.getNString("FECHA")+
                        resultado.getNString("CATIDAD"));

            }
        }else {
            System.out.println("No existen consumos  ");
        }
     conectar.close();
        } catch (Exception e ) { 
            System.out.println("Se ha producido un error en la  base de datos !");
            System.out.println(e.getMessage());
        }
          
    }
    
    public void recargarSaldo(int CodigoProducto, int Recarga) {
        
        ControladorConsumidores Consulta = new ControladorConsumidores();
        Producto Producto = Consulta.buscarProducto(CodigoProducto);
                
        ControladorFacturas Factura = new ControladorFacturas();
        Factura.ImprimirFactura(CodigoProducto);
        
        Producto.setSaldo(Producto.getSaldo()+Recarga);
                
        
    }
    
    
    

}