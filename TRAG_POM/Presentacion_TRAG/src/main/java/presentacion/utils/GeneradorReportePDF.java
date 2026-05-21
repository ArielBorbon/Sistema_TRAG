package presentacion.utils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dtos.cotizacion.CotizacionResumenDTO;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
/**
 *
 * Archivo: GeneradorReporteePDF.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 252583
 * @author Manuel Romo López - 253080
 * 
 */
public class GeneradorReportePDF {
    
    public static void crearReporteConsolidadoPDF(
            String rutaDestino,
            String filtroCliente,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            String filtroEstado,
            List<CotizacionResumenDTO> cotizacionesActivas,
            List<CotizacionResumenDTO> cotizacionesCanceladas
    ) {
        Document documento = new Document(PageSize.LETTER, 40, 40, 40, 40);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DateTimeFormatter formateadorFiltro = DateTimeFormatter.ofPattern("d 'de' MMMM yyyy", Locale.forLanguageTag("es-ES"));
        
        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(rutaDestino));
            documento.open();

            PdfContentByte canvas = writer.getDirectContentUnder();
            float anchoPagina = PageSize.LETTER.getWidth();
            float altoPagina = PageSize.LETTER.getHeight();

            canvas.setColorFill(new BaseColor(128, 130, 133)); 
            canvas.rectangle(0, altoPagina - 60, anchoPagina, 60); 
            canvas.fill();

            canvas.setColorFill(new BaseColor(54, 54, 54)); 
            canvas.moveTo(anchoPagina + 2, altoPagina + 2); 
            canvas.lineTo(anchoPagina - 110, altoPagina); 
            canvas.lineTo(anchoPagina, altoPagina - 110); 
            canvas.closePath();
            canvas.fill();

            Font fuenteTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            Font fuenteEmpresa = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
            Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
            Font fuenteNegrita = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
            Font fuenteBlanca = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
            Font fuenteSeccion = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(54, 54, 54));

            for(int i = 0; i < 3; i++) { documento.add(new Paragraph("\n")); }

            PdfPTable tablaTitulo = new PdfPTable(1);
            tablaTitulo.setWidthPercentage(45);
            tablaTitulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell celdaTitulo = new PdfPCell(new Phrase("REPORTE DE COTIZACIONES", fuenteTitulo));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setPadding(8);
            celdaTitulo.setBorder(Rectangle.NO_BORDER);
            celdaTitulo.setCellEvent(new GeneradorPDF.BordeRedondeadoEvent(new BaseColor(230, 230, 230), BaseColor.DARK_GRAY));
            tablaTitulo.addCell(celdaTitulo);
            documento.add(tablaTitulo);
            documento.add(new Paragraph("\n"));

            PdfPTable tablaHeader = new PdfPTable(1);
            tablaHeader.setWidthPercentage(100);
            Paragraph datosEmpresa = new Paragraph();
            datosEmpresa.add(new Chunk("REFRIGERACIÓN AUTOMOTRIZ GRANADOS\n", fuenteEmpresa));
            datosEmpresa.add(new Chunk("OTANCAHUI NO. 1701, ESQ. GOLFO DE TEHUANTEPEC\n", fuenteNormal));
            datosEmpresa.add(new Chunk("Teléfono: (55) 644 155 7060 | Email: robertogranados888@gmail.com\n", fuenteNormal));
            PdfPCell celdaEmpresa = new PdfPCell(datosEmpresa);
            celdaEmpresa.setBorder(Rectangle.NO_BORDER);
            tablaHeader.addCell(celdaEmpresa);
            documento.add(tablaHeader);
            documento.add(new Paragraph("\n"));

            PdfPTable tablaDatosFiltros = new PdfPTable(2);
            tablaDatosFiltros.setWidthPercentage(100);
            tablaDatosFiltros.setWidths(new float[]{18f, 82f});

            String fInicioStr = (fechaInicio != null) ? fechaInicio.format(formateadorFiltro) : "Siempre";
            String fFinStr = (fechaFin != null) ? fechaFin.format(formateadorFiltro) : "Siempre";
            String clienteStr = (filtroCliente != null && !filtroCliente.trim().isEmpty()) ? filtroCliente : "Todos los clientes";

            agregarCelda(tablaDatosFiltros, "CLIENTE:", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatosFiltros, clienteStr, fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatosFiltros, "FECHA INICIO:", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatosFiltros, fInicioStr, fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatosFiltros, "FECHA FIN:", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatosFiltros, fFinStr, fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatosFiltros, "ESTADO FILTRO:", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatosFiltros, filtroEstado.toUpperCase(), fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);

            documento.add(tablaDatosFiltros);
            documento.add(new Paragraph("\n"));

            BigDecimal dineroActivas = BigDecimal.ZERO;
            BigDecimal dineroCanceladas = BigDecimal.ZERO;

            BaseColor colorHeaderTabla = new BaseColor(100, 100, 100);

            if (filtroEstado.equalsIgnoreCase("Todos") || filtroEstado.equalsIgnoreCase("ACTIVA") || filtroEstado.equalsIgnoreCase("Habilitadas")) {
                documento.add(new Paragraph("Cotizaciones Habilitadas (" + cotizacionesActivas.size() + " encontradas)", fuenteSeccion));
                documento.add(new Paragraph("\n"));

                if (cotizacionesActivas.isEmpty()) {
                    documento.add(new Paragraph("No se registraron cotizaciones habilitadas bajo los filtros seleccionados.\n\n", fuenteNormal));
                } else {
                    PdfPTable tablaActivas = crearEstructuraTablaReporte();
                    crearEncabezadoTabla(tablaActivas, colorHeaderTabla, fuenteBlanca);

                    for (CotizacionResumenDTO c : cotizacionesActivas) {
                        String clienteNombre = c.getNombreCliente() + " " + (c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente() : "");
                        String vehiculo = c.getMarcaAutomovil() + " " + c.getModeloAutomovil();
                        String fCreacion = (c.getFechaCreacion() != null) ? c.getFechaCreacion().toLocalDate().toString() : "N/A";
                        BigDecimal monto = (c.getPrecioTotal() != null) ? c.getPrecioTotal() : BigDecimal.ZERO;
                        dineroActivas = dineroActivas.add(monto);

                        agregarFilaDatos(tablaActivas, fCreacion, clienteNombre, vehiculo, "$" + df.format(monto), fuenteNormal);
                    }
                    documento.add(tablaActivas);
                    
                    // Subtotal financiero de la sección
                    Paragraph pSubActivas = new Paragraph("Total Generado (Habilitadas): $" + df.format(dineroActivas), fuenteNegrita);
                    pSubActivas.setAlignment(Element.ALIGN_RIGHT);
                    documento.add(pSubActivas);
                    documento.add(new Paragraph("\n"));
                }
            }

            // ==========================================
            // SECCIÓN 2: COTIZACIONES CANCELADAS
            // ==========================================
            if (filtroEstado.equalsIgnoreCase("Todos") || filtroEstado.equalsIgnoreCase("CANCELADA") || filtroEstado.equalsIgnoreCase("Canceladas")) {
                documento.add(new Paragraph("Cotizaciones Canceladas (" + cotizacionesCanceladas.size() + " encontradas)", fuenteSeccion));
                documento.add(new Paragraph("\n"));

                if (cotizacionesCanceladas.isEmpty()) {
                    documento.add(new Paragraph("No se registraron cotizaciones canceladas bajo los filtros seleccionados.\n\n", fuenteNormal));
                } else {
                    PdfPTable tablaCanceladas = crearEstructuraTablaReporte();
                    crearEncabezadoTabla(tablaCanceladas, colorHeaderTabla, fuenteBlanca);

                    for (CotizacionResumenDTO c : cotizacionesCanceladas) {
                        String clienteNombre = c.getNombreCliente() + " " + (c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente() : "");
                        String vehiculo = c.getMarcaAutomovil() + " " + c.getModeloAutomovil();
                        String fCreacion = (c.getFechaCreacion() != null) ? c.getFechaCreacion().toLocalDate().toString() : "N/A";
                        BigDecimal monto = (c.getPrecioTotal() != null) ? c.getPrecioTotal() : BigDecimal.ZERO;
                        dineroCanceladas = dineroCanceladas.add(monto);

                        agregarFilaDatos(tablaCanceladas, fCreacion, clienteNombre, vehiculo, "$" + df.format(monto), fuenteNormal);
                    }
                    documento.add(tablaCanceladas);
                    
                    // Subtotal financiero de la sección
                    Paragraph pSubCanceladas = new Paragraph("Total Dejado de Percibir (Canceladas): $" + df.format(dineroCanceladas), fuenteNegrita);
                    pSubCanceladas.setAlignment(Element.ALIGN_RIGHT);
                    documento.add(pSubCanceladas);
                    documento.add(new Paragraph("\n"));
                }
            }

            // ==========================================
            // BLOQUE RESUMEN FINAL DE TOTALES
            // ==========================================
            documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------", fuenteNormal));
            PdfPTable tablaTotalesFinales = new PdfPTable(2);
            tablaTotalesFinales.setWidthPercentage(40);
            tablaTotalesFinales.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaTotalesFinales.setWidths(new float[]{60f, 40f});
            
            BaseColor grisFondoTotales = new BaseColor(240, 240, 240);
            BigDecimal granTotalConsolidado = dineroActivas.add(dineroCanceladas);

            agregarCelda(tablaTotalesFinales, "Total Habilitadas:", fuenteNegrita, Element.ALIGN_RIGHT, grisFondoTotales);
            agregarCelda(tablaTotalesFinales, "$" + df.format(dineroActivas), fuenteNormal, Element.ALIGN_RIGHT, grisFondoTotales);
            
            agregarCelda(tablaTotalesFinales, "Total Canceladas:", fuenteNegrita, Element.ALIGN_RIGHT, grisFondoTotales);
            agregarCelda(tablaTotalesFinales, "$" + df.format(dineroCanceladas), fuenteNormal, Element.ALIGN_RIGHT, grisFondoTotales);
            
            agregarCelda(tablaTotalesFinales, "GRAND TOTAL:", fuenteNegrita, Element.ALIGN_RIGHT, new BaseColor(220, 220, 220));
            agregarCelda(tablaTotalesFinales, "$" + df.format(granTotalConsolidado), fuenteNegrita, Element.ALIGN_RIGHT, new BaseColor(220, 220, 220));

            documento.add(tablaTotalesFinales);
            documento.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static PdfPTable crearEstructuraTablaReporte() throws DocumentException {
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{15f, 35f, 30f, 20f});
        return tabla;
    }

    private static void crearEncabezadoTabla(PdfPTable tabla, BaseColor colorFondo, Font fuente) {
        agregarCelda(tabla, "Fecha", fuente, Element.ALIGN_CENTER, colorFondo);
        agregarCelda(tabla, "Cliente", fuente, Element.ALIGN_LEFT, colorFondo);
        agregarCelda(tabla, "Vehículo", fuente, Element.ALIGN_LEFT, colorFondo);
        agregarCelda(tabla, "Monto Total", fuente, Element.ALIGN_RIGHT, colorFondo);
    }

    private static void agregarFilaDatos(PdfPTable tabla, String fecha, String cliente, String vehiculo, String monto, Font fuente) {
        agregarCelda(tabla, fecha, fuente, Element.ALIGN_CENTER, BaseColor.WHITE);
        agregarCelda(tabla, cliente, fuente, Element.ALIGN_LEFT, BaseColor.WHITE);
        agregarCelda(tabla, vehiculo, fuente, Element.ALIGN_LEFT, BaseColor.WHITE);
        agregarCelda(tabla, monto, fuente, Element.ALIGN_RIGHT, BaseColor.WHITE);
    }

    private static void agregarCelda(PdfPTable tabla, String texto, Font fuente, int alineacion, BaseColor colorFondo) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(alineacion);
        celda.setBackgroundColor(colorFondo);
        celda.setPaddingTop(6);
        celda.setPaddingBottom(6);
        celda.setPaddingLeft(5);
        celda.setPaddingRight(5);
        tabla.addCell(celda);
    }
    
}