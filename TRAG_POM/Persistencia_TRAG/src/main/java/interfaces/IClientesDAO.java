
package interfaces;

import entidades.Cliente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * Archivo: IClientesDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IClientesDAO {
    
    public abstract Cliente crearCliente(Cliente cliente) throws PersistenciaException;
    public abstract Cliente obtenerCliente(Long idCliente) throws PersistenciaException;
    public abstract List<Cliente> obtenerTodosClientes() throws PersistenciaException;
    public abstract Cliente actualizarCliente(Cliente cliente) throws PersistenciaException;
}
