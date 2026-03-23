package presentacion.utils;

import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumos.InsumoResumenDTO;

/**
 * @author Yuri Germán García López - 252583
 */
public interface IControlConsultaCotizacion {
    
    public abstract void iniciar(CotizacionResumenDTO cotizacionSeleccionada);
    
    public void buscarInsumos(String texto);
    
    public void seleccionarInsumo(InsumoResumenDTO insumo);
    
    public abstract void guardar();
    
    public abstract void regresar();
}
