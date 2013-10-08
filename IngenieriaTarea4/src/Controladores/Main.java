/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.Scanner;
import ObjetosBase.Cliente;

/**
 *
 * @author karla
 */
public class Main {
    
    public static void main (String [] args) {
        
        Scanner en = new Scanner(System.in);
        System.out.println("Ingrese su documento de identificacion");
        
        int rif = en.nextInt();
        ControladorConsumidores Consulta = new ControladorConsumidores();
        Cliente cli = Consulta.buscarCliente(rif);
        
        System.out.println("Bienvenido Sr(a). " + cli.getNombre() 
                + "\n Seleccione la opcion de su preferencia: \n" 
                + "1- Generar factura \n" + "2- Agregar servicio adicional");
        
        int sel = en.nextInt();
        
        switch(sel) {
        
            case 1: System.out.println("Ingrese el codigo del producto");
                    int codProd = en.nextInt();
                    ControladorFacturas Consulta2 = new ControladorFacturas();
                    Consulta2.ImprimirFactura(codProd);
                
            case 2: ControlarorServiciosContratables cnt = new ControlarorServiciosContratables();
                    cnt.ContratarServicio();
                
            default:    
                System.out.println("Ingreso una opcion invalida");
        }
    
    }
}
