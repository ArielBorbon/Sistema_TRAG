
package com.mycompany.administradorordenestrabajo;

import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import excepciones.NegocioException;

/**
 *
 * @author 
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
