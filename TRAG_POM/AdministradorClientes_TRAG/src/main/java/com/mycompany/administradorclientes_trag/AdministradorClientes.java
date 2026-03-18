
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
 * @author sonic
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
//        validarCliente(dto);

        dto.setEstado(EstadoClienteNegocios.HABILITADO);
        // Aquí se mapea a entidad y se llama al método de la DAO para crear el cliente
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

//        validarCliente(dto);
        Cliente clienteActualizar = DTOMapeadores.toEntity(dto);

        return Mapeadores.toDTODetalle(clienteActualizar);
    }

    public void deshabilitarCliente(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID es necesario para deshabilitar.");
        }

        // Cliente cliente = dao.buscarPorId(id); o un buscar por nombre o algo, por numero de telefono?
        // cliente.setEstado(EstadoCliente.DESHABILITADO);
        // dao.actualizar(cliente);
        // o algo asi
    }

    public void habilitarCliente(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID es necesario para habilitar.");
        }

        // Cliente cliente = dao.buscarPorId(id);
        // cliente.setEstado(EstadoCliente.HABILITADO);
        // dao.actualizar(cliente);
        // o algo asi
    }
    
    public ClienteDetalleDTO obtenerCliente(Long idCliente) throws NegocioException{
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

//    public List<ClienteDTO> obtenerClientesActivos() {
//        //List<Cliente> clientes = dao.obtenerPorEstado(EstadoCliente.HABILITADO);
//        //return Mapeadores.toDTOClientes(entidades);
//        return new ArrayList<>(); 
//    }
//
//    public List<ClienteDTO> obtenerClientesInactivos() {
//        //List<Cliente> clientes = dao.obtenerPorEstado(EstadoCliente.DESHABILITADO);
//        //return Mapeadores.toDTOClientes(entidades);
//        return new ArrayList<>();
//    }
    
    

//    private void validarCliente(ClienteDTO dto) {
//        // Validaciones de Nombre
//        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
//            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
//        }
//        if (dto.getNombre().trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
//            throw new IllegalArgumentException("El nombre no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
//        }
//
//        // Validaciones de Apellido Paterno
//        if (dto.getApellidoPaterno() == null || dto.getApellidoPaterno().trim().isEmpty()) {
//            throw new IllegalArgumentException("El apellido paterno no puede estar vacío.");
//        }
//        if (dto.getApellidoPaterno().trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
//            throw new IllegalArgumentException("El apellido paterno no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
//        }
//
//        // Validaciones de Apellido Materno
//        if (dto.getApellidoMaterno() != null && dto.getApellidoMaterno().trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
//            throw new IllegalArgumentException("El apellido materno no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
//        }
//
//        // Validaciones de Teléfono 
//        if (dto.getTelefono() != null && dto.getTelefono().trim().length() > MAX_LONGITUD_TELEFONO) {
//            throw new IllegalArgumentException("El teléfono no puede superar los " + MAX_LONGITUD_TELEFONO + " caracteres.");
//        }
//
//        // Validaciones de Correo
//        if (dto.getCorreo() != null && !dto.getCorreo().trim().isEmpty()) {
//            String correo = dto.getCorreo().trim();
//
//            if (correo.length() > MAX_LONGITUD_CORREO) {
//                throw new IllegalArgumentException(
//                        "El correo no puede superar los " + MAX_LONGITUD_CORREO + " caracteres."
//                );
//            }
//
//            if (!correo.matches(REGEX_CORREO)) {
//                throw new IllegalArgumentException("El formato del correo electrónico es inválido.");
//            }
//        }
//    }
}
