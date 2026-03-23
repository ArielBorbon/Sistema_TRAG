
package presentacion.controles;

import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import com.mycompany.administradorinsumos_trag.IAdministradorInsumos;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.cotizacion.CotizacionActualizarDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionActualizarDTO;
import dtos.insumos.InsumoResumenDTO;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import presentacion.borradores.BorradorCotizacion;
import presentacion.borradores.BorradorInsumoCotizacion;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.vistas.IVistaConsultaCotizacion;
import presentacion.interfaces.vistas.IVistaHistorialCotizaciones;
import presentacion.interfaces.IControlConsultarCotizaciones;
import presentacion.interfaces.IControlCotizaciones;

/**
 *
 * Archivo: ControlConsultarCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ControlConsultarCotizaciones implements IControlConsultarCotizaciones {

    private final IAdministradorCotizaciones administradorCotizaciones;
    private final IAdministradorInsumos administradorInsumos;
    
    private IVistaHistorialCotizaciones vistaHistorialCotizaciones;
    private IVistaConsultaCotizacion vistaConsultaCotizacion;
    
    private BorradorCotizacion borradorCotizacion;
    
    private IControlCotizaciones controlCotizaciones;

    public ControlConsultarCotizaciones() {
        this.administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
        this.administradorInsumos = FabricaNegocios.obtenerAdministadorInsumos();
    }

    public void setControlCotizaciones(IControlCotizaciones controlCotizaciones) {
        this.controlCotizaciones = controlCotizaciones;
    }
    
    @Override
    public void iniciar() {
        this.vistaHistorialCotizaciones = FabricaVistas.obtenerVistaHistorialCotizaciones(this);
        
        // Al arrancar la pantalla, traemos todas las cotizaciones sin filtros
        buscarCotizaciones(null, null, null, "Todos");
        
        this.vistaHistorialCotizaciones.mostrar();
    }

    @Override
    public void buscarCotizaciones(String nombreCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) {
        try {

            List<CotizacionResumenDTO> listaFiltrada = administradorCotizaciones.obtenerTodasCotizaciones();

            if (nombreCliente != null && !nombreCliente.trim().isEmpty()) {
                String busquedaLower = nombreCliente.trim().toLowerCase();
                listaFiltrada = listaFiltrada.stream()
                        .filter(c -> {
                            String nom = c.getNombreCliente() != null ? c.getNombreCliente().toLowerCase() : "";
                            String ape = c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente().toLowerCase() : "";
                            return nom.contains(busquedaLower) || ape.contains(busquedaLower);
                        })
                        .collect(Collectors.toList());
            }

            if (fechaInicio != null || fechaFin != null) {
                
                final LocalDateTime inicioAjustada = (fechaInicio != null) 
                        ? fechaInicio.withHour(0).withMinute(0).withSecond(0).withNano(0) 
                        : null;

                final LocalDateTime finAjustada = (fechaFin != null) 
                        ? fechaFin.withHour(23).withMinute(59).withSecond(59).withNano(999999999) 
                        : null;

                listaFiltrada = listaFiltrada.stream()
                        .filter(c -> {
                            if (c.getFechaCreacion() == null) return false;
                            
                            boolean cumpleInicio = (inicioAjustada == null) || !c.getFechaCreacion().isBefore(inicioAjustada);
                            boolean cumpleFin = (finAjustada == null) || !c.getFechaCreacion().isAfter(finAjustada);
                            
                            return cumpleInicio && cumpleFin;
                        })
                        .collect(Collectors.toList());
            }

            if (estado != null && !estado.equalsIgnoreCase("Todos") && !estado.isEmpty()) {
                listaFiltrada = listaFiltrada.stream()
                        .filter(c -> c.getEstadoCotizacion() != null && 
                                     c.getEstadoCotizacion().name().equalsIgnoreCase(estado))
                        .collect(Collectors.toList());
            }
            
            listaFiltrada.forEach(c -> {

                if (c.getEstadoCotizacion() != null && c.getEstadoCotizacion().name().equalsIgnoreCase("ACTIVA")) {
                    
                    
                    if (c.getInsumosCotizacion() != null) {
                        c.getInsumosCotizacion().removeIf(insumo -> !insumo.isActivo());
                    }
                }
            });

            vistaHistorialCotizaciones.mostrarCotizaciones(listaFiltrada);

        } catch (Exception ex) {
            vistaHistorialCotizaciones.mostrarMensajeRapido("Error al filtrar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    @Override
    public void cancelarCotizacion(Long idCotizacion) {
        
        try {
            administradorCotizaciones.eliminarCotizacion(idCotizacion);
            buscarCotizaciones(null, null, null, "Todos");
        } catch (NegocioException ex) {
            vistaHistorialCotizaciones.mostrarMensajeRapido("Error al deshabilitar la cotización");
        }
        
    }

    @Override
    public void activiarCotizacion(Long idCotizacion) {
        try {
            administradorCotizaciones.habilitarCotizacion(idCotizacion);
            buscarCotizaciones(null, null, null, "Todos");
        } catch (NegocioException ex) {
            vistaHistorialCotizaciones.mostrarMensajeRapido("Error al habilitar la cotización");
        }
    }

    @Override
    public void verDetalleCotizacion(CotizacionResumenDTO cotizacionSeleccionada) {
        
        this.vistaHistorialCotizaciones.ocultar();
        
        try {
            CotizacionDetalleDTO cotizacion = administradorCotizaciones.obtenerCotizacion(cotizacionSeleccionada.getId());


            if (cotizacion.getEstado() != null && cotizacion.getEstado().name().equalsIgnoreCase("ACTIVA")) {
                if (cotizacion.getInsumosCotizacion()!= null) {

                    cotizacion.getInsumosCotizacion().removeIf(insumo -> !insumo.isActivo());
                }
            }

            this.vistaConsultaCotizacion = FabricaVistas.obtenerVistaConsultarCotizacion(this);

            this.vistaConsultaCotizacion.mostrar();

            this.vistaConsultaCotizacion.cargarCotizacionSeleccionada(cotizacion);

        } catch (NegocioException ex) {
            vistaHistorialCotizaciones.mostrarMensaje(ex.getMessage());
        }
       
    }

    @Override
    public void volverHistorialCotizaciones() {
        vistaHistorialCotizaciones.ocultar();
        controlCotizaciones.administrarCotizaciones();
    }
    
    @Override
    public void volverConsultarCotizacion() {
        vistaConsultaCotizacion.ocultar();
        vistaHistorialCotizaciones.mostrar();
    }
    
    @Override
    public void cancelarConsultarCotizacion() {
        vistaConsultaCotizacion.ocultar();
        controlCotizaciones.administrarCotizaciones();
    }


    @Override
    public void guardarCambioCotizacion(BorradorCotizacion cotizacion) {
        borradorCotizacion = cotizacion;
    }

    @Override
    public void buscarInsumosNombre(String nombreInsumo) {
        try {
            List<InsumoResumenDTO> insumos = administradorInsumos.obtenerInsumosNombre(nombreInsumo);
            
            vistaConsultaCotizacion.actualizarSugerencias(insumos);
            
        } catch (NegocioException e) {
            vistaConsultaCotizacion.mostrarMensaje(e.getMessage());
        }
    }

    @Override
    public void agregarInsumo(String nombre) {
        try {
            List<InsumoResumenDTO> insumos = administradorInsumos.obtenerInsumosNombre(nombre);
            
            for(InsumoResumenDTO insumo: insumos){
                if(insumo.getNombre().equals(nombre)){
                    vistaConsultaCotizacion.agregarInsumoTabla(insumo);
                }
            }
            
        } catch (NegocioException e) {
            vistaConsultaCotizacion.mostrarMensaje(e.getMessage());
        }
    }

    @Override
    public void actualizarCotizacion() {

        if (borradorCotizacion == null) {
            vistaConsultaCotizacion.mostrarMensaje("No hay cambios pendientes para guardar.");
            return;
        }

        List<InsumoCotizacionActualizarDTO> insumosCotizacion = new ArrayList<>();
        
        try {

            CotizacionDetalleDTO cotizacionExistente = administradorCotizaciones.obtenerCotizacion(borradorCotizacion.getId());
        
            for (BorradorInsumoCotizacion borradorInsumo: borradorCotizacion.getBorradoresInsumoCotizacion()) {
                insumosCotizacion.add(new InsumoCotizacionActualizarDTO(
                    borradorInsumo.getCantidad(), 
                    borradorInsumo.getCosto(), 
                    borradorCotizacion.getId(),
                    borradorInsumo.getIdInsumo()
                ));
            }

            CotizacionActualizarDTO cotizacionActualizar = new CotizacionActualizarDTO(
                borradorCotizacion.getId(), 
                borradorCotizacion.getCostoManoObra(), 
                cotizacionExistente.getDiagnosticoGeneral(), 
                cotizacionExistente.getEstadoAutomovil(),
                insumosCotizacion
            );

            CotizacionDetalleDTO cotizacionActualizada = administradorCotizaciones.actualizarCotizacion(cotizacionActualizar);

            if (cotizacionActualizada != null) {
                vistaConsultaCotizacion.mostrarMensajeExito();
            }

        } catch (NegocioException ex) {
            vistaConsultaCotizacion.mostrarMensaje(ex.getMessage());
        } catch (Exception e) {
            // Captura errores inesperados para que no se cierre la app
            vistaConsultaCotizacion.mostrarMensaje("Ocurrió un error inesperado al actualizar.");
            e.printStackTrace();
        }
    }

    @Override
    public void aceptarExitoActualizacionCotizacion() {
        
        vistaConsultaCotizacion.ocultar();
        controlCotizaciones.administrarCotizaciones();
    }


}
