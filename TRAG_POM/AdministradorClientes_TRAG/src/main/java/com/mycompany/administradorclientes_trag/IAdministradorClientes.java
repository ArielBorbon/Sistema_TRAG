
package com.mycompany.administradorclientes_trag;

import dtos.cliente.ClienteAgregarDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: IAdinistradorClientes.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IAdministradorClientes {
    
    public abstract ClienteDetalleDTO crearCliente(ClienteAgregarDTO dto) throws NegocioException;
    public abstract ClienteDetalleDTO obtenerCliente(Long idCliente) throws NegocioException;
    public abstract List<ClienteResumenDTO> obtenerTodosClientes() throws NegocioException;
    
}
