/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.math.BigDecimal;

/**
 *
 * @author PC Gamer
 */
public class ServicioDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioManoObraSugerido;

    public ServicioDTO() {
    }

    public ServicioDTO(Long id, String nombre, String descripcion, BigDecimal precioManoObraSugerido) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioManoObraSugerido = precioManoObraSugerido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioManoObraSugerido() {
        return precioManoObraSugerido;
    }

    public void setPrecioManoObraSugerido(BigDecimal precioManoObraSugerido) {
        this.precioManoObraSugerido = precioManoObraSugerido;
    }
    
    
    
}
