
package presentacion.borradores;

import java.math.BigDecimal;

/**
 *
 * Archivo: BorradorInsumoCotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class BorradorInsumoCotizacion {

    private String nombreInsumo;
    private int cantidad;
    private BigDecimal costo;
    private BigDecimal subtotal;
    private Long idInsumo;

    
    public BorradorInsumoCotizacion(String nombreInsumo, int cantidad, BigDecimal costo, BigDecimal subtotal, Long idInsumo) {
        this.nombreInsumo = nombreInsumo;
        this.cantidad = cantidad;
        this.costo = costo;
        this.subtotal = subtotal;
        this.idInsumo = idInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }
    

}
