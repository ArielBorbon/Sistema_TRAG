
package presentacion.controles;


import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import com.mycompany.administradorinsumos_trag.IAdministradorInsumos;
import com.mycompany.administradorordenestrabajo.IAdministradorOrdenesTrabajo;
import com.mycompany.administradorservicios_trag.IAdministradorServicios;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.automovil.AutomovilResumenDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.insumos.InsumoResumenDTO;
import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import dtos.servicio.ServicioDetalleDTO;
import dtos.servicio.ServicioResumenDTO;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import presentacion.borradores.BorradorAutomovil;
import presentacion.borradores.BorradorCliente;
import presentacion.borradores.BorradorCotizacion;
import presentacion.borradores.BorradorInsumoCotizacion;
import presentacion.borradores.BorradorServicio;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.IControlCotizaciones;
import presentacion.interfaces.vistas.IVistaServicios;
import presentacion.interfaces.vistas.IVistaCrearCotizacion;
import presentacion.interfaces.vistas.IVistaDiagnosticoEstado;
import presentacion.interfaces.vistas.IVistaSeleccionClienteAuto;

/**
 *
 * Archivo: ControlAgregarCotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class ControlAgregarCotizacion implements IControlAgregarCotizacion{

    private IAdministradorClientes administradorClientes;
    private IAdministradorAutomoviles administradorAutomoviles;
    private IAdministradorServicios administradorSerivicios;
    private IAdministradorCotizaciones administradorCotizaciones;
    private IAdministradorOrdenesTrabajo administradorOrdenesTrabajo;
    private IAdministradorInsumos administradorInsumos;
    
    private IVistaSeleccionClienteAuto vistaSeleccionClienteAuto;
    private IVistaDiagnosticoEstado vistaDiagnosticoEstado;
    private IVistaServicios vistaServicios;
    private IVistaCrearCotizacion vistaCrearCotizacion;
    
    private BorradorCliente borradorCliente;
    private BorradorAutomovil borradorAutomovil;
    private String borradorDiagnostico;
    private String borradorEstado;
    private BorradorServicio borradorServicio;
    private BorradorCotizacion borradorCotizacion;
    
    private IControlCotizaciones controlCotizaciones;
    
    public ControlAgregarCotizacion(){
        administradorClientes = FabricaNegocios.obtenerAdministradorClientes();
        administradorAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
        administradorSerivicios = FabricaNegocios.obtenerAdministradorServicios();
        administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
        administradorOrdenesTrabajo = FabricaNegocios.obtenerAdministradorOrdenesTrabajo();
        administradorInsumos = FabricaNegocios.obtenerAdministadorInsumos();
    }

    public void setControlCotizaciones(IControlCotizaciones controlCotizaciones) {
        this.controlCotizaciones = controlCotizaciones;
    }
    
    @Override
    public void iniciar() {
        try {
            vistaSeleccionClienteAuto = FabricaVistas.obtenerVistaSeleccionClienteAuto(this);
            List<ClienteResumenDTO> clientes = administradorClientes.obtenerTodosClientes();
            vistaSeleccionClienteAuto.cargarClientes(clientes);
            vistaSeleccionClienteAuto.mostrar();
        } catch (NegocioException e) {
            vistaSeleccionClienteAuto.mostrarMensaje(e.getMessage());
        }
    }
    
    @Override
    public void atrasPrincipal() {
        borradorDiagnostico = null;
        borradorEstado = null;
        vistaSeleccionClienteAuto.ocultar();
        controlCotizaciones.administrarCotizaciones();
    }

    @Override
    public void seleccionarCliente(Long idCliente) {
        ClienteDetalleDTO clienteSeleccionado;
        try {
            clienteSeleccionado = administradorClientes.obtenerCliente(idCliente);
            List<AutomovilResumenDTO> automovilesCliente = clienteSeleccionado.getAutomoviles();
            
            borradorCliente = new BorradorCliente(
                    clienteSeleccionado.getId(), 
                    clienteSeleccionado.getNombre(), 
                    clienteSeleccionado.getApellidoPaterno(), 
                    clienteSeleccionado.getApellidoMaterno());
            
            vistaSeleccionClienteAuto.cargarAutosCliente(automovilesCliente);
        } catch (NegocioException e) {
            vistaSeleccionClienteAuto.mostrarMensaje(e.getMessage());
        }
        
    }
    
    @Override
    public void seleccionarAutomovil(AutomovilResumenDTO automovil) {
        
        if(automovil != null){
            borradorAutomovil = new BorradorAutomovil(
                automovil.getId(), 
                automovil.getMarca(), 
                automovil.getModelo(), 
                automovil.getAnio(), 
                automovil.getMatricula()
            );
        }
        
    }

    @Override
    public void seleccionarClienteAutomovil() {
        
        vistaSeleccionClienteAuto.ocultar();
        vistaDiagnosticoEstado = FabricaVistas.obtenerVistaDiagnosticoEstado(this);
        
        if(borradorDiagnostico != null || borradorEstado != null){
            vistaDiagnosticoEstado.cargarDiagnosticoEstado(borradorDiagnostico, borradorEstado);
        }
        
        vistaDiagnosticoEstado.mostrar();
        
    }
    
    
    @Override
    public void guardarDiagnosticoEstado(String diagnsotico, String estado) {
        
        borradorDiagnostico = diagnsotico;
        borradorEstado = estado;
        
        vistaServicios = FabricaVistas.obtenerVistaServicios(this);
        
        List<ServicioResumenDTO> servicios;
        try {
            servicios = administradorSerivicios.obtenerTodosServicios();
            vistaServicios.cargarServicios(servicios);
            vistaDiagnosticoEstado.ocultar();
            vistaServicios.mostrar();
            
        } catch (NegocioException e) {
            vistaDiagnosticoEstado.mostrarMensaje(e.getMessage());
        }
        
        
        
        
    }
    
    @Override
    public void atrasDiagnosticoEstado() {
        
        try {
            List<ClienteResumenDTO> clientes = administradorClientes.obtenerTodosClientes();
            
            if(borradorCliente != null){
                
                vistaSeleccionClienteAuto.cargarClientes(clientes, borradorCliente.getId());
                ClienteDetalleDTO clienteSeleccionado = administradorClientes.obtenerCliente(borradorCliente.getId());
                List<AutomovilResumenDTO> automovilesCliente = clienteSeleccionado.getAutomoviles();
                
                vistaDiagnosticoEstado.ocultar();
                
                if(borradorAutomovil != null){
                    vistaSeleccionClienteAuto.cargarAutosCliente(automovilesCliente, borradorAutomovil.getId());
                } else{
                    vistaSeleccionClienteAuto.cargarAutosCliente(automovilesCliente);
                }
                
            } else{
                vistaSeleccionClienteAuto.cargarClientes(clientes);
            }
            
            vistaSeleccionClienteAuto.mostrar();
            
        } catch (NegocioException e) {
            vistaSeleccionClienteAuto.mostrarMensaje(e.getMessage());
        }
        
    }
    
    
    @Override
    public void seleccionarServicio(ServicioResumenDTO servicio) {
        
        borradorServicio = new BorradorServicio(servicio.getId(), servicio.getNombre());
        
        vistaCrearCotizacion = FabricaVistas.obtenerVistaCrearCotizacion(this);
        
        ServicioDetalleDTO servicioSeleccionado;
        try {
            servicioSeleccionado = administradorSerivicios.obtenerServicio(servicio.getId());
            vistaCrearCotizacion.cargarServicioSeleccionado(servicioSeleccionado);
            vistaServicios.ocultar();
            vistaCrearCotizacion.mostrar();
            
        } catch (NegocioException e) {
            vistaServicios.mostrarMensaje(e.getMessage());
        }
        
        
    }
    
    @Override
    public void buscarServicio(String nombreServicio) {
        
        try {
            List<ServicioResumenDTO> servicios = administradorSerivicios.obtenerServiciosNombre(nombreServicio);
            vistaServicios.cargarServicios(servicios);
            
        } catch (NegocioException e) {
            vistaServicios.mostrarMensaje(e.getMessage());
        }
        
    }
    
    @Override
    public void atrasSeleccionarServicio() {
        
        if(borradorDiagnostico != null &&  borradorEstado != null){
            
            vistaDiagnosticoEstado.cargarDiagnosticoEstado(borradorDiagnostico, borradorEstado);
            vistaServicios.ocultar();
            vistaDiagnosticoEstado.mostrar();
            
        }
        
    }
    
    @Override
    public void guardarCambioCotizacion(BorradorCotizacion cotizacion) {
        borradorCotizacion = cotizacion;   
    }
    
    @Override
    public void buscarInsumosNombre(String nombreInsumo) {
        
        try {
            List<InsumoResumenDTO> insumos = administradorInsumos.obtenerInsumosNombre(nombreInsumo);
            
            vistaCrearCotizacion.actualizarSugerencias(insumos);
            
        } catch (NegocioException e) {
            vistaCrearCotizacion.mostrarMensaje(e.getMessage());
        }
        
    }
    
    @Override
    public void agregarInsumo(String nombre) {
        
        try {
            List<InsumoResumenDTO> insumos = administradorInsumos.obtenerInsumosNombre(nombre);
            
            for(InsumoResumenDTO insumo: insumos){
                if(insumo.getNombre().equals(nombre)){
                    vistaCrearCotizacion.agregarInsumoTabla(insumo);
                }
            }
            
        } catch (NegocioException e) {
            vistaCrearCotizacion.mostrarMensaje(e.getMessage());
        }
        
    }
    
    @Override
    public void crearCotizacion() {
        
        List<InsumoCotizacionAgregarDTO> insumosCotizacion = new ArrayList();
        
        for(BorradorInsumoCotizacion borradorInsumoCotizacion: borradorCotizacion.getBorradoresInsumoCotizacion()){
            
            insumosCotizacion.add(
                    new InsumoCotizacionAgregarDTO(
                            borradorInsumoCotizacion.getCantidad(), 
                            borradorInsumoCotizacion.getCosto(), 
                            borradorInsumoCotizacion.getIdInsumo())
            );
            
        }
        
        OrdenTrabajoCotizacionAgregarDTO nuevaOrdenTrabajoCotizacion = new OrdenTrabajoCotizacionAgregarDTO(
                borradorAutomovil.getId(), 
                borradorServicio.getId(), 
                borradorCotizacion.getCostoManoObra(), 
                borradorDiagnostico, 
                borradorEstado,
                insumosCotizacion);
        
        
                
        try {
            OrdenTrabajoDetalleDTO ordenTrabajo = administradorOrdenesTrabajo.crearOrdenTrabajo(nuevaOrdenTrabajoCotizacion);
            
            if(ordenTrabajo != null){
                
                CotizacionResumenDTO cotizacionGuardada = administradorCotizaciones.obtenerResumenCotizacion(ordenTrabajo.getIdCotizacion());
                
                vistaCrearCotizacion.mostrarGuardadoPdf(cotizacionGuardada);
                
            }
            
        } catch (NegocioException e) {
            vistaCrearCotizacion.mostrarMensaje(e.getMessage());
        }
        
    }
    
    
    @Override
    public void atrasCrearCotizacion() {
        
        vistaCrearCotizacion.ocultar();
        vistaServicios.mostrar();
        
    }
    
    @Override
    public void cancelarAgregar() {
        
        borradorDiagnostico = null;
        borradorEstado = null;
        vistaCrearCotizacion.ocultar();
        controlCotizaciones.administrarCotizaciones();
    }

 
}
