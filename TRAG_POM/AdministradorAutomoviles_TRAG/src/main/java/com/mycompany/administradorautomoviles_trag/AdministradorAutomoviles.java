
package com.mycompany.administradorautomoviles_trag;

import dtos.automovil.AutomovilAgregarDTO;
import dtos.automovil.AutomovilDetalleDTO;
import dtos.automovil.AutomovilResumenDTO;
import entidades.Automovil;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IAutomovilesDAO;
import java.util.List;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * Archivo: AdinistradorAutomoviles.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class AdministradorAutomoviles {

    private IAutomovilesDAO automovilesDAO;
    
    private static final String MENSAJE_ID_AUTOMOVIL_AUSENTE_OBTENER = "El ID es necesario para obtener un automóvil";
    
    private static final String MENSAJE_ERROR_CREAR_AUTOMOVIL = "Error al crear el automóvil";
    private static final String MENSAJE_ERROR_OBTENER_AUTOMOVIL = "Error al obtener el automóvil";
    private static final String MENSAJE_ERROR_OBTENER_TODOS_AUTOMOVILES = "Error al obtener los automoviles";

    public AdministradorAutomoviles(IAutomovilesDAO automovilesDAO) {
        this.automovilesDAO = automovilesDAO;
    }
    
    public AutomovilDetalleDTO crearAutomovil(AutomovilAgregarDTO dto) throws NegocioException{

        Automovil automovil = DTOMapeadores.toEntity(dto);
        
        try {
            return Mapeadores.toDTODetalle(automovilesDAO.crearAutomovil(automovil));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_CREAR_AUTOMOVIL, e);
        }
        
    }
    
    public AutomovilDetalleDTO obtenerAutomovil(Long idAutomovil) throws NegocioException{
        
        if(idAutomovil == null) throw new NegocioException(MENSAJE_ID_AUTOMOVIL_AUSENTE_OBTENER);
        
        try {
            return Mapeadores.toDTODetalle(automovilesDAO.obtenerAutomovil(idAutomovil));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_AUTOMOVIL, e);
        }
        
    }

    public List<AutomovilResumenDTO> obtenerTodosAutomoviles() throws NegocioException{
        
        try {
            return Mapeadores.toDTOAutomoviles(automovilesDAO.obtenerTodosAutomoviles());
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_TODOS_AUTOMOVILES, e);
        }
        
    }
    
    
}
