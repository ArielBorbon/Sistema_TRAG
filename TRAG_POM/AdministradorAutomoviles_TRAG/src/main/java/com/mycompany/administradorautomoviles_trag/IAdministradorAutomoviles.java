

package com.mycompany.administradorautomoviles_trag;

import dtos.automovil.AutomovilAgregarDTO;
import dtos.automovil.AutomovilDetalleDTO;
import dtos.automovil.AutomovilResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: IAdinistradorAutomoviles.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IAdministradorAutomoviles {
    
    public abstract AutomovilDetalleDTO crearAutomovil(AutomovilAgregarDTO dto) throws NegocioException;
    public abstract AutomovilDetalleDTO obtenerAutomovil(Long idAutomovil) throws NegocioException;
    public abstract List<AutomovilResumenDTO> obtenerTodosAutomoviles() throws NegocioException;
    
}
