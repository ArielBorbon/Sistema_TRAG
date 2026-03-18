
package interfaces;

import entidades.Automovil;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IAutomovilesDAO {
    
    public abstract Automovil crearAutomovil(Automovil automovil) throws PersistenciaException;
    public abstract Automovil obtenerAutomovil(Long idAutomovil) throws PersistenciaException;
    public abstract List<Automovil> obtenerTodosAutomoviles() throws PersistenciaException;
    public abstract Automovil desactivarAutomovil(Long idAutomovil) throws PersistenciaException;
    public abstract List<Automovil> obtenerAutomovilesCliente(Long idCliente) throws PersistenciaException;
    public abstract Automovil actualizarAutomovil(Automovil automovil) throws PersistenciaException;
}
