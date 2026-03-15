/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import enums.EstadoImprevisto;
import java.time.LocalDateTime;

/**
 *
 * @author PC Gamer
 */
public class ImprevistoDTO {

    private Long id;
    private LocalDateTime nuevaFechaEntrega;
    private EstadoImprevisto estado;
    private OrdenTrabajoDTO ordenTrabajo;

    public ImprevistoDTO() {
    }

    public ImprevistoDTO(Long id, LocalDateTime nuevaFechaEntrega, EstadoImprevisto estado, OrdenTrabajoDTO ordenTrabajo) {
        this.id = id;
        this.nuevaFechaEntrega = nuevaFechaEntrega;
        this.estado = estado;
        this.ordenTrabajo = ordenTrabajo;
    }

    public ImprevistoDTO(Long id, LocalDateTime nuevaFechaEntrega, EstadoImprevisto estado) {
        this.id = id;
        this.nuevaFechaEntrega = nuevaFechaEntrega;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getNuevaFechaEntrega() {
        return nuevaFechaEntrega;
    }

    public void setNuevaFechaEntrega(LocalDateTime nuevaFechaEntrega) {
        this.nuevaFechaEntrega = nuevaFechaEntrega;
    }

    public EstadoImprevisto getEstado() {
        return estado;
    }

    public void setEstado(EstadoImprevisto estado) {
        this.estado = estado;
    }

    public OrdenTrabajoDTO getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoDTO ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }
    
    
    
    
    
}
