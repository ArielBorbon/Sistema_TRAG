/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.administradorcotizaciones_trag;

import DTO.CotizacionDTO;
import entidades.Cotizacion;
import java.math.BigDecimal;
import mappers.DTOMapeadores;
import mappers.Mapeadores;

/**
 *
 * @author sonic
 */
public class AdministradorCotizaciones {
    
    private static final int MAX_LONGITUD_ESTADO = 255;
    private static final BigDecimal PRECIO_MAXIMO = new BigDecimal("99999999.99");

    public CotizacionDTO crearCotizacion(CotizacionDTO dto) {
        
        validarCotizacion(dto);

        //aqui se mapea a entidad y se llama al metodo de la DAO para crear la cotizacion
        Cotizacion cotizacionRegistrada = DTOMapeadores.toEntity(dto);

        return Mapeadores.toDTO(cotizacionRegistrada);
    }
    
    public CotizacionDTO editarCotizacion(Long id, CotizacionDTO dto) {
        if (id == null) throw new IllegalArgumentException("El ID es necesario para editar.");
        
        validarCotizacion(dto);
        
        Cotizacion cotizacionEditada = DTOMapeadores.toEntity(dto);

        return Mapeadores.toDTO(cotizacionEditada);
    }
    
    
    private void validarCotizacion (CotizacionDTO dto) {
        if (dto.getPrecioManoObra() == null || dto.getPrecioManoObra().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("El precio de mano de obra no es válido, debe ser mayor a 0");
        }
        if (dto.getDiagnosticoGeneral() == null || dto.getDiagnosticoGeneral().isEmpty()) {
            throw new RuntimeException("El diagnóstico no puede estar vacío.");
        }
        
        if (dto.getEstadoAutomovil()== null || dto.getEstadoAutomovil().isEmpty()) {
            throw new RuntimeException("El estado del automovil no puede estar vacio.");
        }
        
        if (dto.getPrecioManoObra().compareTo(PRECIO_MAXIMO) > 0) {
            throw new IllegalArgumentException("El precio excede el límite permitido del sistema.");
        }

        if (dto.getEstadoAutomovil() != null) {
            if (dto.getEstadoAutomovil().trim().length() > MAX_LONGITUD_ESTADO) {
                throw new IllegalArgumentException("La descripción del estado no puede superar los " 
                    + MAX_LONGITUD_ESTADO + " caracteres.");
            }
        }

        if (dto.getDiagnosticoGeneral() == null || dto.getDiagnosticoGeneral().trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico general es obligatorio para procesar la cotización.");
        }

        if (dto.getDiagnosticoGeneral().trim().length() < 10) {
            throw new IllegalArgumentException("El diagnóstico es demasiado breve. Por favor, sea más específico.");
        }
    }
}
