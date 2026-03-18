
package insumoservicio;

/**
 *
 * Archivo: InsumoServicioAgregarDTO.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class InsumoServicioAgregarDTO {
    private Integer cantidadDefault;
    private Long idServicio;
    private Long idInsumo;

    public InsumoServicioAgregarDTO(Integer cantidadDefault, Long idServicio, Long idInsumo) {
        this.cantidadDefault = cantidadDefault;
        this.idServicio = idServicio;
        this.idInsumo = idInsumo;
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
