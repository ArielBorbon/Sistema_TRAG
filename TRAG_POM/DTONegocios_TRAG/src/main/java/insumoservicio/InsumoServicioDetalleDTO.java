
package insumoservicio;

import dtos.insumos.InsumoResumenDTO;
import java.math.BigDecimal;

/**
 *
 * @author
 */
public class InsumoServicioDetalleDTO {

    private Long id;
    private Integer cantidadDefault;
    private Long idServicio;
    private InsumoResumenDTO insumo;
    private BigDecimal subtotal;

    public InsumoServicioDetalleDTO(Long id, Integer cantidadDefault, Long idServicio, InsumoResumenDTO insumo) {
        this.id = id;
        this.cantidadDefault = cantidadDefault;
        this.idServicio = idServicio;
        this.insumo = insumo;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidadDefault() {
        return cantidadDefault;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public InsumoResumenDTO getInsumo() {
        return insumo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
}