

package presentacion.borradores;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author romom
 */
public class BorradorCotizacion {
    
    private BigDecimal totalInsumos;
    private BigDecimal costoManoObra;
    private BigDecimal total;
    private List<BorradorInsumoCotizacion> borradoresInsumoCotizacion;

    public BorradorCotizacion(BigDecimal totalInsumos, BigDecimal costoManoObra, BigDecimal total, List<BorradorInsumoCotizacion> borradoresInsumoCotizacion) {
        this.totalInsumos = totalInsumos;
        this.costoManoObra = costoManoObra;
        this.total = total;
        this.borradoresInsumoCotizacion = borradoresInsumoCotizacion;
    }

    public BigDecimal getTotalInsumos() {
        return totalInsumos;
    }

    public BigDecimal getCostoManoObra() {
        return costoManoObra;
    }

    public BigDecimal getTotal() {
        return total;
    }
    
    public List<BorradorInsumoCotizacion> getBorradoresInsumoCotizacion() {
        return borradoresInsumoCotizacion;
    }

}
