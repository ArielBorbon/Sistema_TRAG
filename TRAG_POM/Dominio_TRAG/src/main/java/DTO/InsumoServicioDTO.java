/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author PC Gamer
 */
public class InsumoServicioDTO {

    private Long id;
    private Integer cantidadDefault;
    private ServicioDTO servicio;
    private InsumoDTO insumo;

    public InsumoServicioDTO() {
    }

    public InsumoServicioDTO(Long id, Integer cantidadDefault, ServicioDTO servicio, InsumoDTO insumo) {
        this.id = id;
        this.cantidadDefault = cantidadDefault;
        this.servicio = servicio;
        this.insumo = insumo;
    }

    public InsumoServicioDTO(Long id, Integer cantidadDefault, ServicioDTO servicio) {
        this.id = id;
        this.cantidadDefault = cantidadDefault;
        this.servicio = servicio;
    }

    public InsumoServicioDTO(Long id, Integer cantidadDefault, InsumoDTO insumo) {
        this.id = id;
        this.cantidadDefault = cantidadDefault;
        this.insumo = insumo;
    }
    
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadDefault() {
        return cantidadDefault;
    }

    public void setCantidadDefault(Integer cantidadDefault) {
        this.cantidadDefault = cantidadDefault;
    }

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    public InsumoDTO getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoDTO insumo) {
        this.insumo = insumo;
    }
    
    
    
}
