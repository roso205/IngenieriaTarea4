package ObjetosBase;


/**
 *
 * @author yue
 */
public class Servicio {
    
    private int Codigo;
    private String Nombre;
    private int Costo;
    private int CodigoTipo;
   
    //Constructor
    public Servicio(int Codigo, String Nombre, int Costo, int CodigoTipo){
        
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Costo = Costo;
        this.CodigoTipo = CodigoTipo;
        
    }
          
      // Metodos
    
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
    
    public void setCosto(int Costo) {
        
        this.Costo = Costo;
                   
    }

    public int getCosto() {
       
        return this.Costo;
              
    } 

     public void setCodigo_TS(int CodigoTipo) {
        
        this.CodigoTipo = CodigoTipo;
                   
    }

    public int getCodigo_TS() {
       
        return this.CodigoTipo;
              
    }    
    
     
}