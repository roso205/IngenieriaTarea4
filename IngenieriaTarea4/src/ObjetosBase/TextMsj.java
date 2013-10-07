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
public class TextMsj extends ServiciosAdicionales{
    
    public  TextMsj (Contratable cont){
    
        super(cont);
    }    
        
    public String getNombre(){
    
        return getCont().getNombre() +"TextMsj";
    }
    
    public double getTarifa(){
    
        return getCont().getTarifa() + 1500;
    }
    
}
