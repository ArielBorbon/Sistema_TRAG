/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;

/**
 *
 * @author PC Gamer
 */
public class InsumoCotizacionDTO {

    private Long id;
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private CotizacionDTO cotizacion;
    private InsumoDTO insumo;

    public InsumoCotizacionDTO() {
    }

    public InsumoCotizacionDTO(Long id, Integer cantidadRequerida, BigDecimal precio, CotizacionDTO cotizacion, InsumoDTO insumo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.cotizacion = cotizacion;
        this.insumo = insumo;
    }

    public InsumoCotizacionDTO(Long id, Integer cantidadRequerida, BigDecimal precio, CotizacionDTO cotizacion) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.cotizacion = cotizacion;
    }

    public InsumoCotizacionDTO(Long id, Integer cantidadRequerida, BigDecimal precio, InsumoDTO insumo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.insumo = insumo;
    }

    public InsumoCotizacionDTO(Long id, Integer cantidadRequerida, BigDecimal precio) {
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

    public CotizacionDTO getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(CotizacionDTO cotizacion) {
        this.cotizacion = cotizacion;
    }

    public InsumoDTO getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoDTO insumo) {
        this.insumo = insumo;
    }
    
    
    
    
    
}
