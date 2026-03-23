
package presentacion.interfaces.vistas;

import presentacion.interfaces.IVista;

/**
 *
 * Archivo: IVistaDiagnosticoEstado.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IVistaDiagnosticoEstado extends IVista{
    public void cargarDiagnosticoEstado(String diagnostico, String estado);
}
