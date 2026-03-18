
package dtos.automovil;

/**
 *
 * Archivo: AutomovilAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class AutomovilAgregarDTO {

    private Integer anio;
    private String matricula;
    private String vin;
    private String modelo;
    private String marca;
    private Long idCliente; 

    public AutomovilAgregarDTO(Integer anio, String matricula, String vin, String modelo, String marca, Long idCliente) {
        this.anio = anio;
        this.matricula = matricula;
        this.vin = vin;
        this.modelo = modelo;
        this.marca = marca;
        this.idCliente = idCliente;
    }

    public AutomovilAgregarDTO(Integer anio, String matricula, String vin, String modelo, String marca) {
        this.anio = anio;
        this.matricula = matricula;
        this.vin = vin;
        this.modelo = modelo;
        this.marca = marca;
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
