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
public class PegOtr30 extends ServiciosAdicionales{
    
    public  PegOtr30 (Contratable cont){
    
        super(cont);
    }    
        
     @Override
    public String getNombre(){
    
        return getCont().getNombre() +"Paquete pegadito con otros 30";
    }
    
    @Override
    public double getTarifa(){
    
        return getCont().getTarifa() + 19;
    }
    
    @Override
    public String getTipo(){
       return "Prepago";
    }
    
}
