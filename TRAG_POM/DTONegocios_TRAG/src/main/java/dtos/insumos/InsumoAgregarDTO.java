
package dtos.insumos;

import java.math.BigDecimal;

/**
 *
 * Archivo: InsumoAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class InsumoAgregarDTO {

    private String nombre;
    private BigDecimal precioSugerido;
    private Long idProveedor; 

    public InsumoAgregarDTO(String nombre, BigDecimal precioSugerido, Long idProveedor) {
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
        this.idProveedor = idProveedor;
    }
    
    public InsumoAgregarDTO(String nombre, BigDecimal precioSugerido) {
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioSugerido() {
        return precioSugerido;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }
    
    
}
