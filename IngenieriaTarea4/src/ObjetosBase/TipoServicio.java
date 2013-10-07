/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjetosBase;

/**
 *
 * @author yue
 */
public class TipoServicio {
    
    private int Codigo;
    private String Nombre;

   
    //Constructor
    TipoServicio(int Codigo, String Nombre){
        
        this.Codigo = Codigo;
        this.Nombre = Nombre;

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
    
     
}
