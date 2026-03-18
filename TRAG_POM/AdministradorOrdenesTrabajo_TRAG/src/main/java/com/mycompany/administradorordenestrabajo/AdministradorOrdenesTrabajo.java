
package com.mycompany.administradorordenestrabajo;

import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import dtos.cotizacion.CotizacionAgregarDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.ordentrabajo.OrdenTrabajoAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import enums.EstadoCotizacionNegocios;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IOrdenesTrabajoDAO;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * @author 
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
        try {
            return Mapeadores.toDTODetalle(ordenesTrabajoDAO.obtenerOrdenTrabajo(idOrdenTrabajo));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_ORDEN_TRABAJO, e);
        }
    }
    
}
