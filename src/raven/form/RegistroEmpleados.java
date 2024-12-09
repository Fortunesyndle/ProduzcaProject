/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.form;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import raven.conexion.Conexion;

/**
 *
 * @author User
 */
public class RegistroEmpleados extends javax.swing.JPanel {

    /**
     * Creates new form P_Tesis
     */
    public RegistroEmpleados() {
        initComponents();
        
        
        cedulaField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         nombresField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         apellidosField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         turnoField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         edadField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         direccionField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         turnoField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panelBorder3.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         
         panelBorder5.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         telefonoField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         cargoField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         emailField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         trabajoField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         generoField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         jButton3.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         
    }
    
    
    private void registrarEmpleado() {
        String cedula = cedulaField.getText().trim();
        String nombres = nombresField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        String edad = edadField.getText().trim();
        String genero = generoField.getText().trim();
        String direccion = direccionField.getText().trim();
        String turno = turnoField.getText().trim();
        String telefono = telefonoField.getText().trim();
        String email = emailField.getText().trim();
        String cargo = cargoField.getText().trim();
        String horario = turnoField.getText().trim();

        // Validar que todos los campos estén llenos
        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || edad.isEmpty() || genero.isEmpty() || direccion.isEmpty() ||
            turno.isEmpty() || telefono.isEmpty() || email.isEmpty() || cargo.isEmpty() || horario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Conectar a la base de datos y registrar el empleado
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
            String sql = "INSERT INTO empleados (Cedula, Nombres, Apellidos, Edad, Genero, Direccion, Turno, Telefono, Email, Cargo, Horario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(cedula));
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setInt(4, Integer.parseInt(edad)); // Convertimos a entero
            ps.setString(5, genero);
            ps.setString(6, direccion);
            ps.setString(7, turno);
            ps.setString(8, telefono);
            ps.setString(9, email);
            ps.setString(10, cargo);
            ps.setString(11, horario);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Empleado registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos después del registro exitoso
            cedulaField.setText("");
            nombresField.setText("");
            apellidosField.setText("");
            edadField.setText("");
            generoField.setText("");
            direccionField.setText("");
            turnoField.setText("");
            telefonoField.setText("");
            emailField.setText("");
            cargoField.setText("");
            turnoField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el empleado:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
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

        jPanel1 = new javax.swing.JPanel();
        panel1 = new com.raven.swing.PanelBorder();
        jLabel19 = new javax.swing.JLabel();
        panel = new com.raven.swing.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        cedulaField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panelBorder3 = new com.raven.swing.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        nombresField = new javax.swing.JTextField();
        apellidosField = new javax.swing.JTextField();
        edadField = new javax.swing.JTextField();
        direccionField = new javax.swing.JTextField();
        turnoField = new javax.swing.JTextField();
        generoField = new javax.swing.JTextField();
        panelBorder5 = new com.raven.swing.PanelBorder();
        jLabel16 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        trabajoField = new javax.swing.JTextField();
        cargoField = new javax.swing.JTextField();
        telefonoField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel19)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        panel.setBackground(new java.awt.Color(20, 33, 100));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cédula de indentidad");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cédula");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(cedulaField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(8, 8, 8)
                .addComponent(cedulaField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        panelBorder3.setBackground(new java.awt.Color(20, 33, 100));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Información personal");

        nombresField.setText("Nombres:");
        nombresField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombresFieldActionPerformed(evt);
            }
        });

        apellidosField.setText("Apellidos:");
        apellidosField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidosFieldActionPerformed(evt);
            }
        });

        edadField.setText("Edad:");
        edadField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edadFieldActionPerformed(evt);
            }
        });

        direccionField.setText("Dirección:");
        direccionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionFieldActionPerformed(evt);
            }
        });

        turnoField.setText("Turno:");
        turnoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turnoFieldActionPerformed(evt);
            }
        });

        generoField.setText("Género:");
        generoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generoFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(generoField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombresField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(apellidosField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(direccionField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder3Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(edadField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(turnoField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombresField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidosField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edadField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccionField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generoField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(turnoField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109))
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(127, 127, 127));
        jLabel16.setText("Cargo laboral");

        cargoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoFieldActionPerformed(evt);
            }
        });

        jLabel17.setText("Teléfono");

        jLabel18.setText("Cargo");

        jLabel20.setText("E-mail");

        jLabel21.setText("Horario de trabajo");

        jButton3.setBackground(new java.awt.Color(20, 33, 100));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Registrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/llooo.jpg"))); // NOI18N
        jLabel23.setText("jLabel10");

        jLabel24.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(127, 127, 127));
        jLabel24.setText("Información de contacto");

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel20)
                                .addComponent(jLabel17)
                                .addComponent(telefonoField)
                                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24))
                        .addGap(62, 62, 62)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trabajoField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cargoField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(247, 247, 247)))
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel24))
                .addGap(37, 37, 37)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(trabajoField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nombresFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombresFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombresFieldActionPerformed

    private void apellidosFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidosFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidosFieldActionPerformed

    private void edadFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edadFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edadFieldActionPerformed

    private void direccionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionFieldActionPerformed

    private void turnoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turnoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_turnoFieldActionPerformed

    private void generoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generoFieldActionPerformed

    private void cargoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cargoFieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        registrarEmpleado();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidosField;
    private javax.swing.JTextField cargoField;
    private javax.swing.JTextField cedulaField;
    private javax.swing.JTextField direccionField;
    private javax.swing.JTextField edadField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField generoField;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombresField;
    private com.raven.swing.PanelBorder panel;
    private com.raven.swing.PanelBorder panel1;
    private com.raven.swing.PanelBorder panelBorder3;
    private com.raven.swing.PanelBorder panelBorder5;
    private javax.swing.JTextField telefonoField;
    private javax.swing.JTextField trabajoField;
    private javax.swing.JTextField turnoField;
    // End of variables declaration//GEN-END:variables
}
