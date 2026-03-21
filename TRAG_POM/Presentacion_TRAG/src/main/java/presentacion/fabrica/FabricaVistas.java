
package presentacion.fabrica;

import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.vistas.IPruebaAgregarCotizacion;
import presentacion.vistas.PruebaAgregarCotizacion;

/**
 *
 * @author 
 */
public class FabricaVistas {
    
    public static IPruebaAgregarCotizacion getVistaPrueba(IControlAgregarCotizacion control){
        
        IPruebaAgregarCotizacion pruebaAgregarCotizacion = new PruebaAgregarCotizacion(control);
        
        return pruebaAgregarCotizacion;
        
    }
    
    
}
