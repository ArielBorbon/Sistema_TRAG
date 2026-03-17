
package dtos.ordentrabajo;

/**
 *
 * @author 
 */
public class OrdenTrabajoAgregarDTO {
    private Long idAutomovil;
    private Long idCotizacion;

    public OrdenTrabajoAgregarDTO(Long idAutomovil, Long idCotizacion) {
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
