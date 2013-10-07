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
public class MIXTOPLUS extends Planes{
       

    @Override
     public int getCodigo(){
       return 27564;
   }
    
   
    @Override
   public String getTipo(){
       return "Postpago";
   }
   
 
   @Override
   public double getTarifa(){
       return 211;
   }  
   
   @Override
   public String getNombre(){
       return "MIXTO-PLUS";
   }

}