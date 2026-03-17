
package insumoservicio;

/**
 *
 * @author
 */
public class InsumoServicioDetalleDTO {

    private Long id;
    private Integer cantidadDefault;
    private Long idServicio;
    private Long idInsumo;

    public InsumoServicioDetalleDTO(Long id, Integer cantidadDefault, Long idServicio, Long idInsumo) {
        this.id = id;
        this.cantidadDefault = cantidadDefault;
        this.idServicio = idServicio;
        this.idInsumo = idInsumo;
    }

    public Long getId() {
        return id;
    }

    public Integer getCantidadDefault() {
        return cantidadDefault;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }
    
}