/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.interfaces;

import dtos.cliente.ClienteActualizarDTO;
import presentacion.borradores.BorradorCliente;

/**
 *
 * @author sonic
 */
public interface IControlAdministrarClientes {
    public abstract void iniciar();
    public abstract void atrasPrincipal();
    
    // consultar
    public abstract void buscarClientes(String nombreOCriterio);
    
    // eliminar
    public abstract void eliminarCliente(Long idCliente);
    
    // navegacion
    public abstract void irAgregarCliente();
    public abstract void irEditarCliente(Long idCliente);
    public abstract void cancelarAgregarEditar();
    
    // guardar
    public abstract void guardarNuevoCliente(BorradorCliente clienteNuevo);
    public abstract void actualizarCliente(BorradorCliente clienteEditado);
}

