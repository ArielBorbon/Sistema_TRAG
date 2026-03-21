
package presentacion.fabrica;

import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.vistas.IPruebaAgregarCotizacion;
import presentacion.interfaces.vistas.IVistaSeleccionClienteAuto;
import presentacion.vistas.PruebaAgregarCotizacion;
import presentacion.vistas.VistaSeleccionClienteAuto;

/**
 *
 * @author 
 */
public class FabricaVistas {
    
    public static IPruebaAgregarCotizacion getVistaPrueba(IControlAgregarCotizacion control){
        
        IPruebaAgregarCotizacion pruebaAgregarCotizacion = new PruebaAgregarCotizacion(control);
        
        return pruebaAgregarCotizacion;
        
    }
    
   public static IVistaSeleccionClienteAuto obtenerVistaSeleccionClienteAuto(IControlAgregarCotizacion control){
       
       IVistaSeleccionClienteAuto vistaSeleccionClienteAuto = new VistaSeleccionClienteAuto(control);
       return vistaSeleccionClienteAuto;
   }
    
    
}
