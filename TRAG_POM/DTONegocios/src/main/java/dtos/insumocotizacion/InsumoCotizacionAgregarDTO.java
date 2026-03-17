
package dtos.insumocotizacion;

import java.math.BigDecimal;

/**
 *
 * @author 
 */
public class InsumoCotizacionAgregarDTO {
    
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private Long idInsumo;

    public InsumoCotizacionAgregarDTO(Integer cantidadRequerida, BigDecimal precio, Long idInsumo) {
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.idInsumo = idInsumo;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }
    
    
}
