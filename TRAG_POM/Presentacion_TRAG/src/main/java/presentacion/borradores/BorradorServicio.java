
package presentacion.borradores;

/**
 *
 * @author 
 */
public class BorradorServicio {
    
    private Long id;
    private String nombre;

    public BorradorServicio(Long id, String nombre) {
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
