
package dtos.cotizacion;

import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import enums.EstadoCotizacionNegocios;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 
 */
public class CotizacionResumenDTO {
    
    // Mostrar auto, precio y fecha.
    private String nombreCliente;
    private String apellidoPaternoCliente;
    private String marcaAutomovil;
    private String modeloAutomovil;
    private Integer anioAutomovil;
    private LocalDateTime fechaCreacion;
    private BigDecimal precioManoObra;
    private List<InsumoCotizacionDetalleDTO> insumosCotizacion;
    private BigDecimal precioTotal;
    private EstadoCotizacionNegocios estadoCotizacion;

    public CotizacionResumenDTO(String nombreCliente, String apellidoPaternoCliente, String marcaAutomovil, String modeloAutomovil, Integer anioAutomovil, LocalDateTime fechaCreacion, BigDecimal precioManoObra, List<InsumoCotizacionDetalleDTO> insumosCotizacion, EstadoCotizacionNegocios estadoCotizacion) {
        this.nombreCliente = nombreCliente;
        this.apellidoPaternoCliente = apellidoPaternoCliente;
        this.marcaAutomovil = marcaAutomovil;
        this.modeloAutomovil = modeloAutomovil;
        this.anioAutomovil = anioAutomovil;
        this.fechaCreacion = fechaCreacion;
        this.precioManoObra = precioManoObra;
        this.insumosCotizacion = insumosCotizacion;
        this.estadoCotizacion = estadoCotizacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoPaternoCliente() {
        return apellidoPaternoCliente;
    }

    public String getMarcaAutomovil() {
        return marcaAutomovil;
    }

    public String getModeloAutomovil() {
        return modeloAutomovil;
    }

    public Integer getAnioAutomovil() {
        return anioAutomovil;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public BigDecimal getPrecioManoObra() {
        return precioManoObra;
    }

    public List<InsumoCotizacionDetalleDTO> getInsumosCotizacion() {
        return insumosCotizacion;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoCotizacionNegocios getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(EstadoCotizacionNegocios estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }
 
}
