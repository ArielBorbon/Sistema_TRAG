
package presentacion.interfaces.vistas;

import dtos.servicio.ServicioDetalleDTO;
import presentacion.interfaces.IVista;

/**
 *
 * @author 
 */
public interface IVistaCrearCotizacion extends IVista{
    
    public abstract void cargarServicioSeleccionado(ServicioDetalleDTO servicio);
    public abstract void mostrarGuardadoPdf();
}
