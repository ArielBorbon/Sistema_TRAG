package daos;


import conexion.Conexion;
import entidades.Insumo;
import entidades.InsumoServicio;
import entidades.Servicio;
import excepciones.PersistenciaException;
import interfaces.IServiciosDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;


/**
 *
 * Archivo: ServiciosDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ServiciosDAO implements IServiciosDAO{

    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar el servicio.";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar el servicio.";
    private final String MENSAJE_ERROR_CONSULTA_TODAS = "Error al consultar todos los servicios.";
    private final String MENSAJE_ERROR_ACTUALIZAR = "Error al actualizar el servicio.";
    private final String MENSAJE_ERROR_ELIMINAR = "Error al eliminar el servicio.";
    
    @Override
    public Servicio crearServicio(Servicio servicio) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            if (servicio.getInsumosServicio() != null && !servicio.getInsumosServicio().isEmpty()) {

                for (InsumoServicio insumoSerivicio: servicio.getInsumosServicio()) {
                    insumoSerivicio.setServicio(servicio);

                    if (insumoSerivicio.getInsumo() != null && insumoSerivicio.getInsumo().getId() != null) {
                        Insumo referenciaInsumo = em.getReference(Insumo.class, insumoSerivicio.getInsumo().getId());
                        insumoSerivicio.setInsumo(referenciaInsumo);
                    }
                }
            }

            em.persist(servicio);

            em.getTransaction().commit();
            return servicio;

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
    public Servicio obtenerServicio(Long idServicio) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            
            String jpql = "SELECT DISTINCT s FROM Servicio s " +
                          "LEFT JOIN FETCH s.insumosServicio i " +
                          "WHERE s.id = :id AND s.activo = :activo " +
                          "AND (i.id IS NULL OR i.activo = :activoInsumo)";

            return em.createQuery(jpql, Servicio.class)
                     .setParameter("id", idServicio)
                     .setParameter("activo", true)
                     .setParameter("activoInsumo", true)
                     .getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA, e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Servicio> obtenerTodosServicios() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            String jpql = "SELECT DISTINCT s FROM Servicio s " +
                          "LEFT JOIN FETCH s.insumosServicio i " +
                          "WHERE s.activo = :activo " +
                          "AND (i.id IS NULL OR i.activo = :activoInsumo)";

            return em.createQuery(jpql, Servicio.class)
                     .setParameter("activo", true)
                     .setParameter("activoInsumo", true)
                     .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_TODAS, e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Servicio> obtenerServiciosNombre(String nombreServicio) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            String terminoBusqueda = "%" + nombreServicio.trim().toLowerCase() + "%";

            String jpql = "SELECT DISTINCT s FROM Servicio s " +
                          "LEFT JOIN FETCH s.insumosServicio i " +
                          "WHERE s.activo = :activo " +
                          "AND (i.id IS NULL OR i.activo = :activoInsumo) " +
                          "AND LOWER(s.nombre) LIKE :nombre";

            return em.createQuery(jpql, Servicio.class)
                     .setParameter("activo", true)
                     .setParameter("activoInsumo", true)
                     .setParameter("nombre", terminoBusqueda)
                     .setHint(QueryHints.REFRESH, HintValues.TRUE) 
                     .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_TODAS, e);
        } finally {
            em.close();
        }
    }
    
}
