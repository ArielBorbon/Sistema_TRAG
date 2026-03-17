
package com.mycompany.administradorservicios_trag;

import dtos.servicio.ServicioAgregarDTO;
import dtos.servicio.ServicioDetalleDTO;
import dtos.servicio.ServicioResumenDTO;
import entidades.Servicio;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IServiciosDAO;
import java.util.List;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * @author 
 */
public class AdministradorServicios {

    private IServiciosDAO serviciosDAO;
    
    private static final String MENSAJE_ID_SERVICIO_AUSENTE_OBTENER = "El ID es necesario para obtener un servicio";
    
    private static final String MENSAJE_ERROR_CREAR_SERVICIO = "Error al crear el servicio";
    private static final String MENSAJE_ERROR_OBTENER_SERVICIO = "Error al obtener el servicio";
    private static final String MENSAJE_ERROR_OBTENER_TODOS_SERVICIOS = "Error al obtener los servicios";

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
            return Mapeadores.toDTODetalle(serviciosDAO.obtenerServicio(idServicio));
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
//        List<ServicioLecturaDTO> serviciosEjemplo = new ArrayList<>();
        
//        serviciosEjemplo.add(
//                new ServicioLecturaDTO(
//                        1L,
//                        "Cambio de filtro de cabina", 
//                        "Descripcion Cambio de filtro de cabina",
//                        new BigDecimal("20000")
//                )
//        );
//        
//        serviciosEjemplo.add(
//                new ServicioLecturaDTO(
//                        2L,
//                        "Cambio de evaporador", 
//                        "Descripcion Cambio de evaporador",
//                        new BigDecimal("15000")
//                )
//        );
//        
//        serviciosEjemplo.add(
//                new ServicioLecturaDTO(
//                        3L,
//                        "Reparación y servicio del compresor", 
//                        "Descripcion Reparación y servicio del compresor",
//                        new BigDecimal("30000")
//                )
//        );
//
//        return serviciosEjemplo;
//        
    }
        
}
