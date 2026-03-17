
package dtos.insumotrabajoadquirido;

import java.math.BigDecimal;

/**
 *
 * @author PC Gamer
 */
public class InsumoTrabajoAdquiridoDetalleDTO {

    private Long id;
    private Integer cantidad;
    private BigDecimal costoReal;
    private Long idTrabajo;
    private Long idInsumo;

    public InsumoTrabajoAdquiridoDetalleDTO(Long id, Integer cantidad, BigDecimal costoReal, Long idTrabajo, Long idInsumo) {
        this.id = id;
        this.cantidad = cantidad;
        this.costoReal = costoReal;
        this.idTrabajo = idTrabajo;
        this.idInsumo = idInsumo;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public BigDecimal getCostoReal() {
        return costoReal;
    }

    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    
    
}