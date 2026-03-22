/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controles;

import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.cotizacion.CotizacionResumenDTO;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlHistorialCotizaciones;
import presentacion.interfaces.vistas.IHistorialCotizaciones;

/**
 *
 * @author sonic
 */
public class ControlHistorialCotizaciones implements IControlHistorialCotizaciones {

    // Dependencias
    private IAdministradorCotizaciones administradorCotizaciones;
    private IHistorialCotizaciones vista;

    public ControlHistorialCotizaciones() {
        this.administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
    }

    @Override
    public void iniciar() {
        this.vista = FabricaVistas.getVistaHistorialCotizaciones(this);
        this.vista.mostrar();
        
        // Al arrancar la pantalla, traemos todas las cotizaciones sin filtros
        buscarCotizaciones(null, null, null, "Todos");
    }

    @Override
    public void buscarCotizaciones(String nombreCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) {
        try {
            List<CotizacionResumenDTO> listaFiltrada = administradorCotizaciones.obtenerTodasCotizaciones();
            
            if (nombreCliente != null && !nombreCliente.trim().isEmpty()) {
                String busquedaLower = nombreCliente.trim().toLowerCase();
                listaFiltrada = listaFiltrada.stream()
                        .filter(c -> {
                            // Protegemos el filtro de nombres nulos
                            String nom = c.getNombreCliente() != null ? c.getNombreCliente().toLowerCase() : "";
                            String ape = c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente().toLowerCase() : "";
                            return nom.contains(busquedaLower) || ape.contains(busquedaLower);
                        })
                        .collect(Collectors.toList());
            }
            
            if (fechaInicio != null || fechaFin != null) {
                listaFiltrada = listaFiltrada.stream()
                        .filter(c -> {
                            // Si no hay fecha en BD, lo descartamos de la busqueda por fecha
                            if (c.getFechaCreacion() == null) return false; 
                            boolean cumpleInicio = (fechaInicio == null) || !c.getFechaCreacion().isBefore(fechaInicio);
                            boolean cumpleFin = (fechaFin == null) || !c.getFechaCreacion().isAfter(fechaFin);
                            return cumpleInicio && cumpleFin;
                        })
                        .collect(Collectors.toList());
            }
            
            if (estado != null && !estado.equalsIgnoreCase("Todos")) {
                listaFiltrada = listaFiltrada.stream()
                        .filter(c -> c.getEstadoCotizacion() != null &&
                                c.getEstadoCotizacion().name().equalsIgnoreCase(estado))
                        .collect(Collectors.toList());
            }
            
            vista.mostrarCotizaciones(listaFiltrada);
            
        } catch (NegocioException ex) {
            vista.mostrarMensajeRapido("Aviso: " + ex.getMessage());
        } catch (Exception ex) {
            vista.mostrarMensajeRapido("Error crítico al procesar datos: " + ex.toString());
            ex.printStackTrace();
        }
    }

    @Override
    public void verDetalleCotizacion(CotizacionResumenDTO cotizacionSeleccionada) {
        // Aquí eventualmente se instanciará el controlador de la pantalla de "Detalles"
        // Por ahora, solo mandamos un mensaje para comprobar que el botón de la card funcione
        vista.mostrarMensajeRapido("Abriendo detalles de la cotización de: " + 
                cotizacionSeleccionada.getNombreCliente() + " " + 
                cotizacionSeleccionada.getApellidoPaternoCliente());
    }

    @Override
    public void regresar() {
        this.vista.ocultar();
        // Aquí podrías instanciar el controlador del menú principal para volver
    }
}
