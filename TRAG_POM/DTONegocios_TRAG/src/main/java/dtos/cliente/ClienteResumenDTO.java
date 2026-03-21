
package dtos.cliente;

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

    public ClienteResumenDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
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

    @Override
    public String toString() {
        return  nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
    
    
}
