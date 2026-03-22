
package presentacion.interfaces.vistas;

import dtos.automovil.AutomovilResumenDTO;
import dtos.cliente.ClienteResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * @author romom
 */
public interface IVistaSeleccionClienteAuto extends IVista{
    
    public void cargarClientes(List<ClienteResumenDTO> clientes);
    public void cargarClientes(List<ClienteResumenDTO> clientes, Long idClienteSeleccionado);
    public void cargarAutosCliente(List<AutomovilResumenDTO> automoviles);
    public void cargarAutosCliente(List<AutomovilResumenDTO> automoviles, Long idAutomovilSeleccionado);
    
}
