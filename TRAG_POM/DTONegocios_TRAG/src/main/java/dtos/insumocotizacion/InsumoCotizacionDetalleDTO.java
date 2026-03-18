
package dtos.insumocotizacion;

import dtos.insumos.InsumoResumenDTO;
import java.math.BigDecimal;

import java.math.BigDecimal;

/**
 *
 * @author 
 */
public class InsumoCotizacionDetalleDTO {

    private Long id;
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private Long idCotizacion;
    private InsumoResumenDTO insumo;
    private BigDecimal subtotal;


    public InsumoCotizacionDetalleDTO(Long id, Integer cantidadRequerida, BigDecimal precio, Long idCotizacion, InsumoResumenDTO insumo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.idCotizacion = idCotizacion;
        this.insumo = insumo;
    }

    public InsumoCotizacionDetalleDTO(Long id, Integer cantidadRequerida, BigDecimal precio) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
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

    public Long getIdCotizacion() {
        return idCotizacion;
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