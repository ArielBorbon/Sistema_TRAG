
package dtos.insumocotizacion;

import java.math.BigDecimal;

/**
 *
 * Archivo: InsumoCotizacionAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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
