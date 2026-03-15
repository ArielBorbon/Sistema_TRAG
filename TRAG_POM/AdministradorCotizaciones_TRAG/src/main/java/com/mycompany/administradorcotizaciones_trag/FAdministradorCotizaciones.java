/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.administradorcotizaciones_trag;

import DTO.CotizacionDTO;

/**
 *
 * @author sonic
 */
public class FAdministradorCotizaciones {
    
    //Esto probablemente cambie despues ya que usemos el inyector de dependencias, por ahora se instancia directamente
    private final AdministradorCotizaciones administrador;

    public FAdministradorCotizaciones() {
        this.administrador = new AdministradorCotizaciones();
    }

    /**
     * Fachada para la creación de una cotización.
     * @param dto Datos de la cotización desde la vista.
     * @return Cotización procesada y validada.
     */
    public CotizacionDTO fCrearCotizacion(CotizacionDTO dto) {
        return administrador.crearCotizacion(dto);
    }

    /**
     * Fachada para la edición de una cotización existente.
     * @param id Identificador único de la cotización.
     * @param dto Datos actualizados.
     * @return Cotización editada.
     */
    public CotizacionDTO fEditarCotizacion(Long id, CotizacionDTO dto) {
        return administrador.editarCotizacion(id, dto);
    }
}
