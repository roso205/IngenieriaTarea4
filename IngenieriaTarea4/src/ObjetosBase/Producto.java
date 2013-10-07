/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBase;

/**
 *
 * @author yue
 */
public class Producto {
    
    private int Codigo;
    private String Nombre;
    private String Descripcion;
    private int CodigoPlan;
    private int RifCliente;
    private String FechaAfiliacion;
    private int Saldo;
   
    //Constructor
    public Producto(int Codigo, String Nombre, String Descripcion, 
            int CodigoPlan, int RifCliente, String FechaAfiliacion, int Saldo){
        
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Descripcion= Descripcion;
        this.CodigoPlan = CodigoPlan;
        this.RifCliente = RifCliente;;
        this.FechaAfiliacion = FechaAfiliacion;
        this.Saldo = Saldo;
        
    }  
    
    
    public void setCodigo(int Codigo) {
        
        this.Codigo = Codigo;
                   
    }

    public int getCodigo() {
       
        return this.Codigo;
              
    }  
    
    public void setNombre(String Nombre) {
        
        this.Nombre = Nombre;
                   
    }

    public String getNombre() {
       
        return this.Nombre;
              
    }  
    
    
    public void setDescripcion(String Descripcion) {
        
        this.Descripcion = Descripcion;
                   
    }

    public String getDescripcion() {
       
        return this.Descripcion;
              
    }  
    
    public void setCodigoPlan(int Codigo_p) {
        
        this.CodigoPlan = CodigoPlan;
                   
    }

    public int getCodigoPlan() {
       
        return this.CodigoPlan;
              
    }  
    
 
    public void setRifCliente(int RifCliente) {
        
        this.RifCliente = RifCliente;
                   
    }

    public int getRifCliente() {
       
        return this.RifCliente;
              
    }  
 
    
    public void setFechaAfiliacion(String FechaAfiliacion) {
        
        this.FechaAfiliacion = FechaAfiliacion;
                   
    }

    public String getFechaAfiliacion() {
       
        return this.FechaAfiliacion;
              
    } 
    
    
    public void setSaldo(int Saldo) {
        
        this.Saldo = Saldo;
                   
    }
    
    public int getSaldo() {
       
        return this.Saldo;
              
    } 
}


