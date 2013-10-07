package ObjetosBase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Karla Alzuro
 */
public class Cliente {
    private String nombre;
    private int rif;
    private String direc;
   
    public Cliente(String nomb,int ri,String dir){

        this.nombre = nomb;
        this.rif = ri;
        this.direc = dir;

    }
    public Cliente(){

        this.nombre = null;
        this.rif = 0;
        this.direc = null;

    }    

    public String getNombre(){
        return this.nombre;
    }
    
     public void SetNombre(String nom){
        this.nombre = nom;
    }

    public int getRif(){
        return this.rif;
    }
    
    public void setRif(int rf){
         this.rif = rf;
    }

    public String getDirec(){
        return this.direc;
    }

    public void setDirec(String ndir){
        this.direc = ndir; 
    }

}