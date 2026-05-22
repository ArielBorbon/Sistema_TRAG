package presentacion.controles;

import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.automovil.AutomovilResumenDTO;
import excepciones.NegocioException;
import java.util.List;
import javax.swing.JOptionPane;
import presentacion.vistas.VistaAutomoviles;

/**
 *
 * @author PC Gamer
 */
public class ControlAdministrarAutomoviles {

    private VistaAutomoviles vista;
    private IAdministradorAutomoviles adminAutomoviles;
    private ControlAutomoviles controlOrquestador;

    public ControlAdministrarAutomoviles() {
        this.adminAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
    }

    public void volverMenuPrincipalAutos() {
        this.vista.dispose();
        controlOrquestador.iniciarModulo();
    }

    public void iniciarModulo() {
        this.vista = new VistaAutomoviles(this);
        cargarTabla();
        this.vista.setVisible(true);
    }

    public void cargarTabla() {
        try {
            List<AutomovilResumenDTO> listaAutos = adminAutomoviles.obtenerTodosAutomoviles();
            vista.llenarTabla(listaAutos);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar los vehículos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarAutomoviles(String textoBusqueda) {
        try {
            if (textoBusqueda.isEmpty()) {
                cargarTabla(); 
            } else {
                List<AutomovilResumenDTO> listaFiltrada = adminAutomoviles.obtenerAutomovilesPorNombreCliente(textoBusqueda);
               vista.llenarTabla(listaFiltrada);
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vista, "Error al buscar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirAgregarVehiculo() {
        ControlAgregarAutomovil controlAgregar = new ControlAgregarAutomovil(vista);
        controlAgregar.iniciarVista();
    }

    public void setControlOrquestador(ControlAutomoviles controlOrquestador) {
        this.controlOrquestador = controlOrquestador;
    }

    public void eliminarVehiculo(Long idAutomovil) {
        try {
            adminAutomoviles.deshabilitarAutomovil(idAutomovil);
            JOptionPane.showMessageDialog(vista, "Vehículo eliminado correctamente.");
            cargarTabla();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirEditarVehiculo(Long idAutomovil) {
        ControlEditarAutomovil controlEditar = new ControlEditarAutomovil(this, idAutomovil);
        controlEditar.iniciarVista();
    }

}
