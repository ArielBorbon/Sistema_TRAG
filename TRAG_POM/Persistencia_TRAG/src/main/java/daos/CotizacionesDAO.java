package daos;


import conexion.Conexion;
import entidades.Cotizacion;
import entidades.Insumo;
import entidades.InsumoCotizacion;
import entidades.Servicio;
import enums.EstadoCotizacion;
import excepciones.PersistenciaException;
import interfaces.ICotizacionesDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;


/**
 *
 * Archivo: CotizacionesDAO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class CotizacionesDAO implements ICotizacionesDAO {

    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar la cotización";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar la cotización";
    private final String MENSAJE_ERROR_CONSULTA_TODAS = "Error al consultar todas las cotizaciones";
    private final String MENSAJE_ERROR_CONSULTA_NOMBRE_CLIENTE = "Error al consultar las cotizaciones por nombre de cliente";
    private final String MENSAJE_ERROR_CONSULTA_FECHAS = "Error al consultar las cotizaciones por rango de fechas";
    private final String MENSAJE_ERROR_ACTUALIZAR = "Error al actualizar la cotización";
    private final String MENSAJE_ERROR_CANCELAR = "Error al cancelar la cotización";
    
    @Override
    public Cotizacion agregarCotizacion(Cotizacion cotizacion) throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        try {
            EntityTransaction transaccion = em.getTransaction();
            transaccion.begin();

            if (cotizacion.getServicio() != null && cotizacion.getServicio().getId() != null) {
                Servicio referenciaServicio = em.getReference(Servicio.class, cotizacion.getServicio().getId());
                cotizacion.setServicio(referenciaServicio);
            }

            if (cotizacion.getInsumosCotizacion() != null && !cotizacion.getInsumosCotizacion().isEmpty()) {
                for (InsumoCotizacion insumoCotizacion : cotizacion.getInsumosCotizacion()) {
                    
                    insumoCotizacion.setCotizacion(cotizacion);

                    if (insumoCotizacion.getInsumo() != null && insumoCotizacion.getInsumo().getId() != null) {
                        Insumo referenciaInsumo = em.getReference(Insumo.class, insumoCotizacion.getInsumo().getId());
                        insumoCotizacion.setInsumo(referenciaInsumo);
                    }
                }
            }

            em.persist(cotizacion);

            transaccion.commit();
            return cotizacion;

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
    public Cotizacion obtenerCotizacion(Long idCotizacion) throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        try {
            // CORRECCIÓN: Quitamos los filtros de estado para poder "Ver" cotizaciones canceladas desde el historial
            String jpql = "SELECT DISTINCT c FROM Cotizacion c " +
                          "LEFT JOIN FETCH c.insumosCotizacion i " +
                          "WHERE c.id = :id";

            return em.createQuery(jpql, Cotizacion.class)
                     .setParameter("id", idCotizacion)
                     .setHint(QueryHints.REFRESH, HintValues.TRUE)
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
    public List<Cotizacion> obtenerTodasCotizaciones() throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        try {

            String jpql = "SELECT DISTINCT c FROM Cotizacion c " +
                          "LEFT JOIN FETCH c.insumosCotizacion i";

            List<Cotizacion> cotizaciones = em.createQuery(jpql, Cotizacion.class)
                     .setHint(QueryHints.REFRESH, HintValues.TRUE)
                     .getResultList();

            return cotizaciones;

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_TODAS, e);
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Cotizacion> obtenerCotizacionesNombreCliente(String nombreCliente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            String terminoBusqueda = "%" + nombreCliente.trim().toLowerCase() + "%";

            String jpql = "SELECT DISTINCT c FROM Cotizacion c " +
                          "LEFT JOIN FETCH c.insumosCotizacion i " +
                          "JOIN c.ordenTrabajo ot " +
                          "JOIN ot.automovil a " +
                          "JOIN a.cliente cl " +
                          "WHERE LOWER(CONCAT(cl.nombre, ' ', cl.apellidoPaterno, ' ', COALESCE(cl.apellidoMaterno, ''))) LIKE :nombreCliente";

            List<Cotizacion> cotizaciones = em.createQuery(jpql, Cotizacion.class)
                     .setParameter("nombreCliente", terminoBusqueda)
                     .setHint(QueryHints.REFRESH, HintValues.TRUE)
                     .getResultList();

            return cotizaciones;

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_NOMBRE_CLIENTE, e);
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Cotizacion> obtenerCotizacionesFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {

            String jpql = "SELECT DISTINCT c FROM Cotizacion c " +
                          "LEFT JOIN FETCH c.insumosCotizacion i " +
                          "WHERE c.fechaCreacion BETWEEN :fechaInicio AND :fechaFin";

            List<Cotizacion> cotizaciones = em.createQuery(jpql, Cotizacion.class)
                     .setParameter("fechaInicio", fechaInicio)
                     .setParameter("fechaFin", fechaFin)
                     .setHint(QueryHints.REFRESH, HintValues.TRUE)
                     .getResultList();

            return cotizaciones;

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_FECHAS, e); 
        } finally {
            em.close();
        }
    }

    @Override
    public Cotizacion actualizarCotizacion(Cotizacion cotizacion) throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Cotizacion cotizacionExistente = em.find(Cotizacion.class, cotizacion.getId());

            if (cotizacionExistente != null) {

                if (cotizacion.getPrecioManoObra() != null) {
                    cotizacionExistente.setPrecioManoObra(cotizacion.getPrecioManoObra());
                }
                if (cotizacion.getEstadoAutomovil() != null) {
                    cotizacionExistente.setEstadoAutomovil(cotizacion.getEstadoAutomovil());
                }
                if(cotizacion.getDiagnosticoGeneral() != null){
                    cotizacionExistente.setDiagnosticoGeneral(cotizacion.getDiagnosticoGeneral());
                }
                if(cotizacion.getFechaCreacion() != null){
                    cotizacionExistente.setFechaCreacion(cotizacion.getFechaCreacion());
                }

                if (cotizacion.getInsumosCotizacion() != null) {

                    Map<Long, InsumoCotizacion> mapaExistentes = new HashMap<>();
                    for (InsumoCotizacion existente : cotizacionExistente.getInsumosCotizacion()) {
                        mapaExistentes.put(existente.getInsumo().getId(), existente);
                    }

                    List<InsumoCotizacion> insumosNuevos = new ArrayList<>();

                    // Se procesan los datos a actualziar
                    List<Long> idsEntrantes = new ArrayList<>();
                    for (InsumoCotizacion entrante : cotizacion.getInsumosCotizacion()) {
                        Long idInsumo = entrante.getInsumo().getId();
                        idsEntrantes.add(idInsumo);

                        InsumoCotizacion existente = mapaExistentes.get(idInsumo);

                        if (existente != null) {
                            existente.setActivo(true);
                            existente.setCantidadRequerida(entrante.getCantidadRequerida());
                            existente.setPrecio(entrante.getPrecio());
                        } else {
                            Insumo referenciaInsumo = em.getReference(Insumo.class, idInsumo);
                            entrante.setInsumo(referenciaInsumo);
                            entrante.setCotizacion(cotizacionExistente);
                            entrante.setActivo(true);
                            insumosNuevos.add(entrante);
                        }
                    }

                    // Se desactivan los que no vienen en la lista
                    for (InsumoCotizacion existente : cotizacionExistente.getInsumosCotizacion()) {
                        if (!idsEntrantes.contains(existente.getInsumo().getId())) {
                            existente.setActivo(false);
                        }
                    }

                    // Se agregan nuevos a la colección de la entidad
                    if (!insumosNuevos.isEmpty()) {
                        cotizacionExistente.getInsumosCotizacion().addAll(insumosNuevos);
                    }
                }
            }

            em.getTransaction().commit();
            return cotizacionExistente;

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
    public Cotizacion eliminarCotizacion(Long idCotizacion) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Cotizacion cotizacion = em.find(Cotizacion.class, idCotizacion);

            if (cotizacion != null) {
                // Cambiamos el estado a Cancelada
                cotizacion.setEstadoCotizacion(EstadoCotizacion.CANCELADA); 
                
                // Se desactivan los insumos asociados al cancelar
                if (cotizacion.getInsumosCotizacion() != null) {
                    for (InsumoCotizacion insumo : cotizacion.getInsumosCotizacion()) {
                        insumo.setActivo(false);
                    }
                }
            }

            em.getTransaction().commit();
            return cotizacion;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException(MENSAJE_ERROR_CANCELAR, e);
        } finally {
            em.close();
        }
    }

    @Override
    public Cotizacion habilitarCotizacion(Long idCotizacion) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            
            Cotizacion cotizacion = em.find(Cotizacion.class, idCotizacion);

            if (cotizacion != null) {
                // Se habilita la cotización principal
                cotizacion.setEstadoCotizacion(EstadoCotizacion.ACTIVA);
                
                // Se activans todos sus insumos asociados
                if (cotizacion.getInsumosCotizacion() != null) {
                    for (InsumoCotizacion insumo : cotizacion.getInsumosCotizacion()) {
                        insumo.setActivo(true);
                    }
                }
            }

            em.getTransaction().commit();
            return cotizacion;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al habilitar la cotización", e);
        } finally {
            em.close();
        }
    }
    
}