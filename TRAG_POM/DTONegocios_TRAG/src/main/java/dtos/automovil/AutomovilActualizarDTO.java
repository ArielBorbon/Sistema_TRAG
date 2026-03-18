
package dtos.automovil;

/**
 *
 * @author 
 */
public class AutomovilActualizarDTO{
    private final Long id;
    private final Integer anio;
    private final String matricula;
    private final String vin;
    private final String modelo;
    private final String marca;
    private final Long idCliente; 

    public AutomovilActualizarDTO(Long id, Integer anio, String matricula, String vin, String modelo, String marca, Long idCliente) {
        this.id = id;
        this.anio = anio;
        this.matricula = matricula;
        this.vin = vin;
        this.modelo = modelo;
        this.marca = marca;
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public Integer getAnio() {
        return anio;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getVin() {
        return vin;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    
    
}
