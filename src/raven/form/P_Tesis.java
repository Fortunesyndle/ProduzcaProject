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
public class P_Tesis extends javax.swing.JPanel {

    /**
     * Creates new form P_Tesis
     */
    public P_Tesis() {
        initComponents();
        idLibroField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarEtiquetas();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarEtiquetas();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarEtiquetas();
            }
        });
        
        idLibroField.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         tituloLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         autorLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         edicionLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         cantidadLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         especialidadLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         edicionLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panelBorder3.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         
         panelBorder5.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         nombre_sol2.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         tipo_sol.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         cedula_sol8.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         carrera_sol2.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         telefono_sol4.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         estanteLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         jButton3.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         jButton4.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
    }
    private void actualizarEtiquetas() {
        String idTesis = idLibroField.getText().trim();

        if (idTesis.isEmpty()) {
            tituloLabel.setText("Título:");
            autorLabel.setText("Autor:");
            edicionLabel.setText("Cedula:");
            cantidadLabel.setText("Fecha:");
            especialidadLabel.setText("Especialidad:");
            estanteLabel.setText("Estante:");
            return;
        }
    

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
            String sql = "SELECT Titulo, Autor, Cedula, Fecha, Especialidad FROM tesis WHERE ID_Tesis = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, idTesis);

            rs = ps.executeQuery();

            if (rs.next()) {
                tituloLabel.setText("Título: " + rs.getString("Titulo"));
                autorLabel.setText("Autor: " + rs.getString("Autor"));
                edicionLabel.setText("Cedula: " + rs.getString("Cedula"));
                cantidadLabel.setText("Fecha: " + rs.getString("Fecha"));
                especialidadLabel.setText("Especialidad: " + rs.getString("Especialidad"));
            } else {
                tituloLabel.setText("Título: ");
                autorLabel.setText("Autor: ");
                edicionLabel.setText("Cedula: ");
                cantidadLabel.setText("Fecha: ");
                especialidadLabel.setText("Especialidad: ");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar la tesis:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
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
    
    private void registrarSolicitanteYPrestamo() {
        String tipo = tipo_sol.getText().trim();
        String nombre = nombre_sol2.getText().trim();
        String cedula = cedula_sol8.getText().trim();
        String carrera = carrera_sol2.getText().trim();
        String telefono = telefono_sol4.getText().trim();
        String idLibro = idLibroField.getText().trim();

        if (tipo.isEmpty() || nombre.isEmpty() || cedula.isEmpty() || carrera.isEmpty() || telefono.isEmpty() || idLibro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conexion = null;
        PreparedStatement psSolicitante = null;
        PreparedStatement psPrestamo = null;

        try {
            conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido

            // Registrar el solicitante
            String sqlSolicitante = "INSERT INTO solicitantes (Tipo, Nombre, Cedula, Carrera, Telefono) VALUES (?, ?, ?, ?, ?)";
            psSolicitante = conexion.prepareStatement(sqlSolicitante);
            psSolicitante.setString(1, tipo);
            psSolicitante.setString(2, nombre);
            psSolicitante.setString(3, cedula);
            psSolicitante.setString(4, carrera);
            psSolicitante.setString(5, telefono);
            psSolicitante.executeUpdate();

            // Registrar el préstamo
            String sqlPrestamo = "INSERT INTO prestamos_tesis (ID_Tesis, Cedula, Fecha, Hora) VALUES (?, ?, CURRENT_DATE(), CURRENT_TIME())";
            psPrestamo = conexion.prepareStatement(sqlPrestamo);
            psPrestamo.setString(1, idLibro);
            psPrestamo.setString(2, cedula);
            psPrestamo.executeUpdate();

            JOptionPane.showMessageDialog(this, "Solicitante y préstamo registrados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos después del registro exitoso
            tipo_sol.setText("");
            nombre_sol2.setText("");
            cedula_sol8.setText("");
            carrera_sol2.setText("");
            telefono_sol4.setText("");
            idLibroField.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el solicitante o préstamo:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (psSolicitante != null) psSolicitante.close();
                if (psPrestamo != null) psPrestamo.close();
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
        idLibroField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panelBorder3 = new com.raven.swing.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        tituloLabel = new javax.swing.JTextField();
        autorLabel = new javax.swing.JTextField();
        cantidadLabel = new javax.swing.JTextField();
        especialidadLabel = new javax.swing.JTextField();
        edicionLabel = new javax.swing.JTextField();
        estanteLabel = new javax.swing.JTextField();
        panelBorder5 = new com.raven.swing.PanelBorder();
        jLabel16 = new javax.swing.JLabel();
        carrera_sol2 = new javax.swing.JTextField();
        telefono_sol4 = new javax.swing.JTextField();
        cedula_sol8 = new javax.swing.JTextField();
        nombre_sol2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tipo_sol = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

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
        jLabel2.setText("Prestamos de Tesis");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Tesis");

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
                            .addComponent(idLibroField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addComponent(idLibroField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        panelBorder3.setBackground(new java.awt.Color(20, 33, 100));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Detalles del libro");

        tituloLabel.setText("Título: ");
        tituloLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloLabelActionPerformed(evt);
            }
        });

        autorLabel.setText("Autor:");
        autorLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autorLabelActionPerformed(evt);
            }
        });

        cantidadLabel.setText("Fecha:");
        cantidadLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadLabelActionPerformed(evt);
            }
        });

        especialidadLabel.setText("Especialidad: ");
        especialidadLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                especialidadLabelActionPerformed(evt);
            }
        });

        edicionLabel.setText("Edicion:");
        edicionLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edicionLabelActionPerformed(evt);
            }
        });

        estanteLabel.setText("Estante:");
        estanteLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estanteLabelActionPerformed(evt);
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
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cantidadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(tituloLabel))
                        .addGap(47, 47, 47)
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(autorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(especialidadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(edicionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estanteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edicionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantidadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(especialidadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estanteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109))
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(127, 127, 127));
        jLabel16.setText("Datos del solicitante");

        cedula_sol8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedula_sol8ActionPerformed(evt);
            }
        });

        jLabel17.setText("Nombre");

        jLabel18.setText("Cédula");

        jLabel20.setText("Carrera");

        jLabel21.setText("Teléfono");

        jButton3.setBackground(new java.awt.Color(20, 33, 100));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Aceptar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel22.setText("Tipo");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/llooo.jpg"))); // NOI18N
        jLabel23.setText("jLabel10");

        jButton4.setBackground(new java.awt.Color(20, 33, 100));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Realizar prestamo");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(152, 152, 152)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20)
                            .addComponent(jLabel17)
                            .addComponent(nombre_sol2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(carrera_sol2))
                        .addGap(64, 64, 64)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(telefono_sol4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cedula_sol8, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBorder5Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelBorder5Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(tipo_sol)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addGap(46, 46, 46)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre_sol2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cedula_sol8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefono_sol4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carrera_sol2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)))
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBorder5Layout.createSequentialGroup()
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
                                .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(56, Short.MAX_VALUE))
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

    private void tituloLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tituloLabelActionPerformed

    private void autorLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autorLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autorLabelActionPerformed

    private void cantidadLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadLabelActionPerformed

    private void especialidadLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_especialidadLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_especialidadLabelActionPerformed

    private void edicionLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edicionLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edicionLabelActionPerformed

    private void estanteLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estanteLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estanteLabelActionPerformed

    private void cedula_sol8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedula_sol8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedula_sol8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        registrarSolicitanteYPrestamo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        registrarSolicitanteYPrestamo();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField autorLabel;
    private javax.swing.JTextField cantidadLabel;
    private javax.swing.JTextField carrera_sol2;
    private javax.swing.JTextField cedula_sol8;
    private javax.swing.JTextField edicionLabel;
    private javax.swing.JTextField especialidadLabel;
    private javax.swing.JTextField estanteLabel;
    private javax.swing.JTextField idLibroField;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre_sol2;
    private com.raven.swing.PanelBorder panel;
    private com.raven.swing.PanelBorder panel1;
    private com.raven.swing.PanelBorder panelBorder3;
    private com.raven.swing.PanelBorder panelBorder5;
    private javax.swing.JTextField telefono_sol4;
    private javax.swing.JTextField tipo_sol;
    private javax.swing.JTextField tituloLabel;
    // End of variables declaration//GEN-END:variables
}
