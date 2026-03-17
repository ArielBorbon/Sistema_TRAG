
package imprevisto;

import dtos.OrdenTrabajoDTO;
import enums.EstadoImprevisto;
import java.time.LocalDateTime;

/**
 *
 * @author PC Gamer
 */
public class ImprevistoDetalleDTO {

    private Long id;
    private LocalDateTime nuevaFechaEntrega;
    private EstadoImprevisto estado;
    private Long idOrdenTrabajo;

    public ImprevistoDetalleDTO(Long id, LocalDateTime nuevaFechaEntrega, EstadoImprevisto estado, Long idOrdenTrabajo) {
        this.id = id;
        this.nuevaFechaEntrega = nuevaFechaEntrega;
        this.estado = estado;
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getNuevaFechaEntrega() {
        return nuevaFechaEntrega;
    }

    public EstadoImprevisto getEstado() {
        return estado;
    }

    public Long getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }
    
}