

package dtos.cotizacion;


import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 
 */
public class CotizacionDetalleDTO {

    private Long id;
    private BigDecimal precioManoObra;
    private String estadoAutomovil;
    private String diagnosticoGeneral;
    private LocalDateTime fechaCreacion;
    private List<InsumoCotizacionDetalleDTO> insumosCotizacion;

    public CotizacionDetalleDTO(Long id, BigDecimal precioManoObra, String estadoAutomovil, String diagnosticoGeneral, LocalDateTime fechaCreacion, List<InsumoCotizacionDetalleDTO> insumosCotizacion) {
        this.id = id;
        this.precioManoObra = precioManoObra;
        this.estadoAutomovil = estadoAutomovil;
        this.diagnosticoGeneral = diagnosticoGeneral;
        this.fechaCreacion = fechaCreacion;
        this.insumosCotizacion = insumosCotizacion;
    }

    public Long getId() {
        return id;
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

    public List<InsumoCotizacionDetalleDTO> getInsumosCotizacion() {
        return insumosCotizacion;
    }
    
}