
package dtos.automovil;

/**
 *
 * @author 
 */
public class AutomovilResumenDTO {

    private Long id;
    private Integer anio;
    private String matricula;
    private String modelo;
    private String marca;

    public AutomovilResumenDTO(Long id, Integer anio, String matricula, String modelo, String marca) {
        this.id = id;
        this.anio = anio;
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
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

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    
    
    
    

}
