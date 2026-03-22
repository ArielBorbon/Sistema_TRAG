/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.vistas;

import com.toedter.calendar.JDateChooser;
import dtos.cotizacion.CotizacionResumenDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel contenido = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        contenido.setBackground(Color.WHITE);

        contenido.add(new JLabel(c.getNombreCliente()));
        contenido.add(new JLabel(c.getMarcaAutomovil() + c.getModeloAutomovil()));
        contenido.add(new JLabel(c.getFechaCreacion().toString()));
        contenido.add(new JLabel("$" + c.getPrecioTotal()));

        JButton btnVer = new JButton("👁");
        btnVer.addActionListener(e -> {
            if (control != null) {
                control.verDetalleCotizacion(c);
            }
        });

        card.add(contenido, BorderLayout.CENTER);
        card.add(btnVer, BorderLayout.EAST);

        return card;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaHistorialCotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaHistorialCotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaHistorialCotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaHistorialCotizaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaHistorialCotizaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JScrollPane scrollCotizaciones;
    // End of variables declaration//GEN-END:variables
}
