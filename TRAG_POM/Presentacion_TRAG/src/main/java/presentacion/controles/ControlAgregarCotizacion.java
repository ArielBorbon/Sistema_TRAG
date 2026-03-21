
package presentacion.controles;

import presentacion.borradores.BorradorCotizacion;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.vistas.IPruebaAgregarCotizacion;

/**
 *
 * @author 
 */
public class ControlAgregarCotizacion implements IControlAgregarCotizacion{

    IPruebaAgregarCotizacion vistaPruebaAgregarCotizacion;
    
    public ControlAgregarCotizacion(){
        
    }
    
    @Override
    public void iniciar() {
        vistaPruebaAgregarCotizacion = FabricaVistas.getVistaPrueba(this);
        vistaPruebaAgregarCotizacion.mostrar();
    }
    
    @Override
    public void pruebaBoton(int num, String texto) {
        
        BorradorCotizacion borradorCotizacion = new BorradorCotizacion(num, texto);
        
        vistaPruebaAgregarCotizacion.mostrarCotizacionGuardada(borradorCotizacion);
        
    }
    
}
