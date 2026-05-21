
package dtos.cliente;

import enums.EstadoClienteNegocios;

/**
 *
 * Archivo: ClienteActualizarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ClienteActualizarDTO {
    
   private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private EstadoClienteNegocios estado;

    public ClienteActualizarDTO() {
    }
    
    public ClienteActualizarDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correo, EstadoClienteNegocios estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
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

    public EstadoClienteNegocios getEstado() {
        return estado;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    

    
}
