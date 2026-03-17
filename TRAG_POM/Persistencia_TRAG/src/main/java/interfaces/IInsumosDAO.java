
package interfaces;

import entidades.Insumo;
import excepciones.PersistenciaException;

/**
 *
 * @author 
 */
public interface IInsumosDAO {
    
    public abstract Insumo crearInsumo(Insumo insumo) throws PersistenciaException;
    public abstract Insumo obtenerInsumo(Long idInsumo) throws PersistenciaException;
    
}
