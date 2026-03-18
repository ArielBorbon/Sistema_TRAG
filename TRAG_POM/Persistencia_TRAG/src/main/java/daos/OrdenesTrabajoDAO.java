
package daos;

import conexion.Conexion;
import entidades.Automovil;
import entidades.Cotizacion;
import entidades.OrdenTrabajo;
import excepciones.PersistenciaException;
import interfaces.IOrdenesTrabajoDAO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * Archivo: OrdenesTrabajoDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class OrdenesTrabajoDAO implements IOrdenesTrabajoDAO{

    private final String MENSAJE_ID_AUTOMOVIL_NULO = "Es necesario especificar el id del automóvil para actualizar.";
    private final String MENSAJE_ID_COTIZACION_NULO = "Es necesario especificar el id de la cotización para actualizar.";
    
    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar la orden de trabajo.";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar la orden de trabajo.";
    
    @Override
    public OrdenTrabajo crearOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws PersistenceException{
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            if (ordenTrabajo.getAutomovil() != null && ordenTrabajo.getAutomovil().getId() != null) {
                Automovil referenciaAutomovil = em.getReference(Automovil.class, ordenTrabajo.getAutomovil().getId());
                ordenTrabajo.setAutomovil(referenciaAutomovil);
            } else {
                 throw new PersistenciaException(MENSAJE_ID_AUTOMOVIL_NULO);
            }

            if (ordenTrabajo.getCotizacion() != null && ordenTrabajo.getCotizacion().getId() != null) {
                Cotizacion referenciaCotizacion = em.getReference(Cotizacion.class, ordenTrabajo.getCotizacion().getId());
                ordenTrabajo.setCotizacion(referenciaCotizacion);
            } else {
                 throw new PersistenciaException(MENSAJE_ID_COTIZACION_NULO);
            }

            em.persist(ordenTrabajo);

            em.getTransaction().commit();
            return ordenTrabajo;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException(MENSAJE_ERROR_AGREGAR, e);
        } finally {
            em.close();
        }
    }

    @Override
    public OrdenTrabajo obtenerOrdenTrabajo(Long idOrdenTrabajo) throws PersistenciaException{
        
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(OrdenTrabajo.class, idOrdenTrabajo);
        } catch (Exception e) {
            throw new PersistenceException(MENSAJE_ERROR_CONSULTA, e);
        } finally {
            em.close();
        }
    }
    
}
