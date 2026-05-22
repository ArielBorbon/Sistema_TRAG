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
import presentacion.vistas.VistaAutomoviles;
import presentacion.vistas.VistaPrincipalAutomoviles;
import presentacion.vistas.VistaSeleccionClienteAuto;

/**
 *
 * @author PC Gamer
 */
public class ControlAgregarAutomovil {

    private ControlAdministrarAutomoviles controlOrquestador;

    private VistaSeleccionClienteAuto vistaCotizacionAnterior;
    private VistaAutomoviles vistaModuloAnterior;
    private VistaAgregarVehiculo vistaNueva;
    private IAdministradorAutomoviles adminAutomoviles;
    private IAdministradorClientes adminClientes;
    private int origen;
    private VistaPrincipalAutomoviles vistaMenuAutosAnterior;

    public ControlAgregarAutomovil(VistaSeleccionClienteAuto vistaAnterior) {
        this.vistaCotizacionAnterior = vistaAnterior;
        this.origen = 1;
        controlOrquestador.iniciarModulo();
        inicializarDependencias();
    }

    public ControlAgregarAutomovil(VistaAutomoviles vistaAnterior) {
        this.vistaModuloAnterior = vistaAnterior;
        this.origen = 2;
        inicializarDependencias();
    }

    public ControlAgregarAutomovil(VistaPrincipalAutomoviles vistaAnterior) {
        this.vistaMenuAutosAnterior = vistaAnterior;
        this.origen = 3;
        inicializarDependencias();
    }

    private void inicializarDependencias() {
        this.adminAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
        this.adminClientes = FabricaNegocios.obtenerAdministradorClientes();
    }

    public void iniciarVista() {
        this.vistaNueva = new VistaAgregarVehiculo(this);

        switch (origen) {
            case 1:
                vistaCotizacionAnterior.setVisible(false);
                break;
            case 2:
                vistaModuloAnterior.setVisible(false);
                break;
            case 3:
                vistaMenuAutosAnterior.setVisible(false);
                break;
            default:
                break;
        }

        this.vistaNueva.setVisible(true);

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

            if (origen == 1) {
                vistaCotizacionAnterior.refrescarYSeleccionar(clienteSeleccionado, autoNuevo.getMatricula());
            } else if (origen == 2) {
                vistaModuloAnterior.actualizarDatos();
            }

            cerrarVista();

        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vistaNueva, "Error al guardar el vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrarVista() {
        vistaNueva.dispose();
        switch (origen) {
            case 1:
                vistaCotizacionAnterior.setVisible(true);
                break;
            case 2:
                vistaModuloAnterior.setVisible(true);
                break;
            case 3:
                vistaMenuAutosAnterior.setVisible(true);
                break;
            default:
                break;
        }
    }
}
