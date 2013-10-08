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
public class MixPls extends ServiciosAdicionales{
    
    public  MixPls (Contratable cont){
    
        super(cont);
    }    
        
    @Override
    public String getNombre(){
    
        return getCont().getNombre() +"junto con el paquete mixto plus";
    }
    
    @Override
    public double getTarifa(){
    
        return getCont().getTarifa() + 211;
    }
    
    @Override
     public String getTipo(){
        return "UNICO";
     }
    
}
