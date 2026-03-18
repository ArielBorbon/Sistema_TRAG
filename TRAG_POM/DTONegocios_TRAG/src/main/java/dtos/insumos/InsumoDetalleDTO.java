
package dtos.insumos;

import java.math.BigDecimal;

/**
 *
 * Archivo: InsumoDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class InsumoDetalleDTO {

    private Long id;
    private String nombre;
    private BigDecimal precioSugerido;
    private Long idProveedor; 
    
    public InsumoDetalleDTO(Long id, String nombre, BigDecimal precioSugerido, Long idProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
        this.idProveedor = idProveedor;
    }

    public InsumoDetalleDTO(Long id, String nombre, BigDecimal precioSugerido) {
        this.id = id;
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecioSugerido() {
        return precioSugerido;
    }

    public void setPrecioSugerido(BigDecimal precioSugerido) {
        this.precioSugerido = precioSugerido;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }
    
}
