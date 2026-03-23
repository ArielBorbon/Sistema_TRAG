
package presentacion.interfaces;

import dtos.cotizacion.CotizacionResumenDTO;
import java.time.LocalDateTime;
import presentacion.borradores.BorradorCotizacion;

/**
 *
 * Archivo: IControlConsultarCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IControlConsultarCotizaciones {
    
    public abstract void iniciar();
    public abstract void buscarCotizaciones(String nombreCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado);
    public abstract void cancelarCotizacion(Long idCotizacion);
    public abstract void activiarCotizacion(Long idCotizacion);
    public abstract void verDetalleCotizacion(CotizacionResumenDTO cotizacionSeleccionada);
    public abstract void volverHistorialCotizaciones();
    public abstract void volverConsultarCotizacion();
    public abstract void cancelarConsultarCotizacion();
    
    public abstract void guardarCambioCotizacion(BorradorCotizacion cotizacion);
    public abstract void buscarInsumosNombre(String nombreInsumo);
    public abstract void agregarInsumo(String nombre);
    public abstract void actualizarCotizacion();
    public abstract void aceptarExitoActualizacionCotizacion();
    
}
