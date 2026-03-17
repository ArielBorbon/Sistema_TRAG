
package dtos.servicio;

import insumoservicio.InsumoServicioAgregarDTO;
import insumoservicio.InsumoServicioDetalleDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author 
 */
public class ServicioAgregarDTO {

    private String nombre;
    private String descripcion;
    private BigDecimal precioManoObraSugerido;
    private List<InsumoServicioAgregarDTO> insumosServicio;

    public ServicioAgregarDTO(String nombre, String descripcion, BigDecimal precioManoObraSugerido, List<InsumoServicioAgregarDTO> insumosServicio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioManoObraSugerido = precioManoObraSugerido;
        this.insumosServicio = insumosServicio;
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

    public List<InsumoServicioAgregarDTO> getInsumosServicio() {
        return insumosServicio;
    }

    
}
