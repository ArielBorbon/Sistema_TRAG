
package com.mycompany.administradorinsumos_trag;

import dtos.insumos.InsumoAgregarDTO;
import dtos.insumos.InsumoDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IAdministradorInsumos {
    
    public abstract InsumoDetalleDTO crearInsumo(InsumoAgregarDTO dto) throws NegocioException;
    public abstract InsumoDetalleDTO obtenerInsumo(Long idInsumo) throws NegocioException;
    public abstract List<InsumoResumenDTO> obtenerInsumosNombre(String nombreInsumo) throws NegocioException;
}
