
package presentacion.controles;

import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.IControlCotizaciones;
import presentacion.interfaces.IVistaPrincipal;

/**
 *
 * @author 
 */
public class ControlCotizaciones implements IControlCotizaciones{
    
    private final IControlAgregarCotizacion controlAgregarCotizacion;
    
    private IVistaPrincipal vistaPrincipal;
    
    public ControlCotizaciones(IControlAgregarCotizacion controlAgregarCotizacion){
        this.controlAgregarCotizacion = controlAgregarCotizacion;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
