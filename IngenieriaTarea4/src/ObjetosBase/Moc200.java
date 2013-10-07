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
public class Moc200 extends ServiciosAdicionales {
    
    public  Moc200 (Contratable cont){
    
        super(cont);
    }    
        
    @Override
    public String getNombre(){
    
        return getCont().getNombre() +" junto con el paquete Mocel 200";
    }
    
    @Override
    public double getTarifa(){
    
        return getCont().getTarifa() + 49;
    }
    
     @Override
     public String getTipo(){
        return "Prepago";
     }
}
