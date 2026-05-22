
package presentacion.controles;

import presentacion.vistas.MenuPrincipal;
import presentacion.vistas.VistaPrincipalAutomoviles;

/**
 *
 * @author PC Gamer
 */
public class ControlAutomoviles {

    private ControlAdministrarAutomoviles controlAdministrarAutomoviles;
    private VistaPrincipalAutomoviles vistaPrincipal;

    public ControlAutomoviles(ControlAdministrarAutomoviles controlAdministrar) {
        this.controlAdministrarAutomoviles = controlAdministrar;
        this.controlAdministrarAutomoviles.setControlOrquestador(this);
    }

    public void iniciarModulo() {
        this.vistaPrincipal = new VistaPrincipalAutomoviles(this);
        this.vistaPrincipal.setVisible(true);
    }

    public void administrarAutomoviles() {
        if (vistaPrincipal != null) {
            vistaPrincipal.dispose();
        }
        controlAdministrarAutomoviles.iniciarModulo();
    }

    public void crearAutomovil() {
        ControlAgregarAutomovil controlAgregar = new ControlAgregarAutomovil(vistaPrincipal);
        controlAgregar.iniciarVista();
    }

    public void volver() {
        if (this.vistaPrincipal != null) {
            this.vistaPrincipal.dispose();
        }
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
}
