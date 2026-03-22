/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.vistas;

import com.toedter.calendar.JDateChooser;
import dtos.cotizacion.CotizacionResumenDTO;
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
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import presentacion.interfaces.IControlHistorialCotizaciones;
import presentacion.interfaces.vistas.IHistorialCotizaciones;

/**
 *
 * @author sonic
 */
public class VistaHistorialCotizaciones extends javax.swing.JFrame implements IHistorialCotizaciones {

    private IControlHistorialCotizaciones control;

    private PanelEncabezado panelEncabezado;
    private JPanel panelFiltros;
    private JPanel contenedorTarjetas;

    private JTextField txtNombreCliente;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JButton btnBuscar;

    private JDateChooser dateInicio;
    private JDateChooser dateFin;

    /**
     * Constructor con controlador
     */
    public VistaHistorialCotizaciones(IControlHistorialCotizaciones control) {
        this.control = control;
        initComponents();
        configurarLayout();
    }

    public VistaHistorialCotizaciones() {
        initComponents();
        configurarLayout();
    }

    private void configurarLayout() {

        this.getContentPane().setLayout(new BorderLayout());

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

        panelCentro.add(new JLabel("Inicio"));
        panelCentro.add(dateInicio);

        panelCentro.add(new JLabel("Fin"));
        panelCentro.add(dateFin);

        panelCentro.add(txtNombreCliente);

        panelFiltros.add(panelCentro, BorderLayout.CENTER);
    }

    @Override
    public void mostrarCotizaciones(List<CotizacionResumenDTO> cotizaciones) {

        contenedorTarjetas.removeAll();

        if (cotizaciones == null || cotizaciones.isEmpty()) {

            JPanel panelVacio = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
            panelVacio.setBackground(new Color(245, 245, 245));

            JLabel lblVacio = new JLabel("No se encontraron cotizaciones.");
            lblVacio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
        contenedor.setPreferredSize(new Dimension(850, 100));
        contenedor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JPanel panelInfo = new JPanel(new GridBagLayout());
        panelInfo.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 8, 0, 8);

        gbc.gridx = 0;
        gbc.weightx = 0;

        JLabel lblIconoCliente = new JLabel(cargarIcono("/cliente.png", 55, 55));
        lblIconoCliente.setPreferredSize(new Dimension(55, 55));
        panelInfo.add(lblIconoCliente, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new BoxLayout(panelNombre, BoxLayout.Y_AXIS));
        panelNombre.setBackground(Color.WHITE);

        
        panelNombre.setPreferredSize(new Dimension(160, 50));
        panelNombre.setMinimumSize(new Dimension(160, 50));
        panelNombre.setMaximumSize(new Dimension(160, 50));

        String nombre = c.getNombreCliente() != null ? c.getNombreCliente() : "";
        String apellido = c.getApellidoPaternoCliente() != null ? c.getApellidoPaternoCliente() : "";

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        JLabel lblApellido = new JLabel(apellido);
        lblApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblApellido.setForeground(Color.GRAY);
        lblApellido.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        panelNombre.add(lblNombre);
        panelNombre.add(lblApellido);

        panelInfo.add(panelNombre, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblIconoAuto = new JLabel(cargarIcono("/automovil.png", 80, 80));
        lblIconoAuto.setPreferredSize(new Dimension(80, 80));
        lblIconoAuto.setMinimumSize(new Dimension(80, 80));
        lblIconoAuto.setMaximumSize(new Dimension(80, 80));
        lblIconoAuto.setHorizontalAlignment(SwingConstants.CENTER);
        lblIconoAuto.setVerticalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblIconoAuto, gbc);

        gbc.gridx = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JPanel panelAutoText = new JPanel();
        panelAutoText.setLayout(new BoxLayout(panelAutoText, BoxLayout.Y_AXIS));
        panelAutoText.setBackground(Color.WHITE);
        
        panelAutoText.setPreferredSize(new Dimension(160, 50));
        panelAutoText.setMinimumSize(new Dimension(160, 50));
        panelAutoText.setMaximumSize(new Dimension(160, 50));

        String marca = c.getMarcaAutomovil() != null ? c.getMarcaAutomovil() : "";
        String modelo = c.getModeloAutomovil() != null ? c.getModeloAutomovil() : "";

        JLabel lblMarca = new JLabel(marca);
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMarca.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        JLabel lblModelo = new JLabel(modelo);
        lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblModelo.setForeground(Color.GRAY);
        lblModelo.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        panelAutoText.add(lblMarca);
        panelAutoText.add(lblModelo);

        panelInfo.add(panelAutoText, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        String fecha = (c.getFechaCreacion() != null)
                ? c.getFechaCreacion().toLocalDate().toString()
                : "N/A";

        JLabel lblFecha = new JLabel(fecha);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFecha.setPreferredSize(new Dimension(110, 30));
        lblFecha.setHorizontalAlignment(SwingConstants.CENTER);

        panelInfo.add(lblFecha, gbc);


        gbc.gridx = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;

        String precio = (c.getPrecioTotal() != null)
                ? "$" + c.getPrecioTotal()
                : "$0.00";

        JLabel lblPrecio = new JLabel(precio);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPrecio.setPreferredSize(new Dimension(100, 30));
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);

        panelInfo.add(lblPrecio, gbc);

        
        JButton btnVer = new JButton(cargarIcono("/ojo.png", 24, 24));
        btnVer.setBackground(Color.WHITE);
        btnVer.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        btnVer.setFocusPainted(false);
        btnVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer.setPreferredSize(new Dimension(50, 40));

        btnVer.addActionListener(e -> {
            if (control != null) {
                control.verDetalleCotizacion(c);
            }
        });

        JPanel panelBtn = new JPanel(new GridBagLayout());
        panelBtn.setBackground(Color.WHITE);
        panelBtn.setPreferredSize(new Dimension(70, 100));
        panelBtn.add(btnVer);


        contenedor.add(panelInfo, BorderLayout.CENTER);
        contenedor.add(panelBtn, BorderLayout.EAST);

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
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
        });

        dateInicio.addPropertyChangeListener("date", evt -> buscar());
        dateFin.addPropertyChangeListener("date", evt -> buscar());
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

            control.buscarCotizaciones(
                    txtNombreCliente.getText(),
                    inicio,
                    fin,
                    null
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
        setMaximumSize(new java.awt.Dimension(1000, 720));
        setMinimumSize(new java.awt.Dimension(1000, 720));
        setPreferredSize(new java.awt.Dimension(1000, 720));

        panelMenu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

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
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        if (control != null) {
            control.regresar();
        }
    }//GEN-LAST:event_btnVolverActionPerformed

    public static void main(String[] args) {
        presentacion.interfaces.IControlHistorialCotizaciones control = new presentacion.controles.ControlHistorialCotizaciones();
        control.iniciar();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JScrollPane scrollCotizaciones;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarError(String mensajeError) {
        JOptionPane.showMessageDialog(this, mensajeError, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
