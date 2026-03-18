
package interfaces;

import entidades.Servicio;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * Archivo: IServiciosDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IServiciosDAO {
    
    public abstract Servicio crearServicio(Servicio servicio) throws PersistenciaException;
    public abstract Servicio obtenerServicio(Long idServicio) throws PersistenciaException;
    public abstract List<Servicio> obtenerTodosServicios() throws PersistenciaException;
    public abstract List<Servicio> obtenerServiciosNombre(String nombreServicio) throws PersistenciaException;
}
