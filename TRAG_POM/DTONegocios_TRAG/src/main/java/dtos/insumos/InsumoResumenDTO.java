
package dtos.insumos;

import java.math.BigDecimal;

/**
 *
 * Archivo: InsumoResumenDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class InsumoResumenDTO {

    private Long id;
    private String nombre;
    private BigDecimal precioSugerido;

    public InsumoResumenDTO(Long id, String nombre, BigDecimal precioSugerido) {
        this.id = id;
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
    }

    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioSugerido() {
        return precioSugerido;
    }


}
