/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ObjetosBase.*;
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
     
        
        
         //Imprimimos la Factura
         System.out.println();
         System.out.print("FACTURA DEL PRODUCTO: "+CodigoProducto);  
         System.out.println(" Fecha de emision de la Factura: "
                                                           +Fecha.format(date)); 
         System.out.println();
         System.out.println("Nombre Cliente: "+Cliente.getNombre());
         System.out.println("Direccion del Cliente: "+Cliente.getDirec()); 


         
        //Buscamos el Plan que posee el producto
        Plan Plan;
        Afiliaciones Consulta3 = new Afiliaciones();
        Plan = Consulta3.buscarPlan(Producto.getCodigoPlan());

        //Revisamos que tipo de plan para seguir imprimiendo
        //Metodo Estrategia
        if (Plan.getTipo().equalsIgnoreCase("PREPAGO") ){
            
            PlanPrepago TipoPlan = new PlanPrepago(Plan.getCodigo(),
                              Plan.getTipo(),Plan.getNombre(),Plan.getTarifa());
            TipoPlan.ImprimirFactura(Producto,Cliente,Plan);
        } else {
            
            PlanPostpago TipoPlan = new PlanPostpago(Plan.getCodigo(),
                              Plan.getTipo(),Plan.getNombre(),Plan.getTarifa());    
            TipoPlan.ImprimirFactura(Producto,Cliente,Plan);
        }



         System.out.println("Observaciones: ");
         System.out.println("     No hay observaciones ");     
         System.out.println();
         System.out.println("Periodo de Facturacion del "
                       +Fecha.format(FechaInicioMes)+" al "+Fecha.format(date));

     }
}