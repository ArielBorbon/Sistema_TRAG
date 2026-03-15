/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author PC Gamer
 */
public class OrdenTrabajoDTO {

    private Long id;
    private AutomovilDTO automovil;
    private CotizacionDTO cotizacion;
    private ServicioDTO servicio;

    public OrdenTrabajoDTO() {
    }

    public OrdenTrabajoDTO(Long id, AutomovilDTO automovil, CotizacionDTO cotizacion, ServicioDTO servicio) {
        this.id = id;
        this.automovil = automovil;
        this.cotizacion = cotizacion;
        this.servicio = servicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AutomovilDTO getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilDTO automovil) {
        this.automovil = automovil;
    }

    public CotizacionDTO getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(CotizacionDTO cotizacion) {
        this.cotizacion = cotizacion;
    }

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }
    
    
    
}
