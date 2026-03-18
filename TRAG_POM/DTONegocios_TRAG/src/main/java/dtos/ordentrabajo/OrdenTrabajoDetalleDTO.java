
package dtos.ordentrabajo;

/**
 *
 * Archivo: OrdenTrabajoDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class OrdenTrabajoDetalleDTO {
    private Long id;
    private Long idAutomovil;
    private Long idCotizacion;

    public OrdenTrabajoDetalleDTO(Long id, Long idAutomovil, Long idCotizacion) {
        this.id = id;
        this.idAutomovil = idAutomovil;
        this.idCotizacion = idCotizacion;
    }

    public Long getId() {
        return id;
    }

    public Long getIdAutomovil() {
        return idAutomovil;
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }

    
}
