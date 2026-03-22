
package presentacion.borradores;

import java.math.BigDecimal;

/**
 *
 * @author 
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
