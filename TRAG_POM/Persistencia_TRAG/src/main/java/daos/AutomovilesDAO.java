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

}
