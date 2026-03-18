
package entidades;

import enums.EstadoCotizacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * Archivo: Cotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
@Entity
@Table(name = "cotizaciones")
public class Cotizacion implements Serializable {

    public Cotizacion() {
    }

    public Cotizacion(Long id) {
        this.id = id;
    }

    public Cotizacion(BigDecimal precioManoObra, String estadoAutomovil, String diagnosticoGeneral, List<InsumoCotizacion> insumosCotizacion) {
        this.precioManoObra = precioManoObra;
        this.estadoAutomovil = estadoAutomovil;
        this.diagnosticoGeneral = diagnosticoGeneral;
        this.insumosCotizacion = insumosCotizacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioManoObra;

    @Column(length = 1000)
    private String estadoAutomovil;

    @Column(columnDefinition = "TEXT")
    private String diagnosticoGeneral;

    private LocalDateTime fechaCreacion;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCotizacion estadoCotizacion;

    @OneToMany (mappedBy = "cotizacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InsumoCotizacion> insumosCotizacion;
    
    @OneToOne(mappedBy = "cotizacion", fetch = FetchType.LAZY)
    private OrdenTrabajo ordenTrabajo;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoCotizacion getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(EstadoCotizacion estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }

    public List<InsumoCotizacion> getInsumosCotizacion() {
        return insumosCotizacion;
    }

    public void setInsumosCotizacion(List<InsumoCotizacion> insumosCotizacion) {
        this.insumosCotizacion = insumosCotizacion;
    }

    public OrdenTrabajo getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    

}
