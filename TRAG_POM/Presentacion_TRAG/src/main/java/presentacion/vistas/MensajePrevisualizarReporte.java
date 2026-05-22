package presentacion.vistas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author Yuri German Garcia López - 252583
 */
public class MensajePrevisualizarReporte extends JDialog {
    
    private boolean confirmado = false;

    public MensajePrevisualizarReporte(Frame padre, String cliente, String fechaInicio, String fechaFin, String estado, int activas, int canceladas, String totalStr) {
        super(padre, true);
        setUndecorated(true);
        initComponents(cliente, fechaInicio, fechaFin, estado, activas, canceladas, totalStr);
        pack();
        setLocationRelativeTo(padre);
    }

    private void initComponents(String cliente, String fechaInicio, String fechaFin, String estado, int activas, int canceladas, String totalStr) {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(Color.WHITE);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(25, 30, 20, 30));

        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 22);
        Font fuenteEtiqueta = new Font("Segoe UI", Font.PLAIN, 15);
        Font fuenteNegrita = new Font("Segoe UI", Font.BOLD, 15);
        Font fuenteVerde = new Font("Segoe UI", Font.BOLD, 15);
        Font fuenteRojo = new Font("Segoe UI", Font.BOLD, 15);
        Font fuenteTotalHero = new Font("Segoe UI", Font.BOLD, 22);

        JLabel lblTitulo = new JLabel("Reporte de Cotizaciones");
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        panelContenido.add(lblTitulo);
        panelContenido.add(javax.swing.Box.createRigidArea(new Dimension(0, 15)));

        JPanel filaCliente = crearFilaTexto("Clientes: ", cliente, fuenteEtiqueta, fuenteNegrita);
        panelContenido.add(filaCliente);
        panelContenido.add(javax.swing.Box.createRigidArea(new Dimension(0, 4)));

        JPanel filaPeriodo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filaPeriodo.setBackground(Color.WHITE);

        boolean tieneInicio = !fechaInicio.equalsIgnoreCase("Siempre");
        boolean tieneFin = !fechaFin.equalsIgnoreCase("Siempre");

        if (!tieneInicio && !tieneFin) {
            JLabel lblFija = new JLabel("Fecha: ");
            lblFija.setFont(fuenteEtiqueta);
            JLabel lblSiempre = new JLabel("Siempre");
            lblSiempre.setFont(fuenteNegrita);
            filaPeriodo.add(lblFija);
            filaPeriodo.add(lblSiempre);
        } else if (tieneInicio && !tieneFin) {
            JLabel lblDesde = new JLabel("Desde: ");
            lblDesde.setFont(fuenteEtiqueta);
            JLabel lblValorInicio = new JLabel(fechaInicio);
            lblValorInicio.setFont(fuenteNegrita);
            filaPeriodo.add(lblDesde);
            filaPeriodo.add(lblValorInicio);
        } else if (!tieneInicio && tieneFin) {
            JLabel lblHasta = new JLabel("Hasta: ");
            lblHasta.setFont(fuenteEtiqueta);
            JLabel lblValorFin = new JLabel(fechaFin);
            lblValorFin.setFont(fuenteNegrita);
            filaPeriodo.add(lblHasta);
            filaPeriodo.add(lblValorFin);
        } else {
            JLabel lblDe = new JLabel("De: ");
            lblDe.setFont(fuenteEtiqueta);
            JLabel lblValorInicio = new JLabel(fechaInicio + " ");
            lblValorInicio.setFont(fuenteNegrita);
            JLabel lblA = new JLabel("a: ");
            lblA.setFont(fuenteEtiqueta);
            JLabel lblValorFin = new JLabel(fechaFin);
            lblValorFin.setFont(fuenteNegrita);

            filaPeriodo.add(lblDe);
            filaPeriodo.add(lblValorInicio);
            filaPeriodo.add(lblA);
            filaPeriodo.add(lblValorFin);
        }

        panelContenido.add(filaPeriodo);
        panelContenido.add(javax.swing.Box.createRigidArea(new Dimension(0, 4)));

        JPanel filaEstado = crearFilaTexto("Estados: ", estado, fuenteEtiqueta, fuenteNegrita);
        panelContenido.add(filaEstado);
        panelContenido.add(javax.swing.Box.createRigidArea(new Dimension(0, 12)));

        JLabel lblActivas = new JLabel(activas + " No Canceladas");
        lblActivas.setFont(fuenteVerde);
        lblActivas.setForeground(new Color(51, 153, 51));
        lblActivas.setAlignmentX(LEFT_ALIGNMENT);
        JPanel wrapperActivas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapperActivas.setBackground(Color.WHITE);
        wrapperActivas.add(lblActivas);
        panelContenido.add(wrapperActivas);
        panelContenido.add(javax.swing.Box.createRigidArea(new Dimension(0, 4)));

        JLabel lblCanceladas = new JLabel(canceladas + " Canceladas");
        lblCanceladas.setFont(fuenteRojo);
        lblCanceladas.setForeground(new Color(204, 0, 0));
        lblCanceladas.setAlignmentX(LEFT_ALIGNMENT);
        JPanel wrapperCanceladas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapperCanceladas.setBackground(Color.WHITE);
        wrapperCanceladas.add(lblCanceladas);
        panelContenido.add(wrapperCanceladas);
        panelContenido.add(javax.swing.Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblTotalHero = new JLabel("Total: $" + totalStr);
        lblTotalHero.setFont(fuenteTotalHero);
        lblTotalHero.setAlignmentX(CENTER_ALIGNMENT);
        panelContenido.add(lblTotalHero);
        panelContenido.add(javax.swing.Box.createRigidArea(new Dimension(0, 25)));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(120, 35));
        btnCancelar.setBackground(new Color(255, 243, 177));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btnCancelar.addActionListener(e -> {
            confirmado = false;
            dispose();
        });

        JButton btnImprimir = new JButton("Descargar");
        btnImprimir.setPreferredSize(new Dimension(120, 35));
        btnImprimir.setBackground(new Color(186, 226, 255));
        btnImprimir.setForeground(Color.BLACK);
        btnImprimir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnImprimir.setFocusPainted(false);
        btnImprimir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnImprimir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btnImprimir.addActionListener(e -> {
            confirmado = true;
            dispose();
        });

        panelBotones.add(btnCancelar);
        panelBotones.add(btnImprimir);

        panelPrincipal.add(panelContenido, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        this.getContentPane().add(panelPrincipal);
    }

    private JPanel crearFilaTexto(String etiqueta, String valor, Font fEtiqueta, Font fValor) {
        JPanel panelFila = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFila.setBackground(Color.WHITE);
        JLabel lblEti = new JLabel(etiqueta);
        lblEti.setFont(fEtiqueta);
        JLabel lblVal = new JLabel(valor);
        lblVal.setFont(fValor);
        panelFila.add(lblEti);
        panelFila.add(lblVal);
        return panelFila;
    }

    public boolean isConfirmado() {
        return confirmado;
    }
}