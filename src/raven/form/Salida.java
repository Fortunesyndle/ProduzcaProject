package raven.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.model.Model_Card;

import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import raven.conexion.Conexion;
import static raven.form.Tesis.eliminar;

public class Salida extends javax.swing.JPanel {

    public Salida() {
        initComponents();
    
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
       
    }
   
    private String texto(JTextField jff) {

        String txt = jff.getText().trim();
        return txt;

    }

    private void blank(JTextField jff) {
        jff.setText("");
    }
    private void fieldenblanco(JTextField JF) {
        String txt = JF.getText();

        if (JF.getText().equals(txt)) {
            JF.setText("");
            JF.setForeground(Color.black);

        }
    }
    
   private void registrarSalida() {
    String cedula = ingresarCedula.getText().trim();

    if (cedula.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa la cédula.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
        String sqlEmpleado = "SELECT Cedula, Nombres, Apellidos, Cargo, Turno, Salida FROM empleados WHERE Cedula = ?";
        ps = conexion.prepareStatement(sqlEmpleado);
        ps.setString(1, cedula);
        rs = ps.executeQuery();

        if (rs.next()) {
            cedula = rs.getString("Cedula");
            String nombres = rs.getString("Nombres");
            String apellidos = rs.getString("Apellidos");
            String cargo = rs.getString("Cargo");
            String turno = rs.getString("Turno");
            String salida = rs.getString("Salida");
            
            // Mostrar los datos en los textfields
            cedulatxt.setText(cedula);
            nombretxt.setText(nombres);
            apellidostxt.setText(apellidos);
            cargotxt.setText(cargo);
            turnotxt.setText(turno);
            salidatxt.setText(salida);

            // Obtener la hora actual de salida
            LocalTime horaSalida = LocalTime.now();
            turnotxt.setText(horaSalida.toString());

            // Verificar si el empleado salió a tiempo
            LocalTime horaLimite = LocalTime.parse(salida);
            boolean salioTemprano = horaSalida.isBefore(horaLimite);
            String estadoPuntualidad = salioTemprano ? "Temprano" : "Puntual";

            // Registrar la salida en la base de datos
            String sqlRegistro = "INSERT INTO salida (Cedula, Nombres, Apellidos, Fecha, Hora, Puntualidad) VALUES (?, ?, ?, CURRENT_DATE(), CURRENT_TIME(), ?)";
            PreparedStatement psRegistro = conexion.prepareStatement(sqlRegistro);
            psRegistro.setString(1, cedula);
            psRegistro.setString(2, nombres);
            psRegistro.setString(3, apellidos);
            psRegistro.setString(4, estadoPuntualidad);
            psRegistro.executeUpdate();

            // Mostrar mensaje de salida
            String mensaje = salioTemprano ? "El empleado salió temprano." : "El empleado salió puntual.";
            JOptionPane.showMessageDialog(this, mensaje, "Registro de Salida", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al registrar la salida:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cerrar la conexión:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        panel1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        nombrelbl = new javax.swing.JLabel();
        apellidolbl = new javax.swing.JLabel();
        cedulalbl = new javax.swing.JLabel();
        jornadalbl = new javax.swing.JLabel();
        salidalbl = new javax.swing.JLabel();
        idlbl = new javax.swing.JLabel();
        observacioneslbl = new javax.swing.JLabel();
        nombretxt = new javax.swing.JTextField();
        apellidostxt = new javax.swing.JTextField();
        cedulatxt = new javax.swing.JTextField();
        cargotxt = new javax.swing.JTextField();
        turnotxt = new javax.swing.JTextField();
        salidatxt = new javax.swing.JTextField();
        observacionestxt = new javax.swing.JTextField();
        salidabtn = new javax.swing.JButton();
        ingresarCedula = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nombrelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nombrelbl.setText("Nombres");

        apellidolbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        apellidolbl.setText("Apellidos");

        cedulalbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cedulalbl.setText("Cédula");

        jornadalbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jornadalbl.setText("Cargo");

        salidalbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        salidalbl.setText("Hora registrada");

        idlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        idlbl.setText("Hora de salida");

        observacioneslbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        observacioneslbl.setText("Observaciones");

        nombretxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        apellidostxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        cedulatxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        cargotxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        turnotxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        salidatxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));
        salidatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidatxtActionPerformed(evt);
            }
        });

        observacionestxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));

        salidabtn.setBackground(new java.awt.Color(0, 51, 255));
        salidabtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salidabtn.setForeground(new java.awt.Color(255, 255, 255));
        salidabtn.setText("Aceptar");
        salidabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidabtnActionPerformed(evt);
            }
        });

        ingresarCedula.setForeground(new java.awt.Color(102, 102, 102));
        ingresarCedula.setText("  Cedula...");
        ingresarCedula.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));
        ingresarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarCedulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(ingresarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(nombrelbl)
                        .addGap(43, 43, 43)
                        .addComponent(nombretxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(apellidolbl)
                        .addGap(41, 41, 41)
                        .addComponent(apellidostxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(cedulalbl)
                        .addGap(42, 42, 42)
                        .addComponent(cedulatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(jornadalbl)
                        .addGap(40, 40, 40)
                        .addComponent(cargotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(salidalbl)
                        .addGap(37, 37, 37)
                        .addComponent(turnotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(idlbl)
                        .addGap(38, 38, 38)
                        .addComponent(salidatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(observacioneslbl)
                        .addGap(36, 36, 36)
                        .addComponent(observacionestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(salidabtn)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(ingresarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombrelbl)
                    .addComponent(nombretxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(apellidolbl)
                    .addComponent(apellidostxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cedulalbl)
                    .addComponent(cedulatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jornadalbl)
                    .addComponent(cargotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salidalbl)
                    .addComponent(turnotxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idlbl)
                    .addComponent(salidatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(observacioneslbl)
                    .addComponent(observacionestxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(salidabtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Salidas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(lb)
                .addContainerGap(308, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void salidatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidatxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salidatxtActionPerformed

    private void salidabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidabtnActionPerformed
        // TODO add your handling code here:
        registrarSalida();
    }//GEN-LAST:event_salidabtnActionPerformed

    private void ingresarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresarCedulaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel apellidolbl;
    private javax.swing.JTextField apellidostxt;
    private javax.swing.JTextField cargotxt;
    private javax.swing.JLabel cedulalbl;
    private javax.swing.JTextField cedulatxt;
    private javax.swing.JLabel idlbl;
    private javax.swing.JTextField ingresarCedula;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jornadalbl;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel nombrelbl;
    private javax.swing.JTextField nombretxt;
    private javax.swing.JLabel observacioneslbl;
    private javax.swing.JTextField observacionestxt;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JLayeredPane panel1;
    private javax.swing.JButton salidabtn;
    private javax.swing.JLabel salidalbl;
    private javax.swing.JTextField salidatxt;
    private javax.swing.JTextField turnotxt;
    // End of variables declaration//GEN-END:variables
}
