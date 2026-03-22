
package presentacion.borradores;

/**
 *
 * @author 
 */
public class BorradorAutomovil {
    private Long id;
    private String marca;
    private String modelo;
    private int anio;
    private String matricula;

    public BorradorAutomovil(Long id, String marca, String modelo, int anio, String matricula) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public String getMatricula() {
        return matricula;
    }
    
    
    
}
