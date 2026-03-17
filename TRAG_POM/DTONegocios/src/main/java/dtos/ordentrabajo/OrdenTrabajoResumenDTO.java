
package dtos.ordentrabajo;

/**
 *
 * @author 
 */
public class OrdenTrabajoResumenDTO {
    private Long idAutomovil;
    private Long idCotizacion;

    public OrdenTrabajoResumenDTO(Long idAutomovil, Long idCotizacion) {
        this.idAutomovil = idAutomovil;
        this.idCotizacion = idCotizacion;
    }
    
    public Long getIdAutomovil() {
        return idAutomovil;
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }
}
