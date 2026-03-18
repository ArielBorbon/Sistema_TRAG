
package dtos.insumoimprevisto;

import java.math.BigDecimal;

/**
 *
 * Archivo: InsumoImprevistoDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class InsumoImprevistoDetalleDTO {

    private Long id;
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private Long idImprevisto;
    private Long idInsumo;

    public InsumoImprevistoDetalleDTO(Long id, Integer cantidadRequerida, BigDecimal precio, Long idImprevisto, Long idInsumo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.idImprevisto = idImprevisto;
        this.idInsumo = idInsumo;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Long getIdImprevisto() {
        return idImprevisto;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    
    
}