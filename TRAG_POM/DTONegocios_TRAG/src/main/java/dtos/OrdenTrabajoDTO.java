
package dtos;

/**
 *
 * Archivo: OrdenTrabajoDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class OrdenTrabajoDTO {

    private Long id;
    private Long idAutomovil;
    private Long idCotizacion;
    private Long idServicio;

    public OrdenTrabajoDTO() {
    }

    public OrdenTrabajoDTO(Long id, Long idAutomovil, Long idCotizacion, Long idServicio) {
        this.id = id;
        this.idAutomovil = idAutomovil;
        this.idCotizacion = idCotizacion;
        this.idServicio = idServicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAutomovil() {
        return idAutomovil;
    }

    public void setIdAutomovil(Long idAutomovil) {
        this.idAutomovil = idAutomovil;
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    
}