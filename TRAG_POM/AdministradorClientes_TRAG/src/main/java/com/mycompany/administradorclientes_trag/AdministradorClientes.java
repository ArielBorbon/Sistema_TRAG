/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.administradorclientes_trag;

import DTO.ClienteDTO;
import entidades.Cliente;
import enums.EstadoCliente;
import java.util.ArrayList;
import java.util.List;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * @author sonic
 */
public class AdministradorClientes {

    // Constantes de validación 
    private static final int MAX_LONGITUD_NOMBRE_APELLIDO = 100;
    private static final int MAX_LONGITUD_TELEFONO = 20;
    private static final int MAX_LONGITUD_CORREO = 150;
    private static final String REGEX_CORREO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public ClienteDTO crearCliente(ClienteDTO dto) {
        validarCliente(dto);

        // Forzamos que todo cliente nuevo se cree con el estado correcto
        dto.setEstado(EstadoCliente.HABILITADO);

        // Aquí se mapea a entidad y se llama al método de la DAO para crear el cliente
        Cliente clienteRegistrado = DTOMapeadores.toEntity(dto);

        return Mapeadores.toDTO(clienteRegistrado);
    }

    public ClienteDTO editarCliente(Long id, ClienteDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("El ID es necesario para editar.");
        }

        validarCliente(dto);

        Cliente clienteEditado = DTOMapeadores.toEntity(dto);
        clienteEditado.setId(id); // Aseguramos que el id se mantenga

        return Mapeadores.toDTO(clienteEditado);
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

    public List<ClienteDTO> obtenerTodosLosClientes() {

        //List<Cliente> clientes = dao.obtenerTodos();
        
        //return Mapeadores.toDTOClientes(entidades);
        
        return new ArrayList<>();
    }

    public List<ClienteDTO> obtenerClientesActivos() {
        //List<Cliente> clientes = dao.obtenerPorEstado(EstadoCliente.HABILITADO);
        //return Mapeadores.toDTOClientes(entidades);
        return new ArrayList<>(); 
    }

    public List<ClienteDTO> obtenerClientesInactivos() {
        //List<Cliente> clientes = dao.obtenerPorEstado(EstadoCliente.DESHABILITADO);
        //return Mapeadores.toDTOClientes(entidades);
        return new ArrayList<>();
    }
    
    

    private void validarCliente(ClienteDTO dto) {
        // Validaciones de Nombre
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        if (dto.getNombre().trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
            throw new IllegalArgumentException("El nombre no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
        }

        // Validaciones de Apellido Paterno
        if (dto.getApellidoPaterno() == null || dto.getApellidoPaterno().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío.");
        }
        if (dto.getApellidoPaterno().trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
            throw new IllegalArgumentException("El apellido paterno no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
        }

        // Validaciones de Apellido Materno
        if (dto.getApellidoMaterno() != null && dto.getApellidoMaterno().trim().length() > MAX_LONGITUD_NOMBRE_APELLIDO) {
            throw new IllegalArgumentException("El apellido materno no puede superar los " + MAX_LONGITUD_NOMBRE_APELLIDO + " caracteres.");
        }

        // Validaciones de Teléfono 
        if (dto.getTelefono() != null && dto.getTelefono().trim().length() > MAX_LONGITUD_TELEFONO) {
            throw new IllegalArgumentException("El teléfono no puede superar los " + MAX_LONGITUD_TELEFONO + " caracteres.");
        }

        // Validaciones de Correo
        if (dto.getCorreo() != null && !dto.getCorreo().trim().isEmpty()) {
            String correo = dto.getCorreo().trim();

            if (correo.length() > MAX_LONGITUD_CORREO) {
                throw new IllegalArgumentException(
                        "El correo no puede superar los " + MAX_LONGITUD_CORREO + " caracteres."
                );
            }

            if (!correo.matches(REGEX_CORREO)) {
                throw new IllegalArgumentException("El formato del correo electrónico es inválido.");
            }
        }
    }
}
