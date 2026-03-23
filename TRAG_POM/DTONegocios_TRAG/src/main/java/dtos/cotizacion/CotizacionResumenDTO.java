
package dtos.cotizacion;

import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import enums.EstadoCotizacionNegocios;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: CotizacionResumenDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class CotizacionResumenDTO {
    
    // Mostrar auto, precio y fecha.
    private Long id;
    private String nombreCliente;
    private String apellidoPaternoCliente;
    public String apellidoMaternoCliente;
    private String marcaAutomovil;
    private String modeloAutomovil;
    private String matriculaAutomovil;
    private Integer anioAutomovil;
    private LocalDateTime fechaCreacion;
    private BigDecimal precioManoObra;
    private List<InsumoCotizacionDetalleDTO> insumosCotizacion;
    private BigDecimal precioTotal;
    private EstadoCotizacionNegocios estadoCotizacion;

    public CotizacionResumenDTO(Long id, String nombreCliente, String apellidoPaternoCliente, String apellidoMaternoCliente, String marcaAutomovil, String modeloAutomovil, String matriculaAutomovil, Integer anioAutomovil, LocalDateTime fechaCreacion, BigDecimal precioManoObra, List<InsumoCotizacionDetalleDTO> insumosCotizacion, EstadoCotizacionNegocios estadoCotizacion) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.apellidoPaternoCliente = apellidoPaternoCliente;
        this.apellidoMaternoCliente = apellidoMaternoCliente;
        this.marcaAutomovil = marcaAutomovil;
        this.modeloAutomovil = modeloAutomovil;
        this.matriculaAutomovil = matriculaAutomovil;
        this.anioAutomovil = anioAutomovil;
        this.fechaCreacion = fechaCreacion;
        this.precioManoObra = precioManoObra;
        this.insumosCotizacion = insumosCotizacion;
        this.estadoCotizacion = estadoCotizacion;
    }

    
    
    public CotizacionResumenDTO(Long id, String nombreCliente, String apellidoPaternoCliente, String marcaAutomovil, String modeloAutomovil, String matriculaAutomovil, Integer anioAutomovil, LocalDateTime fechaCreacion, BigDecimal precioManoObra, List<InsumoCotizacionDetalleDTO> insumosCotizacion, EstadoCotizacionNegocios estadoCotizacion) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.apellidoPaternoCliente = apellidoPaternoCliente;
        this.marcaAutomovil = marcaAutomovil;
        this.modeloAutomovil = modeloAutomovil;
        this.matriculaAutomovil = matriculaAutomovil;
        this.anioAutomovil = anioAutomovil;
        this.fechaCreacion = fechaCreacion;
        this.precioManoObra = precioManoObra;
        this.insumosCotizacion = insumosCotizacion;
        this.estadoCotizacion = estadoCotizacion;
    }

    public Long getId() {
        return id;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getApellidoPaternoCliente() {
        return apellidoPaternoCliente;
    }

    public String getApellidoMaternoCliente() {
        return apellidoMaternoCliente;
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

    public String getMatriculaAutomovil() {
        return matriculaAutomovil;
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
