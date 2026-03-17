package daos;


import conexion.Conexion;
import entidades.Cotizacion;
import entidades.Insumo;
import entidades.InsumoCotizacion;
import enums.EstadoCotizacion;
import excepciones.PersistenciaException;
import interfaces.ICotizacionesDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;



/**
 *
 * @author 
 */
public class CotizacionesDAO implements ICotizacionesDAO{

    private final String MENSAJE_ERROR_AGREGAR = "Error al agregar la cotización";
    private final String MENSAJE_ERROR_CONSULTA = "Error al consultar la cotización";
    private final String MENSAJE_ERROR_CONSULTA_TODAS = "Error al consultar todas las cotizaciones";
    private final String MENSAJE_ERROR_ACTUALIZAR = "Error al actualizar la cotización";
    private final String MENSAJE_ERROR_CANCELAR = "Error al cancelar la cotización";
    
    @Override
    public Cotizacion agregarCotizacion(Cotizacion cotizacion) throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        try {
            EntityTransaction transaccion = em.getTransaction();
            transaccion.begin();

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
            String jpql = "SELECT DISTINCT c FROM Cotizacion c " +
                          "LEFT JOIN FETCH c.insumosCotizacion i " +
                          "WHERE c.id = :id AND c.estadoCotizacion != :estadoCotizacion " +
                          "AND (i IS NULL OR i.activo = :activoInsumo)";

            return em.createQuery(jpql, Cotizacion.class)
                     .setParameter("id", idCotizacion)
                     .setParameter("estadoCotizacion", EstadoCotizacion.CANCELADA)
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
    public List<Cotizacion> obtenerTodasCotizaciones() throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        try {
            String jpql = "SELECT DISTINCT c FROM Cotizacion c " +
                          "LEFT JOIN FETCH c.insumosCotizacion i " +
                          "WHERE c.estadoCotizacion != :estadoCotizacion " +
                          "AND (i IS NULL OR i.activo = :activoInsumo)";

            return em.createQuery(jpql, Cotizacion.class)
                     .setParameter("estadoCotizacion", EstadoCotizacion.CANCELADA)
                     .setParameter("activoInsumo", true)
                     .getResultList();

        } catch (Exception e) {
            throw new PersistenciaException(MENSAJE_ERROR_CONSULTA_TODAS, e);
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

                    // Se obtiene los ids de los Insumos actualizados
                    List<Long> idsInsumosNuevos = new ArrayList<>();
                    for (InsumoCotizacion nuevo: cotizacion.getInsumosCotizacion()) {
                        idsInsumosNuevos.add(nuevo.getInsumo().getId());
                    }

                    // Se desactivan los InsumoCotizacion que ya no están presentes
                    for (InsumoCotizacion existente: cotizacionExistente.getInsumosCotizacion()) {
                        if (!idsInsumosNuevos.contains(existente.getInsumo().getId())) {
                            existente.setActivo(false);
                        }
                    }

                    // Se procesa la lista actualizada
                    for (InsumoCotizacion insumoActualizacion: cotizacion.getInsumosCotizacion()) {
                        Long idInsumoEntrante = insumoActualizacion.getInsumo().getId();

                        // Se determina si el insumo ya estaba o es nuevo, en la cotización
                        InsumoCotizacion insumoEncontrado = null;
                        for (InsumoCotizacion existente: cotizacionExistente.getInsumosCotizacion()) {
                            if (existente.getInsumo().getId().equals(idInsumoEntrante)) {
                                insumoEncontrado = existente;
                                break;
                            }
                        }

                        if (insumoEncontrado != null) {
                            // Si ya estaba presente, se actualiza y activa
                            insumoEncontrado.setCantidadRequerida(insumoActualizacion.getCantidadRequerida());
                            insumoEncontrado.setPrecio(insumoActualizacion.getPrecio());
                            insumoEncontrado.setActivo(true);
                        } else {
                            // Si no estaba, se le asigna la referencia del insumo y se agrega a la lista en la cotizacion
                            Insumo referenciaInsumo = em.getReference(Insumo.class, idInsumoEntrante);
                            insumoActualizacion.setInsumo(referenciaInsumo);
                            insumoActualizacion.setCotizacion(cotizacionExistente);
                            insumoActualizacion.setActivo(true);

                            cotizacionExistente.getInsumosCotizacion().add(insumoActualizacion);
                        }
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
                cotizacion.setEstadoCotizacion(EstadoCotizacion.CANCELADA); 
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
}
