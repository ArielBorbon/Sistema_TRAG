
package dtos.insumos;

import java.math.BigDecimal;

/**
 *
 * @author 
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
