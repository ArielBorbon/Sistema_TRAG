package daos;

import conexion.Conexion;
import entidades.Cliente;
import enums.EstadoCliente;
import excepciones.PersistenciaException;
import interfaces.IClientesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

/**
 *
 * Archivo: ClientesDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ClientesDAO implements IClientesDAO {

    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar el cliente.";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar el cliente.";
    private final String MENSAJE_ERROR_CONSULTA_TODAS = "Error al consultar todos los clientes.";
    private final String MENSAJE_ERROR_ACTUALIZAR = "Error al actualizar el cliente.";
    private final String MENSAJE_ERROR_ELIMINAR = "Error al eliminar el cliente.";

    @Override
    public Cliente crearCliente(Cliente cliente) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        try {
            EntityTransaction transaccion = em.getTransaction();
            transaccion.begin();

            em.persist(cliente);

            transaccion.commit();
            return cliente;

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
    public Cliente obtenerCliente(Long idCliente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            String jpql = "SELECT DISTINCT c FROM Cliente c "
                    + "LEFT JOIN FETCH c.automoviles a ON a.activo = true "
                    + "WHERE c.id = :id AND c.estado != :estadoCliente";

            return em.createQuery(jpql, Cliente.class)
                    .setParameter("id", idCliente)
                    .setParameter("estadoCliente", EstadoCliente.ELIMINADO)
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
    public List<Cliente> obtenerTodosClientes() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {

            String jpql = "SELECT DISTINCT c FROM Cliente c "
                    + "LEFT JOIN FETCH c.automoviles a ON a.activo = true "
                    + "WHERE c.estado != :estadoCliente";

            return em.createQuery(jpql, Cliente.class)
                    .setParameter("estadoCliente", EstadoCliente.ELIMINADO)
                    .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_TODAS, e);
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Cliente clienteActualizado = em.merge(cliente);
            em.getTransaction().commit();
            return clienteActualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException(MENSAJE_ERROR_ACTUALIZAR, e);
        } finally {
            em.close();
        }
    }

}
