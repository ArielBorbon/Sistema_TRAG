
package presentacion;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import presentacion.controles.ControlAgregarCotizacion;
import presentacion.controles.ControlCotizaciones;

/**
 *
 * @author 
 */
public class Presentacion_TRAG {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("No se pudo inicializar FlatLaf");
        }
        
        ControlAgregarCotizacion controlAgregarCotizacion = new ControlAgregarCotizacion();
        
        
        ControlCotizaciones controlCotizaciones = new ControlCotizaciones(controlAgregarCotizacion);
        
        controlCotizaciones.administrarCotizaciones();
    }
}
