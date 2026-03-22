
package mappers;

import dtos.automovil.AutomovilActualizarDTO;
import dtos.automovil.AutomovilAgregarDTO;
import dtos.CitaDTO;
import dtos.cliente.ClienteActualizarDTO;
import dtos.cliente.ClienteAgregarDTO;
import dtos.cotizacion.CotizacionActualizarDTO;
import dtos.cotizacion.CotizacionAgregarDTO;
import dtos.DetallePagoDTO;
import imprevisto.ImprevistoDetalleDTO;
import dtos.insumotrabajoadquirido.InsumoTrabajoAdquiridoDetalleDTO;
import dtos.ProveedorDTO;
import dtos.servicio.ServicioActualizarDTO;
import dtos.servicio.ServicioAgregarDTO;
import dtos.trabajo.TrabajoDetalleDTO;
import dtos.usuario.UsuarioDetalleDTO;
import dtos.insumocotizacion.InsumoCotizacionActualizarDTO;
import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.insumoimprevisto.InsumoImprevistoDetalleDTO;
import dtos.insumos.InsumoAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoAgregarDTO;
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
import enums.EstadoCliente;
import enums.EstadoCotizacion;
import insumoservicio.InsumoServicioAgregarDTO;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * Archivo: DTOMapeadores.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */

public class DTOMapeadores {

    public static Usuario toEntityUsuario(UsuarioDetalleDTO dto) {
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

    public static List<Usuario> toEntityUsuarios(List<UsuarioDetalleDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(DTOMapeadores::toEntityUsuario)
                .collect(Collectors.toList());
    }

    public static Cliente toEntity(ClienteAgregarDTO dto) {
        if (dto == null) {
            return null;
        }
        Cliente entidad = new Cliente();
        entidad.setNombre(dto.getNombre());
        entidad.setApellidoPaterno(dto.getApellidoPaterno());
        entidad.setApellidoMaterno(dto.getApellidoMaterno());
        entidad.setTelefono(dto.getTelefono());
        entidad.setCorreo(dto.getCorreo());
        entidad.setEstado(EstadoCliente.valueOf(dto.getEstado().name()));
        return entidad;
    }
    
    public static Cliente toEntity(ClienteActualizarDTO dto) {
        if (dto == null) {
            return null;
        }
        Cliente entidad = new Cliente();
        entidad.setNombre(dto.getNombre());
        entidad.setApellidoPaterno(dto.getApellidoPaterno());
        entidad.setApellidoMaterno(dto.getApellidoMaterno());
        entidad.setTelefono(dto.getTelefono());
        entidad.setCorreo(dto.getCorreo());
        entidad.setId(dto.getId());
        return entidad;
    }


    public static Proveedor toEntityProveedor(ProveedorDTO dto) {
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

    public static List<Proveedor> toEntityProveedores(List<ProveedorDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(DTOMapeadores::toEntityProveedor)
                .collect(Collectors.toList());
    }

    public static Servicio toEntity(ServicioAgregarDTO dto) {
        if (dto == null) {
            return null;
        }
        Servicio entidad = new Servicio();
        entidad.setNombre(dto.getNombre());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setPrecioManoObraSugerido(dto.getPrecioManoObraSugerido());
        entidad.setDireccionIcono(dto.getDireccionIcono());
        entidad.setInsumosServicio(toEntityInsumosServicio(dto.getInsumosServicio()));
        return entidad;
    }
     
    public static Servicio toEntity(ServicioActualizarDTO dto) {
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

    public static Cotizacion toEntity(CotizacionAgregarDTO dto){
        if (dto == null) {
            return null;
        } 
        Cotizacion entidad = new Cotizacion();
        
        entidad.setPrecioManoObra(dto.getPrecioManoObra());
        entidad.setEstadoAutomovil(dto.getEstadoAutomovil());
        entidad.setDiagnosticoGeneral(dto.getDiagnosticoGeneral());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setInsumosCotizacion(DTOMapeadores.toEntityInsumosCotizacionAgregar(dto.getInsumosCotizacion()));
        entidad.setEstadoCotizacion(EstadoCotizacion.valueOf(dto.getEstadoCotizacion().name()));
        entidad.setServicio(new Servicio(dto.getIdServicio()));
        return entidad;
    }
    
    public static Cotizacion toEntity(CotizacionActualizarDTO dto){
        if (dto == null) {
            return null;
        }
        Cotizacion entidad = new Cotizacion();
        entidad.setId(dto.getId());
        entidad.setPrecioManoObra(dto.getPrecioManoObra());
        entidad.setEstadoAutomovil(dto.getEstadoAutomovil());
        entidad.setDiagnosticoGeneral(dto.getDiagnosticoGeneral());
        entidad.setFechaCreacion(dto.getFechaCreacion());
        entidad.setInsumosCotizacion(DTOMapeadores.toEntityInsumosCotizacionActualizar(dto.getInsumosCotizacion()));
        
        return entidad;
    }

    public static Insumo toEntity(InsumoAgregarDTO dto) {
        if (dto == null) {
            return null;
        }
        Insumo entidad = new Insumo();
        entidad.setNombre(dto.getNombre());
        entidad.setPrecioSugerido(dto.getPrecioSugerido());
        return entidad;
    }
    
    public static Automovil toEntity(AutomovilAgregarDTO dto) {
        if (dto == null) {
            return null;
        }
        Automovil entidad = new Automovil();
        entidad.setAnio(dto.getAnio());
        entidad.setMatricula(dto.getMatricula());
        entidad.setVin(dto.getVin());
        entidad.setModelo(dto.getModelo());
        entidad.setMarca(dto.getMarca());
        entidad.setCliente(new Cliente(dto.getIdCliente()));
        return entidad;
    }
    
    public static Automovil toEntity(AutomovilActualizarDTO dto) {
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
        entidad.setCliente(new Cliente(dto.getIdCliente()));
        return entidad;
    }

    public static InsumoServicio toEntityInsumoServicio(InsumoServicioAgregarDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoServicio entidad = new InsumoServicio();
        entidad.setCantidadDefault(dto.getCantidadDefault());
        entidad.setServicio(new Servicio(dto.getIdServicio()));
        entidad.setInsumo(new Insumo(dto.getIdInsumo()));
        return entidad;
    }
    
    public static List<InsumoServicio> toEntityInsumosServicio(List<InsumoServicioAgregarDTO> dtos){
        
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(DTOMapeadores::toEntityInsumoServicio)
                .collect(Collectors.toList());
    }

    public static Cita toEntityCita(CitaDTO dto) {
        if (dto == null) {
            return null;
        }
        Cita entidad = new Cita();
        entidad.setId(dto.getId());
        entidad.setFechaProgramada(dto.getFechaProgramada());
        entidad.setEstadoCita(dto.getEstadoCita());
        entidad.setAutomovil(new Automovil(dto.getIdAutomovil()));
        return entidad;
    }

    public static List<Cita> toEntityCitas(List<CitaDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(DTOMapeadores::toEntityCita)
                .collect(Collectors.toList());
    }

    public static InsumoCotizacion toEntityAgregar(InsumoCotizacionAgregarDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoCotizacion entidad = new InsumoCotizacion();
        entidad.setCantidadRequerida(dto.getCantidadRequerida());
        entidad.setPrecio(dto.getPrecio());
        entidad.setInsumo(new Insumo(dto.getIdInsumo()));
        return entidad;
    }
    
    public static InsumoCotizacion toEntityActualizar(InsumoCotizacionActualizarDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoCotizacion entidad = new InsumoCotizacion();
        entidad.setId(dto.getIdInsumo());
        entidad.setCantidadRequerida(dto.getCantidadRequerida());
        entidad.setPrecio(dto.getPrecio());
        entidad.setInsumo(new Insumo(dto.getIdInsumo()));
        entidad.setCotizacion(new Cotizacion(dto.getIdCotizacion()));
        return entidad;
    }
    
    public static List<InsumoCotizacion> toEntityInsumosCotizacionAgregar(List<InsumoCotizacionAgregarDTO> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(DTOMapeadores::toEntityAgregar)
                .collect(Collectors.toList());
    }
    
    public static List<InsumoCotizacion> toEntityInsumosCotizacionActualizar(List<InsumoCotizacionActualizarDTO> dtos){
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(DTOMapeadores::toEntityActualizar)
                .collect(Collectors.toList());
    }

    public static OrdenTrabajo toEntity(OrdenTrabajoAgregarDTO dto) {
        if (dto == null) {
            return null;
        }
        OrdenTrabajo entidad = new OrdenTrabajo();
        entidad.setAutomovil(new Automovil(dto.getIdAutomovil()));
        entidad.setCotizacion(new Cotizacion(dto.getIdCotizacion()));
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
        entidad.setOrdenTrabajo(new OrdenTrabajo(dto.getIdOrdenTrabajo()));
        return entidad;
    }

    public static Trabajo toEntityTrabajo(TrabajoDetalleDTO dto) {
        if (dto == null) {
            return null;
        }
        Trabajo entidad = new Trabajo();
        entidad.setId(dto.getId());
        entidad.setFechaInicio(dto.getFechaInicio());
        entidad.setFechaEstimadaTermino(dto.getFechaEstimadaTermino());
        entidad.setFechaTermino(dto.getFechaTermino());
        entidad.setOrdenTrabajo(new OrdenTrabajo(dto.getIdOrdenTrabajo()));
        return entidad;
    }

    public static List<Trabajo> toEntityTrabajos(List<TrabajoDetalleDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(DTOMapeadores::toEntityTrabajo)
                .collect(Collectors.toList());
    }

    public static Imprevisto toEntity(ImprevistoDetalleDTO dto) {
        if (dto == null) {
            return null;
        }
        Imprevisto entidad = new Imprevisto();
        entidad.setId(dto.getId());
        entidad.setNuevaFechaEntrega(dto.getNuevaFechaEntrega());
        entidad.setEstado(dto.getEstado());
        entidad.setOrdenTrabajo(new OrdenTrabajo(dto.getIdOrdenTrabajo()));
        return entidad;
    }

    public static InsumoTrabajoAdquirido toEntity(InsumoTrabajoAdquiridoDetalleDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoTrabajoAdquirido entidad = new InsumoTrabajoAdquirido();
        entidad.setId(dto.getId());
        entidad.setCantidad(dto.getCantidad());
        entidad.setCostoReal(dto.getCostoReal());
        entidad.setTrabajo(new Trabajo(dto.getIdTrabajo()));
        entidad.setInsumo(new Insumo(dto.getIdInsumo()));
        return entidad;
    }

    public static InsumoImprevisto toEntity(InsumoImprevistoDetalleDTO dto) {
        if (dto == null) {
            return null;
        }
        InsumoImprevisto entidad = new InsumoImprevisto();
        entidad.setId(dto.getId());
        entidad.setCantidadRequerida(dto.getCantidadRequerida());
        entidad.setPrecio(dto.getPrecio());
        entidad.setImprevisto(new Imprevisto(dto.getIdImprevisto()));
        entidad.setInsumo(new Insumo(dto.getIdInsumo()));
        return entidad;
    }
}