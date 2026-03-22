
package dtos.servicio;


/**
 *
 * Archivo: ServicioResumenDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ServicioResumenDTO {
    
    private Long id;
    private String nombre;
    private String direccionIcono;

    public ServicioResumenDTO(Long id, String nombre, String direccionIcono) {
        this.id = id;
        this.nombre = nombre;
        this.direccionIcono = direccionIcono;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccionIcono() {
        return direccionIcono;
    }
    
}
