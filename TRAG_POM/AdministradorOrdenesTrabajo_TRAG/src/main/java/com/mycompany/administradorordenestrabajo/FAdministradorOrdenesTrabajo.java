
package com.mycompany.administradorordenestrabajo;

import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import excepciones.NegocioException;

/**
 *
 * Archivo: FAdministradorOrdenesTrabajo.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class FAdministradorOrdenesTrabajo implements IAdministradorOrdenesTrabajo{
    
    private final AdministradorOrdenesTrabajo administradorOrdenesTrabajo;

    public FAdministradorOrdenesTrabajo(AdministradorOrdenesTrabajo administradorOrdenesTrabajo) {
        this.administradorOrdenesTrabajo = administradorOrdenesTrabajo;
    }
    
    @Override
    public OrdenTrabajoDetalleDTO crearOrdenTrabajo(OrdenTrabajoCotizacionAgregarDTO dto) throws NegocioException{
        return administradorOrdenesTrabajo.crearOrdenTrabajo(dto);
    }
    
    @Override
    public OrdenTrabajoDetalleDTO obtenerOrdenTrabajo(Long idOrdenTrabajo) throws NegocioException{
        return administradorOrdenesTrabajo.obtenerOrdenTrabajo(idOrdenTrabajo);
    }
    
    
    
}
