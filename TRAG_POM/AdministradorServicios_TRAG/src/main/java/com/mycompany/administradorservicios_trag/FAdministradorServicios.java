
package com.mycompany.administradorservicios_trag;

import dtos.servicio.ServicioAgregarDTO;
import dtos.servicio.ServicioDetalleDTO;
import dtos.servicio.ServicioResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: FAdministradorServicios.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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
