/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controles;

import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.automovil.AutomovilActualizarDTO;
import dtos.automovil.AutomovilDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import excepciones.NegocioException;
import java.util.List;
import javax.swing.JOptionPane;
import presentacion.vistas.VistaEditarVehiculo;

/**
 *
 * @author PC Gamer
 */
public class ControlEditarAutomovil {

    private ControlAdministrarAutomoviles controlPadre;
    private VistaEditarVehiculo vistaEdicion;
    private IAdministradorAutomoviles adminAutomoviles;
    private IAdministradorClientes adminClientes;
    private Long idAutomovilEditar;

    public ControlEditarAutomovil(ControlAdministrarAutomoviles controlPadre, Long idAutomovilEditar) {
        this.controlPadre = controlPadre;
        this.idAutomovilEditar = idAutomovilEditar;
        this.adminAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
        this.adminClientes = FabricaNegocios.obtenerAdministradorClientes();
    }

    public void iniciarVista() {
        this.vistaEdicion = new VistaEditarVehiculo(this);

        try {
            List<ClienteResumenDTO> clientesActivos = adminClientes.obtenerTodosClientes();
            vistaEdicion.cargarComboClientes(clientesActivos);

            AutomovilDetalleDTO autoDetalle = adminAutomoviles.obtenerAutomovil(idAutomovilEditar);

            vistaEdicion.cargarDatosAutomovil(autoDetalle);

        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
        }

        this.vistaEdicion.setVisible(true);
    }

    public void actualizarAutomovil(AutomovilActualizarDTO dto) {
        try {
            dto.setId(idAutomovilEditar); 
            adminAutomoviles.actualizarAutomovil(dto); 
            JOptionPane.showMessageDialog(vistaEdicion, "Automóvil actualizado exitosamente.");

            cerrarVista();
            controlPadre.cargarTabla();

        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(vistaEdicion, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrarVista() {
        vistaEdicion.dispose();
    }
}
