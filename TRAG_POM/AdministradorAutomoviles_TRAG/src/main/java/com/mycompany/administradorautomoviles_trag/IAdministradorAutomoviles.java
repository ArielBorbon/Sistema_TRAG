

package com.mycompany.administradorautomoviles_trag;

import dtos.automovil.AutomovilAgregarDTO;
import dtos.automovil.AutomovilDetalleDTO;
import dtos.automovil.AutomovilResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author 
 * 
 */
public interface IAdministradorAutomoviles {
    
    public abstract AutomovilDetalleDTO crearAutomovil(AutomovilAgregarDTO dto) throws NegocioException;
    public abstract AutomovilDetalleDTO obtenerAutomovil(Long idAutomovil) throws NegocioException;
    public abstract List<AutomovilResumenDTO> obtenerTodosAutomoviles() throws NegocioException;
    
}
