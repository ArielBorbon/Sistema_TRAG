
package dtos.insumocotizacion;

import dtos.insumos.InsumoResumenDTO;

import java.math.BigDecimal;

/**
 *
 * Archivo: InsumoCotizacionDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class InsumoCotizacionDetalleDTO {

    private Long id;
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private Long idCotizacion;
    private InsumoResumenDTO insumo;
    private BigDecimal subtotal;
    private boolean activo;


    public InsumoCotizacionDetalleDTO(Long id, Integer cantidadRequerida, BigDecimal precio, Long idCotizacion, InsumoResumenDTO insumo, boolean activo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.idCotizacion = idCotizacion;
        this.insumo = insumo;
        this.activo = activo;
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

    public boolean isActivo() {
        return activo;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    
}