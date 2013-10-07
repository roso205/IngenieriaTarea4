/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author roso
 */
public abstract class ServiciosAdicionales implements Contratable{
    
    private Contratable cont;
    
    public ServiciosAdicionales(Contratable cont){
        setCont(cont);
    }
    
    public Contratable getCont(){
        return this.cont;
    }
    
    public boolean setCont(Contratable cont){
        this.cont = cont;
        return true;
    }
    
}
