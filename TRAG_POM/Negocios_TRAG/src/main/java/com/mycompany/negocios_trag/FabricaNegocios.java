
package com.mycompany.negocios_trag;

import com.mycompany.administradorautomoviles_trag.AdministradorAutomoviles;
import com.mycompany.administradorautomoviles_trag.FAdministradorAutomoviles;
import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.AdministradorClientes;
import com.mycompany.administradorclientes_trag.FAdministradorClientes;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.administradorcotizaciones_trag.AdministradorCotizaciones;
import com.mycompany.administradorcotizaciones_trag.FAdministradorCotizaciones;
import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import com.mycompany.administradorinsumos_trag.AdministradorInsumos;
import com.mycompany.administradorinsumos_trag.FAdministradorInsumos;
import com.mycompany.administradorinsumos_trag.IAdministradorInsumos;
import com.mycompany.administradorordenestrabajo.AdministradorOrdenesTrabajo;
import com.mycompany.administradorordenestrabajo.FAdministradorOrdenesTrabajo;
import com.mycompany.administradorordenestrabajo.IAdministradorOrdenesTrabajo;
import com.mycompany.administradorservicios_trag.AdministradorServicios;
import com.mycompany.administradorservicios_trag.FAdministradorServicios;
import com.mycompany.administradorservicios_trag.IAdministradorServicios;
import daos.AutomovilesDAO;
import daos.ClientesDAO;
import interfaces.ICotizacionesDAO;
import daos.CotizacionesDAO;
import daos.InsumosDAO;
import daos.OrdenesTrabajoDAO;
import daos.ServiciosDAO;
import interfaces.IAutomovilesDAO;
import interfaces.IClientesDAO;
import interfaces.IInsumosDAO;
import interfaces.IOrdenesTrabajoDAO;
import interfaces.IServiciosDAO;

/**
 *
 * Archivo: FabricaNegocios.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class FabricaNegocios {
    
    
    public static IAdministradorOrdenesTrabajo obtenerAdministradorOrdenesTrabajo(){
        IOrdenesTrabajoDAO ordenesTrabajoDAO = new OrdenesTrabajoDAO();
        
        IAdministradorCotizaciones administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
        
        AdministradorOrdenesTrabajo administradorOrdenesTrabajo = new AdministradorOrdenesTrabajo(ordenesTrabajoDAO, administradorCotizaciones); 
        FAdministradorOrdenesTrabajo fAdministradorOrdenesTrabajo = new FAdministradorOrdenesTrabajo(administradorOrdenesTrabajo);
        
        return fAdministradorOrdenesTrabajo;
        
    }
    
    public static IAdministradorCotizaciones obtenerAdministradorCotizaciones(){
        ICotizacionesDAO cotizacionesDAO = new CotizacionesDAO();
        
        AdministradorCotizaciones administradorCotizaciones = new AdministradorCotizaciones(cotizacionesDAO); 
        FAdministradorCotizaciones fAdministradorCotizaciones = new FAdministradorCotizaciones(administradorCotizaciones);
        
        return fAdministradorCotizaciones;
        
    }
    
    public static IAdministradorClientes obtenerAdministradorClientes(){
        IClientesDAO clientesDAO = new ClientesDAO();
        
        AdministradorClientes administradorClientes = new AdministradorClientes(clientesDAO); 
        FAdministradorClientes fAdministradorClientes = new FAdministradorClientes(administradorClientes);
        
        return fAdministradorClientes;
        
    }
    
    public static IAdministradorAutomoviles obtenerAdministradorAutomoviles(){
        IAutomovilesDAO automovilesDAO = new AutomovilesDAO();
        
        AdministradorAutomoviles administradorAutomoviles = new AdministradorAutomoviles(automovilesDAO); 
        FAdministradorAutomoviles fAdministradorAutomoviles = new FAdministradorAutomoviles(administradorAutomoviles);
        
        return fAdministradorAutomoviles;
        
    }
    
    public static IAdministradorServicios obtenerAdministradorServicios(){
        IServiciosDAO serviciosDAO = new ServiciosDAO();
        
        AdministradorServicios administradorServicios = new AdministradorServicios(serviciosDAO); 
        FAdministradorServicios fAdministradorServicios = new FAdministradorServicios(administradorServicios);
        
        return fAdministradorServicios;
        
    }
    
    public static IAdministradorInsumos obtenerAdministadorInsumos(){
        
        IInsumosDAO insumosDAO = new InsumosDAO();
        
        AdministradorInsumos administradorInsumos = new AdministradorInsumos(insumosDAO);
        
        IAdministradorInsumos fAdministradorInsumos = new FAdministradorInsumos(administradorInsumos);
        
        return fAdministradorInsumos;
    }
    
}
