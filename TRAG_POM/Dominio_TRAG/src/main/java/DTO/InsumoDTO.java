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
public class InsumoDTO {

    private Long id;
    private String nombre;
    private BigDecimal precioSugerido;
    private ProveedorDTO proveedor; 

    public InsumoDTO() {
    }

    public InsumoDTO(Long id, String nombre, BigDecimal precioSugerido, ProveedorDTO proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
        this.proveedor = proveedor;
    }

    public InsumoDTO(Long id, String nombre, BigDecimal precioSugerido) {
        this.id = id;
        this.nombre = nombre;
        this.precioSugerido = precioSugerido;
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

    public BigDecimal getPrecioSugerido() {
        return precioSugerido;
    }

    public void setPrecioSugerido(BigDecimal precioSugerido) {
        this.precioSugerido = precioSugerido;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
    
}
