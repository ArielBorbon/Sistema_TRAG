package presentacion.utils;

import dtos.cotizacion.CotizacionResumenDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import dtos.insumos.InsumoDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import presentacion.vistas.PanelEncabezado;
import presentacion.interfaces.IControlConsultarCotizaciones;
/**
 * @author Yuri Germán Garcí López - 252583
 */
public class VistaConsultaCotizacionPrevia extends javax.swing.JFrame implements IVistaConsultaCotizacionPrevia {

    private IControlConsultarCotizaciones control;
    private IControlConsultaCotizacion control2;
    
    private PanelEncabezado panelEncabezado;
    private JPanel panelFondo;
    
    private JTable tablaInsumos;
    private DefaultTableModel modeloTabla;
    
    private JTextField txtBuscarInsumo;
    
    private JPanel contenedorInsumosBuscados;
    private JScrollPane scrollInsumosBuscados;

    public VistaConsultaCotizacionPrevia(IControlConsultarCotizaciones control, IControlConsultaCotizacion control2) {
        this.control = control;
        this.control2 = control2;
        initComponents();
        configurarLayout();
        setLocationRelativeTo(null);
    }
    
    private void configurarLayout(){
        this.getContentPane().setLayout(new BorderLayout());

        // importar el encabezado
        panelEncabezado = new PanelEncabezado();
        this.getContentPane().add(panelEncabezado, BorderLayout.NORTH);
        
        // panel que solo pinta el fondo de color
        panelFondo = new JPanel();
        panelFondo.setBackground(new Color(243, 243, 243));
        panelFondo.setLayout(null);
        this.getContentPane().add(panelFondo, BorderLayout.CENTER);
        
        // poner el icono del cliente el label
        etqIconoUsuario.setIcon(cargarIcono("/cliente.png", 55, 55));
        etqIconoUsuario.setPreferredSize(new Dimension(55, 55));
        
        // poner icono del automovil en el label
        etqIconoAutomovil.setIcon(cargarIcono("/automovil.png", 80, 80));
        etqIconoAutomovil.setPreferredSize(new Dimension(80, 80));
        etqIconoAutomovil.setMinimumSize(new Dimension(80, 80));
        etqIconoAutomovil.setMaximumSize(new Dimension(80, 80));
        
        // generar el modelo de la tabla donde estarán todos los insumos de la cotización
        modeloTabla = new DefaultTableModel(new Object[]{"No.", "Pieza", "Costo", "Cantidad Pendiente"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // crear la tabla
        tablaInsumos = new JTable(modeloTabla);
        
        // darle más formato a la tabla
        tablaInsumos.setRowHeight(25);
        tablaInsumos.getTableHeader().setReorderingAllowed(false);
        // fuente de las filas (celdas)
        tablaInsumos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        // altura de filas (ajústala según el tamaño de letra)
        tablaInsumos.setRowHeight(30);
        // cambiar fuente del encabezado
        tablaInsumos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        DefaultTableCellRenderer headerRenderer =
            (DefaultTableCellRenderer) tablaInsumos.getTableHeader().getDefaultRenderer();
        // centrar texto
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        // color de fondo
        headerRenderer.setBackground(new Color(217, 217, 217));
        // color del texto
        headerRenderer.setForeground(Color.BLACK);
        // necesario para que se pinte el fondo correctamente
        headerRenderer.setOpaque(true);
        // borde
        tablaInsumos.setBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2)
        );
        scrollPiezas.setBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2)
        );
        
        // esto sirve para ajustar el ancho de cada columna
        tablaInsumos.getColumnModel().getColumn(0).setPreferredWidth(40);  // No.
        tablaInsumos.getColumnModel().getColumn(1).setPreferredWidth(200); // Pieza
        tablaInsumos.getColumnModel().getColumn(2).setPreferredWidth(150); // Costo
        tablaInsumos.getColumnModel().getColumn(3).setPreferredWidth(205); // Cantidad
        
        // meter tabla en el scroll
        scrollPiezas.setViewportView(tablaInsumos);
        
        // agregar action listener al botón para que ejecute la lógica de regresar a la pantalla anterior
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        
        btnAniadirInsumo.addActionListener(evt -> {
            boolean visible = txtBuscarInsumo.isVisible();
            if (visible) {
                txtBuscarInsumo.setText("");
                contenedorInsumosBuscados.setVisible(false);
                scrollInsumosBuscados.setVisible(false);
            }
            txtBuscarInsumo.setVisible(!visible);
            if (!visible) {
                txtBuscarInsumo.requestFocus();
            }
        });
        
        btnEliminarInsumo.addActionListener(evt -> {
            eliminarInsumo(null);
        });

        btnGuardar.addActionListener(evt -> {
            if (control2 != null) {
                control2.guardar();
            }
        });
        
        // buscador de insumos
        txtBuscarInsumo = new JTextField();
        txtBuscarInsumo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBuscarInsumo.setBounds(467, 495, 200, 30);
        txtBuscarInsumo.setVisible(false);
        panelFondo.add(txtBuscarInsumo);

        // contenedor para resultados de los insumos buscados
        contenedorInsumosBuscados = new JPanel();
        contenedorInsumosBuscados.setLayout(new BoxLayout(contenedorInsumosBuscados, BoxLayout.Y_AXIS));
        contenedorInsumosBuscados.setBackground(new Color(245, 245, 245));
        contenedorInsumosBuscados.setVisible(false);

        scrollInsumosBuscados = new JScrollPane(contenedorInsumosBuscados);
        scrollInsumosBuscados.setBounds(267, 535, 400, 90);
        scrollInsumosBuscados.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        scrollInsumosBuscados.setVisible(false);

        panelFondo.add(scrollInsumosBuscados);

        // este documentListener sirve para realizar la búsqueda en tiempo real de los insumos
        txtBuscarInsumo.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                buscarYMostrar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                buscarYMostrar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                buscarYMostrar();
            }

            private void buscarYMostrar() {
                String texto = txtBuscarInsumo.getText().trim().toLowerCase();
                if (control2 != null) {
                    control2.buscarInsumos(texto);
                }
            }
        });
    }
    
    // método action performed para el botón de regresar
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if (control != null) {
//            control.regresar();
        }
    }
    
    // método que ayuda a crear un card donde se acomodarán todos los insumos
    private JPanel crearCardInsumo(InsumoResumenDTO insumo) {

        JPanel card = new JPanel(new BorderLayout());
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel nombre = new JLabel(insumo.getNombre());
        nombre.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));

        JLabel precio = new JLabel("$" + insumo.getPrecioSugerido());
        precio.setHorizontalAlignment(SwingConstants.RIGHT);
        precio.setBorder(BorderFactory.createEmptyBorder(0,0,0,30));

        card.add(nombre, BorderLayout.WEST);
        card.add(precio, BorderLayout.EAST);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                control2.seleccionarInsumo(insumo);

                txtBuscarInsumo.setText("");
                scrollInsumosBuscados.setVisible(false);
            }
        });

        return card;
    }
    
    private ImageIcon cargarIcono(String ruta, int anchoMax, int altoMax) {
        try {
            java.net.URL url = getClass().getResource(ruta);

            if (url == null) {
                System.err.println("No se encontró la imagen: " + ruta);
                return new ImageIcon();
            }

            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage();

            int anchoOriginal = img.getWidth(null);
            int altoOriginal = img.getHeight(null);

            if (anchoOriginal <= 0 || altoOriginal <= 0) {
                return new ImageIcon();
            }

            double escala = Math.min(
                    (double) anchoMax / anchoOriginal,
                    (double) altoMax / altoOriginal
            );

            int nuevoAncho = (int) (anchoOriginal * escala);
            int nuevoAlto = (int) (altoOriginal * escala);

            Image imgEscalada = img.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            return new ImageIcon(imgEscalada);

        } catch (Exception e) {
            return new ImageIcon();
        }
    }
    
    private void recalcularTotales() {
        java.math.BigDecimal totalInsumos = java.math.BigDecimal.ZERO;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String precioStr = modeloTabla.getValueAt(i, 2).toString().replace("$", "");
            int cantidad = Integer.parseInt(modeloTabla.getValueAt(i, 3).toString());

            java.math.BigDecimal precio = new java.math.BigDecimal(precioStr);
            java.math.BigDecimal subtotal = precio.multiply(java.math.BigDecimal.valueOf(cantidad));

            totalInsumos = totalInsumos.add(subtotal);
        }

        etqTotalPiezasCotizacion.setText("$" + totalInsumos);

        // Mano de obra actual (ya mostrada)
        String manoObraStr = etqTotalManoObra.getText().replace("$", "");
        java.math.BigDecimal manoObra = new java.math.BigDecimal(manoObraStr);

        java.math.BigDecimal totalFinal = totalInsumos.add(manoObra);
        etqTotalCotizacion.setText("$" + totalFinal);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etqCotización = new javax.swing.JLabel();
        etqIconoUsuario = new javax.swing.JLabel();
        etqNombreCliente = new javax.swing.JLabel();
        etqIconoAutomovil = new javax.swing.JLabel();
        scrollPiezas = new javax.swing.JScrollPane();
        btnEliminarInsumo = new javax.swing.JButton();
        btnAniadirInsumo = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        etqAutomovil = new javax.swing.JLabel();
        etqAnioAutomovil = new javax.swing.JLabel();
        etqFecha = new javax.swing.JLabel();
        etqFechaCotizacion = new javax.swing.JLabel();
        etqTotalPiezas = new javax.swing.JLabel();
        etqTotalPiezasCotizacion = new javax.swing.JLabel();
        etqManoObra = new javax.swing.JLabel();
        etqTotalManoObra = new javax.swing.JLabel();
        etqTotal = new javax.swing.JLabel();
        etqTotalCotizacion = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnIniciarTrabajo = new javax.swing.JButton();
        etqApellido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(243, 243, 243));
        setMinimumSize(new java.awt.Dimension(1000, 720));

        etqCotización.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqCotización.setText("Cotización");

        etqNombreCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqNombreCliente.setText("cliente");

        btnEliminarInsumo.setBackground(new java.awt.Color(255, 243, 177));
        btnEliminarInsumo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarInsumo.setText("Eliminar Insumo");

        btnAniadirInsumo.setBackground(new java.awt.Color(188, 226, 255));
        btnAniadirInsumo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAniadirInsumo.setText("Añadir Insumo");

        btnVolver.setBackground(new java.awt.Color(186, 226, 255));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVolver.setText("Volver");

        etqAutomovil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqAutomovil.setText("auto");

        etqAnioAutomovil.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqAnioAutomovil.setText("anio");

        etqFecha.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqFecha.setText("Fecha:");

        etqFechaCotizacion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqFechaCotizacion.setText("fecha");

        etqTotalPiezas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqTotalPiezas.setText("Total Piezas");

        etqTotalPiezasCotizacion.setBackground(new java.awt.Color(255, 255, 255));
        etqTotalPiezasCotizacion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqTotalPiezasCotizacion.setText("total");

        etqManoObra.setBackground(new java.awt.Color(255, 255, 255));
        etqManoObra.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqManoObra.setText("Mano de obra (Intermedio)");

        etqTotalManoObra.setBackground(new java.awt.Color(255, 255, 255));
        etqTotalManoObra.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqTotalManoObra.setText("total");

        etqTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqTotal.setText("Total:");

        etqTotalCotizacion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        etqTotalCotizacion.setText("total");

        btnGuardar.setBackground(new java.awt.Color(177, 255, 186));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setMaximumSize(new java.awt.Dimension(85, 32));
        btnGuardar.setMinimumSize(new java.awt.Dimension(85, 32));
        btnGuardar.setPreferredSize(new java.awt.Dimension(85, 32));

        btnIniciarTrabajo.setBackground(new java.awt.Color(255, 243, 177));
        btnIniciarTrabajo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIniciarTrabajo.setText("Iniciar Trabajo");
        btnIniciarTrabajo.setMaximumSize(new java.awt.Dimension(85, 32));
        btnIniciarTrabajo.setMinimumSize(new java.awt.Dimension(85, 32));
        btnIniciarTrabajo.setPreferredSize(new java.awt.Dimension(85, 32));

        etqApellido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        etqApellido.setText("apellido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqIconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(etqApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(etqNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqIconoAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(etqAnioAutomovil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etqAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(etqCotización, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqFechaCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(etqTotalPiezas)
                            .addComponent(etqTotalPiezasCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etqManoObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etqTotalManoObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqTotalCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarInsumo, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAniadirInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnIniciarTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(etqCotización)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etqAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(etqFechaCotizacion)
                                .addComponent(etqFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqAnioAutomovil))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etqNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etqApellido))
                            .addComponent(etqIconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etqIconoAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etqTotalPiezas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etqTotalPiezasCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(etqManoObra)
                        .addGap(9, 9, 9)
                        .addComponent(etqTotalManoObra)
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etqTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etqTotalCotizacion))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarInsumo)
                    .addComponent(btnAniadirInsumo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIniciarTrabajo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        presentacion.interfaces.IControlConsultaCotizacion control = new presentacion.controles.ControlConsultaCotizacion();
//        control.iniciar();
//    }

    @Override
    public void cargarDatosCotizacion(CotizacionResumenDTO cotizacion) {
        if (cotizacion == null) return;

        etqNombreCliente.setText(
            cotizacion.getNombreCliente() != null ? cotizacion.getNombreCliente() : ""
        );

        etqApellido.setText(
            cotizacion.getApellidoPaternoCliente() != null ? cotizacion.getApellidoPaternoCliente() : ""
        );

        etqAutomovil.setText(
            (cotizacion.getMarcaAutomovil() != null ? cotizacion.getMarcaAutomovil() : "") +
            " " +
            (cotizacion.getModeloAutomovil() != null ? cotizacion.getModeloAutomovil() : "")
        );

        etqAnioAutomovil.setText(
            cotizacion.getAnioAutomovil() != null 
                ? String.valueOf(cotizacion.getAnioAutomovil()) 
                : ""
        );

        String fecha = (cotizacion.getFechaCreacion() != null)
                ? cotizacion.getFechaCreacion().toLocalDate().toString()
                : "N/A";
        etqFechaCotizacion.setText(fecha);
        
        // aquí se calcula el total sumado por todas las pieazas
        java.math.BigDecimal totalInsumos = java.math.BigDecimal.ZERO;

        if (cotizacion.getInsumosCotizacion() != null) {

            for (InsumoCotizacionDetalleDTO insumo : cotizacion.getInsumosCotizacion()) {

                int cantidad = insumo.getCantidadRequerida() != null 
                        ? insumo.getCantidadRequerida() 
                        : 0;

                java.math.BigDecimal precio = insumo.getPrecio() != null 
                        ? insumo.getPrecio() 
                        : java.math.BigDecimal.ZERO;
                
                java.math.BigDecimal subtotal = precio.multiply(
                        java.math.BigDecimal.valueOf(cantidad)
                );

                totalInsumos = totalInsumos.add(subtotal);
            }
        }

        etqTotalPiezasCotizacion.setText("$" + totalInsumos);

        java.math.BigDecimal manoObra = cotizacion.getPrecioManoObra() != null
                ? cotizacion.getPrecioManoObra()
                : java.math.BigDecimal.ZERO;
        etqTotalManoObra.setText("$" + manoObra);
        
        java.math.BigDecimal totalFinal = totalInsumos.add(manoObra);
        etqTotalCotizacion.setText("$" + totalFinal);
        
        // aquí se supone que se cargan los datos de la tabla de insumos
        modeloTabla.setRowCount(0);

        if (cotizacion.getInsumosCotizacion() != null) {

            int contador = 1;

            for (InsumoCotizacionDetalleDTO insumo : cotizacion.getInsumosCotizacion()) {

                String nombrePieza = (insumo.getInsumo() != null 
                        && insumo.getInsumo().getNombre() != null)
                        ? insumo.getInsumo().getNombre()
                        : "N/A";

                java.math.BigDecimal costo = insumo.getPrecio() != null
                        ? insumo.getPrecio()
                        : java.math.BigDecimal.ZERO;

                Integer cantidad = insumo.getCantidadRequerida() != null
                        ? insumo.getCantidadRequerida()
                        : 0;

                // Agregar fila
                modeloTabla.addRow(new Object[]{
                    contador,
                    nombrePieza,
                    "$" + costo,
                    cantidad
                });

                contador++;
            }
        }
        
        recalcularTotales();
    }

    @Override
    public void mostrarMensajeRapido(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void mostrar() {
        this.setVisible(true);
    }

    @Override
    public void ocultar() {
        this.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAniadirInsumo;
    private javax.swing.JButton btnEliminarInsumo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIniciarTrabajo;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel etqAnioAutomovil;
    private javax.swing.JLabel etqApellido;
    private javax.swing.JLabel etqAutomovil;
    private javax.swing.JLabel etqCotización;
    private javax.swing.JLabel etqFecha;
    private javax.swing.JLabel etqFechaCotizacion;
    private javax.swing.JLabel etqIconoAutomovil;
    private javax.swing.JLabel etqIconoUsuario;
    private javax.swing.JLabel etqManoObra;
    private javax.swing.JLabel etqNombreCliente;
    private javax.swing.JLabel etqTotal;
    private javax.swing.JLabel etqTotalCotizacion;
    private javax.swing.JLabel etqTotalManoObra;
    private javax.swing.JLabel etqTotalPiezas;
    private javax.swing.JLabel etqTotalPiezasCotizacion;
    private javax.swing.JScrollPane scrollPiezas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarMensaje(String mensajeError) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void mostrarInsumosBuscados(List<InsumoResumenDTO> insumos) {
        contenedorInsumosBuscados.removeAll();

        if (insumos == null || insumos.isEmpty()) {
            contenedorInsumosBuscados.setVisible(false);
            scrollInsumosBuscados.setVisible(false);
            return;
        }

        for (InsumoResumenDTO insumo : insumos) {
            contenedorInsumosBuscados.add(crearCardInsumo(insumo));
        }

        contenedorInsumosBuscados.revalidate();
        contenedorInsumosBuscados.repaint();

        contenedorInsumosBuscados.setPreferredSize(
            new Dimension(scrollInsumosBuscados.getWidth(), insumos.size() * 55)
        );

        contenedorInsumosBuscados.setVisible(true);
        scrollInsumosBuscados.setVisible(true);
    }

    @Override
    public void aniadirInsumo(InsumoResumenDTO insumo) {
        int rowCount = modeloTabla.getRowCount();
        modeloTabla.addRow(new Object[]{
            rowCount + 1,
            insumo,
            "$" + insumo.getPrecioSugerido(),
            1
        });
        recalcularTotales();
    }
    
    @Override
    public void eliminarInsumo(InsumoResumenDTO insumo) {
        int filaSeleccionada = tablaInsumos.getSelectedRow();

        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un insumo primero");
            return;
        }

        String nombre = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
        int cantidad = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
        
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Desea eliminar " + cantidad + " unidades de \"" + nombre + "\"?",
            "Confirmar eliminación",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            modeloTabla.removeRow(filaSeleccionada);
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                modeloTabla.setValueAt(i + 1, i, 0);
            }
            recalcularTotales();
        }
    }
    
    @Override
    public void guardarInsumo(InsumoResumenDTO insumo) {
        // TODO
    }
    
    @Override
    public List<InsumoCotizacionDetalleDTO> obtenerInsumosActuales() {
        List<InsumoCotizacionDetalleDTO> lista = new java.util.ArrayList<>();

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {

            InsumoResumenDTO insumo =
                (InsumoResumenDTO) modeloTabla.getValueAt(i, 1);

            String precioStr = modeloTabla.getValueAt(i, 2).toString().replace("$", "");
            int cantidad = Integer.parseInt(modeloTabla.getValueAt(i, 3).toString());

            java.math.BigDecimal precio = new java.math.BigDecimal(precioStr);

            InsumoCotizacionDetalleDTO dto =
                new InsumoCotizacionDetalleDTO(
                    null,
                    cantidad,
                    precio
                );

            lista.add(dto);
        }

        return lista;
    }
}
