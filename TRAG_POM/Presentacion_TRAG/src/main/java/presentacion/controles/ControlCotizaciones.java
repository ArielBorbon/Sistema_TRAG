
package presentacion.controles;

import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.IControlCotizaciones;
import presentacion.interfaces.IVistaPrincipal;
import presentacion.interfaces.IControlConsultarCotizaciones;

/**
 *
 * Archivo: ControlCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ControlCotizaciones implements IControlCotizaciones{
    
    private final IControlAgregarCotizacion controlAgregarCotizacion;
    private final IControlConsultarCotizaciones controlHistorialCotizaciones;
    
    private IVistaPrincipal vistaPrincipal;
    
    public ControlCotizaciones(IControlAgregarCotizacion controlAgregarCotizacion, IControlConsultarCotizaciones controlHistorialCotizaciones){
        this.controlAgregarCotizacion = controlAgregarCotizacion;
        this.controlHistorialCotizaciones = controlHistorialCotizaciones;
    }

    @Override
    public void administrarCotizaciones() {
        
        vistaPrincipal = FabricaVistas.obtenerVistaPrincipal(this);
        vistaPrincipal.mostrar();
        
    }

    @Override
    public void crearCotizacion() {
        vistaPrincipal.ocultar();
        controlAgregarCotizacion.iniciar(); 
    }

    @Override
    public void editarCotizacion() {
        vistaPrincipal.ocultar();
        controlHistorialCotizaciones.iniciar();
    }

    @Override
    public void cancelarOperacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void volver() {
        System.exit(0);
    }
    
    
    
}
