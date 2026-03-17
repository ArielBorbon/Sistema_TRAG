
package interfaces;

import entidades.Servicio;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IServiciosDAO {
    
    public abstract Servicio crearServicio(Servicio servicio) throws PersistenciaException;
    public abstract Servicio obtenerServicio(Long idServicio) throws PersistenciaException;
    public abstract List<Servicio> obtenerTodosServicios() throws PersistenciaException;
}
