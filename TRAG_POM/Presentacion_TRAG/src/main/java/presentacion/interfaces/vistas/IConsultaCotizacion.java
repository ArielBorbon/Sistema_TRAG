package presentacion.interfaces.vistas;

import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumos.InsumoDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;
/**
 * @author Yuri Germán García López - 252583
 */
public interface IConsultaCotizacion extends IVista{
    
    public abstract void cargarDatosCotizacion(CotizacionResumenDTO cotizacion);
    
    public abstract void mostrarMensajeRapido(String mensaje);
    
    void mostrarInsumosBuscados(List<InsumoResumenDTO> insumos);

    void aniadirInsumo(InsumoResumenDTO insumo);
    
}
