
package dtos.ordentrabajo;

import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: OrdenTrabajoCotizacionAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class OrdenTrabajoCotizacionAgregarDTO {
    private Long idAutomovil;
    private Long idServicio;
    private BigDecimal precioManoObra;
    private String diagnosticoGeneral;
    private String estadoAutomovil;
    private LocalDateTime fechaCreacion;
    private List<InsumoCotizacionAgregarDTO> insumosCotizacion;

    public OrdenTrabajoCotizacionAgregarDTO(Long idAutomovil, Long idServicio, BigDecimal precioManoObra, String diagnosticoGeneral, String estadoAutomovil, LocalDateTime fechaCreacion, List<InsumoCotizacionAgregarDTO> insumosCotizacion) {
        this.idAutomovil = idAutomovil;
        this.idServicio = idServicio;
        this.precioManoObra = precioManoObra;
        this.diagnosticoGeneral = diagnosticoGeneral;
        this.estadoAutomovil = estadoAutomovil;
        this.fechaCreacion = fechaCreacion;
        this.insumosCotizacion = insumosCotizacion;
    }

    public OrdenTrabajoCotizacionAgregarDTO(Long idAutomovil, Long idServicio, BigDecimal precioManoObra, String diagnosticoGeneral, String estadoAutomovil, List<InsumoCotizacionAgregarDTO> insumosCotizacion) {
        this.idAutomovil = idAutomovil;
        this.idServicio = idServicio;
        this.precioManoObra = precioManoObra;
        this.diagnosticoGeneral = diagnosticoGeneral;
        this.estadoAutomovil = estadoAutomovil;
        this.insumosCotizacion = insumosCotizacion;
    }

    public Long getIdAutomovil() {
        return idAutomovil;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public BigDecimal getPrecioManoObra() {
        return precioManoObra;
    }

    public String getDiagnosticoGeneral() {
        return diagnosticoGeneral;
    }

    public String getEstadoAutomovil() {
        return estadoAutomovil;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public List<InsumoCotizacionAgregarDTO> getInsumosCotizacion() {
        return insumosCotizacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}
