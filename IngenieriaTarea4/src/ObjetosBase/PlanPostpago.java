/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBase;

import Interface.Factura;
import Interface.FacturaPostpago;

/**
 *
 * @author yue
 */
public class PlanPostpago extends Plan {
     
    
public PlanPostpago ( int cod, String nombre, String tip, double tarifa) {
    
    super(cod,nombre,tip,tarifa);
    TipoFactura = new FacturaPostpago();
}



}
