

package com.mycompany.administradorautomoviles_trag;

import dtos.automovil.AutomovilAgregarDTO;
import dtos.automovil.AutomovilDetalleDTO;
import dtos.automovil.AutomovilResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: FAdinistradorAutomoviles.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class FAdministradorAutomoviles implements IAdministradorAutomoviles{
    
    private AdministradorAutomoviles administradorAutomoviles;

    public FAdministradorAutomoviles(AdministradorAutomoviles administradorAutomoviles) {
        this.administradorAutomoviles = administradorAutomoviles;
    }
    
    @Override
    public AutomovilDetalleDTO crearAutomovil(AutomovilAgregarDTO dto) throws NegocioException {
        return administradorAutomoviles.crearAutomovil(dto);
    }

    @Override
    public AutomovilDetalleDTO obtenerAutomovil(Long idAutomovil) throws NegocioException{
        return administradorAutomoviles.obtenerAutomovil(idAutomovil);
    }
    
    @Override
    public List<AutomovilResumenDTO> obtenerTodosAutomoviles() throws NegocioException {
        return administradorAutomoviles.obtenerTodosAutomoviles();
    }

}
