
package dtos.insumocotizacion;

import java.math.BigDecimal;

/**
 *
 * Archivo: InsumoCotizacionActualizarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class InsumoCotizacionActualizarDTO {
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private Long idCotizacion;
    private Long idInsumo;


    public InsumoCotizacionActualizarDTO(Integer cantidadRequerida, BigDecimal precio, Long idCotizacion, Long idInsumo) {
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.idCotizacion = idCotizacion;
        this.idInsumo = idInsumo;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Integer cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }
}
