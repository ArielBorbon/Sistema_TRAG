/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.administradorclientes_trag;

import DTO.ClienteDTO;
import java.util.List;

/**
 *
 * @author sonic
 */
public class FAdministradorClientes {

    private final AdministradorClientes administrador;

    public FAdministradorClientes() {
        this.administrador = new AdministradorClientes();
    }

    /**
     * Fachada para registrar un nuevo cliente en el sistema.
     *
     * @param dto Datos del cliente a registrar.
     * @return ClienteDTO con los datos registrados y validados.
     */
    public ClienteDTO fCrearCliente(ClienteDTO dto) {
        return administrador.crearCliente(dto);
    }

    /**
     * Fachada para actualizar la información de un cliente existente.
     *
     * @param id Identificador único del cliente.
     * @param dto Datos actualizados del cliente.
     * @return ClienteDTO con la información actualizada.
     */
    public ClienteDTO fEditarCliente(Long id, ClienteDTO dto) {
        return administrador.editarCliente(id, dto);
    }

    /**
     * Fachada para cambiar el estado de un cliente a DESHABILITADO.
     *
     * @param id Identificador único del cliente.
     */
    public void fDeshabilitarCliente(Long id) {
        administrador.deshabilitarCliente(id);
    }

    /**
     * Fachada para cambiar el estado de un cliente a HABILITADO.
     *
     * @param id Identificador único del cliente.
     */
    public void fHabilitarCliente(Long id) {
        administrador.habilitarCliente(id);
    }

    /**
     * Fachada para obtener el listado completo de clientes, sin importar su
     * estado.
     *
     * @return Lista de ClienteDTO.
     */
    public List<ClienteDTO> fObtenerTodosLosClientes() {
        return administrador.obtenerTodosLosClientes();
    }

    /**
     * Fachada para obtener el listado de clientes cuyo estado es HABILITADO.
     *
     * @return Lista de ClienteDTO.
     */
    public List<ClienteDTO> fObtenerClientesActivos() {
        return administrador.obtenerClientesActivos();
    }

    /**
     * Fachada para obtener el listado de clientes cuyo estado es DESHABILITADO.
     *
     * @return Lista de ClienteDTO.
     */
    public List<ClienteDTO> fObtenerClientesInactivos() {
        return administrador.obtenerClientesInactivos();
    }
}
