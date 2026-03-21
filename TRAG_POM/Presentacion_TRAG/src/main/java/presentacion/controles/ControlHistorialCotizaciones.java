/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controles;

import dtos.cotizacion.CotizacionResumenDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlHistorialCotizaciones;
import presentacion.interfaces.vistas.IHistorialCotizaciones;

/**
 *
 * @author sonic
 */
public class ControlHistorialCotizaciones implements IControlHistorialCotizaciones {

    private IHistorialCotizaciones vista;

    public ControlHistorialCotizaciones() {
    }

    @Override
    public void iniciar() {
        this.vista = FabricaVistas.getVistaHistorialCotizaciones(this);
        this.vista.mostrar();
        
        buscarCotizaciones(null, null, null, null);
    }

    @Override
    public void buscarCotizaciones(String nombreCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado) {
        
    }

    @Override
    public void verDetalleCotizacion(CotizacionResumenDTO cotizacionSeleccionada) {

    }

    @Override
    public void regresar() {
        this.vista.ocultar();
    }
}
