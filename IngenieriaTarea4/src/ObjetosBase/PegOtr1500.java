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
public class PegOtr1500 extends ServiciosAdicionales{
    
    public  PegOtr1500 (Contratable cont){
    
        super(cont);
    }    
        
     @Override
    public String getNombre(){
    
        return getCont().getNombre() +"Paquete pegadito con otros 1500";
    }
    
    @Override
    public double getTarifa(){
    
        return getCont().getTarifa() + 16;
    }
    
    @Override
    public String getTipo(){
       return "Postpago";
    }
    
}
