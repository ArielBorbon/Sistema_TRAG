/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.vistas;

import dtos.automovil.AutomovilActualizarDTO;
import dtos.automovil.AutomovilDetalleDTO;
import dtos.cliente.ClienteResumenDTO;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.Border;
import presentacion.controles.ControlEditarAutomovil;

/**
 *
 * @author PC Gamer
 */
public class VistaEditarVehiculo extends javax.swing.JFrame {

    private ControlEditarAutomovil control;

    public VistaEditarVehiculo(ControlEditarAutomovil control) {
        this.control = control;
        initComponents();
        configurarRenderCombo();
        btnGuardar.setEnabled(false);
        lblErrores.setForeground(java.awt.Color.RED);
        lblErrores.setText(" ");
        agregarListenersValidacion();
    }

    public void cargarComboClientes(List<ClienteResumenDTO> clientes) {
        DefaultComboBoxModel<ClienteResumenDTO> modeloCombo = new DefaultComboBoxModel<>();
        for (ClienteResumenDTO cliente : clientes) {
            modeloCombo.addElement(cliente);
        }
        cmbDueno.setModel(modeloCombo);
    }

    private void configurarRenderCombo() {
        cmbDueno.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ClienteResumenDTO) {
                    ClienteResumenDTO cliente = (ClienteResumenDTO) value;
                    setText(cliente.getNombre() + " " + cliente.getApellidoPaterno());
                }
                return this;
            }
        });
    }

    public void cargarDatosAutomovil(AutomovilDetalleDTO auto) {
        txtMarca.setText(auto.getMarca());
        txtModelo.setText(auto.getModelo());
        txtAnio.setText(String.valueOf(auto.getAnio()));
        txtMatricula.setText(auto.getMatricula());
        txtNiv.setText(auto.getVin());

        for (int i = 0; i < cmbDueno.getItemCount(); i++) {
            ClienteResumenDTO cliente = cmbDueno.getItemAt(i);
            if (cliente.getId().equals(auto.getIdCliente())) {
                cmbDueno.setSelectedIndex(i);
                break;
            }
        }
    }

    private void agregarListenersValidacion() {
        javax.swing.event.DocumentListener listener = new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                validarFormulario();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                validarFormulario();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                validarFormulario();
            }
        };

        txtMarca.getDocument().addDocumentListener(listener);
        txtModelo.getDocument().addDocumentListener(listener);
        txtAnio.getDocument().addDocumentListener(listener);
        txtMatricula.getDocument().addDocumentListener(listener);
        txtNiv.getDocument().addDocumentListener(listener);

        cmbDueno.addItemListener(e -> validarFormulario());
    }

    private void validarFormulario() {
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String anioStr = txtAnio.getText().trim();
        String matricula = txtMatricula.getText().trim().toUpperCase();
        String niv = txtNiv.getText().trim().toUpperCase();
        ClienteResumenDTO cliente = (ClienteResumenDTO) cmbDueno.getSelectedItem();

        Border bordeError = BorderFactory.createLineBorder(java.awt.Color.RED, 2);
        Border bordeNormal = UIManager.getBorder("TextField.border");

        txtMarca.setBorder(bordeNormal);
        txtModelo.setBorder(bordeNormal);
        txtAnio.setBorder(bordeNormal);
        txtMatricula.setBorder(bordeNormal);
        txtNiv.setBorder(bordeNormal);

        String regexTextoBase = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑ-]+$";
        String regexMatricula = "^[A-Z0-9-]{4,10}$";
        String regexAnio = "^(19|20)\\d{2}$";
        String regexNiv = "^[A-HJ-NPR-Z0-9]{17}$";

        if (marca.isEmpty() || !marca.matches(regexTextoBase)) {
            mostrarError(txtMarca, bordeError, "La marca es requerida y no debe contener caracteres especiales raros.");
            return;
        }

        if (modelo.isEmpty() || !modelo.matches(regexTextoBase)) {
            mostrarError(txtModelo, bordeError, "El modelo es requerido y no debe contener caracteres especiales raros.");
            return;
        }

        if (anioStr.isEmpty() || !anioStr.matches(regexAnio)) {
            mostrarError(txtAnio, bordeError, "El año debe ser un número de 4 dígitos válido (ej. 2018).");
            return;
        } else {
            int anio = Integer.parseInt(anioStr);
            int anioActual = java.time.Year.now().getValue();
            if (anio < 1950 || anio > (anioActual + 1)) {
                mostrarError(txtAnio, bordeError, "El año debe estar entre 1950 y " + (anioActual + 1) + ".");
                return;
            }
        }

        if (matricula.isEmpty() || !matricula.matches(regexMatricula)) {
            mostrarError(txtMatricula, bordeError, "Matrícula inválida. Usa letras mayúsculas, números y guiones (4-10 caracteres).");
            return;
        }

        if (niv.isEmpty() || !niv.matches(regexNiv)) {
            mostrarError(txtNiv, bordeError, "El NIV (VIN) debe tener exactamente 17 caracteres (no usar I, O, Q).");
            return;
        }

        if (cliente == null) {
            lblErrores.setText("Seleccione un dueño para el vehículo.");
            btnGuardar.setEnabled(false);
            return;
        }

        lblErrores.setText(" ");
        btnGuardar.setEnabled(true);
    }

    private void mostrarError(javax.swing.JTextField campo, javax.swing.border.Border borde, String mensaje) {
        campo.setBorder(borde);
        lblErrores.setText(mensaje);
        btnGuardar.setEnabled(false);
    }

    public void mostrarErrorBaseDatos(String mensajeError) {
        lblErrores.setText(mensajeError);
        btnGuardar.setEnabled(false);
        txtNiv.setBorder(BorderFactory.createLineBorder(java.awt.Color.RED, 2));
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

        panelEncabezado1 = new presentacion.vistas.PanelEncabezado();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        imgAutomovil = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNiv = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbDueno = new javax.swing.JComboBox<ClienteResumenDTO>();
        lblErrores = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(panelEncabezado1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Editar Vehiculo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(25, 40, 16, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        ImageIcon iconoOriginalAutomovil = new javax.swing.ImageIcon(getClass().getResource("/automovil.png"));
        java.awt.Image imgEscaladaAutomovil = iconoOriginalAutomovil.getImage().getScaledInstance(100, 57, java.awt.Image.SCALE_SMOOTH);
        imgAutomovil.setIcon(new javax.swing.ImageIcon(imgEscaladaAutomovil));
        imgAutomovil.setMaximumSize(new java.awt.Dimension(100, 80));
        imgAutomovil.setMinimumSize(new java.awt.Dimension(100, 80));
        imgAutomovil.setPreferredSize(new java.awt.Dimension(100, 80));
        Dimension dimensionAutomovil = new Dimension(100, 80);
        imgAutomovil.setPreferredSize(dimensionAutomovil);
        imgAutomovil.setMaximumSize(dimensionAutomovil);
        imgAutomovil.setMinimumSize(dimensionAutomovil);
        imgAutomovil.setSize(dimensionAutomovil);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Marca");

        txtModelo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });

        txtMarca.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Modelo");

        txtMatricula.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatriculaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Matricula");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Dueño");

        txtAnio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("Año");

        txtNiv.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtNiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNivActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("NIV");

        cmbDueno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbDueno.setModel(new DefaultComboBoxModel<ClienteResumenDTO>());

        lblErrores.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblErrores.setText("jLabel2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(imgAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNiv)
                            .addComponent(txtAnio)
                            .addComponent(cmbDueno, 0, 253, Short.MAX_VALUE)))
                    .addComponent(lblErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(79, 79, 79))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblErrores)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(26, 26, 26)
                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(26, 26, 26)
                        .addComponent(cmbDueno, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(26, 26, 26)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imgAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(86, 86, 86))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));
        jPanel4.setLayout(new java.awt.BorderLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 255, 204));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Volver");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar, java.awt.BorderLayout.LINE_START);

        btnGuardar.setBackground(new java.awt.Color(204, 255, 204));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Actualizar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar, java.awt.BorderLayout.LINE_END);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        control.cerrarVista();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        String anio = txtAnio.getText();
        String marca = txtMarca.getText();
        String matricula = txtMatricula.getText();
        String modelo = txtModelo.getText();
        String vin = txtNiv.getText();

        ClienteResumenDTO clienteSeleccionado = (ClienteResumenDTO) cmbDueno.getSelectedItem();

        int anioInt = Integer.parseInt(anio.trim());

        AutomovilActualizarDTO dto = new AutomovilActualizarDTO();
        dto.setAnio(anioInt);
        dto.setMarca(marca);
        dto.setMatricula(matricula);
        dto.setModelo(modelo);
        dto.setVin(vin);
        dto.setIdCliente(clienteSeleccionado.getId());

        control.actualizarAutomovil(dto);


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void txtNivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNivActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNivActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<ClienteResumenDTO> cmbDueno;
    private javax.swing.JLabel imgAutomovil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblErrores;
    private presentacion.vistas.PanelEncabezado panelEncabezado1;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNiv;
    // End of variables declaration//GEN-END:variables
}
