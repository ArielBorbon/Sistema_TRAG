
package presentacion.interfaces;

import dtos.automovil.AutomovilResumenDTO;
import dtos.servicio.ServicioResumenDTO;
import presentacion.borradores.BorradorCotizacion;

/**
 *
 * Archivo: IControlAgregarCotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IControlAgregarCotizacion{
    
    public abstract void iniciar();
    
    public abstract void seleccionarCliente(Long idCliente);
    public abstract void seleccionarAutomovil(AutomovilResumenDTO automovil);
    public abstract void seleccionarClienteAutomovil();
    public abstract void atrasPrincipal();
    
    public abstract void guardarDiagnosticoEstado(String diagnsotico, String estado);
    public abstract void atrasDiagnosticoEstado();
    
    public abstract void seleccionarServicio(ServicioResumenDTO servicio);
    public abstract void buscarServicio(String nombreServicio);
    public abstract void atrasSeleccionarServicio();
    
    public abstract void guardarCambioCotizacion(BorradorCotizacion cotizacion);
    public abstract void buscarInsumosNombre(String nombreInsumo);
    public abstract void agregarInsumo(String nombre);
    public abstract void crearCotizacion();
    public abstract void atrasCrearCotizacion();
            
    public abstract void cancelarAgregar();
}
