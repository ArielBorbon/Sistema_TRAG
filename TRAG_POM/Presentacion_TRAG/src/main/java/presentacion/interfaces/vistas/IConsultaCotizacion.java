package presentacion.interfaces.vistas;

import dtos.cotizacion.CotizacionResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;
/**
 * @author Yuri Germán García López - 252583
 */
public interface IConsultaCotizacion extends IVista{
    
    // Actualiza el detalle de la cotización
    public abstract void mostrarDetalleCotizacion(List<CotizacionResumenDTO> cotizaciones);
    
    // Para mostrar mensajes de error si no se encuentran resultados
    public abstract void mostrarMensajeRapido(String mensaje);
    
    public abstract void limpiarBusqueda();

}
