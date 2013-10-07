 package ObjetosBase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
/**
 *
 * @author nilver
 */
public class Consumo {
    private int Cantidad;
    private int CodServicio; 
    private Date Fecha; 
    private int CodProducto; 
    private String NombreServicio;
    
    public Consumo() {
    
       
    this.Cantidad= 0;
    this.CodProducto= 0; 
    this.Fecha= null;
    this.CodServicio= 0;   
    this.NombreServicio = null;
}
    public Consumo(int Cantidad,int CodServicio, Date Fecha,
                   int CodProducto, String NombreServicio){

        this.Cantidad= Cantidad;
        this.CodProducto= CodProducto; 
        this.Fecha= Fecha;
        this.CodServicio= CodServicio;
        this.NombreServicio = NombreServicio;
    }

    public int getcodServicio(){

        return this.CodServicio;
    }

    public void setcodServicio(int CodigoServicio){

        this.CodServicio = CodigoServicio;
    }

    public int getcodProducto(){

        return this.CodProducto;
    }

    public void setcodProducto(int CodigoProducto){

        this.CodServicio = CodigoProducto;
    }

    public Date getFecha(){

        return this.Fecha;
    }

    public void setFecha(Date Fecha){

        this.Fecha= Fecha;
    }

    public int getCantidad(){

        return this.Cantidad;
    }

    public void setcCantidad(int Cantidad){

        this.Cantidad = Cantidad;
    }
    
    public String getNombreServicio(){

        return this.NombreServicio;
    }

    public void setNombreServicio(String NombreServicio){

        this.NombreServicio= NombreServicio;
    }
}