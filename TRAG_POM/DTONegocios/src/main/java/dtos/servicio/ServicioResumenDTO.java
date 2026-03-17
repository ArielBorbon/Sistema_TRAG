
package dtos.servicio;


/**
 *
 * @author 
 */
public class ServicioResumenDTO {
    
    private Long id;
    private String nombre;

    public ServicioResumenDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
}
