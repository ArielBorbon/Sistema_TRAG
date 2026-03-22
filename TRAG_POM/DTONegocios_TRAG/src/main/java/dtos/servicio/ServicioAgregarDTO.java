
package dtos.servicio;

import insumoservicio.InsumoServicioAgregarDTO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Archivo: ServicioAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ServicioAgregarDTO {

    private String nombre;
    private String descripcion;
    private BigDecimal precioManoObraSugerido;
    private String direccionIcono;
    private List<InsumoServicioAgregarDTO> insumosServicio;

    public ServicioAgregarDTO(String nombre, String descripcion, BigDecimal precioManoObraSugerido, String direccionIcono, List<InsumoServicioAgregarDTO> insumosServicio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioManoObraSugerido = precioManoObraSugerido;
        this.direccionIcono = direccionIcono;
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

    public String getDireccionIcono() {
        return direccionIcono;
    }
    
    public List<InsumoServicioAgregarDTO> getInsumosServicio() {
        return insumosServicio;
    }

    
}
