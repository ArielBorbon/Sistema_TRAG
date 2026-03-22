
package presentacion.interfaces.vistas;

import dtos.servicio.ServicioResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * @author 
 */
public interface IVistaServicios extends IVista{
    public void cargarServicios(List<ServicioResumenDTO> servicios);
}
