
package dtos.servicio;

import insumoservicio.InsumoServicioDetalleDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Archivo: ServicioDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ServicioDetalleDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioManoObraSugerido;
    private List<InsumoServicioDetalleDTO> insumosServicio;

    public ServicioDetalleDTO() {
    }

    public ServicioDetalleDTO(Long id, String nombre, String descripcion, BigDecimal precioManoObraSugerido, List<InsumoServicioDetalleDTO> insumosServicio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioManoObraSugerido = precioManoObraSugerido;
        this.insumosServicio = insumosServicio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecioManoObraSugerido() {
        return precioManoObraSugerido;
    }

    public List<InsumoServicioDetalleDTO> getInsumosServicio() {
        return insumosServicio;
    }
    
    
}