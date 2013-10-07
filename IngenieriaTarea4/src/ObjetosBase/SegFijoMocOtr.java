/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBase;
import  Interface.ServiciosAdicionales;
import  Interface.Contratable;
/**
 *
 * @author roso
 */
public class SegFijoMocOtr {
    
    public  SegFijoMocOtr (Contratable cont){
    
        super(cont);
    }    
        
    public String getNombre(){
    
        return getCont().getNombre() +"SegFijoMocOtr";
    }
    
    public double getTarifa(){
    
        return getCont().getTarifa() + 1500;
    }
    
    
}
