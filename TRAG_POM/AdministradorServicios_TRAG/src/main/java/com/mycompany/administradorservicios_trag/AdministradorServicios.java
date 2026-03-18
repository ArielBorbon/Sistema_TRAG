
package com.mycompany.administradorservicios_trag;

import dtos.servicio.ServicioAgregarDTO;
import dtos.servicio.ServicioDetalleDTO;
import dtos.servicio.ServicioResumenDTO;
import entidades.Servicio;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import insumoservicio.InsumoServicioDetalleDTO;
import interfaces.IServiciosDAO;
import java.math.BigDecimal;
import java.util.List;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * Archivo: AdministradorServicios.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class AdministradorServicios {

    private IServiciosDAO serviciosDAO;
    
    private static final String MENSAJE_ID_SERVICIO_AUSENTE_OBTENER = "El ID es necesario para obtener un servicio";
    
    private static final String MENSAJE_ERROR_CREAR_SERVICIO = "Error al crear el servicio";
    private static final String MENSAJE_ERROR_OBTENER_SERVICIO = "Error al obtener el servicio";
    private static final String MENSAJE_ERROR_OBTENER_TODOS_SERVICIOS = "Error al obtener los servicios";
    private static final String MENSAJE_ERROR_OBTENER_SERVICIOS_NOMBRE = "Error al obtener los servicios por nombre";

    public AdministradorServicios(IServiciosDAO serviciosDAO) {
        this.serviciosDAO = serviciosDAO;
    }
    
    public ServicioDetalleDTO crearServicio(ServicioAgregarDTO dto) throws NegocioException{
        
        Servicio servicioRegistrar = DTOMapeadores.toEntity(dto);
        
        try {
            return Mapeadores.toDTODetalle(serviciosDAO.crearServicio(servicioRegistrar));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_CREAR_SERVICIO, e);
        }
        
    }
    
    public ServicioDetalleDTO obtenerServicio(Long idServicio) throws NegocioException{
        
        if(idServicio == null) throw new NegocioException(MENSAJE_ID_SERVICIO_AUSENTE_OBTENER);
        
        try {
            ServicioDetalleDTO servicioDetalle = Mapeadores.toDTODetalle(serviciosDAO.obtenerServicio(idServicio));
            List<InsumoServicioDetalleDTO> insumosServicio = servicioDetalle.getInsumosServicio();
            
            for(InsumoServicioDetalleDTO dto: insumosServicio){
                dto.setSubtotal(BigDecimal.valueOf(dto.getCantidadDefault()).multiply(dto.getInsumo().getPrecioSugerido()));
            }
            
            return servicioDetalle;
            
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_SERVICIO, e);
        }
        
    }

    public List<ServicioResumenDTO> obtenerTodosServicios() throws NegocioException {
        
        try {
            return Mapeadores.toDTOServicios(serviciosDAO.obtenerTodosServicios());
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_TODOS_SERVICIOS, e);
        }
        
    }
    
    public List<ServicioResumenDTO> obtenerServiciosNombre(String nombreServicio) throws NegocioException {
        try {
            return Mapeadores.toDTOServicios(serviciosDAO.obtenerServiciosNombre(nombreServicio));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_TODOS_SERVICIOS, e);
        }
    }
        
}
