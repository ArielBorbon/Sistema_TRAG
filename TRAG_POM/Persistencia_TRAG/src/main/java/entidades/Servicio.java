
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * Archivo: Servicio.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioManoObraSugerido;
    
    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = true, length = 255)
    private String direccionIcono;
    
    @OneToMany (mappedBy = "servicio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InsumoServicio> insumosServicio;

    public Servicio() {
    }

    public Servicio(Long id) {
        this.id = id;
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

    public String getDireccionIcono() {
        return direccionIcono;
    }

    public void setDireccionIcono(String direccionIcono) {
        this.direccionIcono = direccionIcono;
    }
    
    public List<InsumoServicio> getInsumosServicio() {
        return insumosServicio;
    }

    public void setInsumosServicio(List<InsumoServicio> insumosServicio) {
        this.insumosServicio = insumosServicio;
    }
    
}
