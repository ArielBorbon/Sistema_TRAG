
package presentacion;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import presentacion.controles.ControlAgregarCotizacion;
import presentacion.controles.ControlCotizaciones;
import presentacion.controles.ControlConsultarCotizaciones;

/**
 *
 * Archivo: Presentacion_TRAG.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class Presentacion_TRAG {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("No se pudo inicializar FlatLaf");
        }
        
        ControlAgregarCotizacion controlAgregarCotizacion = new ControlAgregarCotizacion();
        ControlConsultarCotizaciones controlHistorialCotizaciones = new ControlConsultarCotizaciones();
        
        ControlCotizaciones controlCotizaciones = new ControlCotizaciones(controlAgregarCotizacion, controlHistorialCotizaciones);
        
        controlAgregarCotizacion.setControlCotizaciones(controlCotizaciones);
        controlHistorialCotizaciones.setControlCotizaciones(controlCotizaciones);
        
        controlCotizaciones.administrarCotizaciones();
    }
}
