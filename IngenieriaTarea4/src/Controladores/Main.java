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
        
        Contratable plan = new MIXTOPLUS();
        
        
        ControlarorServiciosContratables cnt = new ControlarorServiciosContratables();
            cnt.VerPlanes();
            cnt.VerPaquetes(plan);
        
    }
        
}
