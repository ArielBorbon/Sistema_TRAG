

package dtos.cotizacion;


import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import enums.EstadoCotizacionNegocios;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: CotizacionDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class CotizacionDetalleDTO {

    private Long id;
    private BigDecimal precioManoObra;
    private String estadoAutomovil;
    private String diagnosticoGeneral;
    private LocalDateTime fechaCreacion;
    private List<InsumoCotizacionDetalleDTO> insumosCotizacion;
    private String nombreServicio;
    private EstadoCotizacionNegocios estado;

    public CotizacionDetalleDTO(
            Long id, 
            BigDecimal precioManoObra,
            String estadoAutomovil,
            String diagnosticoGeneral, 
            LocalDateTime fechaCreacion, 
            List<InsumoCotizacionDetalleDTO> insumosCotizacion,
            String nombreServicio,
            EstadoCotizacionNegocios estado) {
        
        this.id = id;
        this.precioManoObra = precioManoObra;
        this.estadoAutomovil = estadoAutomovil;
        this.diagnosticoGeneral = diagnosticoGeneral;
        this.fechaCreacion = fechaCreacion;
        this.insumosCotizacion = insumosCotizacion;
        this.nombreServicio = nombreServicio;
        this.estado = estado;
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

    public String getNombreServicio() {
        return nombreServicio;
    }

    public EstadoCotizacionNegocios getEstado() {
        return estado;
    }
    
}