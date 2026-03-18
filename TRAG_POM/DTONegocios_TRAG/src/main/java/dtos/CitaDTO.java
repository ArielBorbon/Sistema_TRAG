
package dtos;

import enums.EstadoCita;
import java.time.LocalDateTime;

/**
 *
 * Archivo: CitaDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class CitaDTO {

    private Long id;
    private LocalDateTime fechaProgramada;
    private EstadoCita estadoCita;
    private Long idAutomovil;

    public CitaDTO() {
    }

    public CitaDTO(Long id, LocalDateTime fechaProgramada, EstadoCita estadoCita) {
        this.id = id;
        this.fechaProgramada = fechaProgramada;
        this.estadoCita = estadoCita;
    }

    public CitaDTO(Long id, LocalDateTime fechaProgramada, EstadoCita estadoCita, Long idAutomovil) {
        this.id = id;
        this.fechaProgramada = fechaProgramada;
        this.estadoCita = estadoCita;
        this.idAutomovil = idAutomovil;
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

    public Long getIdAutomovil() {
        return idAutomovil;
    }

    public void setIdAutomovil(Long idAutomovil) {
        this.idAutomovil = idAutomovil;
    }

}
