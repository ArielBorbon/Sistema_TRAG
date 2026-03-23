
package presentacion.interfaces.vistas;

import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * Archivo: IVistaConsultaCotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IVistaConsultaCotizacion extends IVista{
    public abstract void cargarCotizacionSeleccionada(CotizacionDetalleDTO cotizacion);
    public abstract void actualizarSugerencias(List<InsumoResumenDTO> insumos);
    public abstract void agregarInsumoTabla(InsumoResumenDTO insumo);
    public abstract void mostrarMensajeExito();
}
