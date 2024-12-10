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

public class Entrada extends javax.swing.JPanel {

    public Entrada() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
    
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
       
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
    
    private void registrarEntrada() {
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
        String sqlEmpleado = "SELECT Cedula, Nombres, Apellidos, Cargo, Turno, Entrada FROM empleados WHERE Cedula = ?";
        ps = conexion.prepareStatement(sqlEmpleado);
        ps.setString(1, cedula);
        rs = ps.executeQuery();

        if (rs.next()) {
            cedula = rs.getString("Cedula");
            String nombres = rs.getString("Nombres");
            String apellidos = rs.getString("Apellidos");
            String cargo = rs.getString("Cargo");
            String turno = rs.getString("Turno");
            String entrada = rs.getString("Entrada");

            // Mostrar los datos en los textfields
            cedulatxt.setText(cedula);
            nombretxt.setText(nombres);
            apellidostxt.setText(apellidos);
            cargotxt.setText(cargo);
            turnotxt.setText(turno);
            entradatxt.setText(entrada);

            // Obtener la hora actual de entrada
            LocalTime horaEntrada = LocalTime.now();
            turnotxt.setText(horaEntrada.toString());

            // Verificar si el empleado llegó a tiempo
            LocalTime horaLimite = LocalTime.parse(entrada);
            boolean llegoATiempo = !horaEntrada.isAfter(horaLimite);
            String estadoPuntualidad = llegoATiempo ? "Puntual" : "Tarde";

            // Registrar la entrada en la base de datos
            String sqlRegistro = "INSERT INTO entrada (Cedula, Nombres, Apellidos, Fecha, Hora, Puntualidad) VALUES (?, ?, ?, CURRENT_DATE(), CURRENT_TIME(), ?)";
            PreparedStatement psRegistro = conexion.prepareStatement(sqlRegistro);
            psRegistro.setString(1, cedula);
            psRegistro.setString(2, nombres);
            psRegistro.setString(3, apellidos);
            psRegistro.setString(4, estadoPuntualidad);
            psRegistro.executeUpdate();

            // Mostrar mensaje de llegada
            String mensaje = llegoATiempo ? "El empleado llegó a tiempo." : "El empleado no llegó a tiempo.";
            JOptionPane.showMessageDialog(this, mensaje, "Registro de Entrada", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al registrar la entrada:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
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
        entradalbl = new javax.swing.JLabel();
        idlbl = new javax.swing.JLabel();
        observacioneslbl = new javax.swing.JLabel();
        nombretxt = new javax.swing.JTextField();
        apellidostxt = new javax.swing.JTextField();
        cedulatxt = new javax.swing.JTextField();
        cargotxt = new javax.swing.JTextField();
        turnotxt = new javax.swing.JTextField();
        entradatxt = new javax.swing.JTextField();
        observacionestxt = new javax.swing.JTextField();
        entradabtn = new javax.swing.JButton();
        ingresarCedula = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombrelbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nombrelbl.setText("Nombres");
        jPanel1.add(nombrelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        apellidolbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        apellidolbl.setText("Apellidos");
        jPanel1.add(apellidolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        cedulalbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cedulalbl.setText("Cédula");
        jPanel1.add(cedulalbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, -1, -1));

        jornadalbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jornadalbl.setText("Hora registrada");
        jPanel1.add(jornadalbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));

        entradalbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        entradalbl.setText("Hora de entrada");
        jPanel1.add(entradalbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, -1, -1));

        idlbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        idlbl.setText("Cargo");
        jPanel1.add(idlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));

        observacioneslbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        observacioneslbl.setText("Observaciones");
        jPanel1.add(observacioneslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, -1, -1));

        nombretxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        nombretxt.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 180, 30));

        apellidostxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        jPanel1.add(apellidostxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 180, 30));

        cedulatxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        jPanel1.add(cedulatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 180, 30));

        cargotxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        jPanel1.add(cargotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 180, 30));

        turnotxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        jPanel1.add(turnotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 180, 30));

        entradatxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        jPanel1.add(entradatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 180, 30));

        observacionestxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        jPanel1.add(observacionestxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, 181, 30));

        entradabtn.setBackground(new java.awt.Color(51, 51, 255));
        entradabtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        entradabtn.setForeground(new java.awt.Color(255, 255, 255));
        entradabtn.setText("Aceptar");
        entradabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradabtnActionPerformed(evt);
            }
        });
        jPanel1.add(entradabtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, -1, -1));

        ingresarCedula.setForeground(new java.awt.Color(102, 102, 102));
        ingresarCedula.setText("  Cedula...");
        ingresarCedula.setActionCommand("null");
        ingresarCedula.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 102), 2, true));
        ingresarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarCedulaActionPerformed(evt);
            }
        });
        jPanel1.add(ingresarCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 160, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, -1, 10));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Entrada");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(lb)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void entradabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradabtnActionPerformed
        // TODO add your handling code here:
        registrarEntrada();
    }//GEN-LAST:event_entradabtnActionPerformed

    private void ingresarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresarCedulaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel apellidolbl;
    private javax.swing.JTextField apellidostxt;
    private javax.swing.JTextField cargotxt;
    private javax.swing.JLabel cedulalbl;
    private javax.swing.JTextField cedulatxt;
    private javax.swing.JButton entradabtn;
    private javax.swing.JLabel entradalbl;
    private javax.swing.JTextField entradatxt;
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
    private javax.swing.JTextField turnotxt;
    // End of variables declaration//GEN-END:variables
}
