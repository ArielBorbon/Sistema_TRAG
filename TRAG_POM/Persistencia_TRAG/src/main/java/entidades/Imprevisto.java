
package entidades;

import enums.EstadoImprevisto;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * Archivo: Imprevisto.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
@Entity
@Table(name = "imprevistos")
public class Imprevisto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime nuevaFechaEntrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoImprevisto estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_trabajo_id", nullable = false)
    private OrdenTrabajo ordenTrabajo;

    public Imprevisto() {
    }

    public Imprevisto(Long id) {
        this.id = id;
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

    public OrdenTrabajo getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }
    
    
}
