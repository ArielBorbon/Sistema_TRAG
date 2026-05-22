
package presentacion.vistas;

import dtos.automovil.AutomovilResumenDTO;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import presentacion.controles.ControlAdministrarAutomoviles;
import presentacion.vistas.VistaCrearCotizacion.ButtonEditor;
import presentacion.vistas.VistaCrearCotizacion.ButtonRenderer;

/**
 *
 * @author PC Gamer
 */
public class VistaAutomoviles extends javax.swing.JFrame {

    private ControlAdministrarAutomoviles control;
    private List<AutomovilResumenDTO> autosActuales;

    public VistaAutomoviles(ControlAdministrarAutomoviles control) {
        this.control = control;
        initComponents();
        setLocationRelativeTo(null);
        configurarTablaAutomoviles();
    }

    public void llenarTabla(List<AutomovilResumenDTO> autos) {
        DefaultTableModel modelo = (DefaultTableModel) tblAutomoviles.getModel();
        modelo.setRowCount(0);

        for (AutomovilResumenDTO auto : autos) {
            Object[] fila = {
                auto.getId(),
                auto.getMarca(), 
                auto.getModelo(), 
                auto.getAnio(),
                auto.getMatricula(),
                auto.getNombreDueno(), 
                "Eliminar" 
            };
            modelo.addRow(fila);
        }
    }

    private AutomovilResumenDTO obtenerAutoSeleccionado() {
        int filaSeleccionada = tblAutomoviles.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un vehículo de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return autosActuales.get(filaSeleccionada);
    }

    public void actualizarDatos() {
        control.cargarTabla();
    }

    private void configurarTablaAutomoviles() {
        String[] columnas = {"ID", "Marca", "Modelo", "Año", "Matrícula", "Dueño", "Acción"};

        javax.swing.table.DefaultTableModel modeloTabla = new javax.swing.table.DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; 
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Long.class;
                }
                if (columnIndex == 3) {
                    return Integer.class; 
                }
                return String.class;
            }
        };

        tblAutomoviles.setModel(modeloTabla);
        tblAutomoviles.setRowHeight(35);
        tblAutomoviles.getTableHeader().setReorderingAllowed(false);
        tblAutomoviles.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        java.awt.Color colorAzulClaro = new java.awt.Color(218, 235, 255);

        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component celda = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    celda.setBackground((row % 2 == 0) ? colorAzulClaro : java.awt.Color.WHITE);
                }
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                ((javax.swing.JComponent) celda).setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, java.awt.Color.BLACK),
                        javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)
                ));
                return celda;
            }
        };

        for (int i = 0; i < tblAutomoviles.getColumnCount(); i++) {
            if (i != 6) {
                tblAutomoviles.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        tblAutomoviles.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tblAutomoviles.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new javax.swing.JCheckBox()));

        tblAutomoviles.setShowGrid(false);
        tblAutomoviles.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        tblAutomoviles.getColumnModel().getColumn(0).setMinWidth(0);
        tblAutomoviles.getColumnModel().getColumn(0).setMaxWidth(0);
        tblAutomoviles.getColumnModel().getColumn(0).setWidth(0);

        tblAutomoviles.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblAutomoviles.getColumnModel().getColumn(2).setPreferredWidth(150); 
        tblAutomoviles.getColumnModel().getColumn(3).setPreferredWidth(70);  
        tblAutomoviles.getColumnModel().getColumn(4).setPreferredWidth(100); 
        tblAutomoviles.getColumnModel().getColumn(5).setPreferredWidth(180); 
        tblAutomoviles.getColumnModel().getColumn(6).setPreferredWidth(100); 
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAutomoviles = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnEditarVehiculo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cmptxtBuscarVehiculos = new javax.swing.JTextField();
        lblBuscarClientes = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNombreServicio = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 30));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 89));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 89));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 89));
        jPanel1.setLayout(new java.awt.BorderLayout());

        ImageIcon iconoOriginal = new javax.swing.ImageIcon(getClass().getResource("/logo.png"));
        java.awt.Image imgEscalada = iconoOriginal.getImage().getScaledInstance(100, 57, java.awt.Image.SCALE_SMOOTH);
        jLabel1.setIcon(new javax.swing.ImageIcon(imgEscalada));
        jLabel1.setToolTipText("");
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SGISCA");
        jPanel1.add(jLabel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));
        jPanel6.setLayout(new java.awt.BorderLayout());

        tblAutomoviles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "Año", "Matricula", "Dueño"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAutomoviles);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel3.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        btnEditarVehiculo.setBackground(new java.awt.Color(204, 255, 204));
        btnEditarVehiculo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditarVehiculo.setText("Editar Vehiculo");
        btnEditarVehiculo.setPreferredSize(new java.awt.Dimension(200, 40));
        btnEditarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarVehiculoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 31, 0);
        jPanel7.add(btnEditarVehiculo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jPanel7, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        cmptxtBuscarVehiculos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmptxtBuscarVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmptxtBuscarVehiculosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 15, 10);
        jPanel10.add(cmptxtBuscarVehiculos, gridBagConstraints);

        lblBuscarClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBuscarClientes.setText("Buscar por nombre: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel10.add(lblBuscarClientes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 0, 30);
        jPanel3.add(jPanel10, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Vehiculos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        jPanel2.add(jLabel4, gridBagConstraints);

        lblNombreServicio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(lblNombreServicio, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));
        jPanel4.setLayout(new java.awt.BorderLayout());

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

        jPanel4.add(jPanel5, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarVehiculoActionPerformed
        int filaSeleccionada = tblAutomoviles.getSelectedRow();
        if (filaSeleccionada != -1) {
            Long idAuto = (Long) tblAutomoviles.getValueAt(filaSeleccionada, 0);
            control.abrirEditarVehiculo(idAuto);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un vehículo de la tabla para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnEditarVehiculoActionPerformed

    private void cmptxtBuscarVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmptxtBuscarVehiculosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmptxtBuscarVehiculosActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();
        control.volverMenuPrincipalAutos();

    }//GEN-LAST:event_btnVolverActionPerformed

    class ButtonRenderer extends javax.swing.JButton implements javax.swing.table.TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new java.awt.Color(255, 102, 102));
            setForeground(java.awt.Color.WHITE);
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Eliminar" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends javax.swing.DefaultCellEditor {

        protected javax.swing.JButton button;
        private boolean isPushed;
        private javax.swing.JTable table;
        private int currentRow;

        public ButtonEditor(javax.swing.JCheckBox checkBox) {
            super(checkBox);
            button = new javax.swing.JButton();
            button.setOpaque(true);
            button.setBackground(new java.awt.Color(255, 102, 102));
            button.setForeground(java.awt.Color.WHITE);
            button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
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
                    if (table.isEditing()) {
                        table.getCellEditor().cancelCellEditing();
                    }

                    Long idAuto = (Long) table.getModel().getValueAt(currentRow, 0);
                    String marca = (String) table.getModel().getValueAt(currentRow, 1);
                    Integer anio = (Integer) table.getModel().getValueAt(currentRow, 3);
                    String matricula = (String) table.getModel().getValueAt(currentRow, 4);
                    String dueno = (String) table.getModel().getValueAt(currentRow, 5);

                    String mensaje = String.format("¿Seguro que quieres eliminar el vehículo (%s, %d, placas %s de %s)?", marca, anio, matricula, dueno);

                    int confirmacion = JOptionPane.showConfirmDialog(VistaAutomoviles.this, mensaje, "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        control.eliminarVehiculo(idAuto);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarVehiculo;
    private javax.swing.JButton btnVolver;
    private javax.swing.JTextField cmptxtBuscarVehiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscarClientes;
    private javax.swing.JLabel lblNombreServicio;
    private javax.swing.JTable tblAutomoviles;
    // End of variables declaration//GEN-END:variables
}
