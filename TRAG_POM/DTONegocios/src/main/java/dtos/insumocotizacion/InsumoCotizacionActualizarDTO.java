
package dtos.insumocotizacion;

import java.math.BigDecimal;

/**
 *
 * @author 
 */
public class InsumoCotizacionActualizarDTO {
    private Long id;
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private Long idCotizacion;
    private Long idInsumo;


    public InsumoCotizacionActualizarDTO(Long id, Integer cantidadRequerida, BigDecimal precio, Long idCotizacion, Long idInsumo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.idCotizacion = idCotizacion;
        this.idInsumo = idInsumo;
    }

    public InsumoCotizacionActualizarDTO(Long id, Integer cantidadRequerida, BigDecimal precio) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
