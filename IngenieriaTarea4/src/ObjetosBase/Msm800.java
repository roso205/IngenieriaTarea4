/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBase;
import  Interface.ServiciosAdicionales;
import  Interface.Contratable;

/**
 *
 * @author karla
 */
public class Msm800 extends ServiciosAdicionales {
    
    public  Msm800 (Contratable cont){
    
        super(cont);
    }    
        
    @Override
    public String getNombre(){
    
        return getCont().getNombre() +" junto con el paquete de mensajes 800";
    }
    
    @Override
    public double getTarifa(){
    
        return getCont().getTarifa() + 38;
    }
    
    @Override
    public String getTipo(){
       return "UNICO";
    }
    
      
    
}
