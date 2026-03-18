
package interfaces;

import entidades.Automovil;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * Archivo: IAutomovilesDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IAutomovilesDAO {
    
    public abstract Automovil crearAutomovil(Automovil automovil) throws PersistenciaException;
    public abstract Automovil obtenerAutomovil(Long idAutomovil) throws PersistenciaException;
    public abstract List<Automovil> obtenerTodosAutomoviles() throws PersistenciaException;
}
