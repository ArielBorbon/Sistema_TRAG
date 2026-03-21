

package presentacion.borradores;

/**
 *
 * @author romom
 */
public class BorradorCotizacion {
    
    private Integer numero;
    private String texto;

    public BorradorCotizacion(Integer numero, String texto) {
        this.numero = numero;
        this.texto = texto;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getTexto() {
        return texto;
    }
    
}
