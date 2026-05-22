package daos;

import conexion.Conexion;
import entidades.Automovil;
import entidades.Cliente;
import excepciones.PersistenciaException;
import interfaces.IAutomovilesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

/**
 *
 * Archivo: AutomovilesDAO.java
 *
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 *
 */
public class AutomovilesDAO implements IAutomovilesDAO {

    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar el automóvil.";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar el automóvil.";
    private final String MENSAJE_ERROR_CONSULTA_TODAS = "Error al consultar todos los automóviles.";
    private final String MENSAJE_ERROR_ACTUALIZAR = "Error al actualizar el automóvil.";
    private final String MENSAJE_ERROR_ELIMINAR = "Error al eliminar el automóvil.";

    @Override
    public Automovil crearAutomovil(Automovil automovil) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        try {
            EntityTransaction transaccion = em.getTransaction();
            transaccion.begin();

            if (automovil.getCliente() != null && automovil.getCliente().getId() != null) {
                Cliente referenciaCliente = em.getReference(Cliente.class, automovil.getCliente().getId());
                automovil.setCliente(referenciaCliente);
            }

            em.persist(automovil);

            transaccion.commit();
            return automovil;

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
    public Automovil obtenerAutomovil(Long idAutomovil) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        try {
            String jpql = "SELECT a FROM Automovil a "
                    + "JOIN FETCH a.cliente "
                    + "WHERE a.id = :id AND a.activo = :activo";
            return em.createQuery(jpql, Automovil.class)
                    .setParameter("id", idAutomovil)
                    .setParameter("activo", true)
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
    public List<Automovil> obtenerTodosAutomoviles() throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        try {

            String jpql = "SELECT a FROM Automovil a "
                    + "JOIN FETCH a.cliente "
                    + "WHERE a.activo = :activo";

            return em.createQuery(jpql, Automovil.class)
                    .setParameter("activo", true)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_TODAS, e);
        } finally {
            em.close();
        }

    }

    @Override
    public List<Automovil> obtenerAutomovilesPorCliente(Long idCliente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        em.getEntityManagerFactory().getCache().evictAll();
        try {
            String jpql = "SELECT a FROM Automovil a "
                    + "JOIN FETCH a.cliente "
                    + "WHERE a.cliente.id = :idCliente "
                    + "AND a.activo = :activo";

            return em.createQuery(jpql, Automovil.class)
                    .setParameter("idCliente", idCliente)
                    .setParameter("activo", true)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar los automóviles del cliente con ID: " + idCliente, e);
        } finally {
            em.close();
        }
    }

    @Override
    public Automovil actualizarAutomovil(Automovil automovil) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            EntityTransaction transaccion = em.getTransaction();
            transaccion.begin();

            Automovil autoActualizado = em.merge(automovil);

            transaccion.commit();
            return autoActualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException(MENSAJE_ERROR_ACTUALIZAR, e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Automovil> buscarAutomovilesPorNombreCliente(String nombre) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();

        try {
            String jpql = "SELECT a FROM Automovil a "
                    + "JOIN FETCH a.cliente c "
                    + "WHERE (LOWER(c.nombre) LIKE LOWER(:nombre) "
                    + "OR LOWER(c.apellidoPaterno) LIKE LOWER(:nombre)) "
                    + "AND a.activo = :activo";

            return em.createQuery(jpql, Automovil.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .setParameter("activo", true)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar automóviles por nombre de cliente.", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Automovil obtenerAutomovilPorNiv(String niv) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            String jpql = "SELECT a FROM Automovil a WHERE a.vin = :niv AND a.activo = true";
            return em.createQuery(jpql, Automovil.class)
                    .setParameter("niv", niv)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null; 
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el NIV.", e);
        } finally {
            em.close();
        }
    }

}
