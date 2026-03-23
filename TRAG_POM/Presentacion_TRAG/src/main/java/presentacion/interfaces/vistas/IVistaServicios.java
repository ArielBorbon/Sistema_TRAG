
package presentacion.interfaces.vistas;

import dtos.servicio.ServicioResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * Archivo: IVistaServicios.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IVistaServicios extends IVista{
    public void cargarServicios(List<ServicioResumenDTO> servicios);
}
