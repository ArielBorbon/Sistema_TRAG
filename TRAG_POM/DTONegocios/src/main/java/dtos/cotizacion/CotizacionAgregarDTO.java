
package dtos.cotizacion;

import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 
 */
public class CotizacionAgregarDTO{
    
    private BigDecimal precioManoObra;
    private String estadoAutomovil;
    private String diagnosticoGeneral;
    private LocalDateTime fechaCreacion;
    private List<InsumoCotizacionAgregarDTO> insumosCotizacion;
    private Long idServicio;

    public CotizacionAgregarDTO(BigDecimal precioManoObra, String estadoAutomovil, String diagnosticoGeneral, LocalDateTime fechaCreacion, List<InsumoCotizacionAgregarDTO> insumosCotizacion, Long idServicio) {
        this.precioManoObra = precioManoObra;
        this.estadoAutomovil = estadoAutomovil;
        this.diagnosticoGeneral = diagnosticoGeneral;
        this.fechaCreacion = fechaCreacion;
        this.insumosCotizacion = insumosCotizacion;
        this.idServicio = idServicio;
    }

    public BigDecimal getPrecioManoObra() {
        return precioManoObra;
    }

    public String getEstadoAutomovil() {
        return estadoAutomovil;
    }

    public String getDiagnosticoGeneral() {
        return diagnosticoGeneral;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public List<InsumoCotizacionAgregarDTO> getInsumosCotizacion() {
        return insumosCotizacion;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    
}
