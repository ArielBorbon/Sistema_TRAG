
package presentacion.fabrica;

import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.IControlHistorialCotizaciones;
import presentacion.interfaces.vistas.IConsultaCotizacion;
import presentacion.interfaces.vistas.IHistorialCotizaciones;
import presentacion.interfaces.vistas.IPruebaAgregarCotizacion;
import presentacion.interfaces.vistas.IVistaSeleccionClienteAuto;
import presentacion.vistas.PruebaAgregarCotizacion;
import presentacion.vistas.VistaHistorialCotizaciones;
import presentacion.vistas.VistaSeleccionClienteAuto;
import presentacion.interfaces.IControlConsultaCotizacion;
import presentacion.vistas.VistaConsultaCotizacion;

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
   
   public static IHistorialCotizaciones getVistaHistorialCotizaciones(IControlHistorialCotizaciones control){
        
        IHistorialCotizaciones historialCotizaciones = new VistaHistorialCotizaciones(control);
        
        return historialCotizaciones;
    }
   
   public static IConsultaCotizacion getVistaConsultarCotizacion(IControlConsultaCotizacion control){
       
       IConsultaCotizacion consultaCotizacion = new VistaConsultaCotizacion(control);
       
       return consultaCotizacion;
       
   }
    
}
