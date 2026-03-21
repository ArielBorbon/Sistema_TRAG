
package presentacion.interfaces.vistas;

import dtos.cliente.ClienteResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * @author romom
 */
public interface IVistaSeleccionClienteAuto extends IVista{
    
    public void cargarClientes(List<ClienteResumenDTO> clientes);
    public void cargarAutosCliente();
    public void mostrarError(String mensajeError);
    
}
