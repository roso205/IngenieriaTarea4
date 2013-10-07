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
public class SegOtr extends ServiciosAdicionales {
    
    public  SegOtr (Contratable cont){
    
        super(cont);
    }    
        
    public String getNombre(){
    
        return getCont().getNombre() +"Segundos a otras operadoras";
    }
    
    public double getTarifa(){
    
        return getCont().getTarifa() + 1500;
    }
}
