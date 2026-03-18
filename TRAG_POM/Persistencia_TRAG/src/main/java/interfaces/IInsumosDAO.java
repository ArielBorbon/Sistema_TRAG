
package interfaces;

import entidades.Insumo;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author 
 */
public interface IInsumosDAO {
    
    public abstract Insumo crearInsumo(Insumo insumo) throws PersistenciaException;
    public abstract Insumo obtenerInsumo(Long idInsumo) throws PersistenciaException;
    public abstract List<Insumo> obtenerInsumosNombre(String nombreInsumo) throws PersistenciaException;
}
