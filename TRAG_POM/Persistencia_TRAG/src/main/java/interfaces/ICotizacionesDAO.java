
package interfaces;

import entidades.Cotizacion;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: ICotizacionesDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface ICotizacionesDAO {
    
    public abstract Cotizacion agregarCotizacion(Cotizacion cotizacion) throws PersistenciaException;
    public abstract Cotizacion obtenerCotizacion(Long idCotizacion) throws PersistenciaException;
    public abstract List<Cotizacion> obtenerTodasCotizaciones() throws PersistenciaException;
    public abstract List<Cotizacion> obtenerCotizacionesNombreCliente(String nombreCliente) throws PersistenciaException;
    public abstract List<Cotizacion> obtenerCotizacionesFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException;
    public abstract Cotizacion actualizarCotizacion(Cotizacion cotizacion) throws PersistenciaException;
    public abstract Cotizacion eliminarCotizacion(Long idCotizacion) throws PersistenciaException;
    public abstract Cotizacion habilitarCotizacion(Long idCotizacion) throws PersistenciaException;
    
}
