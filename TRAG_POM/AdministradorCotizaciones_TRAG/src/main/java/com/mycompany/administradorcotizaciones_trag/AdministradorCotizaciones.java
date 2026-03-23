package com.mycompany.administradorcotizaciones_trag;


import dtos.cotizacion.CotizacionActualizarDTO;
import dtos.cotizacion.CotizacionAgregarDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import entidades.Cotizacion;
import enums.EstadoCotizacionNegocios;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.ICotizacionesDAO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * Archivo: AdministradorCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class AdministradorCotizaciones {

    private ICotizacionesDAO cotizacionesDAO;

    private static final int MAX_LONGITUD_ESTADO = 1000;
    private static final BigDecimal PRECIO_MAXIMO = new BigDecimal("99999999.99");

    private static final String MENSAJE_ID_COTIZACION_AUSENTE_OBTENER = "El ID es necesario para obtener una cotización";
    private static final String MENSAJE_ID_COTIZACION_AUSENTE_ACTUALIZAR = "El ID es necesario para actualizar una cotización";
    private static final String MENSAJE_ID_COTIZACION_AUSENTE_ELIMINAR = "El ID es necesario para eliminar una cotización";

    private static final String MENSAJE_ERROR_CREAR_COTIZACION = "Error al crear la cotización";
    private static final String MENSAJE_ERROR_OBTENER_COTIZACION = "Error al obtener la cotización";
    private static final String MENSAJE_ERROR_OBTENER_TODAS_COTIZACIONES = "Error al obtener las cotizaciones";
    private static final String MENSAJE_ERROR_ACTUALIZAR_COTIZACION = "Error al actualizar la cotización";
    private static final String MENSAJE_ERROR_ELIMINAR_COTIZACION = "Error al eliminar la cotización";

    public AdministradorCotizaciones(ICotizacionesDAO cotizacionesDAO) {
        this.cotizacionesDAO = cotizacionesDAO;
    }

    public CotizacionDetalleDTO crearCotizacion(CotizacionAgregarDTO dto) throws NegocioException {

        validarCotizacionAgregar(dto);
        
        dto.setEstadoCotizacion(EstadoCotizacionNegocios.ACTIVA);

        Cotizacion cotizacionRegistrada = DTOMapeadores.toEntity(dto);

        try {
            return Mapeadores.toDTODetalle(cotizacionesDAO.agregarCotizacion(cotizacionRegistrada));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_CREAR_COTIZACION, e);

        }

    }

    public CotizacionDetalleDTO obtenerCotizacion(Long idCotizacion) throws NegocioException {

        if (idCotizacion == null) {
            throw new NegocioException(MENSAJE_ID_COTIZACION_AUSENTE_OBTENER);
        }

        try {
            CotizacionDetalleDTO cotizacionDetalle = Mapeadores.toDTODetalle(cotizacionesDAO.obtenerCotizacion(idCotizacion));
            List<InsumoCotizacionDetalleDTO> insumosCotizacion = cotizacionDetalle.getInsumosCotizacion();

            for (InsumoCotizacionDetalleDTO dto: insumosCotizacion) {
                dto.setSubtotal(BigDecimal.valueOf(dto.getCantidadRequerida()).multiply(dto.getPrecio()));
            }

            return cotizacionDetalle;
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_COTIZACION, e);
        }

    }
    
    public CotizacionResumenDTO obtenerResumenCotizacion(Long idCotizacion) throws NegocioException {
        if (idCotizacion == null) {
            throw new NegocioException(MENSAJE_ID_COTIZACION_AUSENTE_OBTENER);
        }

        try {
            CotizacionResumenDTO cotizacionResumen = Mapeadores.toDTOResumen(cotizacionesDAO.obtenerCotizacion(idCotizacion));
            List<InsumoCotizacionDetalleDTO> insumosCotizacion = cotizacionResumen.getInsumosCotizacion();

            for (InsumoCotizacionDetalleDTO dto: insumosCotizacion) {
                dto.setSubtotal(BigDecimal.valueOf(dto.getCantidadRequerida()).multiply(dto.getPrecio()));
            }

            return cotizacionResumen;
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_COTIZACION, e);
        } 
    }

    public List<CotizacionResumenDTO> obtenerTodasCotizaciones() throws NegocioException {

        try {
            List<CotizacionResumenDTO> cotizaciones = Mapeadores.toDTOCotizaciones(cotizacionesDAO.obtenerTodasCotizaciones());

            for (CotizacionResumenDTO cotizacion : cotizaciones) {

                BigDecimal total = cotizacion.getPrecioManoObra().add(
                        cotizacion.getInsumosCotizacion().stream().map(insumo -> {
                            insumo.setSubtotal(insumo.getPrecio().multiply(BigDecimal.valueOf(insumo.getCantidadRequerida())));
                            return insumo.getSubtotal();
                        }).reduce(BigDecimal.ZERO, BigDecimal::add)
                );

                cotizacion.setPrecioTotal(total);

            }

            return cotizaciones;

        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_TODAS_COTIZACIONES, e);
        }

    }
    
    public List<CotizacionResumenDTO> obtenerCotizacionesNombreCliente(String nombreCliente) throws NegocioException {
        try {
            List<CotizacionResumenDTO> cotizaciones = Mapeadores.toDTOCotizaciones(cotizacionesDAO.obtenerCotizacionesNombreCliente(nombreCliente));

            for (CotizacionResumenDTO cotizacion : cotizaciones) {

                BigDecimal total = cotizacion.getPrecioManoObra().add(
                        cotizacion.getInsumosCotizacion().stream().map(insumo -> {
                            insumo.setSubtotal(insumo.getPrecio().multiply(BigDecimal.valueOf(insumo.getCantidadRequerida())));
                            return insumo.getSubtotal();
                        }).reduce(BigDecimal.ZERO, BigDecimal::add)
                );

                cotizacion.setPrecioTotal(total);

            }

            return cotizaciones;

        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_TODAS_COTIZACIONES, e);
        }
    }
    
    public List<CotizacionResumenDTO> obtenerCotizacionesFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        try {
            List<CotizacionResumenDTO> cotizaciones = Mapeadores.toDTOCotizaciones(cotizacionesDAO.obtenerCotizacionesFecha(fechaInicio, fechaFin));

            for (CotizacionResumenDTO cotizacion : cotizaciones) {

                BigDecimal total = cotizacion.getPrecioManoObra().add(
                        cotizacion.getInsumosCotizacion().stream().map(insumo -> {
                            insumo.setSubtotal(insumo.getPrecio().multiply(BigDecimal.valueOf(insumo.getCantidadRequerida())));
                            return insumo.getSubtotal();
                        }).reduce(BigDecimal.ZERO, BigDecimal::add)
                );

                cotizacion.setPrecioTotal(total);

            }

            return cotizaciones;

        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_OBTENER_TODAS_COTIZACIONES, e);
        }
    }

    public CotizacionDetalleDTO actualizarCotizacion(CotizacionActualizarDTO dto) throws NegocioException {

        if (dto.getId() == null) {
            throw new NegocioException(MENSAJE_ID_COTIZACION_AUSENTE_ACTUALIZAR);
        }

        validarCotizacionActualizar(dto);
        
        dto.setFechaCreacion(LocalDateTime.now());
        Cotizacion cotizacionActualizar = DTOMapeadores.toEntity(dto);

        try {
            return Mapeadores.toDTODetalle(cotizacionesDAO.actualizarCotizacion(cotizacionActualizar));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_ACTUALIZAR_COTIZACION, e);
        }
    }

    public CotizacionDetalleDTO eliminarCotizacion(Long idCotizacion) throws NegocioException {

        if (idCotizacion == null) {
            throw new NegocioException(MENSAJE_ID_COTIZACION_AUSENTE_ELIMINAR);
        }

        try {
            return Mapeadores.toDTODetalle(cotizacionesDAO.eliminarCotizacion(idCotizacion));
        } catch (PersistenciaException e) {
            throw new NegocioException(MENSAJE_ERROR_ELIMINAR_COTIZACION, e);
        }

    }

    public CotizacionDetalleDTO habilitarCotizacion(Long idCotizacion) throws NegocioException {
        
        if(idCotizacion == null){
            throw new NegocioException("El id de la cotización no puede ser nulo.");
        }
        
        try {
            return Mapeadores.toDTODetalle(cotizacionesDAO.habilitarCotizacion(idCotizacion));
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al habilitar la cotización", e);
        }
        
        
    }

    private void validarCotizacionAgregar(CotizacionAgregarDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("Los datos de la cotización no pueden ser nulos.");
        }
        validarPrecioManoObra(dto.getPrecioManoObra());
        validarEstadoAutomovil(dto.getEstadoAutomovil());
        validarDiagnosticoGeneral(dto.getDiagnosticoGeneral());
    }

    private void validarCotizacionActualizar(CotizacionActualizarDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("Los datos de la cotización no pueden ser nulos.");
        }
        validarPrecioManoObra(dto.getPrecioManoObra());
        validarEstadoAutomovil(dto.getEstadoAutomovil());
        validarDiagnosticoGeneral(dto.getDiagnosticoGeneral());
    }

    private void validarPrecioManoObra(BigDecimal precioManoObra) throws NegocioException {
        if (precioManoObra == null || precioManoObra.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegocioException("El precio de mano de obra no es válido, debe ser mayor o igual a 0.");
        }
        if (precioManoObra.compareTo(PRECIO_MAXIMO) > 0) {
            throw new NegocioException("El precio excede el límite permitido del sistema.");
        }
    }

    private void validarEstadoAutomovil(String estadoAutomovil) throws NegocioException {
        if (estadoAutomovil == null || estadoAutomovil.trim().isEmpty()) {
            throw new NegocioException("El estado del automóvil no puede estar vacío.");
        }
        if (estadoAutomovil.trim().length() > MAX_LONGITUD_ESTADO) {
            throw new IllegalArgumentException("La descripción del estado no puede superar los "
                    + MAX_LONGITUD_ESTADO + " caracteres.");
        }
    }

    private void validarDiagnosticoGeneral(String diagnosticoGeneral) throws NegocioException {
        if (diagnosticoGeneral == null || diagnosticoGeneral.trim().isEmpty()) {
            throw new NegocioException("El diagnóstico general es obligatorio para procesar la cotización.");
        }
    }

}
