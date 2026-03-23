

package com.mycompany.pruebas_trag;

import com.mycompany.administradorautomoviles_trag.IAdministradorAutomoviles;
import com.mycompany.administradorclientes_trag.IAdministradorClientes;
import com.mycompany.administradorcotizaciones_trag.IAdministradorCotizaciones;
import com.mycompany.administradorinsumos_trag.IAdministradorInsumos;
import com.mycompany.administradorordenestrabajo.IAdministradorOrdenesTrabajo;
import com.mycompany.administradorservicios_trag.IAdministradorServicios;
import com.mycompany.negocios_trag.FabricaNegocios;
import dtos.automovil.AutomovilAgregarDTO;
import dtos.automovil.AutomovilResumenDTO;
import dtos.cliente.ClienteAgregarDTO;
import dtos.cliente.ClienteResumenDTO;
import dtos.cotizacion.CotizacionActualizarDTO;
import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionActualizarDTO;
import dtos.insumocotizacion.InsumoCotizacionAgregarDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import dtos.insumos.InsumoAgregarDTO;
import dtos.insumos.InsumoResumenDTO;
import dtos.ordentrabajo.OrdenTrabajoCotizacionAgregarDTO;
import dtos.servicio.ServicioAgregarDTO;
import dtos.servicio.ServicioResumenDTO;
import enums.EstadoClienteNegocios;
import excepciones.NegocioException;
import insumoservicio.InsumoServicioAgregarDTO;
import insumoservicio.InsumoServicioDetalleDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * Archivo: Pruebas_TRAG.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class Pruebas_TRAG {

    public static void main(String[] args) {
        

        IAdministradorClientes administradorClientes = FabricaNegocios.obtenerAdministradorClientes();
        IAdministradorAutomoviles administradorAutomoviles = FabricaNegocios.obtenerAdministradorAutomoviles();
        IAdministradorInsumos administradorInsumos = FabricaNegocios.obtenerAdministadorInsumos();
        IAdministradorServicios administradorServicios = FabricaNegocios.obtenerAdministradorServicios();
        IAdministradorOrdenesTrabajo administradorOrdenesTrabajo = FabricaNegocios.obtenerAdministradorOrdenesTrabajo();
        IAdministradorCotizaciones administradorCotizaciones = FabricaNegocios.obtenerAdministradorCotizaciones();
        
        //Registro de clientes
        
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

        // Servicio 1
        try {

            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Evaporador", new BigDecimal("1800.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Válvula de Expansión", new BigDecimal("450.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Kit de O-Rings (Sellos)", new BigDecimal("15.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Lata Gas Refrigerante R134a 500gr", new BigDecimal("300.00"))
            );

            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Bote Aceite PAG 46/100 250ml", new BigDecimal("280.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Sello de Flecha (Mecánico)", new BigDecimal("350.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Rodamiento de Polea", new BigDecimal("220.00"))
            );

            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Tramo Manguera de Barrera #8 o #10 (1m)", new BigDecimal("240.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Férulas (Casquillos de aluminio)", new BigDecimal("45.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Bote Limpiador de Tubería Flush", new BigDecimal("190.00"))
            );

            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Filtro Deshidratador", new BigDecimal("550.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Filtro de Cabina (Habitáculo)", new BigDecimal("250.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Ampolleta de Tinte Detector UV", new BigDecimal("80.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Bote Solvente Flush Especializado 1L", new BigDecimal("210.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Relevador Automotriz A/C de 4/5 pines", new BigDecimal("120.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Fusible Automotriz (varios amperajes)", new BigDecimal("10.00"))
            );
            administradorInsumos.crearInsumo(
                    new InsumoAgregarDTO("Lata de Espuma Sanitizante / Ozono", new BigDecimal("180.00"))
            );
            
            
            
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
        
        //Registro de Servicios
       
        
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
                            "servicio_cambio_evaporador_icono.png",
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
                            "servicio_reparacion_servicio_compresor_icono.png",
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
                            "servicio_recontruccion_tuberias_mangueras_icono.png",
                            List.of(
                                    new InsumoServicioAgregarDTO(1, 3L, 8L),
                                    new InsumoServicioAgregarDTO(2, 3L, 9L),
                                    new InsumoServicioAgregarDTO(1, 3L, 10L),
                                    new InsumoServicioAgregarDTO(2, 3L, 3L)
                            )
                    )
            );
            
            administradorServicios.crearServicio(
                    new ServicioAgregarDTO(
                    "Servicio General",
                    "Mantenimiento preventivo completo del sistema frigorífico. Consiste en la inspección de líneas, "
                            + "limpieza de condensador, revisión de presiones, reemplazo del filtro deshidratador y sustitución "
                            + "de empaques esenciales. Su objetivo es preservar la vida útil del sistema y evitar fallas críticas.",
                    new BigDecimal("1500.00"),
                    "servicio_general_icono.png",
                    List.of(
                            new InsumoServicioAgregarDTO(1, 4L, 11L), // 1 Filtro Deshidratador
                            new InsumoServicioAgregarDTO(3, 4L, 3L)   // 3 O-Rings
                    )
            ));


            administradorServicios.crearServicio(
                    new ServicioAgregarDTO(
                    "Recarga de Gas Refrigerante",
                    "Servicio enfocado a recuperar la eficiencia térmica inyectando el fluido refrigerante exacto "
                            + "según las especificaciones técnicas del fabricante. Incluye estabilización de aceite del sistema.",
                    new BigDecimal("600.00"),
                    "servicio_recarga_gas_refrigerante_icono.png",
                    List.of(
                            new InsumoServicioAgregarDTO(2, 5L, 4L),  // 2 Latas de Gas de 500gr
                            new InsumoServicioAgregarDTO(1, 5L, 5L)   // 1 Bote de Aceite PAG
                    )
            ));

            administradorServicios.crearServicio(
                    new ServicioAgregarDTO(
                    "Cambio de Filtro de Cabina",
                    "Reemplazo del elemento filtrante interior, vital para mantener el flujo de aire libre de "
                            + "partículas, polen, polvo y evitar obstrucciones mecánicas en el flujo hacia el evaporador.",
                    new BigDecimal("350.00"),
                    "servicio_cambio_filtro_cabina_icono.png",
                    List.of(
                            new InsumoServicioAgregarDTO(1, 6L, 12L)  // 1 Filtro de Cabina
                    )
            ));


            administradorServicios.crearServicio(
                    new ServicioAgregarDTO(
                    "Detección de Fugas (Nitrógeno y UV)",
                    "Diagnóstico especializado para localizar microporosidades. Se presuriza el circuito con nitrógeno "
                            + "y se utiliza una ampolleta de tinte trazador UV para revelar fugas bajo luz ultravioleta.",
                    new BigDecimal("450.00"),
                    "servicio_deteccion_fugas_nitrogeno_uv_icono.png",
                    List.of(
                            new InsumoServicioAgregarDTO(1, 7L, 13L)  // 1 Ampolleta UV
                    )
            ));

            administradorServicios.crearServicio(
                    new ServicioAgregarDTO(
                    "Lavado Interno del Sistema",
                    "Remoción de humedad, virutas metálicas y aceite degradado tras una falla catastrófica de componentes. "
                            + "Utiliza solventes a presión para garantizar que el nuevo compresor no se contamine.",
                    new BigDecimal("950.00"),
                    "servicio_lavado_interno_sistema_icono.png",
                    List.of(
                            new InsumoServicioAgregarDTO(2, 8L, 14L), // 2 Botes Solvente Flush
                            new InsumoServicioAgregarDTO(4, 8L, 3L)   // 4 O-Rings nuevos
                    )
            ));

            // 9. Diagnóstico Eléctrico del A/C
            administradorServicios.crearServicio(new ServicioAgregarDTO(
                    "Diagnóstico Eléctrico del A/C",
                    "Evaluación de la red de control: sensores de presión, termistores, cableado, relevadores "
                            + "y actuadores. Ideal para fallas donde el sistema está cargado pero no realiza el acoplamiento.",
                    new BigDecimal("500.00"),
                    "servicio_diagnostico_electrico_ac_icono.png",
                    List.of(
                            new InsumoServicioAgregarDTO(1, 9L, 15L), // 1 Relevador (por si necesita cambio)
                            new InsumoServicioAgregarDTO(2, 9L, 16L)  // 2 Fusibles
                    )
            ));

            // 10. Sanitización de Ductos y Tratamiento de Ozono
            administradorServicios.crearServicio(new ServicioAgregarDTO(
                    "Sanitización de Ductos y Tratamiento de Ozono",
                    "Erradicación de bacterias, esporas y malos olores en el circuito de ventilación interior "
                            + "utilizando una combinación de espuma activa y tratamiento con gas ozono.",
                    new BigDecimal("400.00"),
                    "servicio_sanitizacion_ductos_tratamiento_ozon_icono.png",
                    List.of(
                            new InsumoServicioAgregarDTO(1, 10L, 17L) // 1 Lata Sanitizante
                    )
            ));
            
        } catch(NegocioException e){
            System.out.println(e.getMessage());
        }
        
        // Registro de Ordenes de Trabajo (En estado de Cotización)
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
                            5L,
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
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("300.00"), 8L),
                                    new InsumoCotizacionAgregarDTO(2, new BigDecimal("50.00"), 9L),
                                    new InsumoCotizacionAgregarDTO(1, new BigDecimal("210.00"), 10L),
                                    new InsumoCotizacionAgregarDTO(2, new BigDecimal("17.00"), 3L)
                            )
                    )
            );
            
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
        
        
        

        ClienteResumenDTO clientePrueba = null;
        try {
            // Consultar Clientes:
            System.out.println("-".repeat(50));
            List<ClienteResumenDTO> clientes = administradorClientes.obtenerTodosClientes();
            
            System.out.println("\nClientes registrados: ");
            for(ClienteResumenDTO cliente: clientes){
                if(cliente.getId() == 2L) clientePrueba = cliente;
                System.out.println(cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
            }
            
        } catch (NegocioException e) {
            
            System.out.println(e.getCause().getCause());
        }
        
        
        // Consultar automoviles de un cliente
        System.out.println("-".repeat(50));
        System.out.println("\nConsultar automóviles del cliente 2: ");
        try{ 
            
            List<AutomovilResumenDTO> automovilesCliente = administradorClientes.obtenerCliente(clientePrueba.getId()).getAutomoviles();
            
            for(AutomovilResumenDTO automovil: automovilesCliente){
                System.out.println(automovil.getMarca() + ", " + automovil.getModelo() + ", " + automovil.getAnio());
            }
            
            
        } catch (NegocioException e) {
            System.out.println(e);
        }
    
        // Consulta de servicios
        System.out.println("-".repeat(50));
        System.out.println("\nConsulta de servicios:");
        
        ServicioResumenDTO servicioEjemplo = null;
        List<ServicioResumenDTO> servicios;
        try {
            servicios = administradorServicios.obtenerTodosServicios();
            for(ServicioResumenDTO servicio: servicios){
                if(servicio.getId() == 3L) servicioEjemplo = servicio;
                System.out.println(servicio.getNombre());
            }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        // Consulta de insumos de servicio
        System.out.println("-".repeat(50));
        System.out.println("\nConsulta de insumos de servicio 3: ");
        
        try {
            List<InsumoServicioDetalleDTO> insumosServicio = administradorServicios.obtenerServicio(servicioEjemplo.getId()).getInsumosServicio();
            
            for(InsumoServicioDetalleDTO insumoServicio: insumosServicio){
                
                System.out.println(
                        insumoServicio.getId() + 
                        ", " + insumoServicio.getInsumo().getNombre() +
                                ", " + insumoServicio.getInsumo().getPrecioSugerido()  +
                                ", " + insumoServicio.getCantidadDefault() + 
                                ", " + insumoServicio.getSubtotal());
            }
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        // Consulta de insumos de cotización
        System.out.println("-".repeat(50));
        System.out.println("\nConsulta de insumos de cotización 3");
        
        try {
            CotizacionDetalleDTO cotizacion = administradorCotizaciones.obtenerCotizacion(3L);
            
            List<InsumoCotizacionDetalleDTO> insumosCotizacion = cotizacion.getInsumosCotizacion();
            
            for(InsumoCotizacionDetalleDTO insumoCotizacion: insumosCotizacion){
                System.out.println(
                        insumoCotizacion.getId() +
                                "," + insumoCotizacion.getInsumo().getNombre() +
                                ", " + insumoCotizacion.getPrecio()  +
                                ", " + insumoCotizacion.getCantidadRequerida() + 
                                ", " + insumoCotizacion.getSubtotal());
            }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        // Consulta de cotización
        System.out.println("-".repeat(50));
        System.out.println("\nConsulta de cotización 3 antes de actualizar");
        
        try {
            CotizacionDetalleDTO cotizacion = administradorCotizaciones.obtenerCotizacion(3L);
            
            System.out.println("ID: " + cotizacion.getId());
            System.out.println("Estado automovil: " + cotizacion.getEstadoAutomovil());
            System.out.println("Diagnóstico general: " + cotizacion.getDiagnosticoGeneral());
            System.out.println("Fecha: " + cotizacion.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Precio mano de obra: " + cotizacion.getPrecioManoObra());
            System.out.println("Insumos en cotización: ");
            List<InsumoCotizacionDetalleDTO> insumosCotizacion = cotizacion.getInsumosCotizacion();
            
            for(InsumoCotizacionDetalleDTO insumoCotizacion: insumosCotizacion){
                System.out.println(
                        insumoCotizacion.getId() +
                                "," + insumoCotizacion.getInsumo().getNombre() +
                                ", " + insumoCotizacion.getPrecio()  +
                                ", " + insumoCotizacion.getCantidadRequerida() + 
                                ", " + insumoCotizacion.getSubtotal());
            }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        // Actualziación de cotización
        System.out.println("-".repeat(50));
        System.out.println("\nActualización de Cotización 3");
        
        try {
            administradorCotizaciones.actualizarCotizacion(
                    new CotizacionActualizarDTO(
                            3L,
                            new BigDecimal("1000.00"),
                            "Localización de fuga activa mediante trazador UV en el cuerpo flexible de la manguera de descarga. Se observa degradación del material "
                                    + "por fatiga térmica y roce con componentes del chasis, lo que ha provocado una pérdida total de la carga. Se diagnostica la "
                                    + "reconstrucción de la línea mediante el reemplazo de la sección de caucho y el reaprovechamiento de los conectores de aluminio"
                                    + " originales para asegurar el sellado.",
                            "El sistema de enfriamiento del motor (radiador y ventiladores) está en buen estado, lo cual es favorable para el intercambio de calor del "
                                    + "condensador. La carrocería presenta los soportes de las líneas de aire íntegros, aunque falta una tolva protectora inferior, lo"
                                    + " que deja las tuberías expuestas a impactos de piedras o desechos del camino, factor que probablemente contribuyó al daño actual.",
                            List.of(
                                    new InsumoCotizacionActualizarDTO(1, new BigDecimal("400.00"), 3L, 8L),
                                    new InsumoCotizacionActualizarDTO(3, new BigDecimal("210.00"), 3L, 10L),
                                    new InsumoCotizacionActualizarDTO(2, new BigDecimal("20.00"), 3L, 3L)
                            )
                    )
            );
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        
        System.out.println("\nConsulta de cotización 3 actualizada");
        
        try {
            CotizacionDetalleDTO cotizacion = administradorCotizaciones.obtenerCotizacion(3L);
            
            System.out.println("ID: " + cotizacion.getId());
            System.out.println("Estado automovil: " + cotizacion.getEstadoAutomovil());
            System.out.println("Diagnóstico general: " + cotizacion.getDiagnosticoGeneral());
            System.out.println("Fecha: " + cotizacion.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Precio mano de obra: " + cotizacion.getPrecioManoObra());
            System.out.println("Insumos en cotización: ");
            List<InsumoCotizacionDetalleDTO> insumosCotizacion = cotizacion.getInsumosCotizacion();
            
            for(InsumoCotizacionDetalleDTO insumoCotizacion: insumosCotizacion){
                System.out.println(
                        insumoCotizacion.getId() +
                                "," + insumoCotizacion.getInsumo().getNombre() +
                                ", " + insumoCotizacion.getPrecio()  +
                                ", " + insumoCotizacion.getCantidadRequerida() + 
                                ", " + insumoCotizacion.getSubtotal());
            }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
    
        System.out.println("-".repeat(50));
        // Cancelación de cotización
        System.out.println("\nCancelar Cotización");
        
        try {
            administradorCotizaciones.eliminarCotizacion(2L);
            
            System.out.println("\nCotizaciones después de cancela r la Cotización 2: ");
            List<CotizacionResumenDTO> cotizaciones = administradorCotizaciones.obtenerTodasCotizaciones();

            for(CotizacionResumenDTO cotizacion: cotizaciones){
                System.out.println("\nCliente: " + cotizacion.getNombreCliente() + " " + cotizacion.getApellidoPaternoCliente());
                System.out.println("Automóvil: " + cotizacion.getMarcaAutomovil() + " " + cotizacion.getModeloAutomovil() + " " + cotizacion.getAnioAutomovil());
                System.out.println("Fecha: " + cotizacion.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Total: " + cotizacion.getPrecioTotal());
                System.out.println("Estado: " + cotizacion.getEstadoCotizacion());
            }
        
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        
        // Consultas por nombre
        
        System.out.println("-".repeat(50));

        // Consulta de cotización por nombre de cliente
        System.out.println("\nConsultar Cotización por nombre de cliente");
        
        try {
            
            List<CotizacionResumenDTO> cotizacionesNombre = administradorCotizaciones.obtenerCotizacionesNombre("Yuri");
            
            for(CotizacionResumenDTO cotizacionNombre: cotizacionesNombre){
                
                System.out.println("\nCliente: " + cotizacionNombre.getNombreCliente() + " " + cotizacionNombre.getApellidoPaternoCliente());
                System.out.println("Automóvil: " + cotizacionNombre.getMarcaAutomovil() + " " + cotizacionNombre.getModeloAutomovil() + " " + cotizacionNombre.getAnioAutomovil());
                System.out.println("Fecha: " + cotizacionNombre.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Total: " + cotizacionNombre.getPrecioTotal());
                System.out.println("Estado: " + cotizacionNombre.getEstadoCotizacion());
                
            }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        System.out.println("-".repeat(50));

        // Consulta de cotización por nombre de cliente
        System.out.println("\nConsultar Cotización por rango de fechas");
        try {
            LocalDateTime fechaInicio = LocalDateTime.of(2026, 3, 12, 0, 0, 0);
            LocalDateTime fechaFin = LocalDateTime.of(2026, 3, 18, 23, 59, 59);
            
            List<CotizacionResumenDTO> cotizacionesNombre = administradorCotizaciones.obtenerCotizacionesFecha(fechaInicio, fechaFin);
            
            for(CotizacionResumenDTO cotizacionNombre: cotizacionesNombre){
                
                System.out.println("\nCliente: " + cotizacionNombre.getNombreCliente() + " " + cotizacionNombre.getApellidoPaternoCliente());
                System.out.println("Automóvil: " + cotizacionNombre.getMarcaAutomovil() + " " + cotizacionNombre.getModeloAutomovil() + " " + cotizacionNombre.getAnioAutomovil());
                System.out.println("Fecha: " + cotizacionNombre.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Total: " + cotizacionNombre.getPrecioTotal());
                System.out.println("Estado: " + cotizacionNombre.getEstadoCotizacion());
                
            }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        System.out.println("-".repeat(50));
        
        // Consulta de Insumos por nombre
        System.out.println("\nConsultar Insumos por nombre");

        List<InsumoResumenDTO> insumosNombre;
        try {
            insumosNombre = administradorInsumos.obtenerInsumosNombre("Manguera");
            
            for(InsumoResumenDTO insumoNombre: insumosNombre){
            System.out.println("Nombre: " + insumoNombre.getNombre());
            System.out.println("Precio sugerido: " + insumoNombre.getPrecioSugerido());
        }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
        
        System.out.println("-".repeat(50));
        
        // Consulta de Servicios por nombre
        System.out.println("\nConsultar Servicios por nombre");
        
        try {
            List<ServicioResumenDTO> serviciosNombre = administradorServicios.obtenerServiciosNombre("Evaporador");
            for(ServicioResumenDTO servicio: serviciosNombre){
                System.out.println(servicio.getNombre());
            }
            
        } catch (NegocioException e) {
            System.out.println(e.getCause().getCause());
        }
        
    }

}
