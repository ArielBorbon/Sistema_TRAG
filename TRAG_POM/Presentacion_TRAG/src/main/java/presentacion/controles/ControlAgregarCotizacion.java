
package presentacion.controles;


import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.negocios_trag.FabricaNegocios;
import excepciones.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.borradores.BorradorCotizacion;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.vistas.IPruebaAgregarCotizacion;
import presentacion.interfaces.vistas.IVistaSeleccionClienteAuto;

/**
 *
 * @author 
 */
public class ControlAgregarCotizacion implements IControlAgregarCotizacion{

    IAdministradorClientes administradorClientes;
    IAdministradorAutomoviles administradorAutomoviles;
    
    IPruebaAgregarCotizacion vistaPruebaAgregarCotizacion;
    IVistaSeleccionClienteAuto vistaSeleccionClienteAuto;
    
    public ControlAgregarCotizacion(){
        administradorClientes = FabricaNegocios.obtenerAdministradorClientes();
        administradorAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
    }
    
    @Override
    public void iniciar() {
        try {
            vistaSeleccionClienteAuto = FabricaVistas.obtenerVistaSeleccionClienteAuto(this);
            vistaSeleccionClienteAuto.cargarClientes(administradorClientes.obtenerTodosClientes());
            vistaSeleccionClienteAuto.mostrar();
        } catch (NegocioException e) {
            vistaSeleccionClienteAuto.mostrarError(e.getMessage());
        }
    }
    
    @Override
    public void pruebaBoton(int num, String texto) {
        
        BorradorCotizacion borradorCotizacion = new BorradorCotizacion(num, texto);
        
        vistaPruebaAgregarCotizacion.mostrarCotizacionGuardada(borradorCotizacion);
        
    }

    @Override
    public void cancelarAgregar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
