
package interfaces;

import entidades.Insumo;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * Archivo: IInsumosDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public interface IInsumosDAO {
    
    public abstract Insumo crearInsumo(Insumo insumo) throws PersistenciaException;
    public abstract Insumo obtenerInsumo(Long idInsumo) throws PersistenciaException;
    public abstract List<Insumo> obtenerInsumosNombre(String nombreInsumo) throws PersistenciaException;
}
