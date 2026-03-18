
package com.mycompany.administradorinsumos_trag;

import dtos.insumos.InsumoAgregarDTO;
import dtos.insumos.InsumoDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import entidades.Insumo;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IInsumosDAO;
import java.util.List;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * Archivo: AdinistradorInusmos.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class AdministradorInsumos {
    
    private final IInsumosDAO insumosDAO;

    private static final String MENSAJE_ERROR_CREAR_INSUMO = "Error al crear el insumo";
    private static final String MENSAJE_ERROR_OBTENER_INSUMO = "Error al obtener el insumo";
    private static final String MENSAJE_ERROR_OBTENER_INSUMOS_NOMBRE = "Error al obtener los insumos por nombre";
    
    public AdministradorInsumos(IInsumosDAO insumosDAO) {
        this.insumosDAO = insumosDAO;
    }
    
    public InsumoDetalleDTO crearInsumo(InsumoAgregarDTO dto) throws NegocioException {
        
        Insumo insumoAgregar = DTOMapeadores.toEntity(dto);
        
        try {
            return Mapeadores.toDTODetalle(insumosDAO.crearInsumo(insumoAgregar));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_CREAR_INSUMO, e);
        }
    }

    public InsumoDetalleDTO obtenerInsumo(Long idInsumo) throws NegocioException {
        
        try {
            return Mapeadores.toDTODetalle(insumosDAO.obtenerInsumo(idInsumo));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_INSUMO, e);
        }
    }
    
    public List<InsumoResumenDTO> obtenerInsumosNombre(String nombreInsumo) throws NegocioException{
        
        try {
            return Mapeadores.toDTOResumenInsumos(insumosDAO.obtenerInsumosNombre(nombreInsumo));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_INSUMOS_NOMBRE, e);
        }
        
        
    }
    
}
