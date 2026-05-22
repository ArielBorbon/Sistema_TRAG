/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.vistas;

import dtos.automovil.AutomovilAgregarDTO;
import dtos.cliente.ClienteResumenDTO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import presentacion.controles.ControlAgregarAutomovil;

/**
 *
 * @author PC Gamer
 */
public class VistaAgregarVehiculo extends javax.swing.JFrame {

    private ControlAgregarAutomovil control;

    public VistaAgregarVehiculo(ControlAgregarAutomovil control) {
        this.control = control;
        initComponents();
        configurarRenderCombo();
        btnGuardar.setEnabled(false);
        lblErrores.setForeground(java.awt.Color.RED);
        lblErrores.setText(" ");
        agregarListenersValidacion();

        ImageIcon iconoOriginalAutomovil = new ImageIcon(getClass().getResource("/automovil.png"));

        int ancho = imgAutomovil.getWidth();
        int alto = imgAutomovil.getHeight();

        if (ancho <= 0) {
            ancho = 264;
        }
        if (alto <= 0) {
            alto = 262;
        }

        Image imgEscaladaAutomovil = iconoOriginalAutomovil.getImage()
                .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        imgAutomovil.setIcon(new javax.swing.ImageIcon(imgEscaladaAutomovil));

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

    private void resetearErrores() {
        Border bordeNormal = UIManager.getBorder("TextField.border");

        txtMarca.setBorder(bordeNormal);
        txtModelo.setBorder(bordeNormal);
        txtAnio.setBorder(bordeNormal);
        txtMatricula.setBorder(bordeNormal);
        txtNiv.setBorder(bordeNormal);

        lblErrorMarca.setText(" ");
        lblErrorModelo.setText(" ");
        lblErrorAnio.setText(" ");
        lblErrorMatricula.setText("");
        lblErrorNIV.setText(" ");
        lblErrorDueno.setText(" ");
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
        resetearErrores();

        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String anioStr = txtAnio.getText().trim();
        String matricula = txtMatricula.getText().trim().toUpperCase();
        String niv = txtNiv.getText().trim().toUpperCase();
        ClienteResumenDTO cliente = (ClienteResumenDTO) cmbDueno.getSelectedItem();

        Border bordeError = BorderFactory.createLineBorder(java.awt.Color.RED, 2);
        boolean formularioValido = true;

        String regexTextoBase = "^[a-zA-Z0-9 áéíóúÁÉÍÓÚñÑ-]+$";
        String regexMatricula = "^[A-Z0-9-]{4,10}$";
        String regexAnio = "^(19|20)\\d{2}$";
        String regexNiv = "^[A-HJ-NPR-Z0-9]{17}$";

        if (marca.isEmpty() || !marca.matches(regexTextoBase)) {
            txtMarca.setBorder(bordeError);
            lblErrorMarca.setText("Marca inválida.");
            formularioValido = false;
        }

        if (modelo.isEmpty() || !modelo.matches(regexTextoBase)) {
            txtModelo.setBorder(bordeError);
            lblErrorModelo.setText("Modelo inválido.");
            formularioValido = false;
        }

        if (anioStr.isEmpty() || !anioStr.matches(regexAnio)) {
            txtAnio.setBorder(bordeError);
            lblErrorAnio.setText("Año inválido.");
            formularioValido = false;
        } else {
            int anio = Integer.parseInt(anioStr);
            int anioActual = java.time.Year.now().getValue();
            if (anio < 1950 || anio > (anioActual + 1)) {
                txtAnio.setBorder(bordeError);
                lblErrorAnio.setText("Año fuera de rango.");
                formularioValido = false;
            }
        }

        if (matricula.isEmpty() || !matricula.matches(regexMatricula)) {
            txtMatricula.setBorder(bordeError);
            lblErrorMatricula.setText("Matricula Invalida");
            formularioValido = false;
        }

        if (niv.isEmpty() || !niv.matches(regexNiv)) {
            txtNiv.setBorder(bordeError);
            lblErrorNIV.setText("NIV inválido (17 caracteres).");
            formularioValido = false;
        }

        if (cliente == null) {
            lblErrorDueno.setText("Seleccione un dueño.");
            formularioValido = false;
        }

        btnGuardar.setEnabled(formularioValido);
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
        lblErrorModelo = new javax.swing.JLabel();
        lblErrorMatricula = new javax.swing.JLabel();
        lblErrorMarca = new javax.swing.JLabel();
        lblErrorNIV = new javax.swing.JLabel();
        lblErrorDueno = new javax.swing.JLabel();
        lblErrorAnio = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(panelEncabezado1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Nuevo Vehiculo");
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

        lblErrorModelo.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorMatricula.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorMarca.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorNIV.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorDueno.setForeground(new java.awt.Color(255, 51, 51));

        lblErrorAnio.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(imgAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatricula)
                            .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(lblErrorModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrorMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrorMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNiv)
                            .addComponent(txtAnio)
                            .addComponent(cmbDueno, 0, 253, Short.MAX_VALUE)
                            .addComponent(lblErrorAnio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrorNIV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblErrorDueno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(79, 79, 79))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblErrores)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErrorMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblErrorNIV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblErrorModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblErrorAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbDueno, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(278, 278, 278)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblErrorMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                            .addComponent(lblErrorDueno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(imgAutomovil, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
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
        btnGuardar.setText("Guardar");
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

        AutomovilAgregarDTO dto = new AutomovilAgregarDTO();
        dto.setAnio(anioInt);
        dto.setMarca(marca);
        dto.setMatricula(matricula);
        dto.setModelo(modelo);
        dto.setVin(vin);
        dto.setIdCliente(clienteSeleccionado.getId());

        control.guardarAutomovil(dto, clienteSeleccionado);


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
    private javax.swing.JLabel lblErrorAnio;
    private javax.swing.JLabel lblErrorDueno;
    private javax.swing.JLabel lblErrorMarca;
    private javax.swing.JLabel lblErrorMatricula;
    private javax.swing.JLabel lblErrorModelo;
    private javax.swing.JLabel lblErrorNIV;
    private javax.swing.JLabel lblErrores;
    private presentacion.vistas.PanelEncabezado panelEncabezado1;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNiv;
    // End of variables declaration//GEN-END:variables
}
