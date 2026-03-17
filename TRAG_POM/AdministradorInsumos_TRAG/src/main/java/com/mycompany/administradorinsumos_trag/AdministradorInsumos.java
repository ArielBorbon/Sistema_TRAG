
package com.mycompany.administradorinsumos_trag;

import dtos.insumos.InsumoAgregarDTO;
import dtos.insumos.InsumoDetalleDTO;
import entidades.Insumo;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IInsumosDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * @author 
 */
public class AdministradorInsumos {
    
    private final IInsumosDAO insumosDAO;

    private static final String MENSAJE_ERROR_CREAR_INSUMO = "Error al crear el insumo";
    private static final String MENSAJE_ERROR_OBTENER_INSUMO = "Error al obtener el insumo";
    
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
    
}
