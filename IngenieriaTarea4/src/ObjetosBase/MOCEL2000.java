/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBase;
import Interface.Planes;
/**
 *
 * @author roso
 */
public class MOCEL2000 extends Planes{
       

    @Override
     public int getCodigo(){
       return 25649;
   }
    
   
    @Override
   public String getTipo(){
       return "Prepago";
   }
   
 
   @Override
   public double getTarifa(){
       return 49;
   }  
   
   @Override
   public String getNombre(){
       return "MOCEL-2000";
   }

}
