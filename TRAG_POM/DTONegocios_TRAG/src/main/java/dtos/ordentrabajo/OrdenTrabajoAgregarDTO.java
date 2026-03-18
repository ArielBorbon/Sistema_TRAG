
package dtos.ordentrabajo;

/**
 *
 * Archivo: OrdenTrabajoAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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
