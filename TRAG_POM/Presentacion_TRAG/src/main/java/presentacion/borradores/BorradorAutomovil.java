
package presentacion.borradores;

/**
 *
 * Archivo: BorradorAutomovil.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
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
