
package dtos.automovil;

/**
 *
 * Archivo: AutomovilDetalleDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */

public class AutomovilDetalleDTO {

    private Long id;
    private Integer anio;
    private String matricula;
    private String vin;
    private String modelo;
    private String marca;
    private Long idCliente; 

    public AutomovilDetalleDTO(Long id, Integer anio, String matricula, String vin, String modelo, String marca, Long idCliente) {
        this.id = id;
        this.anio = anio;
        this.matricula = matricula;
        this.vin = vin;
        this.modelo = modelo;
        this.marca = marca;
        this.idCliente = idCliente;
    }

    public AutomovilDetalleDTO(Long id, Integer anio, String matricula, String vin, String modelo, String marca) {
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

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
}