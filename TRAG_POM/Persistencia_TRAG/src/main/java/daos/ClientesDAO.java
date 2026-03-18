package daos;

import conexion.Conexion;
import entidades.Automovil;
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
 * @author
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
                    + "LEFT JOIN FETCH c.automoviles "
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
                    + "LEFT JOIN FETCH c.automoviles "
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
            EntityTransaction transaccion = em.getTransaction();
            transaccion.begin();

            Cliente clienteActualizado = em.merge(cliente);

            transaccion.commit();
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

    @Override
    public Cliente cambiarEstadoClienteHabilitado(Long idCliente) throws PersistenciaException {
        return cambiarEstadoCliente(idCliente, EstadoCliente.HABILITADO);
    }

    @Override
    public Cliente cambiarEstadoClienteDeshabilitado(Long idCliente) throws PersistenciaException {
        return cambiarEstadoCliente(idCliente, EstadoCliente.DESHABILITADO);
    }

    @Override
    public Cliente cambiarEstadoClienteEliminado(Long idCliente) throws PersistenciaException {
        return cambiarEstadoCliente(idCliente, EstadoCliente.ELIMINADO);
    }

    private Cliente cambiarEstadoCliente(Long idCliente, EstadoCliente nuevoEstado) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            EntityTransaction transaccion = em.getTransaction();
            transaccion.begin();

            Cliente cliente = em.find(Cliente.class, idCliente);

            if (cliente != null) {
                cliente.setEstado(nuevoEstado);

                if (nuevoEstado == EstadoCliente.ELIMINADO && cliente.getAutomoviles() != null) {
                    for (Automovil auto : cliente.getAutomoviles()) {
                        auto.setActivo(false);
                    }
                }

                cliente = em.merge(cliente);
                transaccion.commit();
                return cliente;
            } else {
                transaccion.rollback();
                return null;
            }

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
