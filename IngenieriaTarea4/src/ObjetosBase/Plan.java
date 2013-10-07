package ObjetosBase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roso
 */
public class Plan {
    
    private int codigo;
    private String tipo;
    private double tarifa;
    private String nombre;
    

public Plan(int cod, String nombre, String tip, double tarifa ){

    this.codigo = cod;
    this.tipo = tip;
    this.tarifa = tarifa;
    this.nombre = nombre;

}
    
public int getCodigo(){
     return this.codigo;

}

public void setCodigo(int cod){
     this.codigo = cod;

}

public String getTipo(){
     return this.tipo;

}

public void setTipo(String tip){
     this.tipo = tip;

}
    
public Double getTarifa(){
     return this.tarifa;

}

public  void setTarifa(Double tar){
      this.tarifa = tar;

}

public String getNombre(){
        return this.nombre;

}

public void setNombre(String nom){
        this.nombre = nom;

}


}