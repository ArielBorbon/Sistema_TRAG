
package interfaces;

import entidades.Cliente;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IClientesDAO {
    
    public abstract Cliente crearCliente(Cliente cliente) throws PersistenciaException;
    public abstract Cliente obtenerCliente(Long idCliente) throws PersistenciaException;
    public abstract List<Cliente> obtenerTodosClientes() throws PersistenciaException;
    public abstract Cliente cambiarEstadoClienteEliminado(Long idCliente) throws PersistenciaException;
    public abstract Cliente cambiarEstadoClienteDeshabilitado(Long idCliente) throws PersistenciaException;
    public abstract Cliente cambiarEstadoClienteHabilitado(Long idCliente) throws PersistenciaException;
    public abstract Cliente actualizarCliente(Cliente cliente) throws PersistenciaException;
}
