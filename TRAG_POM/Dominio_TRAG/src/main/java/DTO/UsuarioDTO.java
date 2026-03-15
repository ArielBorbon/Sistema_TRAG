/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import enums.EstadoUsuario;
import enums.RolUsuario;

/**
 *
 * @author PC Gamer
 */
public class UsuarioDTO {

    private Long id;
    private String correo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private EstadoUsuario estado;
    private RolUsuario rol;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String correo, String nombre, String apellidoPaterno, String apellidoMaterno, EstadoUsuario estado, RolUsuario rol) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.rol = rol;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }
    
    
    
    
}
