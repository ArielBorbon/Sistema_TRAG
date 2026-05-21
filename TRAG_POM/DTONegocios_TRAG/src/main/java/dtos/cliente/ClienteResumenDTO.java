
package dtos.cliente;

import enums.EstadoClienteNegocios;

/**
 *
 * Archivo: ClienteResumenDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ClienteResumenDTO {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private EstadoClienteNegocios estado;
    private String telefono; 
    private String correo;     
    private int cantidadAutomoviles;

    public ClienteResumenDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, 
                             EstadoClienteNegocios estado, String telefono, String correo, int cantidadAutomoviles) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.telefono = telefono;
        this.correo = correo;
        this.cantidadAutomoviles = cantidadAutomoviles;
    }

    public ClienteResumenDTO(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public EstadoClienteNegocios getEstado() {
        return estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public int getCantidadAutomoviles() {
        return cantidadAutomoviles;
    }
    
    

    
    @Override
    public String toString() {
        return  nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
    
    
}
