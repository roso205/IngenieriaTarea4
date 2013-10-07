/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author roso
 */
public abstract class Planes implements Contratable {
    
    protected int codigo;
    protected String tipo; 
    protected double tarifa;
    protected String nombre;
    
   public int getCodigo(){
       return this.codigo;
   }
    
   public  boolean setCodigo(int cod){
       this.codigo = cod;
       return true;
   }
   
    @Override
   public String getTipo(){
       return this.tipo;
   }
   
   public  boolean setTipo(String tip){
       this.tipo = tip;
       return true;
   }
   
   @Override
   public double getTarifa(){
       return this.tarifa;
   }   

   public boolean setTarifa(double tar){
       this.tarifa = tar;
       return true;
   } 
   
   @Override
   public String getNombre(){
       return this.nombre;
   }

   public boolean setNombre(String nom){
       this.nombre = nom;
       return true;
   }
   
}
    