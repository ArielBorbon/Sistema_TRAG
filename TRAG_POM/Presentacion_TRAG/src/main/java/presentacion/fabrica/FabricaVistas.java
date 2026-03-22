package presentacion.fabrica;

import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.IControlHistorialCotizaciones;
import presentacion.interfaces.vistas.IConsultaCotizacion;
import presentacion.interfaces.vistas.IHistorialCotizaciones;
import presentacion.interfaces.vistas.IVistaSeleccionClienteAuto;
import presentacion.vistas.VistaHistorialCotizaciones;
import presentacion.vistas.VistaSeleccionClienteAuto;
import presentacion.interfaces.IControlConsultaCotizacion;
import presentacion.interfaces.IControlCotizaciones;
import presentacion.interfaces.IVistaPrincipal;
import presentacion.interfaces.vistas.IVistaCrearCotizacion;
import presentacion.interfaces.vistas.IVistaDiagnosticoEstado;
import presentacion.interfaces.vistas.IVistaServicios;
import presentacion.vistas.VistaConsultaCotizacion;
import presentacion.vistas.VistaCrearCotizacion;
import presentacion.vistas.VistaDiagnosticoEstado;
import presentacion.vistas.VistaPrincipal;
import presentacion.vistas.VistaServicios;

/**
 *
 * @author
 */
public class FabricaVistas {

    public static IVistaPrincipal obtenerVistaPrincipal(IControlCotizaciones control){
        IVistaPrincipal vistaPrincipal = new VistaPrincipal(control);
        return vistaPrincipal;
    }
    
    public static IVistaSeleccionClienteAuto obtenerVistaSeleccionClienteAuto(IControlAgregarCotizacion control) {

        IVistaSeleccionClienteAuto vistaSeleccionClienteAuto = new VistaSeleccionClienteAuto(control);
        return vistaSeleccionClienteAuto;
    }

    public static IVistaDiagnosticoEstado obtenerVistaDiagnosticoEstado(IControlAgregarCotizacion control) {

        IVistaDiagnosticoEstado vistaDiagnosticoEstado = new VistaDiagnosticoEstado(control);
        return vistaDiagnosticoEstado;

    }

    public static IVistaServicios obtenerVistaServicios(IControlAgregarCotizacion control) {

        IVistaServicios vistaServicios = new VistaServicios(control);
        return vistaServicios;
    }
    
    public static IVistaCrearCotizacion obtenerVistaCrearCotizacion(IControlAgregarCotizacion control){
     
        IVistaCrearCotizacion vistaCrearCotizacion = new VistaCrearCotizacion(control);
        return vistaCrearCotizacion;
        
    }

    public static IHistorialCotizaciones getVistaHistorialCotizaciones(IControlHistorialCotizaciones control) {

        IHistorialCotizaciones historialCotizaciones = new VistaHistorialCotizaciones(control);

        return historialCotizaciones;
    }

    public static IConsultaCotizacion getVistaConsultarCotizacion(IControlConsultaCotizacion control) {

        IConsultaCotizacion consultaCotizacion = new VistaConsultaCotizacion();
        return consultaCotizacion;

    }

}
