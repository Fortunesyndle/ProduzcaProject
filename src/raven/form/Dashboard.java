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

        lb = new javax.swing.JLabel();

        lb.setText("Bienvenida de nuevo, Astrid Hernández");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(lb))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lb)
                .addGap(545, 545, 545))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
