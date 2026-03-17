
package com.mycompany.administradorclientes_trag;

import dtos.cliente.ClienteAgregarDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IAdministradorClientes {
    
    public abstract ClienteDetalleDTO crearCliente(ClienteAgregarDTO dto) throws NegocioException;
    public abstract ClienteDetalleDTO obtenerCliente(Long idCliente) throws NegocioException;
    public abstract List<ClienteResumenDTO> obtenerTodosClientes() throws NegocioException;
    
}
