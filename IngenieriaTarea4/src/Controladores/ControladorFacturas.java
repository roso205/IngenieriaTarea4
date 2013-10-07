/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ObjetosBase.Producto;
import ObjetosBase.Cliente;
import ObjetosBase.Consumo;
import ObjetosBase.Plan;
import java.util.*;
import java.text.*;


/**
 *
 * @author yue
 */
public class ControladorFacturas {
 
    
    
    public void ImprimirFactura(int CodigoProducto) {
    
        
        //Buscamos el Producto con el codigo ingresado   
        Producto Producto;
        ControladorConsumidores Consulta = new ControladorConsumidores();
        Producto = Consulta.buscarProducto(CodigoProducto);

        //Buscamos el Cliente que posee el producto
        Cliente Cliente;
        ControladorConsumidores Consulta2 = new ControladorConsumidores();
        Cliente = Consulta2.buscarCliente(Producto.getRifCliente());

        //Buscamos la fecha del dia en que se genera la factura
        //Tambien buscamos el periodo de se realizara la factura
        Date date = new Date();
        DateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -30);
        Date FechaInicioMes = cal.getTime(); 
        DateFormat Fecha2 = new SimpleDateFormat("dd/MM/yyyy");
       
        //Buscamos el Plan que posee el producto
        Plan Plan;
        Afiliaciones Consulta3 = new Afiliaciones();
        Plan = Consulta3.buscarPlan(Producto.getCodigoPlan());

        //Buscamos los Servicios obtenidos por el Plan
        List<String> Paquetes;
        Paquetes = Consulta3.BuscarPaquete(Plan.getCodigo());



        ArrayList<String> ServiciosPlan = new ArrayList<String>();
        ArrayList<String> ServiciosPlanTotal = new ArrayList<String>();
        ControladorServiciosOfrecidos Consulta4 = 
                                            new ControladorServiciosOfrecidos();
        System.out.println(Paquetes);

        for (int i = 0; i < Paquetes.size(); i++) {

            ServiciosPlan = Consulta4.obtenerServicios(
                                             Integer.parseInt(Paquetes.get(i)));
                     
            for (int j = 0; j < ServiciosPlan.size(); j++) {

            ServiciosPlanTotal.add(ServiciosPlan.get(j));

            }
        }

        ServiciosPlan = Consulta.buscarNombreServicios(ServiciosPlanTotal);

        //Buscamos los Servicios adicionales del Prodcuto
        List<String> PaquetesAdicionales;
        PaquetesAdicionales =Consulta.BuscarPaquetesAdicionales(CodigoProducto);
        ArrayList<String> ServiciosAdicionales= new ArrayList<String>();
        ArrayList<String> ServiciosAdicionalesTotal = new ArrayList<String>(); 

        for (int i = 0; i < PaquetesAdicionales.size(); i++) {

            ServiciosAdicionales = Consulta4.obtenerServicios(
                                  Integer.parseInt(PaquetesAdicionales.get(i)));

            for (int j = 0; j < ServiciosAdicionales.size(); j++) {

            ServiciosAdicionalesTotal.add(ServiciosAdicionales.get(j));

            }
         }    

         ServiciosAdicionales = Consulta.buscarNombreServicios(
                                                    ServiciosAdicionalesTotal);


         //Buscamos los Consumos del Producto
         ArrayList <Consumo> Consumos;

         ControladorConsumos Consulta5 = new ControladorConsumos();
         Consumos = Consulta5.consultarConsumo(CodigoProducto);

         
         //Calculamos el TOTAL a pagar      
         double TotalPago = Plan.getTarifa();
         ControladorServiciosOfrecidos Consulta6= 
                                            new ControladorServiciosOfrecidos();
         TotalPago+= 
                  Consulta6.TarifaTotalPaquetesAdicionales(PaquetesAdicionales);


         //Imprimimos la Factura
         System.out.println();
         System.out.print("FACTURA DEL PRODUCTO: "+CodigoProducto);  
         System.out.println(" Fecha de emision de la Factura: "
                                                           +Fecha.format(date)); 
         System.out.println();
         System.out.println("Nombre Cliente: "+Cliente.getNombre());
         System.out.println("Direccion del Cliente: "+Cliente.getDirec()); 
         System.out.println("Plan del Producto: "+Plan.getNombre());
         System.out.println();
         System.out.println("Servicios del Producto: ");
         System.out.println("     "+ServiciosPlan);   
         System.out.println();
         System.out.println("Servicios Adicionales del Producto: ");
         System.out.println("     "+ServiciosAdicionales);  
         System.out.println();
         System.out.println("Consumos del Producto: ");     

         //Boleano para verificar si existieron cosumos en el periodo
         boolean ver = false;
          
         if( !Consumos.isEmpty() ) { 
             
            for (int i= 0 ; i < Consumos.size(); i++) {
              
             //Verificamos que el consumo se realizo en el periodo de la factura
              if (Consumos.get(i).getFecha().before(date) &&
                             Consumos.get(i).getFecha().after(FechaInicioMes)) {  
    
             
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
         System.out.println("Observaciones: ");
         System.out.println("     No hay observaciones ");     
         System.out.println();
         System.out.println("Periodo de Facturacion del "
                       +Fecha.format(FechaInicioMes)+" al "+Fecha.format(date));

     }
}