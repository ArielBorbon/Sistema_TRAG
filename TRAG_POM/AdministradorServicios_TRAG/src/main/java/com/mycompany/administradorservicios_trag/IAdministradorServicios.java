
package com.mycompany.administradorservicios_trag;

import dtos.servicio.ServicioAgregarDTO;
import dtos.servicio.ServicioDetalleDTO;
import dtos.servicio.ServicioResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: IAdministradorServicios.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IAdministradorServicios {
 
    public abstract ServicioDetalleDTO crearServicio(ServicioAgregarDTO dto) throws NegocioException;
    public abstract ServicioDetalleDTO obtenerServicio(Long idServicio) throws NegocioException;
    public abstract List<ServicioResumenDTO> obtenerTodosServicios() throws NegocioException;
    public abstract List<ServicioResumenDTO> obtenerServiciosNombre(String nombreServicio) throws NegocioException;
    
}
