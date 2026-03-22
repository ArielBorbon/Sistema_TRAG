package presentacion.interfaces;

import dtos.cotizacion.CotizacionResumenDTO;
/**
 * @author Yuri Germán García López - 252583
 */
public interface IControlConsultaCotizacion {
    
    public abstract void iniciar();
    
    public abstract void detalleCotizacion(CotizacionResumenDTO cotizacionSeleccionada);
    
    public abstract void aniadirInsumo(String nombreInsumo);
    
    public abstract void eliminarInsumo();
    
    public abstract void guardar();
    
    public abstract void regresar();
}
