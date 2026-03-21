
package presentacion.interfaces.vistas;

import presentacion.borradores.BorradorCotizacion;
import presentacion.interfaces.IVista;

/**
 *
 * @author 
 */
public interface IPruebaAgregarCotizacion extends IVista{
    
    public abstract void mostrarCotizacionGuardada(BorradorCotizacion borradorCotizacion);
    
}
