
package interfaces;

import entidades.OrdenTrabajo;
import excepciones.PersistenciaException;

/**
 *
 * @author 
 */
public interface IOrdenesTrabajoDAO {
    
    public abstract OrdenTrabajo crearOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws PersistenciaException;
    public abstract OrdenTrabajo obtenerOrdenTrabajo(Long idOrdenTrabajo) throws PersistenciaException;
    
}
