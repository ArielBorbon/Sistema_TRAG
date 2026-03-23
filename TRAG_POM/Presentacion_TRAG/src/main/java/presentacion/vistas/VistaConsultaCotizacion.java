
package presentacion.vistas;

import dtos.cotizacion.CotizacionDetalleDTO;
import dtos.insumocotizacion.InsumoCotizacionDetalleDTO;
import dtos.insumos.InsumoResumenDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import presentacion.borradores.BorradorCotizacion;
import presentacion.borradores.BorradorInsumoCotizacion;
import presentacion.interfaces.vistas.IVistaConsultaCotizacion;
import presentacion.interfaces.IControlConsultarCotizaciones;

/**
 *
 * Archivo: VistaConsultaCotizacion.java
 * 
 * @author Ariel Eduardo Borbón Izaguirre - 253080
 * @author Sebastián Bórquez Huerta - 253080
 * @author Yuri Germán García López - 253080
 * @author Manuel Romo López - 253080
 * 
 */
public class VistaConsultaCotizacion extends JFrame implements IVistaConsultaCotizacion{

    private IControlConsultarCotizaciones control;
    
    private Long idCotizacion;
    
    private boolean esCancelada = false;
    /**
     * Creates new form VistaCrearCotizacion
     */
    public VistaConsultaCotizacion(IControlConsultarCotizaciones control) {
        initComponents();
        configurarTablaInsumos();
        configurarBuscardorInsumos();
        
        this.control = control;
        
        setLocationRelativeTo(null);
    }
    
    private void configurarBuscardorInsumos() {

        popMenuBuscarInsumos.add(scrollPaneBuscarInsumos);

        cmpTxtBuscarInsumos.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { buscarSugerencias(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { buscarSugerencias(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { buscarSugerencias(); }
        });

        listBuscarInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 1) { 
                    String seleccion = listBuscarInsumos.getSelectedValue();
                    if (seleccion != null) {
                        cmpTxtBuscarInsumos.setText(seleccion);
                        popMenuBuscarInsumos.setVisible(false);
                        cmpTxtBuscarInsumos.requestFocus();
                    }
                }
            }
        });

        cmpTxtBuscarInsumos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    String textoBuscador = cmpTxtBuscarInsumos.getText();
                    
                    if (!textoBuscador.trim().isEmpty()) {
                        seleccionInsumoAgregarInsumoATabla(textoBuscador);
                        
                        cmpTxtBuscarInsumos.setText("");
                        popMenuBuscarInsumos.setVisible(false);
                    }
                }
            }
        });
    }
    
    private void buscarSugerencias() {
        String texto = cmpTxtBuscarInsumos.getText().trim();
        if (!texto.isEmpty()) {
            control.buscarInsumosNombre(texto);
        } else {
            popMenuBuscarInsumos.setVisible(false);
        }
    }
    
    @Override
    public void actualizarSugerencias(List<InsumoResumenDTO> insumos) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        
        insumos.stream()
                .map(InsumoResumenDTO::getNombre)
                .forEach(modelo::addElement);
                
        boolean tieneCoincidencias = !modelo.isEmpty();

        if (tieneCoincidencias) {
            listBuscarInsumos.setModel(modelo);
            scrollPaneBuscarInsumos.setPreferredSize(new Dimension(cmpTxtBuscarInsumos.getWidth(), 150));
            popMenuBuscarInsumos.pack();
            popMenuBuscarInsumos.show(cmpTxtBuscarInsumos, 0, cmpTxtBuscarInsumos.getHeight());
            cmpTxtBuscarInsumos.requestFocus(); 
        } else {
            popMenuBuscarInsumos.setVisible(false);
        }
    }
    
    private void seleccionInsumoAgregarInsumoATabla(String nombreInsumo) {
        control.agregarInsumo(nombreInsumo);
    }
    
    @Override
    public void agregarInsumoTabla(InsumoResumenDTO insumoResumen) {
        DefaultTableModel modelo = (DefaultTableModel) tblInsumosCotizacion.getModel();
        Long idNuevo = insumoResumen.getId();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Long idExistente = (Long) modelo.getValueAt(i, 6);

            if (idExistente != null && idExistente.equals(idNuevo)) {
                return; 
            }
        }

        int numeroInsumo = modelo.getRowCount() + 1;
        String nombreInsumo = insumoResumen.getNombre();
        BigDecimal costoSugerido = insumoResumen.getPrecioSugerido();
        int cantidad = 1;
        BigDecimal subtotal = insumoResumen.getPrecioSugerido(); 

        modelo.addRow(new Object[]{
            numeroInsumo,
            nombreInsumo,
            costoSugerido,
            cantidad,
            subtotal,
            "Eliminar",
            idNuevo
        });

        recalcularTotales();
        crearBorradorCotizacion();
    }
    
    private void configurarTablaInsumos() {

        String[] columnas = {"No.", "Insumo", "Costo", "Cantidad", "Subtotal", "Acción", "ID_INSUMO"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (esCancelada) {
                    return false;
                }
                return column == 2 || column == 3 || column == 5;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0: case 3: return Integer.class;
                    case 2: case 4: return java.math.BigDecimal.class;
                    case 6: return Long.class;
                    default: return String.class;
                }
            }
        };
        
        tblInsumosCotizacion.setModel(modeloTabla);

        if (tblInsumosCotizacion.getColumnCount() > 6) {
            tblInsumosCotizacion.removeColumn(tblInsumosCotizacion.getColumnModel().getColumn(6));
        }

        DefaultTableCellRenderer rendererAzul = new DefaultTableCellRenderer() {
            Color colorAzulClaro = new Color(218, 235, 255);
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column == 5) return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                Component celda = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    celda.setBackground((row % 2 == 0) ? colorAzulClaro : Color.WHITE);
                }
                ((javax.swing.JComponent) celda).setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK),
                        BorderFactory.createEmptyBorder(0, 10, 0, 0)
                ));
                return celda;
            }
        };

        for (int i = 0; i <= 4; i++) {
            tblInsumosCotizacion.getColumnModel().getColumn(i).setCellRenderer(rendererAzul);
        }

        tblInsumosCotizacion.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tblInsumosCotizacion.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new javax.swing.JCheckBox()));

        tblInsumosCotizacion.setRowHeight(35);
        tblInsumosCotizacion.getTableHeader().setReorderingAllowed(false);
        tblInsumosCotizacion.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        tblInsumosCotizacion.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int col = e.getColumn();
                if (col == 2 || col == 3) {
                    javax.swing.SwingUtilities.invokeLater(() -> actualizarSubtotal(e.getFirstRow()));
                }
            }
        });
        
        tblInsumosCotizacion.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblInsumosCotizacion.getColumnModel().getColumn(0).setMaxWidth(60);

        tblInsumosCotizacion.getColumnModel().getColumn(1).setPreferredWidth(300);

        tblInsumosCotizacion.getColumnModel().getColumn(2).setPreferredWidth(100);

        tblInsumosCotizacion.getColumnModel().getColumn(3).setPreferredWidth(100);

        tblInsumosCotizacion.getColumnModel().getColumn(4).setPreferredWidth(100);

        tblInsumosCotizacion.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblInsumosCotizacion.getColumnModel().getColumn(5).setMinWidth(100);
        
        tblInsumosCotizacion.getColumnModel().getColumn(3).setCellEditor(new SpinnerEditor());
        
    }
    
    private void actualizarSubtotal(int fila) {
        DefaultTableModel modelo = (DefaultTableModel) tblInsumosCotizacion.getModel();
        
        try {
            Object valorCosto = modelo.getValueAt(fila, 2);
            Object valorCantidad = modelo.getValueAt(fila, 3);

            BigDecimal costo = new BigDecimal(valorCosto.toString());
            int cantidad = Integer.parseInt(valorCantidad.toString());

            BigDecimal subtotal = costo.multiply(new BigDecimal(cantidad));

            modelo.setValueAt(subtotal, fila, 4);
            
            recalcularTotales();
            
        } catch (NumberFormatException e) {
            modelo.setValueAt(BigDecimal.ZERO, fila, 4);
            recalcularTotales();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel8 = new javax.swing.JPanel();
        popMenuBuscarInsumos = new javax.swing.JPopupMenu();
        scrollPaneBuscarInsumos = new javax.swing.JScrollPane();
        listBuscarInsumos = new javax.swing.JList<>();
        panelEncabezado1 = new presentacion.vistas.PanelEncabezado();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNombreServicio = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInsumosCotizacion = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmpTxtTotalInsumos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmpTxtCostoManoObra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnAgregarInsumo = new javax.swing.JButton();
        cmpTxtBuscarInsumos = new javax.swing.JTextField();
        lblBuscarInsumo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        listBuscarInsumos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        scrollPaneBuscarInsumos.setViewportView(listBuscarInsumos);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 700));
        getContentPane().add(panelEncabezado1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(204, 255, 51));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Nueva Cotización");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 0, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText(" - ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        jPanel2.add(jLabel2, gridBagConstraints);

        lblNombreServicio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNombreServicio.setText("jLabel3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(lblNombreServicio, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setLayout(new java.awt.BorderLayout());

        tblInsumosCotizacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Insumo", "Costo", "Cantidad", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInsumosCotizacion);
        if (tblInsumosCotizacion.getColumnModel().getColumnCount() > 0) {
            tblInsumosCotizacion.getColumnModel().getColumn(0).setHeaderValue("No.");
            tblInsumosCotizacion.getColumnModel().getColumn(1).setHeaderValue("Insumo");
            tblInsumosCotizacion.getColumnModel().getColumn(2).setHeaderValue("Costo");
            tblInsumosCotizacion.getColumnModel().getColumn(3).setHeaderValue("Cantidad");
            tblInsumosCotizacion.getColumnModel().getColumn(4).setHeaderValue("Subtotal");
        }

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel3.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Total insumos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 111;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        jPanel7.add(jLabel3, gridBagConstraints);

        cmpTxtTotalInsumos.setEditable(false);
        cmpTxtTotalInsumos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmpTxtTotalInsumos.setText("jTextField1");
        cmpTxtTotalInsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpTxtTotalInsumosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 55);
        jPanel7.add(cmpTxtTotalInsumos, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Mano de obra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 21, 0, 25);
        jPanel7.add(jLabel4, gridBagConstraints);

        cmpTxtCostoManoObra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmpTxtCostoManoObra.setText("jTextField2");
        cmpTxtCostoManoObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpTxtCostoManoObraActionPerformed(evt);
            }
        });
        cmpTxtCostoManoObra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmpTxtCostoManoObraKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 55);
        jPanel7.add(cmpTxtCostoManoObra, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("TOTAL: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 0);
        jPanel7.add(jLabel5, gridBagConstraints);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotal.setText(" jLabel6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel7.add(lblTotal, gridBagConstraints);

        jLabel6.setText("Cantidades en pesos MXN.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 53);
        jPanel7.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel7, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        btnAgregarInsumo.setBackground(new java.awt.Color(203, 229, 255));
        btnAgregarInsumo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarInsumo.setText("Agregar Insumo");
        btnAgregarInsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarInsumoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        jPanel10.add(btnAgregarInsumo, gridBagConstraints);

        cmpTxtBuscarInsumos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmpTxtBuscarInsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmpTxtBuscarInsumosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 10);
        jPanel10.add(cmpTxtBuscarInsumos, gridBagConstraints);

        lblBuscarInsumo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBuscarInsumo.setText("Buscar insumo: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel10.add(lblBuscarInsumo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 30);
        jPanel3.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));
        jPanel4.setLayout(new java.awt.BorderLayout());

        btnActualizar.setBackground(new java.awt.Color(204, 255, 204));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setText("Guardar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(btnActualizar, java.awt.BorderLayout.LINE_END);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        btnVolver.setBackground(new java.awt.Color(255, 255, 204));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(btnVolver, gridBagConstraints);

        btnCancelar.setBackground(new java.awt.Color(255, 204, 204));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 55, 0, 0);
        jPanel5.add(btnCancelar, gridBagConstraints);

        jPanel4.add(jPanel5, java.awt.BorderLayout.LINE_START);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarCotizacion();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelarConsultarCotizacion();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmpTxtTotalInsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpTxtTotalInsumosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmpTxtTotalInsumosActionPerformed

    private void cmpTxtCostoManoObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpTxtCostoManoObraActionPerformed
        actualizarCostoManoObra();
    }//GEN-LAST:event_cmpTxtCostoManoObraActionPerformed

    private void cmpTxtCostoManoObraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmpTxtCostoManoObraKeyReleased
        actualizarCostoManoObra();
    }//GEN-LAST:event_cmpTxtCostoManoObraKeyReleased

    private void btnAgregarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInsumoActionPerformed
        agregarInsumo();
    }//GEN-LAST:event_btnAgregarInsumoActionPerformed

    private void cmpTxtBuscarInsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmpTxtBuscarInsumosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmpTxtBuscarInsumosActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volverConsultaCotizacion();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void agregarInsumo(){
        String textoBuscador = cmpTxtBuscarInsumos.getText().trim();
        
        if (!textoBuscador.isEmpty()) {
            seleccionInsumoAgregarInsumoATabla(textoBuscador);
            
            cmpTxtBuscarInsumos.setText("");
            popMenuBuscarInsumos.setVisible(false);
            cmpTxtBuscarInsumos.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un insumo antes de agregar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void volverConsultaCotizacion(){
        control.volverConsultarCotizacion();
    }
    
    private void cancelarConsultarCotizacion(){
        control.cancelarConsultarCotizacion();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarInsumo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JTextField cmpTxtBuscarInsumos;
    private javax.swing.JTextField cmpTxtCostoManoObra;
    private javax.swing.JTextField cmpTxtTotalInsumos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscarInsumo;
    private javax.swing.JLabel lblNombreServicio;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JList<String> listBuscarInsumos;
    private presentacion.vistas.PanelEncabezado panelEncabezado1;
    private javax.swing.JPopupMenu popMenuBuscarInsumos;
    private javax.swing.JScrollPane scrollPaneBuscarInsumos;
    private javax.swing.JTable tblInsumosCotizacion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void cargarCotizacionSeleccionada(CotizacionDetalleDTO cotizacion) {
        idCotizacion = cotizacion.getId();
        lblNombreServicio.setText(cotizacion.getNombreServicio());
        cmpTxtCostoManoObra.setText(cotizacion.getPrecioManoObra().toString());
        

        esCancelada = cotizacion.getEstado() != null && 
                      cotizacion.getEstado().name().equals("CANCELADA");

        cmpTxtCostoManoObra.setEditable(!esCancelada);
        
        lblBuscarInsumo.setVisible(!esCancelada);
        cmpTxtBuscarInsumos.setVisible(!esCancelada);
        btnAgregarInsumo.setVisible(!esCancelada);
        
        btnActualizar.setVisible(!esCancelada); 
        
        llenarTablaInsumos(cotizacion);
        
        gestionarColumnaAccion();
        
        recalcularTotales();
    }
    
    private void llenarTablaInsumos(CotizacionDetalleDTO cotizacion) {
        
        DefaultTableModel modelo = (DefaultTableModel) tblInsumosCotizacion.getModel();
        
        modelo.setRowCount(0);

        if (cotizacion != null && cotizacion.getInsumosCotizacion() != null) {
            int i = 1;
            for (InsumoCotizacionDetalleDTO insumoCotizacion : cotizacion.getInsumosCotizacion()) {
                modelo.addRow(new Object[]{
                    i++,
                    insumoCotizacion.getInsumo().getNombre(),
                    insumoCotizacion.getPrecio(),
                    insumoCotizacion.getCantidadRequerida(),
                    insumoCotizacion.getSubtotal(),
                    "Eliminar",
                    insumoCotizacion.getInsumo().getId()
                });
            }
        }
    }
    
    private void gestionarColumnaAccion() {

        TableColumnModel modeloColumnas = tblInsumosCotizacion.getColumnModel();

        int indiceColumna = -1;
        for (int i = 0; i < modeloColumnas.getColumnCount(); i++) {
            if (modeloColumnas.getColumn(i).getHeaderValue().toString().equalsIgnoreCase("Acción")) {
                indiceColumna = i;
                break;
            }
        }

        if (esCancelada && indiceColumna != -1) {
            modeloColumnas.removeColumn(modeloColumnas.getColumn(indiceColumna));
        }
    }
    
    private void recalcularTotales() {

        DefaultTableModel modelo = (DefaultTableModel) tblInsumosCotizacion.getModel();
        BigDecimal sumaInsumos = BigDecimal.ZERO;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valorSubtotal = modelo.getValueAt(i, 4);
            if (valorSubtotal != null) {
                BigDecimal subtotalFila = new BigDecimal(valorSubtotal.toString());
                sumaInsumos = sumaInsumos.add(subtotalFila);
            }
        }

        cmpTxtTotalInsumos.setText(sumaInsumos.setScale(2, java.math.RoundingMode.HALF_UP).toString());

        BigDecimal costoManoObra = BigDecimal.ZERO;
        try {
            String textoManoObra = cmpTxtCostoManoObra.getText();
            if (!textoManoObra.trim().isEmpty()) {
                costoManoObra = new BigDecimal(textoManoObra);
            }
        } catch (NumberFormatException e) {}

        BigDecimal totalGeneral = sumaInsumos.add(costoManoObra);

        lblTotal.setText(totalGeneral.setScale(2, java.math.RoundingMode.HALF_UP).toString());
    }
    
    private void actualizarCostoManoObra(){
        recalcularTotales();
        crearBorradorCotizacion();
    }
    
    private void crearBorradorCotizacion(){
        
        String totalInsumosS = cmpTxtTotalInsumos.getText();
        String costoManoObraS = cmpTxtCostoManoObra.getText();
        String totalS = lblTotal.getText();
        
        Double totalInsumosD = null;
        Double costoManoObraD = null;
        Double totalD = null;
        try{
            totalInsumosD = Double.valueOf(totalInsumosS);
            costoManoObraD = Double.valueOf(costoManoObraS);
            totalD = Double.valueOf(totalS);
            
        } catch(NumberFormatException e){}
        
        if(totalInsumosD != null && costoManoObraD != null && totalD != null){
            
            BigDecimal totalInsumos = BigDecimal.valueOf(totalInsumosD);
            BigDecimal costoManoObra = BigDecimal.valueOf(costoManoObraD);
            BigDecimal total = BigDecimal.valueOf(totalD);
            
            List<BorradorInsumoCotizacion> borradoresInsumoCotizacion = obtenerInsumosCotizacion();
            
            BorradorCotizacion borradorCotizacion = new BorradorCotizacion(idCotizacion, totalInsumos, costoManoObra, total, borradoresInsumoCotizacion);
         
            control.guardarCambioCotizacion(borradorCotizacion);
            
        }

    }
    
    private List<BorradorInsumoCotizacion> obtenerInsumosCotizacion(){
        
        List<BorradorInsumoCotizacion> borradoresInsumoCotizacion = new ArrayList<>();

        DefaultTableModel modelo = (DefaultTableModel) tblInsumosCotizacion.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {

            String nombre = modelo.getValueAt(i, 1).toString();

            BigDecimal costo = new BigDecimal(modelo.getValueAt(i, 2).toString());
            Integer cantidad = Integer.valueOf(modelo.getValueAt(i, 3).toString());
            BigDecimal subtotal = new BigDecimal(modelo.getValueAt(i, 4).toString());
            Long idInsumo = Long.valueOf(modelo.getValueAt(i, 6).toString());

            BorradorInsumoCotizacion borrador = new BorradorInsumoCotizacion(nombre, cantidad, costo, subtotal, idInsumo);

            borradoresInsumoCotizacion.add(borrador);
        }
        
        return borradoresInsumoCotizacion;
    }
    
    
    private void actualizarCotizacion(){
        crearBorradorCotizacion();
        control.actualizarCotizacion();
    }

    @Override
    public void mostrar() {
        setVisible(true);
    }

    @Override
    public void ocultar() {
        dispose();
    }

    @Override
    public void mostrarMensaje(String mensajeError) {
        JOptionPane.showMessageDialog(this, mensajeError, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void mostrarMensajeExito() {
        JOptionPane.showMessageDialog(this, "La cotización se ha actualizado.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        control.aceptarExitoActualizacionCotizacion();
    }
    
    // Clases para botón de eliminar
    private void reordenarNumerosTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblInsumosCotizacion.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(i + 1, i, 0);
        }
    }

    class ButtonRenderer extends javax.swing.JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(255, 102, 102)); // Rojo suave
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 12));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            if (esCancelada) {
                return new javax.swing.JLabel(""); 
            }
            
            setText((value == null) ? "Eliminar" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends javax.swing.DefaultCellEditor {
        protected javax.swing.JButton button;
        private boolean isPushed;
        private JTable table;
        private int currentRow;

        public ButtonEditor(javax.swing.JCheckBox checkBox) {
            super(checkBox);
            button = new javax.swing.JButton();
            button.setOpaque(true);
            button.setBackground(new Color(250, 226, 95));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Segoe UI", Font.BOLD, 12));
            
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.currentRow = row;
            button.setText("Eliminar");
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                isPushed = false; 
                
                javax.swing.SwingUtilities.invokeLater(() -> {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    
                    if (currentRow >= 0 && currentRow < model.getRowCount()) {
                        
                        if (table.isEditing()) {
                            table.getCellEditor().cancelCellEditing();
                        }
                        
                        model.removeRow(currentRow);
                        reordenarNumerosTabla();
                        recalcularTotales();
                        crearBorradorCotizacion();
                    }
                });
            }
            return "Eliminar";
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
    
    class SpinnerEditor extends javax.swing.DefaultCellEditor {
        private javax.swing.JSpinner spinner;

        public SpinnerEditor() {
            super(new javax.swing.JTextField());

            javax.swing.SpinnerNumberModel model = new javax.swing.SpinnerNumberModel(1, 1, 9999, 1);
            spinner = new javax.swing.JSpinner(model);
            spinner.setBorder(null);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }

}
