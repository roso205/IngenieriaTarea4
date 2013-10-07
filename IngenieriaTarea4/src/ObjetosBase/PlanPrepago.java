/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBase;

import Interface.FacturaPrepago;

/**
 *
 * @author yue
 */
public class PlanPrepago extends Plan {
 
    
    public PlanPrepago ( int cod, String nombre, String tip, double tarifa) {
    
    super(cod,nombre,tip,tarifa);
    TipoFactura = new FacturaPrepago();
    
    }
}
