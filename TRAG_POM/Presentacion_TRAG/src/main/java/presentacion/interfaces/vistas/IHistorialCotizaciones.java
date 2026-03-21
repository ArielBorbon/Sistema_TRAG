/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.interfaces.vistas;

import dtos.cotizacion.CotizacionResumenDTO;
import java.util.List;
import presentacion.interfaces.IVista;

/**
 *
 * @author sonic
 */
public interface IHistorialCotizaciones extends IVista{
    
    // Actualiza el listado de cotrizaciones
    public abstract void mostrarCotizaciones(List<CotizacionResumenDTO> cotizaciones);
    
    // Para mostrar mensajes de error si no se encuentran resultados
    public abstract void mostrarMensajeRapido(String mensaje);
    
    public abstract void limpiarFiltros();
    
}
