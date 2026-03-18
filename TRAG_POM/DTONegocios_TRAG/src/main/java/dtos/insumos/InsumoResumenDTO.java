
package dtos.insumos;

import java.math.BigDecimal;

/**
 *
 * @author 
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
