

package com.mycompany.pruebas_trag;

import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.administradorinsumos_trag.IAdministradorInsumos;
import com.mycompany.administradorordenestrabajo.IAdministradorOrdenesTrabajo;
import com.mycompany.administradorservicios_trag.IAdministradorServicios;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.automovil.AutomovilAgregarDTO;
import dtos.cliente.ClienteAgregarDTO;
import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.insumos.InsumoAgregarDTO;
import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.servicio.ServicioAgregarDTO;
import enums.EstadoClienteNegocios;
import excepciones.NegocioException;
import insumoservicio.InsumoServicioAgregarDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
/**
 *
 * @author 
 */
public class Pruebas_TRAG {

    public static void main(String[] args) {
        
        //Registro de clientes
        IAdministradorClientes administradorClientes = FabricaNegocios.obtenerAdministradorClientes();
        
        try {
            administradorClientes.crearCliente(new ClienteAgregarDTO(
                            "Roberto",
                            "Pérez",
                            "López",
                            "644123456",
                            "robertoperez@gmail.com",
                            EstadoClienteNegocios.HABILITADO)
            );
            administradorClientes.crearCliente(new ClienteAgregarDTO(
                            "Manuel",
                            "Romo",
                            "López",
                            "6443216549",
                            "manuelr@gmail.com", 
                            EstadoClienteNegocios.HABILITADO)
            );
            administradorClientes.crearCliente(new ClienteAgregarDTO(
                            "Ariel",
                            "Borbón",
                            "Izaguirre",
                            "6447894561",
                            "aborbon@gmail.com", 
                            EstadoClienteNegocios.HABILITADO)
            );
            administradorClientes.crearCliente(new ClienteAgregarDTO(
                            "Sebastián",
                            "Bórquez",
                            "Huerta",
                            "6441472589",
                            "borquezsebastian@gmail.com", 
                            EstadoClienteNegocios.HABILITADO)
            );
            
            administradorClientes.crearCliente(new ClienteAgregarDTO(
                            "Yuri",
                            "García",
                            "López",
                            "6447539514",
                            "yurig@gmail.com", 
                            EstadoClienteNegocios.HABILITADO)
            );
            
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
        
        // Registro de automóviles
        IAdministradorAutomoviles administradorAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
        try {
            administradorAutomoviles.crearAutomovil(
                    new AutomovilAgregarDTO(
                            2010,
                            "ABC123",
                            "23424234",
                            "Sahara",
                            "Jeep",
                            2L)
            );
            
            administradorAutomoviles.crearAutomovil(
                    new AutomovilAgregarDTO(
                            2016, 
                            "XYZ098",
                            "93757374",
                            "Sahara",
                            "Jeep", 
                            2L)
            );
            
            administradorAutomoviles.crearAutomovil(
                    new AutomovilAgregarDTO(
                            2021, 
                            "JFH747",
                            "93757374",
                            "March",
                            "Nissan", 
                            3L)
            );
            
            administradorAutomoviles.crearAutomovil(
                    new AutomovilAgregarDTO(
                            2005, 
                            "OEY834",
                            "672342111",
                            "Lobo",
                            "Ford", 
                            4L)
            );
            
            administradorAutomoviles.crearAutomovil(
                    new AutomovilAgregarDTO(
                            2005, 
                            "OEY834",
                            "672342111",
                            "Lobo",
                            "Ford", 
                            4L)
            );
            
            administradorAutomoviles.crearAutomovil(
                    new AutomovilAgregarDTO(
                            2023, 
                            "OEY834",
                            "672342111",
                            "Aveo",
                            "Chrevrolet", 
                            5L)
            );
            
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
       
        
        //Registro de Insumos
        
        IAdministradorInsumos administradorInsumos = FabricaNegocios.obtenerAdministadorInsumos();
        
        // Servicio 1
        try {
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Evaporador",
                            new BigDecimal("1800.00")
                    )
            );
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Válvula de Expansión",
                            new BigDecimal("450.00")
                    )
            );
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Kit de O-Rings (Sellos)",
                            new BigDecimal("15.00")
                    )
            );
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Gas Refrigerante R134a 500gr",
                            new BigDecimal("300.00")
                    )
            );
            
            // Servicio 2
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Aceite PAG 46/100",
                            new BigDecimal("280.00")
                    )
            );
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Sello de Flecha (Mecánico)",
                            new BigDecimal("350.00")
                    )
            );
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Rodamiento de Polea",
                            new BigDecimal("220.00")
                    )
            );
            
            // Servicio 3
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Manguera de Barrera #8 o #10, 1m",
                            new BigDecimal("240.00")
                    )
            );
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Férulas (Casquillos de aluminio)",
                            new BigDecimal("45.00")
                    )
            );
            
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO(
                            "Limpiador de Tubería (Flush)",
                            new BigDecimal("190.00")
                    )
            );
            
            
            
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
        
        //Registro de Servicios
        
        IAdministradorServicios administradorServicios = FabricaNegocios.obtenerAdministradorServicios();
        
        try{
            administradorServicios.crearServicio(new ServicioAgregarDTO(
                            "Cambio de evaporador",
                            "Esta es la intervención de mayor impacto en el cronograma del taller debido al tiempo de desarme"
                                    + " y armado del tablero de instrumentos. El servicio implica la sustitución de la unidad "
                                    + "de enfriamiento interna por pérdida de estanqueidad o saturación térmica irreversible."
                                    + " Operativamente, requiere una gestión de tiempos crítica para no bloquear la bahía de servicio,"
                                    + " asegurando que el reensamble de la consola mantenga los estándares de ajuste originales para "
                                    + "evitar reclamaciones por ruidos o fallos en los controles de ventilación.",
                            new BigDecimal("3500.00"),
                            List.of(
                                    new InsumoServicioAgregarDTO(1, 1L, 1L),
                                     new InsumoServicioAgregarDTO(1, 1L, 2L),
                                      new InsumoServicioAgregarDTO(4, 1L, 3L),
                                       new InsumoServicioAgregarDTO(1, 1l, 4L)
                            )
                    )
            );

            administradorServicios.crearServicio(
                    new ServicioAgregarDTO(
                            "Reparación y Servicio de Compresor",
                            "Este servicio se centra en la recuperación mecánica de la unidad de potencia cuando el daño no ha comprometido"
                                    + " la integridad estructural del cilindro o pistones. La labor consiste en la apertura del componente "
                                    + "para corregir problemas de compresión, ruidos en rodamientos o fallos en el embrague, permitiendo una "
                                    + "solución de costo intermedio frente al reemplazo total. Es una tarea que demanda precisión en el banco"
                                    + " de trabajo y una verificación rigurosa de la limpieza interna para garantizar que la reparación sea "
                                    + "duradera y no contamine el resto del ciclo.",
                            new BigDecimal("1200.00"),
                            List.of(
                                    new InsumoServicioAgregarDTO(1, 2L, 5L),
                                    new InsumoServicioAgregarDTO(1, 2L, 6L),
                                    new InsumoServicioAgregarDTO(1, 2L, 7L),
                                    new InsumoServicioAgregarDTO(2, 2L, 3L)
                            )
                    
                    )
            );

            administradorServicios.crearServicio(
                    new ServicioAgregarDTO(
                            "Reconstrucción de Mangueras y Tuberías",
                            "Este proceso permite la resolución inmediata de fugas en las líneas de conducción de alta y baja presión sin depender "
                                    + "de la disponibilidad de refacciones de agencia. El servicio consiste en el reemplazo de los tramos de caucho "
                                    + "degradados o la reparación de secciones de aluminio impactadas, manteniendo las conexiones y puertos originales. "
                                    + "Para la administración del taller, es una capacidad estratégica que acelera la salida de vehículos con piezas "
                                    + "descontinuadas o de importación lenta, manteniendo el flujo de trabajo constante en el área de carga.",
                            new BigDecimal("800.00"),
                            List.of(
                                    new InsumoServicioAgregarDTO(1, 3L, 8L),
                                    new InsumoServicioAgregarDTO(2, 3L, 9L),
                                    new InsumoServicioAgregarDTO(1, 3L, 10L),
                                    new InsumoServicioAgregarDTO(2, 3L, 3L)
                            )
                    )
            );
            
        } catch(NegocioException e){
            System.out.println(e.getMessage());
        }
        
        // Registro de Ordenes de Trabajo (En estado de Cotización)
        
        IAdministradorOrdenesTrabajo administradorOrdenesTrabajo = FabricaNegocios.obtenerAdministradorOrdenesTrabajo();
        
        try {
            
            administradorOrdenesTrabajo.crearOrdenTrabajo(
                    new OrdenTrabajoCotizacionAgregarDTO(
                            3L, 
                            1L, 
                            new BigDecimal("3500.00"), 
                            "Se detecta una pérdida gradual de presión en el lado de baja, "
                                    + "sin evidencia de fugas en el compartimento del motor tras prueba de "
                                    + "presurización. La presencia de rastro de aceite y olor característico"
                                    + " en las rejillas de ventilación de la cabina confirma porosidad en el "
                                    + "serpentín del evaporador. Se determina la necesidad de desmontaje de "
                                    + "tablero para el reemplazo del componente y evitar la contaminación por"
                                    + " humedad en el resto del ciclo.", 
                            "El vehículo presenta un habitáculo con rastro de humedad persistente y"
                                    + " suciedad acumulada en los ductos de aire. El sistema eléctrico de la consola"
                                    + " central y las ventilas de flujo funcionan correctamente, aunque se observa"
                                    + " un desgaste natural en los clips de sujeción del tablero debido a la antigüedad "
                                    + "del modelo. El soplador (blower) opera sin ruidos extraños, permitiendo la reutilización "
                                    + "del mismo.",
                            LocalDateTime.of(2026, Month.MARCH, 10, 15, 30), 
                            List.of(
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("1800.00"), 1L),
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("450.00"), 2L),
                                    new InsumoCotizacionAgregarDTO(4, new BigDecimal("15.00"), 3L),
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("3000.00"), 4L)
                            )       
                    )        
            );
            
            administradorOrdenesTrabajo.crearOrdenTrabajo(
                    new OrdenTrabajoCotizacionAgregarDTO(
                            5L, 
                            1L,
                            new BigDecimal("1500.00"),
                            "Se identifica una falla en el acoplamiento del embrague electromagnético y ruidos metálicos internos durante la operación."
                                    + " La prueba de rendimiento muestra presiones inestables (oscilaciones en alta), lo que sugiere un desgaste en las "
                                    + "válvulas internas o platos de succión. Se procede a la extracción de la unidad para servicio de banco, ajuste de "
                                    + "tolerancias y sellado, evitando así el gasto de una unidad nueva.",
                            "El motor se encuentra en condiciones operativas estables, sin embargo, se nota una ligera vibración excedente cuando el"
                                    + " compresor intenta entrar en carga. Las bandas de accesorios presentan un desgaste moderado (resecas), pero el"
                                    + " resto de los componentes periféricos del motor no muestran fugas de aceite o refrigerante que interfieran con"
                                    + " la intervención del sistema de aire.",
                            LocalDateTime.of(2026, Month.MARCH, 12, 16, 45), 
                            List.of(
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("280.00"), 5L),
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("350.00"), 6L),
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("220.00"), 7L),
                                    new InsumoCotizacionAgregarDTO(2, new BigDecimal("15.00"), 3L)
                            
                            )
                    )
            );
            
            administradorOrdenesTrabajo.crearOrdenTrabajo(
                    new OrdenTrabajoCotizacionAgregarDTO(
                            6L,
                            3L,
                            new BigDecimal("800.00"),
                            "Localización de fuga activa mediante trazador UV en el cuerpo flexible de la manguera de descarga. Se observa degradación del material "
                                    + "por fatiga térmica y roce con componentes del chasis, lo que ha provocado una pérdida total de la carga. Se diagnostica la "
                                    + "reconstrucción de la línea mediante el reemplazo de la sección de caucho y el reaprovechamiento de los conectores de aluminio"
                                    + " originales para asegurar el sellado.",
                            "El sistema de enfriamiento del motor (radiador y ventiladores) está en buen estado, lo cual es favorable para el intercambio de calor del "
                                    + "condensador. La carrocería presenta los soportes de las líneas de aire íntegros, aunque falta una tolva protectora inferior, lo"
                                    + " que deja las tuberías expuestas a impactos de piedras o desechos del camino, factor que probablemente contribuyó al daño actual.",
                            LocalDateTime.of(2026, Month.MARCH, 14, 10, 0), 
                            List.of(
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("240.00"), 8L),
                                    new InsumoCotizacionAgregarDTO(2, new BigDecimal("45.00"), 9L),
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("190.00"), 10L),
                                    new InsumoCotizacionAgregarDTO(2, new BigDecimal("15.00"), 3L)
                            )
                    )
            );
            
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
     
        
        
    }
}
