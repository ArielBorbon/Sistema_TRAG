
package com.mycompany.administradorinsumos_trag;

import dtos.insumos.InsumoAgregarDTO;
import dtos.insumos.InsumoDetalleDTO;
import excepciones.NegocioException;

/**
 *
 * @author 
 */
public interface IAdministradorInsumos {
    
    public abstract InsumoDetalleDTO crearInsumo(InsumoAgregarDTO dto) throws NegocioException;
    public abstract InsumoDetalleDTO obtenerInsumo(Long idInsumo) throws NegocioException;
    
}
