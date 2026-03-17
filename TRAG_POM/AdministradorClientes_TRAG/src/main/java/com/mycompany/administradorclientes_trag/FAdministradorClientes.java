
package com.mycompany.administradorclientes_trag;

import dtos.cliente.ClienteActualizarDTO;
import dtos.cliente.ClienteAgregarDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author sonic
 */
public class FAdministradorClientes implements IAdministradorClientes{

    private final AdministradorClientes administradorClientes;

    public FAdministradorClientes(AdministradorClientes administradorClientes) {
        this.administradorClientes = administradorClientes;
    }

    /**
     * Fachada para registrar un nuevo cliente en el sistema.
     *
     * @param dto Datos del cliente a registrar.
     * @return ClienteDTO con los datos registrados y validados.
     */
    @Override
    public ClienteDetalleDTO crearCliente(ClienteAgregarDTO dto) throws NegocioException {
        return administradorClientes.crearCliente(dto);
    }

    /**
     * Fachada para actualizar la información de un cliente existente.
     *
     * @param dto Datos actualizados del cliente.
     * @return ClienteDTO con la información actualizada.
     */
    public ClienteDetalleDTO actualizarCliente(ClienteActualizarDTO dto) {
        return administradorClientes.actualizarCliente(dto);
    }

    /**
     * Fachada para cambiar el estado de un cliente a DESHABILITADO.
     *
     * @param id Identificador único del cliente.
     */
    public void fDeshabilitarCliente(Long id) {
        administradorClientes.deshabilitarCliente(id);
    }

    /**
     * Fachada para cambiar el estado de un cliente a HABILITADO.
     *
     * @param id Identificador único del cliente.
     */
    public void fHabilitarCliente(Long id) {
        administradorClientes.habilitarCliente(id);
    }

    
    @Override
    public ClienteDetalleDTO obtenerCliente(Long idCliente) throws NegocioException{
        return administradorClientes.obtenerCliente(idCliente);
    }
    
    /**
     * Fachada para obtener el listado completo de clientes, sin importar su
     * estado.
     *
     * @return Lista de ClienteDTO.
     */
    @Override
    public List<ClienteResumenDTO> obtenerTodosClientes() throws NegocioException {
        return administradorClientes.obtenerTodosClientes();
    }

//    /**
//     * Fachada para obtener el listado de clientes cuyo estado es HABILITADO.
//     *
//     * @return Lista de ClienteDTO.
//     */
//    public List<ClienteDTO> fObtenerClientesActivos() {
//        return administradorClientes.obtenerClientesActivos();
//    }

//    /**
//     * Fachada para obtener el listado de clientes cuyo estado es DESHABILITADO.
//     *
//     * @return Lista de ClienteDTO.
//     */
//    public List<ClienteDTO> fObtenerClientesInactivos() {
//        return administradorClientes.obtenerClientesInactivos();
//    }
}
