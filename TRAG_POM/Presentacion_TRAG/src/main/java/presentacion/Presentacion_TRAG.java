
package presentacion;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import presentacion.controles.ControlAgregarCotizacion;
import presentacion.controles.ControlCotizaciones;
import presentacion.controles.ControlConsultarCotizaciones;
import presentacion.vistas.MenuPrincipal;

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
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("No se pudo inicializar FlatLaf");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
}
