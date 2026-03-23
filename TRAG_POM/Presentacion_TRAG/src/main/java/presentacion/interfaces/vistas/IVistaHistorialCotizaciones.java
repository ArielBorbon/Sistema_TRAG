
package presentacion.interfaces.vistas;

import dtos.cotizacion.CotizacionResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * Archivo: IVistaHistorialCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IVistaHistorialCotizaciones extends IVista{
    
    // Actualiza el listado de cotrizaciones
    public abstract void mostrarCotizaciones(List<CotizacionResumenDTO> cotizaciones);
    
    // Para mostrar mensajes de error si no se encuentran resultados
    public abstract void mostrarMensajeRapido(String mensaje);
    
    public abstract void limpiarFiltros();
    
}
