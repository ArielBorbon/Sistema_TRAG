
package daos;

import conexion.Conexion;
import entidades.Insumo;
import excepciones.PersistenciaException;
import interfaces.IInsumosDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author 
 */
public class InsumosDAO implements IInsumosDAO{

 
    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar el insumo.";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar el insumo.";
    @Override
    public Insumo crearInsumo(Insumo insumo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(insumo);
            em.getTransaction().commit();
            return insumo;
            
        } catch (Exception e) {
            
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException(MENSAJE_ERROR_AGREGAR, e);
        } finally {
            em.close();
        }
    }

    @Override
    public Insumo obtenerInsumo(Long idInsumo) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Insumo.class, idInsumo);
        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA, e);
        } finally {
            em.close();
        }
    }
    
}
