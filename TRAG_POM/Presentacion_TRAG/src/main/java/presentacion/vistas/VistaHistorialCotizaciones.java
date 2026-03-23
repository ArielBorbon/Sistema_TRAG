
package presentacion.vistas;

import com.toedter.calendar.JDateChooser;
import dtos.cotizacion.CotizacionResumenDTO;
import enums.EstadoCotizacionNegocios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import presentacion.interfaces.vistas.IVistaHistorialCotizaciones;
import presentacion.interfaces.IControlConsultarCotizaciones;

/**
 *
 * Archivo: VistaHistorialCotizaciones.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class VistaHistorialCotizaciones extends JFrame implements IVistaHistorialCotizaciones {

    private IControlConsultarCotizaciones control;

    private PanelEncabezado panelEncabezado;
    private JPanel panelFiltros;
    private JPanel contenedorTarjetas;

    private JComboBox<String> cmbEstado;
    private JTextField txtNombreCliente;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JButton btnBuscar;

    private JDateChooser dateInicio;
    private JDateChooser dateFin;

    /**
     * Constructor con controlador
     */
    public VistaHistorialCotizaciones(IControlConsultarCotizaciones control) {
        this.control = control;
        initComponents();
        configurarLayout();
        setLocationRelativeTo(null);
    }

    public VistaHistorialCotizaciones() {
        initComponents();
        configurarLayout();
    }

    private void configurarLayout() {

        this.getContentPane().setLayout(new BorderLayout());

        btnBuscar = new JButton();
        panelEncabezado = new PanelEncabezado();
        this.getContentPane().add(panelEncabezado, BorderLayout.NORTH);

        this.getContentPane().add(panelMenu, BorderLayout.CENTER);
        panelMenu.setLayout(new BorderLayout(10, 10));

        crearPanelFiltros();
        panelMenu.add(panelFiltros, BorderLayout.NORTH);

        configurarBusquedaEnTiempoReal();

        panelMenu.add(scrollCotizaciones, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelBoton.add(btnVolver);
        panelMenu.add(panelBoton, BorderLayout.SOUTH);

        contenedorTarjetas = new JPanel();
        contenedorTarjetas.setLayout(new BoxLayout(contenedorTarjetas, BoxLayout.Y_AXIS));
        contenedorTarjetas.setBackground(new Color(245, 245, 245));

        scrollCotizaciones.setViewportView(contenedorTarjetas);
    }

    private void crearPanelFiltros() {
        panelFiltros = new JPanel(new BorderLayout());
        panelFiltros.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Cotizaciones");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelFiltros.add(lblTitulo, BorderLayout.WEST);

        JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelCentro.setBackground(Color.WHITE);

        dateInicio = new JDateChooser();
        dateFin = new JDateChooser();

        dateInicio.setPreferredSize(new Dimension(120, 30));
        dateFin.setPreferredSize(new Dimension(120, 30));

        txtNombreCliente = new JTextField(15);

        cmbEstado = new JComboBox<>(new String[]{"Todos", "Habilitadas", "Canceladas"});
        cmbEstado.setPreferredSize(new Dimension(120, 30));
        cmbEstado.setBackground(Color.WHITE);

        panelCentro.add(new JLabel("Inicio"));
        panelCentro.add(dateInicio);

        panelCentro.add(new JLabel("Fin"));
        panelCentro.add(dateFin);

        panelCentro.add(new JLabel("Cliente:"));
        panelCentro.add(txtNombreCliente);

        panelCentro.add(new JLabel("Estado:"));
        panelCentro.add(cmbEstado);

        panelFiltros.add(panelCentro, BorderLayout.CENTER);
    }

    @Override
    public void mostrarCotizaciones(List<CotizacionResumenDTO> cotizaciones) {

        contenedorTarjetas.removeAll();

        if (cotizaciones == null || cotizaciones.isEmpty()) {

            JPanel panelVacio = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
            panelVacio.setBackground(new Color(245, 245, 245));

            JLabel lblVacio = new JLabel("No se encontraron cotizaciones.");
            lblVacio.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblVacio.setForeground(new Color(150, 150, 150));

            panelVacio.add(lblVacio);
            contenedorTarjetas.add(panelVacio);

            contenedorTarjetas.revalidate();
            contenedorTarjetas.repaint();
            return;
        }

        for (CotizacionResumenDTO c : cotizaciones) {
            contenedorTarjetas.add(crearCardCotizacion(c));
        }

        contenedorTarjetas.revalidate();
        contenedorTarjetas.repaint();
    }

    private JPanel crearCardCotizacion(CotizacionResumenDTO c) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        contenedor.setPreferredSize(new Dimension(950, 100));
        contenedor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JPanel panelInfo = new JPanel(new GridBagLayout());
        panelInfo.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 8, 0, 8);
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        panelInfo.add(new JLabel(cargarIcono("/cliente.png", 55, 55)), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.Y_AXIS));
        panelNombre.setBackground(Color.WHITE);
        panelNombre.setPreferredSize(new Dimension(160, 50));

        JLabel lblNombre = new JLabel(c.getNombreCliente() != null ? c.getNombreCliente() : "");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel lblApellido = new JLabel(c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente() : "");
        lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblApellido.setForeground(Color.GRAY);

        panelNombre.add(lblNombre);
        panelNombre.add(lblApellido);
        panelInfo.add(panelNombre, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelInfo.add(new JLabel(cargarIcono("/automovil.png", 80, 80)), gbc);

        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel panelAutoText = new JPanel();
        panelAutoText.setLayout(new BoxLayout(panelAutoText, BoxLayout.Y_AXIS));
        panelAutoText.setBackground(Color.WHITE);
        panelAutoText.setPreferredSize(new Dimension(160, 50));

        JLabel lblMarca = new JLabel(c.getMarcaAutomovil() != null ? c.getMarcaAutomovil() : "");
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel lblModelo = new JLabel(c.getModeloAutomovil() != null ? c.getModeloAutomovil() : "");
        lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblModelo.setForeground(Color.GRAY);

        panelAutoText.add(lblMarca);
        panelAutoText.add(lblModelo);
        panelInfo.add(panelAutoText, gbc);

        gbc.gridx = 4;
        String fecha = (c.getFechaCreacion() != null) ? c.getFechaCreacion().toLocalDate().toString() : "N/A";
        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelInfo.add(lblFecha, gbc);

        gbc.gridx = 5;
        gbc.weightx = 1.0; 
        gbc.anchor = GridBagConstraints.EAST;
        String precio = (c.getPrecioTotal() != null) ? "$" + c.getPrecioTotal() : "$0.00";
        JLabel lblPrecio = new JLabel(precio);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelInfo.add(lblPrecio, gbc);

        boolean estaActiva = c.getEstadoCotizacion().name().equals("ACTIVA");
        gbc.gridx = 6;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 15, 0, 5);
        JLabel lblEstado = new JLabel(estaActiva ? "Habilitada" : "Cancelada");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstado.setForeground(estaActiva ? new Color(34, 139, 34) : new Color(220, 20, 60));
        panelInfo.add(lblEstado, gbc);

        gbc.gridx = 7;
        gbc.insets = new Insets(0, 5, 0, 10);
        JButton btnCambiarEstado = new JButton(estaActiva ? "Cancelar" : "Habilitar");
        btnCambiarEstado.setBackground(estaActiva ? new Color(255, 102, 102) : new Color(102, 204, 102));
        btnCambiarEstado.setForeground(Color.WHITE);
        btnCambiarEstado.setFocusPainted(false);
        btnCambiarEstado.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCambiarEstado.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Deseas " + (estaActiva ? "cancelar" : "activar") + " esta cotización?", 
                "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (estaActiva) control.cancelarCotizacion(c.getId());
                else control.activiarCotizacion(c.getId());
                btnBuscar.doClick(); 
            }
        });
        panelInfo.add(btnCambiarEstado, gbc);

        JButton btnVer = new JButton(cargarIcono("/ojo.png", 24, 24));
        btnVer.setBackground(Color.WHITE);
        btnVer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVer.setPreferredSize(new Dimension(50, 40));
        btnVer.addActionListener(e -> { if (control != null) control.verDetalleCotizacion(c); });

        JPanel panelBtnVer = new JPanel(new GridBagLayout());
        panelBtnVer.setBackground(Color.WHITE);
        panelBtnVer.setPreferredSize(new Dimension(70, 100));
        panelBtnVer.add(btnVer);

        contenedor.add(panelInfo, BorderLayout.CENTER);
        contenedor.add(panelBtnVer, BorderLayout.EAST);

        card.add(contenedor, BorderLayout.CENTER);
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

    private void configurarBusquedaEnTiempoReal() {

        txtNombreCliente.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        });

        dateInicio.addPropertyChangeListener("date", evt -> buscar());
        dateFin.addPropertyChangeListener("date", evt -> buscar());
        
        cmbEstado.addActionListener(evt -> buscar());
    }

    private void buscar() {
        if (control != null) {

            LocalDateTime inicio = null;
            LocalDateTime fin = null;

            if (dateInicio.getDate() != null) {
                inicio = dateInicio.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
            }

            if (dateFin.getDate() != null) {
                fin = dateFin.getDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
            }

            String estadoStr = "Todos";
            int seleccion = cmbEstado.getSelectedIndex();
            
            if (seleccion == 1) {
                estadoStr = EstadoCotizacionNegocios.ACTIVA.name();
            } else if (seleccion == 2) {
                estadoStr = EstadoCotizacionNegocios.CANCELADA.name();
            }

            control.buscarCotizaciones(
                    txtNombreCliente.getText(),
                    inicio,
                    fin,
                    estadoStr 
            );
        }
    }

    @Override
    public void mostrarMensajeRapido(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void limpiarFiltros() {
        txtNombreCliente.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
    }

    @Override
    public void mostrar() {
        this.setVisible(true);
    }

    @Override
    public void ocultar() {
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        scrollCotizaciones = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1100, 700));
        setMinimumSize(new java.awt.Dimension(1100, 700));
        setPreferredSize(new java.awt.Dimension(1000, 720));

        panelMenu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btnVolver.setBackground(new java.awt.Color(255, 255, 204));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap(379, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(377, 377, 377))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(scrollCotizaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(scrollCotizaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volver();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void volver(){
        control.volverHistorialCotizaciones();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JScrollPane scrollCotizaciones;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarMensaje(String mensajeError) {
        JOptionPane.showMessageDialog(this, mensajeError, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
