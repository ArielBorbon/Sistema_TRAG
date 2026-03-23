package presentacion.utils;

import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import dtos.insumos.InsumoDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;
/**
 * @author Yuri Germán García López - 252583
 */
public interface IVistaConsultaCotizacionPrevia extends IVista{
    
    public abstract void cargarDatosCotizacion(CotizacionResumenDTO cotizacion);
    
    public abstract void mostrarMensajeRapido(String mensaje);
    
    public abstract void mostrarInsumosBuscados(List<InsumoResumenDTO> insumos);

    public abstract void aniadirInsumo(InsumoResumenDTO insumo);
    
    public abstract void eliminarInsumo(InsumoResumenDTO insumo);
    
    public abstract void guardarInsumo(InsumoResumenDTO insumo);
    
    public List<InsumoCotizacionDetalleDTO> obtenerInsumosActuales();
    
}
