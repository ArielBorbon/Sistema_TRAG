
package com.mycompany.administradorinsumos_trag;

import dtos.insumos.InsumoAgregarDTO;
import dtos.insumos.InsumoDetalleDTO;
import excepciones.NegocioException;

/**
 *
 * @author 
 */
public class FAdministradorInsumos implements IAdministradorInsumos{

    private final AdministradorInsumos administradorInsumos;

    public FAdministradorInsumos(AdministradorInsumos administradorInsumos) {
        this.administradorInsumos = administradorInsumos;
    }

    @Override
    public InsumoDetalleDTO crearInsumo(InsumoAgregarDTO dto) throws NegocioException {
        return administradorInsumos.crearInsumo(dto);
    }

    @Override
    public InsumoDetalleDTO obtenerInsumo(Long idInsumo) throws NegocioException {
        return administradorInsumos.obtenerInsumo(idInsumo);
    }
    
}
