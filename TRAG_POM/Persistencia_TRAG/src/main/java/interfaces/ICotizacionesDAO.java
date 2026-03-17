
package interfaces;

import entidades.Cotizacion;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author romom
 */
public interface ICotizacionesDAO {
    
    public abstract Cotizacion agregarCotizacion(Cotizacion cotizacion) throws PersistenciaException;
    public abstract Cotizacion obtenerCotizacion(Long idCotizacion) throws PersistenciaException;
    public abstract List<Cotizacion> obtenerTodasCotizaciones() throws PersistenciaException;
    public abstract Cotizacion actualizarCotizacion(Cotizacion cotizacion) throws PersistenciaException;
    public abstract Cotizacion eliminarCotizacion(Long idCotizacion) throws PersistenciaException;
    
}
