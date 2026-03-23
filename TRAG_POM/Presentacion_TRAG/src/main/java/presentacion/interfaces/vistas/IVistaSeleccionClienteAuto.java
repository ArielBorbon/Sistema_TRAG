
package presentacion.interfaces.vistas;

import dtos.automovil.AutomovilResumenDTO;
import dtos.cliente.ClienteResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * Archivo: IVistaSeleccionClienteAuto.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IVistaSeleccionClienteAuto extends IVista{
    
    public void cargarClientes(List<ClienteResumenDTO> clientes);
    public void cargarClientes(List<ClienteResumenDTO> clientes, Long idClienteSeleccionado);
    public void cargarAutosCliente(List<AutomovilResumenDTO> automoviles);
    public void cargarAutosCliente(List<AutomovilResumenDTO> automoviles, Long idAutomovilSeleccionado);
    
}
