
package daos;

import conexion.Conexion;
import entidades.Automovil;
import entidades.Cotizacion;
import entidades.OrdenTrabajo;
import interfaces.IOrdenesTrabajoDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author 
 */
public class OrdenesTrabajoDAO implements IOrdenesTrabajoDAO{

    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar la orden de trabajo.";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar la orden de trabajo.";
    
    @Override
    public OrdenTrabajo crearOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            
            // Obtención de referencias
            Automovil referenciaAutomovil = em.getReference(Automovil.class, ordenTrabajo.getAutomovil().getId());
            Cotizacion referenciaCotizacion = em.getReference(Cotizacion.class, ordenTrabajo.getCotizacion().getId());
            
            ordenTrabajo.setAutomovil(referenciaAutomovil);
            ordenTrabajo.setCotizacion(referenciaCotizacion);
            
            em.persist(ordenTrabajo);
            
            em.getTransaction().commit();
            return ordenTrabajo;
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(MENSAJE_ERROR_AGREGAR, e);
        } finally {
            em.close();
        }
    }

    @Override
    public OrdenTrabajo obtenerOrdenTrabajo(Long idOrdenTrabajo) {
        
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(OrdenTrabajo.class, idOrdenTrabajo);
        } catch (Exception e) {
            throw new RuntimeException(MENSAJE_ERROR_CONSULTA, e);
        } finally {
            em.close();
        }
    }
    
}
