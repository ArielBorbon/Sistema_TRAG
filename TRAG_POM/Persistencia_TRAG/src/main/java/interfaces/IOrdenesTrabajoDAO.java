
package interfaces;

import entidades.OrdenTrabajo;
import excepciones.PersistenciaException;

/**
 *
 * Archivo: IOrdenesTrabajoDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IOrdenesTrabajoDAO {
    
    public abstract OrdenTrabajo crearOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws PersistenciaException;
    public abstract OrdenTrabajo obtenerOrdenTrabajo(Long idOrdenTrabajo) throws PersistenciaException;
    
}
