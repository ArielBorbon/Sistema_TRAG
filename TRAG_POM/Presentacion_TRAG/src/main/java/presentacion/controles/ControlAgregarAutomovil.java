
package presentacion.controles;

import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.automovil.AutomovilAgregarDTO;
import dtos.cliente.ClienteResumenDTO;
import excepciones.NegocioException;
import java.util.List;
import javax.swing.JOptionPane;
import presentacion.vistas.VistaAgregarVehiculo;
import presentacion.vistas.VistaSeleccionClienteAuto;

/**
 *
 * @author PC Gamer
 */
public class ControlAgregarAutomovil {

    private VistaSeleccionClienteAuto vistaAnterior;
    private VistaAgregarVehiculo vistaNueva;
    private IAdministradorAutomoviles adminAutomoviles;
    private IAdministradorClientes adminClientes;

    public ControlAgregarAutomovil(VistaSeleccionClienteAuto vistaAnterior) {
        this.vistaAnterior = vistaAnterior;
        this.adminAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
        this.adminClientes = FabricaNegocios.obtenerAdministradorClientes();
    }

    public void iniciarVista() {
        this.vistaNueva = new VistaAgregarVehiculo(this);

        this.vistaAnterior.setVisible(false);

        try {
            List<ClienteResumenDTO> clientesActivos = adminClientes.obtenerTodosClientes();
            vistaNueva.cargarComboClientes(clientesActivos);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar clientes: " + e.getMessage());
        }

        this.vistaNueva.setVisible(true);
    }

    public void guardarAutomovil(AutomovilAgregarDTO autoNuevo, ClienteResumenDTO clienteSeleccionado) {
        try {
            adminAutomoviles.crearAutomovil(autoNuevo);
            JOptionPane.showMessageDialog(vistaNueva, "Automóvil guardado exitosamente.");

            vistaAnterior.refrescarYSeleccionar(clienteSeleccionado, autoNuevo.getMatricula());

            cerrarVista();

        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vistaNueva, "Error al guardar el vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrarVista() {
        vistaNueva.dispose(); 
        vistaAnterior.setVisible(true); 
          
    }
}
