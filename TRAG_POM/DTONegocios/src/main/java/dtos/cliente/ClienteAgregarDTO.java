
package dtos.cliente;

import enums.EstadoCliente;

/**
 *
 * @author 
 */
public class ClienteAgregarDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private EstadoCliente estado;

    public ClienteAgregarDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, EstadoCliente estado) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    
    
    
}
