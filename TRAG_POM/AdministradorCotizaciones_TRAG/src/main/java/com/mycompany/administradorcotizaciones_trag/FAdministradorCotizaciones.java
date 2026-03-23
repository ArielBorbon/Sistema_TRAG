

package com.mycompany.administradorcotizaciones_trag;

import dtos.cotizacion.CotizacionActualizarDTO;
import dtos.cotizacion.CotizacionResumenDTO;
import dtos.cotizacion.CotizacionAgregarDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: FAdministradorCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class FAdministradorCotizaciones implements IAdministradorCotizaciones{
    
    private final AdministradorCotizaciones administradorCotizaciones;

    public FAdministradorCotizaciones(AdministradorCotizaciones administradorCotizaciones) {
        this.administradorCotizaciones = administradorCotizaciones;
    }

    /**
     * Fachada para la creación de una cotización.
     * @param dto Datos de la cotización desde la vista.
     * @return Cotización procesada y validada.
     */
    @Override
    public CotizacionDetalleDTO crearCotizacion(CotizacionAgregarDTO dto) throws NegocioException{
        return administradorCotizaciones.crearCotizacion(dto);
    }

    @Override
    public CotizacionDetalleDTO obtenerCotizacion(Long idCotizacion) throws NegocioException {
        return administradorCotizaciones.obtenerCotizacion(idCotizacion);
    }
    
    @Override
    public CotizacionResumenDTO obtenerResumenCotizacion(Long idCotizacion) throws NegocioException {
        return administradorCotizaciones.obtenerResumenCotizacion(idCotizacion);
    }
    
    @Override
    public List<CotizacionResumenDTO> obtenerTodasCotizaciones() throws NegocioException {
        return administradorCotizaciones.obtenerTodasCotizaciones();
    }
    
    @Override
    public List<CotizacionResumenDTO> obtenerCotizacionesNombre(String nombreCliente) throws NegocioException {
        return administradorCotizaciones.obtenerCotizacionesNombreCliente(nombreCliente);
    }
    
    @Override
    public List<CotizacionResumenDTO> obtenerCotizacionesFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        return administradorCotizaciones.obtenerCotizacionesFecha(fechaInicio, fechaFin);
    }
    
    @Override
    public CotizacionDetalleDTO actualizarCotizacion(CotizacionActualizarDTO dto) throws NegocioException{
        return administradorCotizaciones.actualizarCotizacion(dto);
    }

    @Override
    public CotizacionDetalleDTO eliminarCotizacion(Long idCotizacion) throws NegocioException {
        return administradorCotizaciones.eliminarCotizacion(idCotizacion);
    }

    @Override
    public CotizacionDetalleDTO habilitarCotizacion(Long idCotizacion) throws NegocioException {
        return administradorCotizaciones.habilitarCotizacion(idCotizacion);
    }


}
