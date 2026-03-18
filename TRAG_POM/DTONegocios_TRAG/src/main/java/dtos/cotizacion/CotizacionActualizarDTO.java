

package dtos.cotizacion;

import dtos.insumocotizacion.InsumoCotizacionActualizarDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: CotizacionActualizarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class CotizacionActualizarDTO{
    
    private Long id;
    private BigDecimal precioManoObra;
    private String estadoAutomovil;
    private String diagnosticoGeneral;
    private LocalDateTime fechaCreacion;
    private List<InsumoCotizacionActualizarDTO> insumosCotizacion;

    public CotizacionActualizarDTO(Long id, BigDecimal precioManoObra, String estadoAutomovil, String diagnosticoGeneral, List<InsumoCotizacionActualizarDTO> insumosCotizacion) {
        this.id = id;
        this.precioManoObra = precioManoObra;
        this.estadoAutomovil = estadoAutomovil;
        this.diagnosticoGeneral = diagnosticoGeneral;
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

    public List<InsumoCotizacionActualizarDTO> getInsumosCotizacion() {
        return insumosCotizacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    

}
