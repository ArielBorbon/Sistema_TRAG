
package com.mycompany.administradorinsumos_trag;

import dtos.insumos.InsumoAgregarDTO;
import dtos.insumos.InsumoDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: FAdinistradorInusmos.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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

    @Override
    public List<InsumoResumenDTO> obtenerInsumosNombre(String nombreInsumo) throws NegocioException {
        return administradorInsumos.obtenerInsumosNombre(nombreInsumo);
    }
    
}
