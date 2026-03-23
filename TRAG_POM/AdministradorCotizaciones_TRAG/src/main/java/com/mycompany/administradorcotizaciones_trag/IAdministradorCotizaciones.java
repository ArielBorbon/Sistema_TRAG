
package com.mycompany.administradorcotizaciones_trag;

import dtos.cotizacion.CotizacionActualizarDTO;
import dtos.cotizacion.CotizacionAgregarDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.cotizacion.CotizacionResumenDTO;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * Archivo: IAdministradorCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */

public interface IAdministradorCotizaciones {
    
    public abstract CotizacionDetalleDTO crearCotizacion(CotizacionAgregarDTO dto) throws NegocioException;
    public abstract CotizacionDetalleDTO obtenerCotizacion(Long idCotizacion) throws NegocioException;
    public abstract CotizacionResumenDTO obtenerResumenCotizacion(Long idCotizacion) throws NegocioException;
    public abstract List<CotizacionResumenDTO> obtenerTodasCotizaciones() throws NegocioException;
    public abstract List<CotizacionResumenDTO> obtenerCotizacionesNombre(String nombre) throws NegocioException;
    public abstract List<CotizacionResumenDTO> obtenerCotizacionesFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException;
    public abstract CotizacionDetalleDTO actualizarCotizacion(CotizacionActualizarDTO dto) throws NegocioException;
    public abstract CotizacionDetalleDTO eliminarCotizacion(Long idCotizacion) throws NegocioException;
    public abstract CotizacionDetalleDTO habilitarCotizacion(Long idCotizacion) throws NegocioException;
}
