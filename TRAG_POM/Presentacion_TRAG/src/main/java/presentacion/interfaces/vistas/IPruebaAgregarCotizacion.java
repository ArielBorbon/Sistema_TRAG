
package presentacion.interfaces.vistas;

import presentacion.borradores.BorradorCotizacion;
import presentacion.interfaces.IVista;

/**
 *
 * Archivo: IPruebaAgregarCotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IPruebaAgregarCotizacion extends IVista{
    
    public abstract void mostrarCotizacionGuardada(BorradorCotizacion borradorCotizacion);
    
}
