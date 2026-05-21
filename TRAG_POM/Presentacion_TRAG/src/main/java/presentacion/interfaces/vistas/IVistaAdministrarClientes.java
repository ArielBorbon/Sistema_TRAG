/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.interfaces.vistas;

import dtos.cliente.ClienteResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * @author sonic
 */
public interface IVistaAdministrarClientes extends IVista {
    public abstract void cargarClientes(List<ClienteResumenDTO> clientes);
    public abstract void mostrarMensaje(String mensaje);
    public abstract void mostrarMensajeExito(String mensaje);
    public abstract Long obtenerIdClienteSeleccionado();
    public abstract boolean confirmarEliminacion();
}