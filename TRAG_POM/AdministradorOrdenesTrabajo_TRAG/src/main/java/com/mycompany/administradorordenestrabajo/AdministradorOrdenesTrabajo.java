
package com.mycompany.administradorordenestrabajo;

import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import dtos.cotizacion.CotizacionAgregarDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.ordentrabajo.OrdenTrabajoAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IOrdenesTrabajoDAO;
import java.time.LocalDateTime;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * Archivo: AdministradorOrdenesTrabajo.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class AdministradorOrdenesTrabajo {
 
    private final IOrdenesTrabajoDAO ordenesTrabajoDAO;
    private final IAdministradorCotizaciones administradorCotizaciones;
    
    private static final String MENSAJE_ERROR_CREAR_ORDEN_TRABAJO = "Error al crear la orden de trabajo";
    private static final String MENSAJE_ERROR_OBTENER_ORDEN_TRABAJO = "Error al obtener la orden de trabajo";

    public AdministradorOrdenesTrabajo(IOrdenesTrabajoDAO ordenesTrabajoDAO, IAdministradorCotizaciones administradorCotizaciones) {
        this.ordenesTrabajoDAO = ordenesTrabajoDAO;
        this.administradorCotizaciones = administradorCotizaciones;
    }  
    
    public OrdenTrabajoDetalleDTO crearOrdenTrabajo(OrdenTrabajoCotizacionAgregarDTO dto) throws NegocioException{
        
        Long idAutomovil = dto.getIdAutomovil();
        Long idServicio = dto.getIdServicio();
        
        if(idAutomovil == null || idServicio == null){
            throw new NegocioException(MENSAJE_ERROR_CREAR_ORDEN_TRABAJO);
        }
        
        if(dto.getFechaCreacion() == null){
            dto.setFechaCreacion(LocalDateTime.now());
        }
        
        CotizacionDetalleDTO cotizacionCreadaDTO = null;
        try {
            cotizacionCreadaDTO = administradorCotizaciones.crearCotizacion(
                    new CotizacionAgregarDTO(
                            dto.getPrecioManoObra(),
                            dto.getEstadoAutomovil(),
                            dto.getDiagnosticoGeneral(),
                            dto.getFechaCreacion(),
                            dto.getInsumosCotizacion(),
                            idServicio)
            );
        } catch (NegocioException e) {
            throw new NegocioException(MENSAJE_ERROR_CREAR_ORDEN_TRABAJO, e);
        }
        
        if(cotizacionCreadaDTO != null){
            try {
                return Mapeadores.toDTODetalle(ordenesTrabajoDAO.crearOrdenTrabajo(
                        DTOMapeadores.toEntity(
                                new OrdenTrabajoAgregarDTO(
                                        idAutomovil,
                                        cotizacionCreadaDTO.getId()
                                )
                        )
                ));
            } catch (PersistenciaException e) {
                throw new NegocioException(MENSAJE_ERROR_CREAR_ORDEN_TRABAJO, e);
            }
        } else{
            throw new NegocioException(MENSAJE_ERROR_CREAR_ORDEN_TRABAJO);
        }
        
        
    }
    
    public OrdenTrabajoDetalleDTO obtenerOrdenTrabajo(Long idOrdenTrabajo) throws NegocioException{
        
        if(idOrdenTrabajo == null){
            throw new NegocioException(MENSAJE_ERROR_OBTENER_ORDEN_TRABAJO);
        }
        
        try {
            return Mapeadores.toDTODetalle(ordenesTrabajoDAO.obtenerOrdenTrabajo(idOrdenTrabajo));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_ORDEN_TRABAJO, e);
        }
    }
    
}
