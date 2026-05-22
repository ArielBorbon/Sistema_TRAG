/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controles;

import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.cliente.ClienteActualizarDTO;
import dtos.cliente.ClienteAgregarDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import enums.EstadoClienteNegocios;
import excepciones.NegocioException;
import java.util.List;
import java.util.stream.Collectors;
import presentacion.borradores.BorradorCliente;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAdministrarClientes;
import presentacion.interfaces.IControlClientes;
import presentacion.interfaces.vistas.IVistaAdministrarClientes;
import presentacion.interfaces.vistas.IVistaAgregarEditarCliente;

/**
 *
 * @author sonic
 */
public class ControlAdministrarClientes implements IControlAdministrarClientes {

    private final IAdministradorClientes administradorClientes;

    private IVistaAdministrarClientes vistaAdministrar;
    private IVistaAgregarEditarCliente vistaAgregarEditar;

    private IControlClientes controlClientes;

    private Long idClienteEdicion;

    public ControlAdministrarClientes() {
        this.administradorClientes = FabricaNegocios.obtenerAdministradorClientes();
    }

    public void setControlClientes(IControlClientes controlClientes) {
        this.controlClientes = controlClientes;
    }

    @Override
    public void iniciar() {
        this.vistaAdministrar = (IVistaAdministrarClientes) FabricaVistas.obtenerVistaAdministrarCliente(this);

        buscarClientes(null);

        this.vistaAdministrar.mostrar();
    }

    @Override
    public void buscarClientes(String busqueda) {
        try {
            List<ClienteResumenDTO> listaFiltrada = administradorClientes.obtenerTodosClientes();

            if (busqueda != null && !busqueda.trim().isEmpty()) {
                String busquedaLower = busqueda.trim().toLowerCase();

                listaFiltrada = listaFiltrada.stream()
                        .filter(c -> {
                            String nom = c.getNombre() != null ? c.getNombre().toLowerCase() : "";
                            String apePat = c.getApellidoPaterno() != null ? c.getApellidoPaterno().toLowerCase() : "";
                            String apeMat = c.getApellidoMaterno() != null ? c.getApellidoMaterno().toLowerCase() : "";

                            return nom.contains(busquedaLower)
                                    || apePat.contains(busquedaLower)
                                    || apeMat.contains(busquedaLower);
                        })
                        .collect(Collectors.toList());
            }

            listaFiltrada = listaFiltrada.stream()
                    .filter(c -> c.getEstado() == null || !c.getEstado().name().equalsIgnoreCase("DESHABILITADO"))
                    .collect(Collectors.toList());

            vistaAdministrar.cargarClientes(listaFiltrada);

        } catch (NegocioException ex) {
            vistaAdministrar.mostrarMensaje("Error al buscar clientes: " + ex.getMessage());
        }
    }

    @Override
    public void atrasPrincipal() {
        this.vistaAdministrar.ocultar();
        if (this.controlClientes != null) {
            this.controlClientes.volver();
        }
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        if (idCliente == null) {
            vistaAdministrar.mostrarMensaje("Por favor, seleccione un cliente de la tabla.");
            return;
        }

        if (vistaAdministrar.confirmarEliminacion()) {
            try {
                administradorClientes.deshabilitarCliente(idCliente);

                vistaAdministrar.mostrarMensajeExito("Cliente eliminado (deshabilitado) correctamente.");

                buscarClientes(null);
            } catch (NegocioException ex) {
                vistaAdministrar.mostrarMensaje("Error al eliminar: " + ex.getMessage());
            }
        }
    }

    @Override
    public void irAgregarCliente() {
        this.idClienteEdicion = null;

        if (this.vistaAdministrar == null) {
            this.vistaAdministrar = FabricaVistas.obtenerVistaAdministrarCliente(this);
        }

        this.vistaAdministrar.ocultar();

        this.vistaAgregarEditar = FabricaVistas.obtenerVistaAgregarEditarCliente(this);
        this.vistaAgregarEditar.limpiarFormulario();
        this.vistaAgregarEditar.mostrar();
    }

    @Override
    public void irEditarCliente(Long idCliente) {
        try {
            ClienteDetalleDTO cliente = administradorClientes.obtenerCliente(idCliente);

            this.idClienteEdicion = cliente.getId();

            this.vistaAdministrar.ocultar();
            this.vistaAgregarEditar = FabricaVistas.obtenerVistaAgregarEditarCliente(this);
            this.vistaAgregarEditar.cargarDatosCliente(cliente);
            this.vistaAgregarEditar.mostrar();
        } catch (NegocioException ex) {
            vistaAdministrar.mostrarMensaje("Error: " + ex.getMessage());
        }
    }

    @Override
    public void cancelarAgregarEditar() {
        this.vistaAgregarEditar.ocultar();
        buscarClientes(null);
        this.vistaAdministrar.mostrar();
    }

    @Override
    public void guardarNuevoCliente(BorradorCliente borrador) {
        try {
            ClienteAgregarDTO dtoNuevo = new ClienteAgregarDTO(
                    borrador.getNombres(),
                    borrador.getApellidoPaterno(),
                    borrador.getApellidoMaterno(),
                    borrador.getTelefono(),
                    borrador.getCorreo(),
                    EstadoClienteNegocios.HABILITADO
            );

            administradorClientes.crearCliente(dtoNuevo);

            vistaAgregarEditar.mostrarMensajeExito("Cliente registrado con éxito.");
            vistaAgregarEditar.ocultar();
            buscarClientes(null);
            vistaAdministrar.mostrar();
        } catch (NegocioException | IllegalArgumentException ex) {
            vistaAgregarEditar.mostrarMensaje(ex.getMessage());
        }
    }

    @Override
    public void actualizarCliente(BorradorCliente borrador) {
        try {
            ClienteActualizarDTO dtoEditado = new ClienteActualizarDTO(
                    this.idClienteEdicion,
                    borrador.getNombres(),
                    borrador.getApellidoPaterno(),
                    borrador.getApellidoMaterno(),
                    borrador.getTelefono(),
                    borrador.getCorreo(),
                    EstadoClienteNegocios.HABILITADO
            );

            administradorClientes.actualizarCliente(dtoEditado);

            vistaAgregarEditar.mostrarMensajeExito("Cliente actualizado con éxito.");
            vistaAgregarEditar.ocultar();

            buscarClientes(null);
            vistaAdministrar.mostrar();
        } catch (NegocioException | IllegalArgumentException ex) {
            vistaAgregarEditar.mostrarMensaje(ex.getMessage());
        }
    }
}
