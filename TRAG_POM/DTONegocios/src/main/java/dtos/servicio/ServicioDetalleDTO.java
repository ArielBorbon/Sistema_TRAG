
package dtos.servicio;

import java.math.BigDecimal;

/**
 *
 * @author 
 */
public class ServicioDetalleDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioManoObraSugerido;

    public ServicioDetalleDTO() {
    }

    public ServicioDetalleDTO(Long id, String nombre, String descripcion, BigDecimal precioManoObraSugerido) {
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