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
public class CotizacionDTO {

    private Long id;
    private BigDecimal precioManoObra;
    private String estadoAutomovil;
    private String diagnosticoGeneral;

    public CotizacionDTO() {
    }

    public CotizacionDTO(Long id, BigDecimal precioManoObra, String estadoAutomovil, String diagnosticoGeneral) {
        this.id = id;
        this.precioManoObra = precioManoObra;
        this.estadoAutomovil = estadoAutomovil;
        this.diagnosticoGeneral = diagnosticoGeneral;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecioManoObra() {
        return precioManoObra;
    }

    public void setPrecioManoObra(BigDecimal precioManoObra) {
        this.precioManoObra = precioManoObra;
    }

    public String getEstadoAutomovil() {
        return estadoAutomovil;
    }

    public void setEstadoAutomovil(String estadoAutomovil) {
        this.estadoAutomovil = estadoAutomovil;
    }

    public String getDiagnosticoGeneral() {
        return diagnosticoGeneral;
    }

    public void setDiagnosticoGeneral(String diagnosticoGeneral) {
        this.diagnosticoGeneral = diagnosticoGeneral;
    }
    
    
    
    
    
}
