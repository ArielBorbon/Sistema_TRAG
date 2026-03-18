
package dtos.trabajo;

import java.time.LocalDateTime;

/**
 *
 * Archivo: TrabajoDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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