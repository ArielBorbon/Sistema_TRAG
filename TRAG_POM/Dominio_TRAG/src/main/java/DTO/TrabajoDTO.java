/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author PC Gamer
 */
public class TrabajoDTO {

    private Long id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaEstimadaTermino;
    private LocalDateTime fechaTermino;
    private OrdenTrabajoDTO ordenTrabajo;

    public TrabajoDTO() {
    }

    public TrabajoDTO(Long id, LocalDateTime fechaInicio, LocalDateTime fechaEstimadaTermino, LocalDateTime fechaTermino) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaTermino = fechaEstimadaTermino;
        this.fechaTermino = fechaTermino;
    }

    public TrabajoDTO(Long id, LocalDateTime fechaInicio, LocalDateTime fechaEstimadaTermino, LocalDateTime fechaTermino, OrdenTrabajoDTO ordenTrabajo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaTermino = fechaEstimadaTermino;
        this.fechaTermino = fechaTermino;
        this.ordenTrabajo = ordenTrabajo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaEstimadaTermino() {
        return fechaEstimadaTermino;
    }

    public void setFechaEstimadaTermino(LocalDateTime fechaEstimadaTermino) {
        this.fechaEstimadaTermino = fechaEstimadaTermino;
    }

    public OrdenTrabajoDTO getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajoDTO ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public LocalDateTime getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(LocalDateTime fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

}
