/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladores.*;
import Interface.Factura;
import ObjetosBase.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author yue
 */
public class FacturaPrepago implements Factura {
    
        
    public void ImprimirFactura(Producto Producto,Cliente Cliente,Plan Plan) {
        
        
        //Buscamos los Servicios obtenidos por el Plan
        Afiliaciones Consulta = new Afiliaciones();
        List<String> Paquetes;
        Paquetes = Consulta.BuscarPaquete(Plan.getCodigo());



        ArrayList<String> ServiciosPlan = new ArrayList<String>();
        ArrayList<String> ServiciosPlanTotal = new ArrayList<String>();
        ControladorServiciosOfrecidos Consulta2 = 
                                            new ControladorServiciosOfrecidos();

        for (int i = 0; i < Paquetes.size(); i++) {

            ServiciosPlan = Consulta2.obtenerServicios(
                                             Integer.parseInt(Paquetes.get(i)));
                     
            for (int j = 0; j < ServiciosPlan.size(); j++) {

            ServiciosPlanTotal.add(ServiciosPlan.get(j));

            }
        }
        ControladorConsumidores Consulta3 = new ControladorConsumidores();
        ServiciosPlan = Consulta3.buscarNombreServicios(ServiciosPlanTotal);

        //Buscamos los Servicios adicionales del Prodcuto
        List<String> PaquetesAdicionales;
        PaquetesAdicionales =
                      Consulta3.BuscarPaquetesAdicionales(Producto.getCodigo());
        ArrayList<String> ServiciosAdicionales= new ArrayList<String>();
        ArrayList<String> ServiciosAdicionalesTotal = new ArrayList<String>(); 

        for (int i = 0; i < PaquetesAdicionales.size(); i++) {

            ServiciosAdicionales = Consulta2.obtenerServicios(
                                  Integer.parseInt(PaquetesAdicionales.get(i)));

            for (int j = 0; j < ServiciosAdicionales.size(); j++) {

            ServiciosAdicionalesTotal.add(ServiciosAdicionales.get(j));

            }
         }    

         ServiciosAdicionales = Consulta3.buscarNombreServicios(
                                                    ServiciosAdicionalesTotal);


         //Buscamos los Consumos del Producto
         ArrayList <Consumo> Consumos;

         ControladorConsumos Consulta5 = new ControladorConsumos();
         Consumos = Consulta5.consultarConsumo(Producto.getCodigo());

         
         //Calculamos el TOTAL a pagar      
         double TotalPago = Plan.getTarifa();
         ControladorServiciosOfrecidos Consulta6= 
                                            new ControladorServiciosOfrecidos();
         TotalPago+= 
                  Consulta6.TarifaTotalPaquetesAdicionales(PaquetesAdicionales);

         System.out.println();
         System.out.print("Plan de Producto: ");  
         System.out.println(Plan.getNombre()+" ("+Plan.getTipo()+")");        
         System.out.println();
         System.out.println("Servicios del Producto: ");
         System.out.println("     "+ServiciosPlan);   
         System.out.println();
         System.out.println("Servicios Adicionales del Producto: ");
         System.out.println("     "+ServiciosAdicionales);  
         System.out.println();
         System.out.println("Consumos del Producto: ");     

         //Booleano para verificar si existieron consumos en el periodo
         boolean ver = false;
         Date date = new Date();
         DateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");

          if( !Consumos.isEmpty() ) { 
             
            for (int i= 0 ; i < Consumos.size(); i++) {
              
             //Verificamos que el consumo se realizo en el periodo de la factura
              if (Consumos.get(i).getFecha().before(date) &&
                 Consumos.get(i).getFecha().after(Producto.getFechaAfiliacion())) {  
    
             
                ver = true;
                System.out.println();
                System.out.println("Servicio:  "
                                          +Consumos.get(i).getNombreServicio());
                System.out.println("Codigo Servicio:"
                                             +Consumos.get(i).getcodServicio());
                System.out.println("Fecha del Consumo: "
                                                   +Consumos.get(i).getFecha());   
                System.out.println("Cantidad Consumida: "
                                                +Consumos.get(i).getCantidad());
             }
              
           } 
            
           if (!ver) {
                System.out.println("     El Producto no ha realizado "
                                             + "consumos durante este periodo"); 
           }
            
         } else {
         
              System.out.println("El Producto no ha realizado consumos");  
         }   
         

         
         System.out.println();
         System.out.println("Monto TOTAL a pagar: ");
         System.out.println("     " +TotalPago+" Bs");   
         System.out.println();

    }
}
