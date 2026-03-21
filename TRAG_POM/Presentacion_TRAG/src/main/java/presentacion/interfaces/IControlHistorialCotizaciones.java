/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.interfaces;

import dtos.cotizacion.CotizacionResumenDTO;
import java.time.LocalDateTime;

/**
 *
 * @author sonic
 */
public interface IControlHistorialCotizaciones {
    
    public abstract void iniciar();
    
    public abstract void buscarCotizaciones(String nombreCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin, String estado);
    
    public abstract void verDetalleCotizacion(CotizacionResumenDTO cotizacionSeleccionada);
    
    public abstract void regresar();
}
