
package dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author PC Gamer
 */
public class DetallePagoDTO {

    private Long id;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaGarantia;
    private BigDecimal pagoTotal;
    private Long idOrdenTrabajo;

    public DetallePagoDTO(Long id, LocalDateTime fechaEntrega, LocalDateTime fechaGarantia, BigDecimal pagoTotal, Long idOrdenTrabajo) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.fechaGarantia = fechaGarantia;
        this.pagoTotal = pagoTotal;
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public LocalDateTime getFechaGarantia() {
        return fechaGarantia;
    }

    public BigDecimal getPagoTotal() {
        return pagoTotal;
    }

    public Long getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }
    
    
}