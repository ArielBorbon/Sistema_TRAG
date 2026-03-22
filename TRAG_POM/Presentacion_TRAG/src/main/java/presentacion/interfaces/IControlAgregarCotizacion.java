
package presentacion.interfaces;

import dtos.automovil.AutomovilResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.servicio.ServicioResumenDTO;
import java.math.BigDecimal;
import java.util.List;
import presentacion.borradores.BorradorCotizacion;

/**
 *
 * @author 
 */
public interface IControlAgregarCotizacion{
    
    public abstract void iniciar();
    
    public abstract void seleccionarCliente(Long idCliente);
    public abstract void seleccionarAutomovil(AutomovilResumenDTO automovil);
    public abstract void seleccionarClienteAutomovil();
    
    public abstract void guardarDiagnosticoEstado(String diagnsotico, String estado);
    public abstract void atrasDiagnosticoEstado();
    
    public abstract void seleccionarServicio(ServicioResumenDTO servicio);
    public abstract void atrasSeleccionarServicio();
    
    public abstract void guardarCambioCotizacion(BorradorCotizacion cotizacion);
    public abstract void crearCotizacion();
            
    public abstract void cancelarAgregar();
}
