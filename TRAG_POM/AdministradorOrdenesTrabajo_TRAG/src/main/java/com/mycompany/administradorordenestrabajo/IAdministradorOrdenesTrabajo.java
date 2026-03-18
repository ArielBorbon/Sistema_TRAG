
package com.mycompany.administradorordenestrabajo;

import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import dtos.ordentrabajo.OrdenTrabajoResumenDTO;
import excepciones.NegocioException;

/**
 *
 * @author 
 */
public interface IAdministradorOrdenesTrabajo {
 
    public abstract OrdenTrabajoDetalleDTO crearOrdenTrabajo(OrdenTrabajoCotizacionAgregarDTO dto) throws NegocioException;
    public abstract OrdenTrabajoDetalleDTO obtenerOrdenTrabajo(Long idOrdenTrabajo) throws NegocioException;
    
}
