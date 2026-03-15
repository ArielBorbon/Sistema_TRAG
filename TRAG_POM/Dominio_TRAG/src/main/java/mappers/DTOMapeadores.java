
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
public class DTOMapeadores {

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario entidad = new Usuario();
        entidad.setId(dto.getId());
        entidad.setCorreo(dto.getCorreo());
        entidad.setNombre(dto.getNombre());
        entidad.setApellidoPaterno(dto.getApellidoPaterno());
        entidad.setApellidoMaterno(dto.getApellidoMaterno());
        entidad.setEstado(dto.getEstado());
        entidad.setRol(dto.getRol());
        return entidad;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        Cliente entidad = new Cliente();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setApellidoPaterno(dto.getApellidoPaterno());
        entidad.setApellidoMaterno(dto.getApellidoMaterno());
        entidad.setTelefono(dto.getTelefono());
        entidad.setCorreo(dto.getCorreo());
        return entidad;
    }

    public static Proveedor toEntity(ProveedorDTO dto) {
        if (dto == null) {
            return null;
        }
        Proveedor entidad = new Proveedor();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setTelefono(dto.getTelefono());
        entidad.setCorreo(dto.getCorreo());
        return entidad;
    }

    public static Servicio toEntity(ServicioDTO dto) {
        if (dto == null) {
            return null;
        }
        Servicio entidad = new Servicio();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setPrecioManoObraSugerido(dto.getPrecioManoObraSugerido());
        return entidad;
    }

    public static Cotizacion toEntity(CotizacionDTO dto) {
        if (dto == null) {
            return null;
        }
        Cotizacion entidad = new Cotizacion();
        entidad.setId(dto.getId());
        entidad.setPrecioManoObra(dto.getPrecioManoObra());
        entidad.setEstadoAutomovil(dto.getEstadoAutomovil());
        entidad.setDiagnosticoGeneral(dto.getDiagnosticoGeneral());
        return entidad;
    }

    public static Insumo toEntity(InsumoDTO dto) {
        if (dto == null) {
            return null;
        }
        Insumo entidad = new Insumo();
        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());
        entidad.setPrecioSugerido(dto.getPrecioSugerido());
        entidad.setProveedor(toEntity(dto.getProveedor())); 
        return entidad;
    }

    public static Automovil toEntity(AutomovilDTO dto) {
        if (dto == null) {
            return null;
        }
        Automovil entidad = new Automovil();
        entidad.setId(dto.getId());
        entidad.setAnio(dto.getAnio());
        entidad.setMatricula(dto.getMatricula());
        entidad.setVin(dto.getVin());
        entidad.setModelo(dto.getModelo());
        entidad.setMarca(dto.getMarca());
        entidad.setCliente(toEntity(dto.getCliente()));
        return entidad;
    }

    public static InsumoServicio toEntity(InsumoServicioDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoServicio entidad = new InsumoServicio();
        entidad.setId(dto.getId());
        entidad.setCantidadDefault(dto.getCantidadDefault());
        entidad.setServicio(toEntity(dto.getServicio()));
        entidad.setInsumo(toEntity(dto.getInsumo()));
        return entidad;
    }

    public static Cita toEntity(CitaDTO dto) {
        if (dto == null) {
            return null;
        }
        Cita entidad = new Cita();
        entidad.setId(dto.getId());
        entidad.setFechaProgramada(dto.getFechaProgramada());
        entidad.setEstadoCita(dto.getEstadoCita());
        entidad.setAutomovil(toEntity(dto.getAutomovil()));
        return entidad;
    }

    public static InsumoCotizacion toEntity(InsumoCotizacionDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoCotizacion entidad = new InsumoCotizacion();
        entidad.setId(dto.getId());
        entidad.setCantidadRequerida(dto.getCantidadRequerida());
        entidad.setPrecio(dto.getPrecio());
        entidad.setCotizacion(toEntity(dto.getCotizacion()));
        entidad.setInsumo(toEntity(dto.getInsumo()));
        return entidad;
    }

    public static OrdenTrabajo toEntity(OrdenTrabajoDTO dto) {
        if (dto == null) {
            return null;
        }
        OrdenTrabajo entidad = new OrdenTrabajo();
        entidad.setId(dto.getId());
        entidad.setAutomovil(toEntity(dto.getAutomovil()));
        entidad.setCotizacion(toEntity(dto.getCotizacion()));
        entidad.setServicio(toEntity(dto.getServicio()));
        return entidad;
    }

    public static DetallePago toEntity(DetallePagoDTO dto) {
        if (dto == null) {
            return null;
        }
        DetallePago entidad = new DetallePago();
        entidad.setId(dto.getId());
        entidad.setFechaEntrega(dto.getFechaEntrega());
        entidad.setFechaGarantia(dto.getFechaGarantia());
        entidad.setPagoTotal(dto.getPagoTotal());
        entidad.setOrdenTrabajo(toEntity(dto.getOrdenTrabajo()));
        return entidad;
    }

    public static Trabajo toEntity(TrabajoDTO dto) {
        if (dto == null) {
            return null;
        }
        Trabajo entidad = new Trabajo();
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaEstimadaTermino(dto.getFechaEstimadaTermino());
        entidad.setOrdenTrabajo(toEntity(dto.getOrdenTrabajo()));
        return entidad;
    }

    public static Imprevisto toEntity(ImprevistoDTO dto) {
        if (dto == null) {
            return null;
        }
        Imprevisto entidad = new Imprevisto();
        entidad.setId(dto.getId());
        entidad.setNuevaFechaEntrega(dto.getNuevaFechaEntrega());
        entidad.setEstado(dto.getEstado());
        entidad.setOrdenTrabajo(toEntity(dto.getOrdenTrabajo()));
        return entidad;
    }

    public static InsumoTrabajoAdquirido toEntity(InsumoTrabajoAdquiridoDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoTrabajoAdquirido entidad = new InsumoTrabajoAdquirido();
        entidad.setId(dto.getId());
        entidad.setCantidad(dto.getCantidad());
        entidad.setCostoReal(dto.getCostoReal());
        entidad.setTrabajo(toEntity(dto.getTrabajo()));
        entidad.setInsumo(toEntity(dto.getInsumo()));
        return entidad;
    }

    public static InsumoImprevisto toEntity(InsumoImprevistoDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoImprevisto entidad = new InsumoImprevisto();
        entidad.setId(dto.getId());
        entidad.setCantidadRequerida(dto.getCantidadRequerida());
        entidad.setPrecio(dto.getPrecio());
        entidad.setImprevisto(toEntity(dto.getImprevisto()));
        entidad.setInsumo(toEntity(dto.getInsumo()));
        return entidad;
    }
}
