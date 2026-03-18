
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

    private String nombre;
    private BigDecimal precioSugerido;

    public InsumoResumenDTO(String nombre, BigDecimal precioSugerido) {
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioSugerido() {
        return precioSugerido;
    }


}
