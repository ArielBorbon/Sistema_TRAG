package com.mycompany.administradorclientes_trag;

import dtos.cliente.ClienteActualizarDTO;
import dtos.cliente.ClienteAgregarDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import entidades.Cliente;
import enums.EstadoClienteNegocios;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IClientesDAO;
import java.util.List;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * Archivo: AdinistradorClientes.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class AdministradorClientes {

    private IClientesDAO clientesDAO;

    // Constantes de validación 
    private static final int MAX_LONGITUD_NOMBRE_APELLIDO = 100;
    private static final int MAX_LONGITUD_TELEFONO = 20;
    private static final int MAX_LONGITUD_CORREO = 150;
    private static final String REGEX_CORREO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String MENSAJE_ID_CLIENTE_AUSENTE_OBTENER = "El ID es necesario para obtener un cliente";

    private static final String MENSAJE_ERROR_CREAR_CLIENTE = "Error al crear el cliente";
    private static final String MENSAJE_ERROR_OBTENER_CLIENTE = "Error al obtener el cliente";
    private static final String MENSAJE_ERROR_OBTENER_TODOS_CLIENTES = "Error al obtener los clientes";

    public AdministradorClientes(IClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }

    public ClienteDetalleDTO crearCliente(ClienteAgregarDTO dto) throws NegocioException {
        
        validarClienteAgregar(dto);

        dto.setEstado(EstadoClienteNegocios.HABILITADO);
        
        Cliente clienteRegistrar = DTOMapeadores.toEntity(dto);

        try {
            return Mapeadores.toDTODetalle(clientesDAO.crearCliente(clienteRegistrar));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_CREAR_CLIENTE, e);
        }
    }

    public ClienteDetalleDTO actualizarCliente(ClienteActualizarDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("El ID es necesario para editar.");
        }

        validarClienteActualizar(dto);

        Cliente clienteActualizar = DTOMapeadores.toEntity(dto);

        return Mapeadores.toDTODetalle(clienteActualizar);
    }

    public void deshabilitarCliente(Long id) throws NegocioException {
        if (id == null) {
            throw new IllegalArgumentException("El ID es necesario para deshabilitar.");
        }

        try {
            Cliente cliente = clientesDAO.obtenerCliente(id);
            
            if (cliente == null) {
                throw new NegocioException("No se encontró ningún cliente con el ID proporcionado.");
            }

            cliente.setEstado(enums.EstadoCliente.DESHABILITADO);

            clientesDAO.actualizarCliente(cliente);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al deshabilitar el cliente en la base de datos.", e);
        }
    }

    public void habilitarCliente(Long id) throws NegocioException {
        if (id == null) {
            throw new IllegalArgumentException("El ID es necesario para habilitar.");
        }

        try {
            Cliente cliente = clientesDAO.obtenerCliente(id);
            
            if (cliente == null) {
                throw new NegocioException("No se encontró ningún cliente con el ID proporcionado.");
            }
            
            cliente.setEstado(enums.EstadoCliente.HABILITADO);
            
            clientesDAO.actualizarCliente(cliente);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al habilitar el cliente en la base de datos.", e);
        }
    }

    public ClienteDetalleDTO obtenerCliente(Long idCliente) throws NegocioException {
        if (idCliente == null) {
            throw new NegocioException(MENSAJE_ID_CLIENTE_AUSENTE_OBTENER);
        }

        try {
            return Mapeadores.toDTODetalle(clientesDAO.obtenerCliente(idCliente));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_CLIENTE, e);
        }
    }

    public List<ClienteResumenDTO> obtenerTodosClientes() throws NegocioException {

        try {
            return Mapeadores.toDTOClientes(clientesDAO.obtenerTodosClientes());
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_TODOS_CLIENTES, e);
        }

    }

    
    private void validarClienteAgregar(ClienteAgregarDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("Los datos del cliente no pueden ser nulos.");
        }

        validarNombre(dto.getNombre());
        validarApellidoPaterno(dto.getApellidoPaterno());
        validarApellidoMaterno(dto.getApellidoMaterno());
        validarTelefono(dto.getTelefono());
        validarCorreo(dto.getCorreo());
    }

    private void validarClienteActualizar(ClienteActualizarDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Los datos del cliente no pueden ser nulos.");
        }
        if (dto.getId() == null) {
            throw new IllegalArgumentException("El ID es necesario para editar.");
        }

        validarNombre(dto.getNombre());
        validarApellidoPaterno(dto.getApellidoPaterno());
        validarApellidoMaterno(dto.getApellidoMaterno());
        validarTelefono(dto.getTelefono());
        validarCorreo(dto.getCorreo());
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (nombre.trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
            throw new IllegalArgumentException("El nombre no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
        }
    }

    private void validarApellidoPaterno(String apellidoPaterno) {
        if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío.");
        }
        if (apellidoPaterno.trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
            throw new IllegalArgumentException("El apellido paterno no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
        }
    }

    private void validarApellidoMaterno(String apellidoMaterno) {
        if (apellidoMaterno != null && apellidoMaterno.trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
            throw new IllegalArgumentException("El apellido materno no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
        }
    }

    private void validarTelefono(String telefono) {
        if (telefono != null && telefono.trim().length() > MAX_LONGITUD_TELEFONO) {
            throw new IllegalArgumentException("El teléfono no puede superar los " + MAX_LONGITUD_TELEFONO + " caracteres.");
        }
    }

    private void validarCorreo(String correo) {
        if (correo != null && !correo.trim().isEmpty()) {
            String correoTrimmed = correo.trim();

            if (correoTrimmed.length() > MAX_LONGITUD_CORREO) {
                throw new IllegalArgumentException("El correo no puede superar los " + MAX_LONGITUD_CORREO + " caracteres.");
            }

            if (!correoTrimmed.matches(REGEX_CORREO)) {
                throw new IllegalArgumentException("El formato del correo electrónico es inválido.");
            }
        }
    }
 
}
