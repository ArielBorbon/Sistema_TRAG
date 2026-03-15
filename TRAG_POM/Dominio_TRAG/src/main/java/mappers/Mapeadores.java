
package mappers;

import DTO.AutomovilDTO;
import DTO.CitaDTO;
import DTO.ClienteDTO;
import DTO.CotizacionDTO;
import DTO.DetallePagoDTO;
import DTO.ImprevistoDTO;
import DTO.InsumoCotizacionDTO;
import DTO.InsumoDTO;
import DTO.InsumoImprevistoDTO;
import DTO.InsumoServicioDTO;
import DTO.InsumoTrabajoAdquiridoDTO;
import DTO.OrdenTrabajoDTO;
import DTO.ProveedorDTO;
import DTO.ServicioDTO;
import DTO.TrabajoDTO;
import DTO.UsuarioDTO;
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

/**
 *
 * @author PC Gamer
 */
public class Mapeadores {

    public static UsuarioDTO toDTO(Usuario entidad) {
        if (entidad == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entidad.getId());
        dto.setCorreo(entidad.getCorreo());
        dto.setNombre(entidad.getNombre());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setEstado(entidad.getEstado());
        dto.setRol(entidad.getRol());
        return dto;
    }

    public static ClienteDTO toDTO(Cliente entidad) {
        if (entidad == null) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setTelefono(entidad.getTelefono());
        dto.setCorreo(entidad.getCorreo());
        return dto;
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

    public static ServicioDTO toDTO(Servicio entidad) {
        if (entidad == null) {
            return null;
        }
        ServicioDTO dto = new ServicioDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setPrecioManoObraSugerido(entidad.getPrecioManoObraSugerido());
        return dto;
    }

    public static CotizacionDTO toDTO(Cotizacion entidad) {
        if (entidad == null) {
            return null;
        }
        CotizacionDTO dto = new CotizacionDTO();
        dto.setId(entidad.getId());
        dto.setPrecioManoObra(entidad.getPrecioManoObra());
        dto.setEstadoAutomovil(entidad.getEstadoAutomovil());
        dto.setDiagnosticoGeneral(entidad.getDiagnosticoGeneral());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        return dto;
    }

    public static InsumoDTO toDTO(Insumo entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoDTO dto = new InsumoDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setPrecioSugerido(entidad.getPrecioSugerido());
        dto.setProveedor(toDTO(entidad.getProveedor())); 
        return dto;
    }

    public static AutomovilDTO toDTO(Automovil entidad) {
        if (entidad == null) {
            return null;
        }
        AutomovilDTO dto = new AutomovilDTO();
        dto.setId(entidad.getId());
        dto.setAnio(entidad.getAnio());
        dto.setMatricula(entidad.getMatricula());
        dto.setVin(entidad.getVin());
        dto.setModelo(entidad.getModelo());
        dto.setMarca(entidad.getMarca());
        dto.setCliente(toDTO(entidad.getCliente())); 
        return dto;
    }

    public static InsumoServicioDTO toDTO(InsumoServicio entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoServicioDTO dto = new InsumoServicioDTO();
        dto.setId(entidad.getId());
        dto.setCantidadDefault(entidad.getCantidadDefault());
        dto.setServicio(toDTO(entidad.getServicio()));
        dto.setInsumo(toDTO(entidad.getInsumo()));
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
        dto.setAutomovil(toDTO(entidad.getAutomovil()));
        return dto;
    }

    public static InsumoCotizacionDTO toDTO(InsumoCotizacion entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoCotizacionDTO dto = new InsumoCotizacionDTO();
        dto.setId(entidad.getId());
        dto.setCantidadRequerida(entidad.getCantidadRequerida());
        dto.setPrecio(entidad.getPrecio());
        dto.setCotizacion(toDTO(entidad.getCotizacion()));
        dto.setInsumo(toDTO(entidad.getInsumo()));
        return dto;
    }

    public static OrdenTrabajoDTO toDTO(OrdenTrabajo entidad) {
        if (entidad == null) {
            return null;
        }
        OrdenTrabajoDTO dto = new OrdenTrabajoDTO();
        dto.setId(entidad.getId());
        dto.setAutomovil(toDTO(entidad.getAutomovil()));
        dto.setCotizacion(toDTO(entidad.getCotizacion()));
        dto.setServicio(toDTO(entidad.getServicio()));
        return dto;
    }

    public static DetallePagoDTO toDTO(DetallePago entidad) {
        if (entidad == null) {
            return null;
        }
        DetallePagoDTO dto = new DetallePagoDTO();
        dto.setId(entidad.getId());
        dto.setFechaEntrega(entidad.getFechaEntrega());
        dto.setFechaGarantia(entidad.getFechaGarantia());
        dto.setPagoTotal(entidad.getPagoTotal());
        dto.setOrdenTrabajo(toDTO(entidad.getOrdenTrabajo()));
        return dto;
    }

    public static TrabajoDTO toDTO(Trabajo entidad) {
        if (entidad == null) {
            return null;
        }
        TrabajoDTO dto = new TrabajoDTO();
        dto.setId(entidad.getId());
        dto.setFechaInicio(entidad.getFechaInicio());
        dto.setFechaEstimadaTermino(entidad.getFechaEstimadaTermino());
        dto.setFechaTermino(entidad.getFechaTermino());
        dto.setOrdenTrabajo(toDTO(entidad.getOrdenTrabajo()));
        return dto;
    }

    public static ImprevistoDTO toDTO(Imprevisto entidad) {
        if (entidad == null) {
            return null;
        }
        ImprevistoDTO dto = new ImprevistoDTO();
        dto.setId(entidad.getId());
        dto.setNuevaFechaEntrega(entidad.getNuevaFechaEntrega());
        dto.setEstado(entidad.getEstado());
        dto.setOrdenTrabajo(toDTO(entidad.getOrdenTrabajo()));
        return dto;
    }

    public static InsumoTrabajoAdquiridoDTO toDTO(InsumoTrabajoAdquirido entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoTrabajoAdquiridoDTO dto = new InsumoTrabajoAdquiridoDTO();
        dto.setId(entidad.getId());
        dto.setCantidad(entidad.getCantidad());
        dto.setCostoReal(entidad.getCostoReal());
        dto.setTrabajo(toDTO(entidad.getTrabajo()));
        dto.setInsumo(toDTO(entidad.getInsumo()));
        return dto;
    }

    public static InsumoImprevistoDTO toDTO(InsumoImprevisto entidad) {
        if (entidad == null) {
            return null;
        }
        InsumoImprevistoDTO dto = new InsumoImprevistoDTO();
        dto.setId(entidad.getId());
        dto.setCantidadRequerida(entidad.getCantidadRequerida());
        dto.setPrecio(entidad.getPrecio());
        dto.setImprevisto(toDTO(entidad.getImprevisto()));
        dto.setInsumo(toDTO(entidad.getInsumo()));
        return dto;
    }
}
