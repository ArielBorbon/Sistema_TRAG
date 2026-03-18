
package dtos.cotizacion;

import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import enums.EstadoCotizacionNegocios;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: CotizacionAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class CotizacionAgregarDTO{
    
    private BigDecimal precioManoObra;
    private String estadoAutomovil;
    private String diagnosticoGeneral;
    private LocalDateTime fechaCreacion;
    private List<InsumoCotizacionAgregarDTO> insumosCotizacion;
    private Long idServicio;
    private EstadoCotizacionNegocios estadoCotizacion;

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

    public EstadoCotizacionNegocios getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(EstadoCotizacionNegocios estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }
    
}
