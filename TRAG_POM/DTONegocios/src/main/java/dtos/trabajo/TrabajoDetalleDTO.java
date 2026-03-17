/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos.trabajo;

import dtos.OrdenTrabajoDTO;
import java.time.LocalDateTime;

/**
 *
 * @author PC Gamer
 */
public class TrabajoDetalleDTO {

    private Long id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaEstimadaTermino;
    private LocalDateTime fechaTermino;
    private Long idOrdenTrabajo;

    public TrabajoDetalleDTO(Long id, LocalDateTime fechaInicio, LocalDateTime fechaEstimadaTermino, LocalDateTime fechaTermino, Long idOrdenTrabajo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaTermino = fechaEstimadaTermino;
        this.fechaTermino = fechaTermino;
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaEstimadaTermino() {
        return fechaEstimadaTermino;
    }

    public LocalDateTime getFechaTermino() {
        return fechaTermino;
    }

    public Long getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    

}