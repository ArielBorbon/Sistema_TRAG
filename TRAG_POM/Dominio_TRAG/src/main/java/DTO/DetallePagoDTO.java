/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author PC Gamer
 */
public class DetallePagoDTO {

    private Long id;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaGarantia;
    private BigDecimal pagoTotal;
    private OrdenTrabajoDTO ordenTrabajo;

    public DetallePagoDTO() {
    }

    public DetallePagoDTO(Long id, LocalDateTime fechaEntrega, LocalDateTime fechaGarantia, BigDecimal pagoTotal, OrdenTrabajoDTO ordenTrabajo) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.fechaGarantia = fechaGarantia;
        this.pagoTotal = pagoTotal;
        this.ordenTrabajo = ordenTrabajo;
    }

    public DetallePagoDTO(Long id, LocalDateTime fechaEntrega, LocalDateTime fechaGarantia, BigDecimal pagoTotal) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.fechaGarantia = fechaGarantia;
        this.pagoTotal = pagoTotal;
    }
    
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDateTime getFechaGarantia() {
        return fechaGarantia;
    }

    public void setFechaGarantia(LocalDateTime fechaGarantia) {
        this.fechaGarantia = fechaGarantia;
    }

    public BigDecimal getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(BigDecimal pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public OrdenTrabajoDTO getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoDTO ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }
    
    
    
    
    
}
