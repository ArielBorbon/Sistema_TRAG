
package com.mycompany.administradorservicios_trag;

import dtos.servicio.ServicioAgregarDTO;
import dtos.servicio.ServicioDetalleDTO;
import dtos.servicio.ServicioResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IAdministradorServicios {
 
    public abstract ServicioDetalleDTO crearServicio(ServicioAgregarDTO dto) throws NegocioException;
    public abstract ServicioDetalleDTO obtenerServicio(Long idServicio) throws NegocioException;
    public abstract List<ServicioResumenDTO> obtenerTodosServicios() throws NegocioException;
    public abstract List<ServicioResumenDTO> obtenerServiciosNombre(String nombreServicio) throws NegocioException;
    
}
