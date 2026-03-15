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
public class InsumoTrabajoAdquiridoDTO {

    private Long id;
    private Integer cantidad;
    private BigDecimal costoReal;
    private TrabajoDTO trabajo;
    private InsumoDTO insumo;

    public InsumoTrabajoAdquiridoDTO() {
    }

    public InsumoTrabajoAdquiridoDTO(Long id, Integer cantidad, BigDecimal costoReal, TrabajoDTO trabajo, InsumoDTO insumo) {
        this.id = id;
        this.cantidad = cantidad;
        this.costoReal = costoReal;
        this.trabajo = trabajo;
        this.insumo = insumo;
    }

    public InsumoTrabajoAdquiridoDTO(Long id, Integer cantidad, BigDecimal costoReal, TrabajoDTO trabajo) {
        this.id = id;
        this.cantidad = cantidad;
        this.costoReal = costoReal;
        this.trabajo = trabajo;
    }

    public InsumoTrabajoAdquiridoDTO(Long id, Integer cantidad, BigDecimal costoReal, InsumoDTO insumo) {
        this.id = id;
        this.cantidad = cantidad;
        this.costoReal = costoReal;
        this.insumo = insumo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoReal() {
        return costoReal;
    }

    public void setCostoReal(BigDecimal costoReal) {
        this.costoReal = costoReal;
    }

    public TrabajoDTO getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(TrabajoDTO trabajo) {
        this.trabajo = trabajo;
    }

    public InsumoDTO getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoDTO insumo) {
        this.insumo = insumo;
    }
    
    
    
    
}
