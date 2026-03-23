package mappers;

import dtos.automovil.AutomovilResumenDTO;
import dtos.CitaDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cotizacion.CotizacionResumenDTO;
import dtos.DetallePagoDTO;
import imprevisto.ImprevistoDetalleDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import dtos.insumos.InsumoDetalleDTO;
import dtos.insumoimprevisto.InsumoImprevistoDetalleDTO;
import insumoservicio.InsumoServicioDetalleDTO;
import dtos.insumotrabajoadquirido.InsumoTrabajoAdquiridoDetalleDTO;
import dtos.OrdenTrabajoDTO;
import dtos.ProveedorDTO;
import dtos.servicio.ServicioResumenDTO;
import dtos.trabajo.TrabajoDetalleDTO;
import dtos.usuario.UsuarioDetalleDTO;
import dtos.automovil.AutomovilDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.insumos.InsumoResumenDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import dtos.servicio.ServicioDetalleDTO;
import entidades.Automovil;
import entidades.Cita;
import entidades.Cliente;
import entidades.Cotizacion;
import entidades.DetallePago;
import entidades.Imprevisto;
import entidades.Insumo;
import entidades.InsumoCotizacion;
import entidades.InsumoImprevisto;
import entidades.InsumoServicio;
import entidades.InsumoTrabajoAdquirido;
import entidades.OrdenTrabajo;
import entidades.Proveedor;
import entidades.Servicio;
import entidades.Trabajo;
import entidades.Usuario;
import enums.EstadoClienteNegocios;
import enums.EstadoCotizacionNegocios;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Archivo: Mapeadores.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class Mapeadores {

    public static UsuarioDetalleDTO toDTO(Usuario entidad) {
        if (entidad == null) {
            return null;
        }
        UsuarioDetalleDTO dto = new UsuarioDetalleDTO();
        dto.setId(entidad.getId());
        dto.setCorreo(entidad.getCorreo());
        dto.setNombre(entidad.getNombre());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setEstado(entidad.getEstado());
        dto.setRol(entidad.getRol());
        return dto;
    }

    public static List<UsuarioDetalleDTO> toDTOUsuarios(List<Usuario> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTO)
                .collect(Collectors.toList());
    }

    public static ClienteResumenDTO toDTOResumen(Cliente entidad) {
        if (entidad == null) {
            return null;
        }
        ClienteResumenDTO dto = new ClienteResumenDTO(
                entidad.getId(),
                entidad.getNombre(), 
                entidad.getApellidoPaterno(),
                entidad.getApellidoMaterno()
        );
       
        return dto;
    }
    
    public static ClienteDetalleDTO toDTODetalle(Cliente entidad) {
        if (entidad == null) {
            return null;
        }
        ClienteDetalleDTO dto = new ClienteDetalleDTO(
                entidad.getId(), 
                entidad.getNombre(), 
                entidad.getApellidoPaterno(),
                entidad.getApellidoMaterno(),
                entidad.getTelefono(), 
                entidad.getCorreo(),
                EstadoClienteNegocios.valueOf(entidad.getEstado().name()),
                entidad.getAutomoviles().stream()
                    .map(Mapeadores::toDTOResumen)
                    .collect(Collectors.toList())
        );
       
        return dto;
    }

    public static List<ClienteResumenDTO> toDTOClientes(List<Cliente> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTOResumen)
                .collect(Collectors.toList());
    }

    public static ProveedorDTO toDTO(Proveedor entidad) {
        if (entidad == null) {
            return null;
        }
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setTelefono(entidad.getTelefono());
        dto.setCorreo(entidad.getCorreo());
        return dto;
    }

    public static List<ProveedorDTO> toDTOProveedores(List<Proveedor> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTO)
                .collect(Collectors.toList());
    }

    public static ServicioDetalleDTO toDTODetalle(Servicio entidad) {
        if (entidad == null) {
            return null;
        }
        ServicioDetalleDTO dto = new ServicioDetalleDTO(
                entidad.getId(), 
                entidad.getNombre(), 
                entidad.getDescripcion(), 
                entidad.getPrecioManoObraSugerido(),
                entidad.getDireccionIcono(),
                entidad.getInsumosServicio().stream()
                    .map(Mapeadores::toDTODetalle)
                    .collect(Collectors.toList())
        );
        return dto;
    }
    
    public static ServicioResumenDTO toDTOResumen(Servicio entidad) {
        if (entidad == null) {
            return null;
        }
        ServicioResumenDTO dto = new ServicioResumenDTO(
                entidad.getId(), 
                entidad.getNombre(),
                entidad.getDireccionIcono());
        return dto;
    }

    public static List<ServicioResumenDTO> toDTOServicios(List<Servicio> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTOResumen)
                .collect(Collectors.toList());
    }

    public static CotizacionDetalleDTO toDTODetalle(Cotizacion entidad) {
        if (entidad == null) {
            return null;
        }
        CotizacionDetalleDTO dto = new CotizacionDetalleDTO(
                entidad.getId(), 
                entidad.getPrecioManoObra(), 
                entidad.getEstadoAutomovil(), 
                entidad.getDiagnosticoGeneral(),
                entidad.getFechaCreacion(),
                Mapeadores.toDTODetalleInsumosCotizacion(entidad.getInsumosCotizacion()),
                entidad.getServicio().getNombre(),
                EstadoCotizacionNegocios.valueOf(entidad.getEstadoCotizacion().name()));
        
        return dto;
    }
    
    public static CotizacionResumenDTO toDTOResumen(Cotizacion entidad) {
        if (entidad == null) {
            return null;
        }
        
        CotizacionResumenDTO dto;
        
        if(entidad.getOrdenTrabajo().getAutomovil().getCliente().getApellidoMaterno() != null){

            dto = new CotizacionResumenDTO(
                entidad.getId(),
                entidad.getOrdenTrabajo().getAutomovil().getCliente().getNombre(),
                entidad.getOrdenTrabajo().getAutomovil().getCliente().getApellidoPaterno(),
                entidad.getOrdenTrabajo().getAutomovil().getCliente().getApellidoMaterno(),
                entidad.getOrdenTrabajo().getAutomovil().getMarca(),
                entidad.getOrdenTrabajo().getAutomovil().getModelo(),
                entidad.getOrdenTrabajo().getAutomovil().getMatricula(),
                entidad.getOrdenTrabajo().getAutomovil().getAnio(),
                entidad.getFechaCreacion(),
                entidad.getPrecioManoObra(),
                Mapeadores.toDTODetalleInsumosCotizacion(entidad.getInsumosCotizacion()),
                EstadoCotizacionNegocios.valueOf(entidad.getEstadoCotizacion().name())
            );
        } else{
            dto = new CotizacionResumenDTO(
                entidad.getId(),
                entidad.getOrdenTrabajo().getAutomovil().getCliente().getNombre(),
                entidad.getOrdenTrabajo().getAutomovil().getCliente().getApellidoPaterno(),
                entidad.getOrdenTrabajo().getAutomovil().getMarca(),
                entidad.getOrdenTrabajo().getAutomovil().getModelo(),
                entidad.getOrdenTrabajo().getAutomovil().getMatricula(),
                entidad.getOrdenTrabajo().getAutomovil().getAnio(),
                entidad.getFechaCreacion(),
                entidad.getPrecioManoObra(),
                Mapeadores.toDTODetalleInsumosCotizacion(entidad.getInsumosCotizacion()),
                EstadoCotizacionNegocios.valueOf(entidad.getEstadoCotizacion().name())
            );
        }
        
        return dto;
    }

    public static List<CotizacionResumenDTO> toDTOCotizaciones(List<Cotizacion> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTOResumen)
                .collect(Collectors.toList());
    }

    public static InsumoDetalleDTO toDTODetalle(Insumo entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoDetalleDTO dto = new InsumoDetalleDTO(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getPrecioSugerido(),
                null
        );

        return dto;
    }
    
    public static InsumoResumenDTO toDTOResumen(Insumo entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoResumenDTO dto = new InsumoResumenDTO(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getPrecioSugerido()
        );

        return dto;
    }

    public static List<InsumoDetalleDTO> toDTODetalleInsumos(List<Insumo> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTODetalle)
                .collect(Collectors.toList());
    }
    
    public static List<InsumoResumenDTO> toDTOResumenInsumos(List<Insumo> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTOResumen)
                .collect(Collectors.toList());
    }
    
    
    public static AutomovilDetalleDTO toDTODetalle(Automovil entidad) {
        if (entidad == null) {
            return null;
        }
        AutomovilDetalleDTO dto = new AutomovilDetalleDTO(
                entidad.getId(),
                entidad.getAnio(), 
                entidad.getMatricula(), 
                entidad.getVin(), 
                entidad.getModelo(),
                entidad.getMarca(),
                entidad.getCliente().getId());
        
        return dto;
    }
    
    public static AutomovilResumenDTO toDTOResumen(Automovil entidad) {
        if (entidad == null) {
            return null;
        }
        AutomovilResumenDTO dto = new AutomovilResumenDTO(
                entidad.getId(),
                entidad.getAnio(), 
                entidad.getMatricula(), 
                entidad.getModelo(),
                entidad.getMarca());
        
        return dto;
    }

    public static List<AutomovilResumenDTO> toDTOAutomoviles(List<Automovil> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTOResumen)
                .collect(Collectors.toList());
    }

    public static InsumoServicioDetalleDTO toDTODetalle(InsumoServicio entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoServicioDetalleDTO dto = 
                new InsumoServicioDetalleDTO(
                    entidad.getId(), 
                    entidad.getCantidadDefault(), 
                    entidad.getServicio().getId(), 
                    Mapeadores.toDTOResumen(entidad.getInsumo())
                );
        return dto;
    }

    public static CitaDTO toDTO(Cita entidad) {
        if (entidad == null) {
            return null;
        }
        CitaDTO dto = new CitaDTO();
        dto.setId(entidad.getId());
        dto.setFechaProgramada(entidad.getFechaProgramada());
        dto.setEstadoCita(entidad.getEstadoCita());
        dto.setIdAutomovil(entidad.getAutomovil().getId());
        return dto;
    }

    public static List<CitaDTO> toDTOCitas(List<Cita> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTO)
                .collect(Collectors.toList());
    }

    public static InsumoCotizacionDetalleDTO toDTODetalle(InsumoCotizacion entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoCotizacionDetalleDTO dto = new InsumoCotizacionDetalleDTO(
                entidad.getId(),
                entidad.getCantidadRequerida(),
                entidad.getPrecio(),
                entidad.getCotizacion().getId(), 
                Mapeadores.toDTOResumen(entidad.getInsumo()),
                entidad.getActivo());
        return dto;
    }

    public static List<InsumoCotizacionDetalleDTO> toDTODetalleInsumosCotizacion(List<InsumoCotizacion> entidades){
        
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTODetalle)
                .collect(Collectors.toList());
        
    }


    public static OrdenTrabajoDetalleDTO toDTODetalle(OrdenTrabajo entidad) {
        if (entidad == null) {
            return null;
        }
        OrdenTrabajoDetalleDTO dto = new OrdenTrabajoDetalleDTO(entidad.getId(), entidad.getAutomovil().getId(), entidad.getCotizacion().getId());

        return dto;
    }

    public static List<OrdenTrabajoDetalleDTO> toDTOOrdenesTrabajo(List<OrdenTrabajo> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTODetalle)
                .collect(Collectors.toList());
    }

    public static DetallePagoDTO toDTODetalle(DetallePago entidad) {
        if (entidad == null) {
            return null;
        }
        DetallePagoDTO dto = new DetallePagoDTO(
                entidad.getId(), 
                entidad.getFechaEntrega(), 
                entidad.getFechaGarantia(), 
                entidad.getPagoTotal(), 
                entidad.getOrdenTrabajo().getId()
        );

        return dto;
    }

    public static TrabajoDetalleDTO toDTODetalle(Trabajo entidad) {
        if (entidad == null) {
            return null;
        }
        TrabajoDetalleDTO dto = new TrabajoDetalleDTO(
                entidad.getId(), 
                entidad.getFechaInicio(), 
                entidad.getFechaEstimadaTermino(), 
                entidad.getFechaTermino(), 
                entidad.getOrdenTrabajo().getId());

        return dto;
    }

    public static List<TrabajoDetalleDTO> toDTOTrabajos(List<Trabajo> entidades) {
        if (entidades == null) {
            return null;
        }
        return entidades.stream()
                .map(Mapeadores::toDTODetalle)
                .collect(Collectors.toList());
    }

    public static ImprevistoDetalleDTO toDTODetalle(Imprevisto entidad) {
        if (entidad == null) {
            return null;
        }
        ImprevistoDetalleDTO dto = new ImprevistoDetalleDTO(
                entidad.getId(), 
                entidad.getNuevaFechaEntrega(), 
                entidad.getEstado(), 
                entidad.getOrdenTrabajo().getId());
        return dto;
    }

    public static InsumoTrabajoAdquiridoDetalleDTO toDTODetalle(InsumoTrabajoAdquirido entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoTrabajoAdquiridoDetalleDTO dto = new InsumoTrabajoAdquiridoDetalleDTO(
                entidad.getId(), 
                entidad.getCantidad(), 
                entidad.getCostoReal(), 
                entidad.getTrabajo().getId(), 
                entidad.getInsumo().getId());
        return dto;
    }

    public static InsumoImprevistoDetalleDTO toDTODetalle(InsumoImprevisto entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoImprevistoDetalleDTO dto = new InsumoImprevistoDetalleDTO(
                entidad.getId(), 
                entidad.getCantidadRequerida(), 
                entidad.getPrecio(), 
                entidad.getImprevisto().getId(), entidad.getInsumo().getId());
        return dto;
    }
}
