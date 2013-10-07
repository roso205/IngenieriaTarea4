/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBase;
import  Interface.ServiciosAdicionales;
import  Interface.Contratable;

/**
 *
 * @author nilver
 */
public class BuzDMens extends ServiciosAdicionales{
    
    public  BuzDMens (Contratable cont){
    
        super(cont);
    }    
        
    public String getNombre(){
    
        return getCont().getNombre() +"BuzDMens";
    }
    
    public double getTarifa(){
    
        return getCont().getTarifa() + 1500;
    }
    
}
