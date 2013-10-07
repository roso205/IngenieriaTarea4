package ObjetosBase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arturo
 */
public class Paquete {
    
    // Atributos
    
    private int codigo;
    private int tarifa;
    private String nombre;
    private String tipo;
    
    
    // Constructor
    
    public Paquete(int codigo, int tarifa, String nombre, String tipo) {        
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
    // Metodos
    
    public int getCodigo() {        
        return this.codigo;
    }
    
    public int getTarifa() {        
        return this.tarifa;
    }
    
    public void setTarifa(int tarifa) {        
        this.tarifa =  tarifa;
    }
    
    public String getTipo() {        
        return this.tipo;
    }
    
    public String getNombre() {        
        return this.nombre;
    }
    
    public void imprimir() {
        System.out.println(this.codigo);
        System.out.println(this.tarifa);
        System.out.println(this.tipo);
        System.out.println(this.nombre);
        
    }
    
    
}


