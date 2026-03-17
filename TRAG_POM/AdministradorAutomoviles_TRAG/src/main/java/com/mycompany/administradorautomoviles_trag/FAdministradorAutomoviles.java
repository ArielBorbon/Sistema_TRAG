

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
