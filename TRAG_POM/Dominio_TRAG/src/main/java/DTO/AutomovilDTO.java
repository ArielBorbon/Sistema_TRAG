/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author PC Gamer
 */
public class AutomovilDTO {

    private Long id;
    private Integer anio;
    private String matricula;
    private String vin;
    private String modelo;
    private String marca;
    private ClienteDTO cliente; 

    public AutomovilDTO() {
    }

    public AutomovilDTO(Long id, Integer anio, String matricula, String vin, String modelo, String marca, ClienteDTO cliente) {
        this.id = id;
        this.anio = anio;
        this.matricula = matricula;
        this.vin = vin;
        this.modelo = modelo;
        this.marca = marca;
        this.cliente = cliente;
    }

    public AutomovilDTO(Long id, Integer anio, String matricula, String vin, String modelo, String marca) {
        this.id = id;
        this.anio = anio;
        this.matricula = matricula;
        this.vin = vin;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    
    
}
