
package presentacion.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 *
 * Archivo: GeneradorPDF.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class GeneradorPDF {

    public static void crearDocumentoPDF(
            String rutaDestino,
            LocalDateTime fecha, 
            String nombreCompletoCliente,
            BigDecimal costoManoObra,
            String automovil,
            List<InsumoCotizacionDetalleDTO> insumosCotizacion) {
        
        Document documento = new Document(PageSize.LETTER, 40, 40, 40, 40);

        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(rutaDestino));
            documento.open();

            // Barras superiores
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

            // Fuentes
            Font fuenteTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            Font fuenteEmpresa = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
            Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
            Font fuenteNegrita = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
            Font fuenteBlanca = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.WHITE);
            Font fuenteSubrayada = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD | Font.UNDERLINE, BaseColor.BLACK);

            for(int i=0; i < 3; i++) { documento.add(new Paragraph("\n")); }

            // Datos
            PdfPTable tablaTitulo = new PdfPTable(1);
            tablaTitulo.setWidthPercentage(35);
            tablaTitulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell celdaTitulo = new PdfPCell(new Phrase("COTIZACIÓN", fuenteTitulo));
            celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaTitulo.setPadding(8);
            celdaTitulo.setBorder(Rectangle.NO_BORDER);
            celdaTitulo.setCellEvent(new BordeRedondeadoEvent(new BaseColor(230, 230, 230), BaseColor.DARK_GRAY));
            tablaTitulo.addCell(celdaTitulo);
            documento.add(tablaTitulo);
            documento.add(new Paragraph("\n"));

            // Encabezado
            PdfPTable tablaHeader = new PdfPTable(2);
            tablaHeader.setWidthPercentage(100);
            tablaHeader.setWidths(new float[]{60f, 40f});

            Paragraph datosEmpresa = new Paragraph();
            datosEmpresa.add(new Chunk("REFRIGERACIÓN AUTOMOTRIZ GRANADOS\n", fuenteEmpresa));
            datosEmpresa.add(new Chunk("OTANCAHUI NO. 1701, ESQ. GOLFO DE TEHUANTEPEC\n", fuenteNormal));
            datosEmpresa.add(new Chunk("Teléfono: (55) 644 155 7060\nEmail: robertogranados888@gmail.com", fuenteNormal));
            
            PdfPCell celdaEmpresa = new PdfPCell(datosEmpresa);
            celdaEmpresa.setBorder(Rectangle.NO_BORDER);
            tablaHeader.addCell(celdaEmpresa);

            PdfPCell celdaLogo = new PdfPCell();
            celdaLogo.setBorder(Rectangle.NO_BORDER);
            celdaLogo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            try {
                java.net.URL urlLogo = GeneradorPDF.class.getResource("/logo_pdf.png");

                if (urlLogo != null) {
                    Image logo = Image.getInstance(urlLogo);
                    logo.scalePercent(33f);
                    logo.setAlignment(Image.ALIGN_RIGHT);
                    celdaLogo.addElement(logo);
                } else {
                    celdaLogo.addElement(new Phrase("[LOGO NO ENCONTRADO]", fuenteNegrita));
                } 
            } catch (Exception e) { celdaLogo.addElement(new Phrase("[LOGO]", fuenteNegrita)); }
            tablaHeader.addCell(celdaLogo);
            documento.add(tablaHeader);
            documento.add(new Paragraph("\n"));

            // Datos
            PdfPTable tablaDatos = new PdfPTable(2);
            tablaDatos.setWidthPercentage(100);
            tablaDatos.setWidths(new float[]{15f, 85f});
            
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d 'de' MMMM yyyy", Locale.forLanguageTag("es-ES"));
            String fechaFormateada = fecha.format(formateador);

            agregarCelda(tablaDatos, "FECHA", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatos, fechaFormateada, fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatos, "CLIENTE", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatos, nombreCompletoCliente, fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatos, "UNIDAD", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatos, automovil, fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatos, "REVISÓ", fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
            agregarCelda(tablaDatos, "Roberto Granados Bravo", fuenteNormal, Element.ALIGN_LEFT, BaseColor.WHITE);
            
            documento.add(tablaDatos);
            documento.add(new Paragraph("\n"));

            documento.add(new Paragraph("Estimado cliente, estamos presentando a su amable consideración la siguiente cotización solicitada:\n\n", fuenteNormal));

            // Tabla de insumos
            PdfPTable tablaInsumos = new PdfPTable(6);
            tablaInsumos.setWidthPercentage(100);
            tablaInsumos.setWidths(new float[]{36f, 14f, 4f, 21f, 4f, 21f});

            BaseColor colorHeader = new BaseColor(100, 100, 100);
            agregarCelda(tablaInsumos, "Concepto", fuenteBlanca, Element.ALIGN_LEFT, colorHeader);
            agregarCelda(tablaInsumos, "Cantidad", fuenteBlanca, Element.ALIGN_CENTER, colorHeader);
            
            PdfPCell hPU = new PdfPCell(new Phrase("P. Unitario", fuenteBlanca));
            hPU.setColspan(2); hPU.setBackgroundColor(colorHeader); hPU.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaInsumos.addCell(hPU);

            PdfPCell hPre = new PdfPCell(new Phrase("Precio", fuenteBlanca));
            hPre.setColspan(2); hPre.setBackgroundColor(colorHeader); hPre.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaInsumos.addCell(hPre);

            // Filas
            for(InsumoCotizacionDetalleDTO insumoCotizacion: insumosCotizacion){
                agregarFilaInsumo(
                        tablaInsumos,
                        insumoCotizacion.getInsumo().getNombre(),
                        insumoCotizacion.getCantidadRequerida().toString(), 
                        insumoCotizacion.getPrecio().toString(), 
                        insumoCotizacion.getSubtotal().toString(),
                        fuenteNegrita);
            }
            
            BigDecimal subtotal = insumosCotizacion.stream()
                .map(InsumoCotizacionDetalleDTO::getSubtotal)
                .filter(s -> s != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(costoManoObra);
            
            BigDecimal iva = subtotal.multiply(new BigDecimal("0.16"));
            
            BigDecimal total = subtotal.add(iva);

            // Totales
            BaseColor grisClaro = new BaseColor(230, 230, 230);
            agregarFilaTotales(tablaInsumos, "Subtotal", subtotal.toString(), fuenteNegrita, grisClaro);
            agregarFilaTotales(tablaInsumos, "IVA", iva.toString(), fuenteNegrita, grisClaro);
            agregarFilaTotales(tablaInsumos, "Total", total.toString(), fuenteNegrita, grisClaro);

            documento.add(tablaInsumos);
            documento.add(new Paragraph("\n"));

            Paragraph terminos = new Paragraph("PRECIOS DE INSUMOS SUJETOS A CAMBIO SIN PREVIO AVISO SEGÚN PROVEEDOR. LA VIGENCIA DEPENDE DE LA DISPONIBILIDAD Y ESTADO ACTUAL DEL VEHÍCULO.\n", fuenteSubrayada);
            documento.add(terminos);
            documento.add(new Paragraph("\n\n"));
            
            // Datos bancarios
            Paragraph banco = new Paragraph(
                    "Pago Transferencia:\n" +
                    "Bancomer\n" +
                    "Cuenta: 01158801032\n" +
                    "Clabe Interbancaria: 012650001188451430\n\n", fuenteNormal);
            documento.add(banco);

            documento.add(new Paragraph("En espera de vernos favorecidos con este presupuesto, hacemos propicia la ocasión para enviar un cordial saludo y reiterar que estamos a su servicio.\n\n\n", fuenteNormal));

            // Pie de página
            PdfPTable tablaFooter = new PdfPTable(1);
            tablaFooter.setWidthPercentage(70);
            PdfPCell celdaFooter = new PdfPCell(new Phrase("TALLER DE REFRIGERACIÓN AUTOMOTRIZ GRANADOS", fuenteNegrita));
            celdaFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
            celdaFooter.setPadding(10);
            celdaFooter.setBorder(Rectangle.NO_BORDER);
            celdaFooter.setCellEvent(new BordeRedondeadoEvent(new BaseColor(230, 230, 230), BaseColor.DARK_GRAY));
            tablaFooter.addCell(celdaFooter);
            documento.add(tablaFooter);

            documento.close();

        } catch (Exception e) { e.printStackTrace(); }
    }

    private static void agregarCelda(PdfPTable tabla, String texto, Font fuente, int alineacion, BaseColor colorFondo) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(alineacion);
        celda.setBackgroundColor(colorFondo);
        celda.setPaddingBottom(5);
        tabla.addCell(celda);
    }

    private static void agregarFilaInsumo(PdfPTable tabla, String concepto, String cantidad, String pUnitario, String precio, Font fuenteNegrita) {
        agregarCelda(tabla, concepto, fuenteNegrita, Element.ALIGN_LEFT, BaseColor.WHITE);
        agregarCelda(tabla, cantidad, fuenteNegrita, Element.ALIGN_CENTER, BaseColor.WHITE);

        PdfPCell c1 = new PdfPCell(new Phrase("$", fuenteNegrita));
        c1.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM); 
        tabla.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase(pUnitario, fuenteNegrita));
        c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c2.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        tabla.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("$", fuenteNegrita));
        c3.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
        tabla.addCell(c3);
        PdfPCell c4 = new PdfPCell(new Phrase(precio, fuenteNegrita));
        c4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        c4.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        tabla.addCell(c4);
    }

    private static void agregarFilaTotales(PdfPTable tabla, String etiqueta, String valor, Font fuenteNegrita, BaseColor colorFondo) {
        PdfPCell vacia = new PdfPCell(new Phrase(""));
        vacia.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(vacia); tabla.addCell(vacia);

        PdfPCell eti = new PdfPCell(new Phrase(etiqueta, fuenteNegrita));
        eti.setHorizontalAlignment(Element.ALIGN_RIGHT);
        eti.setBackgroundColor(colorFondo);
        eti.setColspan(2); 
        tabla.addCell(eti); 

        PdfPCell s = new PdfPCell(new Phrase("$", fuenteNegrita));
        s.setBackgroundColor(colorFondo);
        s.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
        tabla.addCell(s);

        PdfPCell v = new PdfPCell(new Phrase(valor, fuenteNegrita));
        v.setHorizontalAlignment(Element.ALIGN_RIGHT);
        v.setBackgroundColor(colorFondo);
        v.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.BOTTOM);
        tabla.addCell(v); 
    }

    static class BordeRedondeadoEvent implements PdfPCellEvent {
        private BaseColor colorFondo, colorBorde;
        public BordeRedondeadoEvent(BaseColor f, BaseColor b) { this.colorFondo = f; this.colorBorde = b; }
        @Override
        public void cellLayout(PdfPCell cell, Rectangle pos, PdfContentByte[] canvases) {
            PdfContentByte cb = canvases[PdfPTable.BACKGROUNDCANVAS];
            cb.setColorFill(colorFondo);
            cb.roundRectangle(pos.getLeft(), pos.getBottom(), pos.getWidth(), pos.getHeight(), 6);
            cb.fill();
            PdfContentByte lin = canvases[PdfPTable.LINECANVAS];
            lin.setColorStroke(colorBorde);
            lin.setLineWidth(1.5f);
            lin.roundRectangle(pos.getLeft(), pos.getBottom(), pos.getWidth(), pos.getHeight(), 6);
            lin.stroke();
        }
    }
}