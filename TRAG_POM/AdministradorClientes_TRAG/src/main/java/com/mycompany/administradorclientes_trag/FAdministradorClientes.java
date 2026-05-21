
package com.mycompany.administradorclientes_trag;

import dtos.cliente.ClienteActualizarDTO;
import dtos.cliente.ClienteAgregarDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: FAdinistradorClientes.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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
    public ClienteDetalleDTO actualizarCliente(ClienteActualizarDTO dto) throws NegocioException {
        return administradorClientes.actualizarCliente(dto);
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

    /**
     * Fachada para deshabilitar (eliminación lógica) un cliente.
     * 
     * @param id ID del cliente a deshabilitar.
     */
    @Override
    public void deshabilitarCliente(Long id) throws NegocioException {
        administradorClientes.deshabilitarCliente(id);
    }

    /**
     * Fachada para buscar clientes cuyo nombre coincida con el criterio de búsqueda.
     * 
     * @param nombre Texto a buscar en el nombre del cliente.
     * @return Lista de ClienteResumenDTO que coinciden con la búsqueda.
     */
    @Override
    public List<ClienteResumenDTO> obtenerClientesPorNombre(String nombre) throws NegocioException {
        return administradorClientes.obtenerClientesPorNombre(nombre);
    }

}
