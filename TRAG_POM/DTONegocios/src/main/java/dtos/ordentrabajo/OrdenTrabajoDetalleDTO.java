
package dtos.ordentrabajo;

/**
 *
 * @author 
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
