
package dtos.servicio;

import java.math.BigDecimal;

/**
 *
 * Archivo: ServicioActualizarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ServicioActualizarDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioManoObraSugerido;

    public ServicioActualizarDTO() {
    }

    public ServicioActualizarDTO(Long id, String nombre, String descripcion, BigDecimal precioManoObraSugerido) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioManoObraSugerido = precioManoObraSugerido;
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
    
    
}
