package presentacion.utils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dtos.cotizacion.CotizacionResumenDTO;
import java.awt.Color;
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
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

            Font fuenteTituloEncabezado = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            Font fuenteFiltrosEtiqueta = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            Font fuenteFiltrosNegrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
            
            Font fuenteVerdeStatus = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, new BaseColor(34, 139, 34));
            Font fuenteRojoStatus = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, new BaseColor(220, 20, 60));
            
            Font fuenteTableHeader = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
            Font fuenteCeldaNormal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
            Font fuenteCeldaNegrita = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);

            for (int i = 0; i < 3; i++) { documento.add(new Paragraph("\n")); }

            PdfPTable tablaTitulo = new PdfPTable(1);
            tablaTitulo.setWidthPercentage(30);
            tablaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell celdaTitulo = new PdfPCell(new Phrase("Cotizaciones", fuenteTituloEncabezado));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setPadding(10);
            celdaTitulo.setBorder(Rectangle.NO_BORDER);
            celdaTitulo.setCellEvent(new GeneradorPDF.BordeRedondeadoEvent(new BaseColor(245, 245, 245), BaseColor.DARK_GRAY));
            tablaTitulo.addCell(celdaTitulo);
            documento.add(tablaTitulo);
            
            documento.add(new Paragraph("\n"));

            String textCliente = (filtroCliente != null && !filtroCliente.trim().isEmpty()) ? filtroCliente : "Todos";
            String textInicio = (fechaInicio != null) ? fechaInicio.format(formateador) : "Siempre";
            String textFin = (fechaFin != null) ? fechaFin.format(formateador) : "Siempre";
            String textEstado = filtroEstado.equalsIgnoreCase("ACTIVA") ? "Habilitadas" : (filtroEstado.equalsIgnoreCase("CANCELADA") ? "Canceladas" : "Todos");

            Paragraph pFiltros = new Paragraph();
            pFiltros.setIndentationLeft(30);
            pFiltros.setLeading(18f);
            
            pFiltros.add(new Chunk("Cliente: ", fuenteFiltrosEtiqueta));
            pFiltros.add(new Chunk(textCliente + "\n", fuenteFiltrosNegrita));
            pFiltros.add(new Chunk("Fecha de inicio: ", fuenteFiltrosEtiqueta));
            pFiltros.add(new Chunk(textInicio + "\n", fuenteFiltrosNegrita));
            pFiltros.add(new Chunk("Fecha fin: ", fuenteFiltrosEtiqueta));
            pFiltros.add(new Chunk(textFin + "\n", fuenteFiltrosNegrita));
            pFiltros.add(new Chunk("Estado: ", fuenteFiltrosEtiqueta));
            pFiltros.add(new Chunk(textEstado + "\n", fuenteFiltrosNegrita));
            documento.add(pFiltros);

            documento.add(new Paragraph("\n"));

            BigDecimal totalHabilitadas = cotizacionesActivas.stream().map(c -> c.getPrecioTotal() != null ? c.getPrecioTotal() : BigDecimal.ZERO).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalCanceladas = cotizacionesCanceladas.stream().map(c -> c.getPrecioTotal() != null ? c.getPrecioTotal() : BigDecimal.ZERO).reduce(BigDecimal.ZERO, BigDecimal::add);
            
            BigDecimal granTotalTodo = BigDecimal.ZERO;
            if (filtroEstado.equalsIgnoreCase("Todos")) {
                granTotalTodo = totalHabilitadas.add(totalCanceladas);
            } else if (filtroEstado.equalsIgnoreCase("ACTIVA")) {
                granTotalTodo = totalHabilitadas;
            } else if (filtroEstado.equalsIgnoreCase("CANCELADA")) {
                granTotalTodo = totalCanceladas;
            }

            BaseColor colorGrisEncabezado = new BaseColor(110, 110, 110);

            if (filtroEstado.equalsIgnoreCase("Todos") || filtroEstado.equalsIgnoreCase("ACTIVA")) {
                Paragraph tituloActivas = new Paragraph("No Canceladas: " + cotizacionesActivas.size(), fuenteVerdeStatus);
                tituloActivas.setIndentationLeft(10);
                documento.add(tituloActivas);
                documento.add(new Paragraph("\n"));

                if (cotizacionesActivas.isEmpty()) {
                    Paragraph vacio = new Paragraph("   No se encontraron registros activos.", fuenteCeldaNormal);
                    documento.add(vacio);
                } else {
                    PdfPTable tablaA = crearTablaBaseReporte();
                    inicializarHeadersReporte(tablaA, colorGrisEncabezado, fuenteTableHeader);

                    for (CotizacionResumenDTO c : cotizacionesActivas) {
                        String f = c.getFechaCreacion() != null ? c.getFechaCreacion().format(formateador) : "N/A";
                        String cl = c.getNombreCliente() + " " + (c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente() : "");
                        String v = c.getMarcaAutomovil() + " " + c.getModeloAutomovil();
                        String servicio = "Servicio de Refrigeración"; 

                        llenarFilaReporte(tablaA, f, cl, v, servicio, fuenteCeldaNormal);
                    }
                    documento.add(tablaA);
                }
                documento.add(new Paragraph("\n\n"));
            }

            if (filtroEstado.equalsIgnoreCase("Todos") || filtroEstado.equalsIgnoreCase("CANCELADA")) {
                Paragraph tituloCanceladas = new Paragraph("Canceladas: " + cotizacionesCanceladas.size(), fuenteRojoStatus);
                tituloCanceladas.setIndentationLeft(10);
                documento.add(tituloCanceladas);
                documento.add(new Paragraph("\n"));

                if (cotizacionesCanceladas.isEmpty()) {
                    Paragraph vacio = new Paragraph("   No se encontraron registros cancelados.", fuenteCeldaNormal);
                    documento.add(vacio);
                } else {
                    PdfPTable tablaC = crearTablaBaseReporte();
                    inicializarHeadersReporte(tablaC, colorGrisEncabezado, fuenteTableHeader);

                    for (CotizacionResumenDTO c : cotizacionesCanceladas) {
                        String f = c.getFechaCreacion() != null ? c.getFechaCreacion().format(formateador) : "N/A";
                        String cl = c.getNombreCliente() + " " + (c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente() : "");
                        String v = c.getMarcaAutomovil() + " " + c.getModeloAutomovil();
                        String servicio = "Servicio Cancelado";

                        llenarFilaReporte(tablaC, f, cl, v, servicio, fuenteCeldaNormal);
                    }
                    documento.add(tablaC);
                }
                documento.add(new Paragraph("\n\n"));
            }

            PdfPTable tablaCierreOkey = new PdfPTable(2);
            tablaCierreOkey.setWidthPercentage(40);
            tablaCierreOkey.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaCierreOkey.setWidths(new float[]{60f, 40f});

            BaseColor grisFondoTotales = new BaseColor(242, 242, 242);

            if (filtroEstado.equalsIgnoreCase("Todos") || filtroEstado.equalsIgnoreCase("ACTIVA")) {
                agregarCeldaEspecial(tablaCierreOkey, "Total Habilitadas:", fuenteCeldaNegrita, Element.ALIGN_RIGHT, grisFondoTotales);
                agregarCeldaEspecial(tablaCierreOkey, "$" + df.format(totalHabilitadas), fuenteCeldaNormal, Element.ALIGN_RIGHT, grisFondoTotales);
            } else {
                agregarCeldaEspecial(tablaCierreOkey, "Total Habilitadas:", fuenteCeldaNegrita, Element.ALIGN_RIGHT, grisFondoTotales);
                agregarCeldaEspecial(tablaCierreOkey, "", fuenteCeldaNormal, Element.ALIGN_RIGHT, grisFondoTotales);
            }

            if (filtroEstado.equalsIgnoreCase("Todos") || filtroEstado.equalsIgnoreCase("CANCELADA")) {
                agregarCeldaEspecial(tablaCierreOkey, "Total Canceladas:", fuenteCeldaNegrita, Element.ALIGN_RIGHT, grisFondoTotales);
                agregarCeldaEspecial(tablaCierreOkey, "$" + df.format(totalCanceladas), fuenteCeldaNormal, Element.ALIGN_RIGHT, grisFondoTotales);
            } else {
                agregarCeldaEspecial(tablaCierreOkey, "Total Canceladas:", fuenteCeldaNegrita, Element.ALIGN_RIGHT, grisFondoTotales);
                agregarCeldaEspecial(tablaCierreOkey, "", fuenteCeldaNormal, Element.ALIGN_RIGHT, grisFondoTotales);
            }

            agregarCeldaEspecial(tablaCierreOkey, "TOTAL:", fuenteCeldaNegrita, Element.ALIGN_RIGHT, new BaseColor(220, 220, 220));
            agregarCeldaEspecial(tablaCierreOkey, "$" + df.format(granTotalTodo), fuenteCeldaNegrita, Element.ALIGN_RIGHT, new BaseColor(220, 220, 220));

            documento.add(tablaCierreOkey);
            documento.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PdfPTable crearTablaBaseReporte() throws DocumentException {
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{18f, 27f, 25f, 30f});
        return tabla;
    }

    private static void inicializarHeadersReporte(PdfPTable tabla, BaseColor fondo, Font fuente) {
        agregarCeldaEspecial(tabla, "Fecha", fuente, Element.ALIGN_CENTER, fondo);
        agregarCeldaEspecial(tabla, "Cliente", fuente, Element.ALIGN_CENTER, fondo);
        agregarCeldaEspecial(tabla, "Vehiculo", fuente, Element.ALIGN_CENTER, fondo);
        agregarCeldaEspecial(tabla, "Servicio", fuente, Element.ALIGN_CENTER, fondo);
    }

    private static void llenarFilaReporte(PdfPTable tabla, String f, String c, String v, String s, Font fuente) {
        agregarCeldaEspecial(tabla, f, fuente, Element.ALIGN_CENTER, BaseColor.WHITE);
        agregarCeldaEspecial(tabla, c, fuente, Element.ALIGN_CENTER, BaseColor.WHITE);
        agregarCeldaEspecial(tabla, v, fuente, Element.ALIGN_CENTER, BaseColor.WHITE);
        agregarCeldaEspecial(tabla, s, fuente, Element.ALIGN_CENTER, BaseColor.WHITE);
    }

    private static void agregarCeldaEspecial(PdfPTable tabla, String texto, Font fuente, int alineacion, BaseColor colorFondo) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(alineacion);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setBackgroundColor(colorFondo);
        celda.setPaddingTop(6);
        celda.setPaddingBottom(6);
        tabla.addCell(celda);
    }
    
}