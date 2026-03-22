
package presentacion.controles;


import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import com.mycompany.administradorordenestrabajo.IAdministradorOrdenesTrabajo;
import com.mycompany.administradorservicios_trag.IAdministradorServicios;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.automovil.AutomovilResumenDTO;
import dtos.cliente.ClienteDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoDetalleDTO;
import dtos.servicio.ServicioDetalleDTO;
import dtos.servicio.ServicioResumenDTO;
import excepciones.NegocioException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.borradores.BorradorAutomovil;
import presentacion.borradores.BorradorCliente;
import presentacion.borradores.BorradorCotizacion;
import presentacion.borradores.BorradorInsumoCotizacion;
import presentacion.borradores.BorradorServicio;
import presentacion.fabrica.FabricaVistas;
import presentacion.interfaces.IControlAgregarCotizacion;
import presentacion.interfaces.vistas.IVistaServicios;
import presentacion.interfaces.vistas.IPruebaAgregarCotizacion;
import presentacion.interfaces.vistas.IVistaCrearCotizacion;
import presentacion.interfaces.vistas.IVistaDiagnosticoEstado;
import presentacion.interfaces.vistas.IVistaSeleccionClienteAuto;

/**
 *
 * @author 
 */
public class ControlAgregarCotizacion implements IControlAgregarCotizacion{

    private IAdministradorClientes administradorClientes;
    private IAdministradorAutomoviles administradorAutomoviles;
    private IAdministradorServicios administradorSerivicios;
    private IAdministradorCotizaciones administradorCotizaciones;
    private IAdministradorOrdenesTrabajo administradorOrdenesTrabajo;
    
    private IPruebaAgregarCotizacion vistaPruebaAgregarCotizacion;
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
    
    public ControlAgregarCotizacion(){
        administradorClientes = FabricaNegocios.obtenerAdministradorClientes();
        administradorAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
        administradorSerivicios = FabricaNegocios.obtenerAdministradorServicios();
        administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
        administradorOrdenesTrabajo = FabricaNegocios.obtenerAdministradorOrdenesTrabajo();
    }
    
    @Override
    public void iniciar() {
        try {
            vistaSeleccionClienteAuto = FabricaVistas.obtenerVistaSeleccionClienteAuto(this);
            List<ClienteResumenDTO> clientes = administradorClientes.obtenerTodosClientes();
            vistaSeleccionClienteAuto.cargarClientes(clientes);
            vistaSeleccionClienteAuto.mostrar();
        } catch (NegocioException e) {
            vistaSeleccionClienteAuto.mostrarError(e.getMessage());
        }
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
            vistaSeleccionClienteAuto.mostrarError(e.getMessage());
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
            vistaDiagnosticoEstado.mostrarError(e.getMessage());
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
            vistaSeleccionClienteAuto.mostrarError(e.getMessage());
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
            vistaCrearCotizacion.mostrar();
            
        } catch (NegocioException e) {
            vistaCrearCotizacion.mostrarError(e.getMessage());
        }
        
        
    }
    
    @Override
    public void guardarCambioCotizacion(BorradorCotizacion cotizacion) {
        borradorCotizacion = cotizacion;   
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
                
                vistaCrearCotizacion.mostrarGuardadoPdf();
                
            }
            
        } catch (NegocioException e) {
            vistaCrearCotizacion.mostrarError(e.getMessage());
        }
        
    }
    
    @Override
    public void atrasSeleccionarServicio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void cancelarAgregar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
