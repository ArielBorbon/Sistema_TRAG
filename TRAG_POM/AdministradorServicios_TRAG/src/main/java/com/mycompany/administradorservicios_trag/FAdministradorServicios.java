
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
public class FAdministradorServicios implements IAdministradorServicios{

    private AdministradorServicios administradorServicios;

    public FAdministradorServicios(AdministradorServicios administradorServicios) {
        this.administradorServicios = administradorServicios;
    }
    
    @Override
    public ServicioDetalleDTO crearServicio(ServicioAgregarDTO dto) throws NegocioException{
        return administradorServicios.crearServicio(dto);
    }

    @Override
    public ServicioDetalleDTO obtenerServicio(Long idServicio) throws NegocioException{
        return administradorServicios.obtenerServicio(idServicio);
    }
    
    @Override
    public List<ServicioResumenDTO> obtenerTodosServicios() throws NegocioException{ 
        return administradorServicios.obtenerTodosServicios();
    }

    @Override
    public List<ServicioResumenDTO> obtenerServiciosNombre(String nombreServicio) throws NegocioException {
        return administradorServicios.obtenerServiciosNombre(nombreServicio);
    }
    
}
