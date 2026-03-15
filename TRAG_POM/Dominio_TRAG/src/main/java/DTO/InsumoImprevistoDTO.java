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
public class InsumoImprevistoDTO {

    private Long id;
    private Integer cantidadRequerida;
    private BigDecimal precio;
    private ImprevistoDTO imprevisto;
    private InsumoDTO insumo;

    public InsumoImprevistoDTO() {
    }

    public InsumoImprevistoDTO(Long id, Integer cantidadRequerida, BigDecimal precio, ImprevistoDTO imprevisto, InsumoDTO insumo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.imprevisto = imprevisto;
        this.insumo = insumo;
    }

    public InsumoImprevistoDTO(Long id, Integer cantidadRequerida, BigDecimal precio, ImprevistoDTO imprevisto) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.imprevisto = imprevisto;
    }

    public InsumoImprevistoDTO(Long id, Integer cantidadRequerida, BigDecimal precio, InsumoDTO insumo) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
        this.precio = precio;
        this.insumo = insumo;
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

    public ImprevistoDTO getImprevisto() {
        return imprevisto;
    }

    public void setImprevisto(ImprevistoDTO imprevisto) {
        this.imprevisto = imprevisto;
    }

    public InsumoDTO getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoDTO insumo) {
        this.insumo = insumo;
    }
    
    
    
    
    
}
