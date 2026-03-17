
package dtos.insumoimprevisto;

import imprevisto.ImprevistoDetalleDTO;
import dtos.insumos.InsumoDetalleDTO;
import java.math.BigDecimal;

/**
 *
 * @author 
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