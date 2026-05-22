package com.mycompany.administradorautomoviles_trag;

import dtos.automovil.AutomovilActualizarDTO;
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
public class FAdministradorAutomoviles implements IAdministradorAutomoviles {

    private AdministradorAutomoviles administradorAutomoviles;

    public FAdministradorAutomoviles(AdministradorAutomoviles administradorAutomoviles) {
        this.administradorAutomoviles = administradorAutomoviles;
    }

    @Override
    public AutomovilDetalleDTO crearAutomovil(AutomovilAgregarDTO dto) throws NegocioException {
        return administradorAutomoviles.crearAutomovil(dto);
    }

    @Override
    public AutomovilDetalleDTO obtenerAutomovil(Long idAutomovil) throws NegocioException {
        return administradorAutomoviles.obtenerAutomovil(idAutomovil);
    }

    @Override
    public List<AutomovilResumenDTO> obtenerTodosAutomoviles() throws NegocioException {
        return administradorAutomoviles.obtenerTodosAutomoviles();
    }

    @Override
    public List<AutomovilResumenDTO> obtenerAutomovilesPorCliente(Long idCliente) throws NegocioException {
        return administradorAutomoviles.obtenerAutomovilesPorCliente(idCliente);
    }

    @Override
    public AutomovilDetalleDTO actualizarAutomovil(AutomovilActualizarDTO dto) throws NegocioException {
        return administradorAutomoviles.actualizarAutomovil(dto);
    }

    @Override
    public void deshabilitarAutomovil(Long idAutomovil) throws NegocioException {
        administradorAutomoviles.deshabilitarAutomovil(idAutomovil);
    }

    @Override
    public List<AutomovilResumenDTO> obtenerAutomovilesPorNombreCliente(String nombre) throws NegocioException {
        return administradorAutomoviles.obtenerAutomovilesPorNombreCliente(nombre);
    }
    
}
