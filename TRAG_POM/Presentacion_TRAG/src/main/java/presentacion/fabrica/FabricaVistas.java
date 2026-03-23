package presentacion.fabrica;

import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.vistas.IVistaSeleccionClienteAuto;
import presentacion.vistas.VistaHistorialCotizaciones;
import presentacion.vistas.VistaSeleccionClienteAuto;
import presentacion.interfaces.IControlCotizaciones;
import presentacion.interfaces.IVistaPrincipal;
import presentacion.interfaces.vistas.IVistaConsultaCotizacion;
import presentacion.interfaces.vistas.IVistaCrearCotizacion;
import presentacion.interfaces.vistas.IVistaDiagnosticoEstado;
import presentacion.interfaces.vistas.IVistaServicios;
import presentacion.vistas.VistaCrearCotizacion;
import presentacion.vistas.VistaDiagnosticoEstado;
import presentacion.vistas.VistaPrincipal;
import presentacion.vistas.VistaServicios;
import presentacion.interfaces.vistas.IVistaHistorialCotizaciones;
import presentacion.vistas.VistaConsultaCotizacion;
import presentacion.interfaces.IControlConsultarCotizaciones;

/**
 *
 * Archivo: FabricaVistas.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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

    public static IVistaHistorialCotizaciones obtenerVistaHistorialCotizaciones(IControlConsultarCotizaciones control) {

        IVistaHistorialCotizaciones historialCotizaciones = new VistaHistorialCotizaciones(control);

        return historialCotizaciones;
    }

    public static IVistaConsultaCotizacion obtenerVistaConsultarCotizacion(IControlConsultarCotizaciones control) {

        IVistaConsultaCotizacion vistaConsultaCotizacion = new VistaConsultaCotizacion(control);
        return vistaConsultaCotizacion;

    }

}
