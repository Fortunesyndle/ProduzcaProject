package raven.form;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import raven.conexion.Conexion;
import raven.swing.AvatarIcon;


/**
 *
 * @author Raven
 */
public class Dashboard extends javax.swing.JPanel {

    public Dashboard() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        jPanel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:50;"
                + "[light]background:darken(@background,6%);"
                + "[dark]background:lighten(@background,6%)");
       
        
        avatar.setIcon(new AvatarIcon(getClass().getResource("/raven/image/astrid.jpg"), 150, 150, 1000));
       
        mostrarMensajeAleatorio();
    }
    
   
  
    private void mostrarMensajeAleatorio() {
        List<String> mensajes = obtenerMensajesDeBaseDeDatos();
        
        if (!mensajes.isEmpty()) {
            Random random = new Random();
            String mensajeAleatorio = mensajes.get(random.nextInt(mensajes.size()));
            labelMensaje.setText(mensajeAleatorio);
        } else {
            labelMensaje.setText("No hay mensajes disponibles.");
        }
    }

    private List<String> obtenerMensajesDeBaseDeDatos() {
        List<String> mensajes = new ArrayList<>();
        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = new Conexion().conectar(); // Asegúrate de que tu clase Conexion esté bien configurada
            String consulta = "SELECT mensaje FROM mensajes"; // Cambia 'mensaje' por el nombre real de la columna en tu tabla
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(consulta);

            while (resultSet.next()) {
                mensajes.add(resultSet.getString("mensaje")); // Cambia 'mensaje' por el nombre real de la columna
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return mensajes;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelMensaje = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        avatar = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        labelMensaje.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMensaje)
                .addContainerGap(546, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(labelMensaje)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 590, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        avatar.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel2.add(avatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        lb.setText("Bienvenida de nuevo, Astrid Hernández");
        jPanel2.add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 540, 290));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/image/fondoastrid.jpg"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -10, 1400, -1));

        jLabel4.setText("jLabel4");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, -1, -1));

        jLabel5.setText("jLabel5");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 210, 210));

        jLabel7.setText("jLabel7");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, 100, 140));

        jLabel8.setText("jLabel8");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, 160, 410));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 180));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelMensaje;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
