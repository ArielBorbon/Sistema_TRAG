
package com.mycompany.administradorordenestrabajo;

import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import excepciones.NegocioException;

/**
 *
 * Archivo: IAdministradorOrdenesTrabajo.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IAdministradorOrdenesTrabajo {
 
    public abstract OrdenTrabajoDetalleDTO crearOrdenTrabajo(OrdenTrabajoCotizacionAgregarDTO dto) throws NegocioException;
    public abstract OrdenTrabajoDetalleDTO obtenerOrdenTrabajo(Long idOrdenTrabajo) throws NegocioException;
    
}
