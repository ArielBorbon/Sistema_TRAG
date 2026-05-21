/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.interfaces.vistas;

import dtos.cliente.ClienteDetalleDTO;
import presentacion.interfaces.IVista;

/**
 *
 * @author sonic
 */
public interface IVistaAgregarEditarCliente extends IVista {
    public abstract void cargarDatosCliente(ClienteDetalleDTO cliente);
    public abstract void limpiarFormulario();
    public abstract void mostrarMensaje(String mensaje);
    public abstract void mostrarMensajeExito(String mensaje);
}
