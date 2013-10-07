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
public class SegMoMcl extends ServiciosAdicionales {
    
    public  SegMoMcl (Contratable cont){
    
        super(cont);
    }    
        
    public String getNombre(){
    
        return getCont().getNombre() +"Segundos a la misma operadora";
    }
    
    public double getTarifa(){
    
        return getCont().getTarifa() + 1500;
    }
}
