

package presentacion.borradores;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Archivo: BorradorCotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class BorradorCotizacion {
    
    private Long id;
    private BigDecimal totalInsumos;
    private BigDecimal costoManoObra;
    private BigDecimal total;
    private List<BorradorInsumoCotizacion> borradoresInsumoCotizacion;

    public BorradorCotizacion(Long id, BigDecimal totalInsumos, BigDecimal costoManoObra, BigDecimal total, List<BorradorInsumoCotizacion> borradoresInsumoCotizacion) {
        this.id = id;
        this.totalInsumos = totalInsumos;
        this.costoManoObra = costoManoObra;
        this.total = total;
        this.borradoresInsumoCotizacion = borradoresInsumoCotizacion;
    }

    public BorradorCotizacion(BigDecimal totalInsumos, BigDecimal costoManoObra, BigDecimal total, List<BorradorInsumoCotizacion> borradoresInsumoCotizacion) {
        this.totalInsumos = totalInsumos;
        this.costoManoObra = costoManoObra;
        this.total = total;
        this.borradoresInsumoCotizacion = borradoresInsumoCotizacion;
    }

    public Long getId() {
        return id;
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
