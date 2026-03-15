/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import enums.EstadoCita;
import java.time.LocalDateTime;

/**
 *
 * @author PC Gamer
 */
public class CitaDTO {

    private Long id;
    private LocalDateTime fechaProgramada;
    private EstadoCita estadoCita;
    private AutomovilDTO automovil;

    public CitaDTO() {
    }

    public CitaDTO(Long id, LocalDateTime fechaProgramada, EstadoCita estadoCita) {
        this.id = id;
        this.fechaProgramada = fechaProgramada;
        this.estadoCita = estadoCita;
    }

    public CitaDTO(Long id, LocalDateTime fechaProgramada, EstadoCita estadoCita, AutomovilDTO automovil) {
        this.id = id;
        this.fechaProgramada = fechaProgramada;
        this.estadoCita = estadoCita;
        this.automovil = automovil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public AutomovilDTO getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilDTO automovil) {
        this.automovil = automovil;
    }

}
