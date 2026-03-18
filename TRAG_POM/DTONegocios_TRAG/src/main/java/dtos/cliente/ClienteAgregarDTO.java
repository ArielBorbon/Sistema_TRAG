
package dtos.cliente;

import enums.EstadoClienteNegocios;

/**
 *
 * Archivo: ClienteAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ClienteAgregarDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private EstadoClienteNegocios estado;

    public ClienteAgregarDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, EstadoClienteNegocios estado) {
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

    public EstadoClienteNegocios getEstado() {
        return estado;
    }

    public void setEstado(EstadoClienteNegocios estado) {
        this.estado = estado;
    }
    
}
