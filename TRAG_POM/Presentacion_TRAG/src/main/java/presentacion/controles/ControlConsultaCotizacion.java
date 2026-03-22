package presentacion.controles;
import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.cotizacion.CotizacionResumenDTO;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlConsultaCotizacion;
import presentacion.interfaces.vistas.IConsultaCotizacion;
/**
 * @author Yuri Germán García López - 252583
 */
public class ControlConsultaCotizacion implements IControlConsultaCotizacion {

    private IAdministradorCotizaciones administradorCotizaciones;
    private IConsultaCotizacion vista;

    public ControlConsultaCotizacion() {
        this.administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
    }
    
    @Override
    public void iniciar() {
        this.vista = FabricaVistas.getVistaConsultarCotizacion(this);
        this.vista.mostrar();
    }

    @Override
    public void detalleCotizacion(CotizacionResumenDTO cotizacionSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void aniadirInsumo(String nombreInsumo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarInsumo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void regresar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
