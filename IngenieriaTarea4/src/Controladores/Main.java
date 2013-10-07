/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.text.*;
import ObjetosBase.*;
import Interface.*;


/**
 * 
 *
 * @author arturo
 */
public class Main {
    
    public static void main(String[] args) {
        
        
       //PlanPostpago Puta = new PlanPostpago(5,"hola","chao",9);
        
     //  Puta.ImprimirFactura(74223);

         // PlanPrepago Puta2 = new PlanPrepago(5,"hola","chao",9);
        
     // Puta2.ImprimirFactura(74223);         
        
         ControladorFacturas Factura = new ControladorFacturas();
        
        Factura.ImprimirFactura(74223);
         
      
    }
        
}
