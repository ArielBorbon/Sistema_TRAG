//package presentacion.controles;
//import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
//import com.mycompany.administradorinsumos_trag.IAdministradorInsumos;
//import com.mycompany.negocios_trag.FabricaNegocios;
//import dtos.cotizacion.CotizacionResumenDTO;
//import dtos.insumos.InsumoDetalleDTO;
//import dtos.insumos.InsumoResumenDTO;
//import java.util.List;
//import presentacion.fabrica.FabricaVistas;
//import presentacion.interfaces.IControlConsultaCotizacion;
//import presentacion.interfaces.vistas.IVistaConsultaCotizacionPrevia;
///**
// * @author Yuri Germán García López - 252583
// */
//public class ControlConsultaCotizacion implements IControlConsultaCotizacion {
//
//    private IAdministradorCotizaciones administradorCotizaciones;
//    private IAdministradorInsumos administradorInsumos;
//    private IVistaConsultaCotizacionPrevia vista;
//
//    public ControlConsultaCotizacion() {
//        this.administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
//        this.administradorInsumos = FabricaNegocios.obtenerAdministadorInsumos();
//    }
//    
//    @Override
//    public void iniciar(CotizacionResumenDTO cotizacionSeleccionada) {
//        this.vista = FabricaVistas.getVistaConsultarCotizacion(this);
//        this.vista.mostrar();
//        // cargar los datos de la cotización seleciconada
//        this.vista.cargarDatosCotizacion(cotizacionSeleccionada);
//    }
//    
//    @Override
//    public void buscarInsumos(String texto) {
//        if (texto == null) texto = "";
//
//        try {
//            List<InsumoResumenDTO> insumos = administradorInsumos.obtenerInsumosNombre(texto);
//
//            if (vista != null) {
//                vista.mostrarInsumosBuscados(insumos);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void seleccionarInsumo(InsumoResumenDTO insumo) {
//        if (insumo == null) return;
//
//        try {
//            vista.aniadirInsumo(insumo);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void guardar() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void regresar() {
//        this.vista.ocultar();
//        new ControlHistorialCotizaciones().iniciar();
//    }
//    
//}
