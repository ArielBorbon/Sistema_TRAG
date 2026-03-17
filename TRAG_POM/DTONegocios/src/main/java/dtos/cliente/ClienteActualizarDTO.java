
package dtos.cliente;

import enums.EstadoCliente;

/**
 *
 * @author 
 */
public class ClienteActualizarDTO {
    
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private EstadoCliente estado;

    public ClienteActualizarDTO() {
    }

    public Long getId() {
        return id;
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
    
    public void setId(Long id) {
        this.id = id;
    }

    
}
