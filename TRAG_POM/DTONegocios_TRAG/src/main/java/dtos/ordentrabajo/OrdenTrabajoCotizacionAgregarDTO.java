
package dtos.ordentrabajo;

import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 
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

}
