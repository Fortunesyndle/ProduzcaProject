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
import javax.swing.*;
import com.raven.swing.PanelBorder;


/**
 *
 * @author User
 */
public class P_libros extends javax.swing.JPanel {

    /**
     * Creates new form P_Libros
     */
    public P_libros() {
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
         panelBorder1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panelBorder3.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:30;");
         panelBorder4.putClientProperty(FlatClientProperties.STYLE, ""
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
    String idLibro = idLibroField.getText().trim();

    if (idLibro.isEmpty()) {
        tituloLabel.setText("Título:");
        autorLabel.setText("Autor:");
        edicionLabel.setText("Edicion:");
        cantidadLabel.setText("Copias:");
        especialidadLabel.setText("Especialidad:");
        estanteLabel.setText("Estante:");
        return;
    }

    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
        String sql = "SELECT Titulo, Autor, Edicion, Copias, Especialidad, Estante FROM libros WHERE ID_Libro = ?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, idLibro);

        rs = ps.executeQuery();

        if (rs.next()) {
            tituloLabel.setText("Título: " + rs.getString("Titulo"));
            autorLabel.setText("Autor: " + rs.getString("Autor"));
            edicionLabel.setText("Edicion: " + rs.getString("Edicion"));
            cantidadLabel.setText("Cantidad: " + rs.getString("Copias"));
            especialidadLabel.setText("Especialidad: " + rs.getString("Especialidad"));
            estanteLabel.setText("Estante: " + rs.getString("Estante"));
        } else {
            tituloLabel.setText("Título: ");
            autorLabel.setText("Autor: ");
            edicionLabel.setText("Edicion: ");
            cantidadLabel.setText("Copias: ");
            especialidadLabel.setText("Especialidad: ");
            estanteLabel.setText("Estante: ");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al buscar el libro:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
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

    
    private void registrarPrestamo() {
        String tipo = tipo_sol.getText().trim();
        String nombre = nombre_sol.getText().trim();
        String cedula = cedula_sol.getText().trim();
        String carrera = carrera_sol.getText().trim();
        String telefono = telefono_sol.getText().trim();
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

            // Registrar el préstamo
            String sqlPrestamo = "INSERT INTO prestamos_lib (ID_Libro, Cedula, Fecha, Hora) VALUES (?, ?, CURRENT_DATE(), CURRENT_TIME())";
            psPrestamo = conexion.prepareStatement(sqlPrestamo);
            psPrestamo.setString(1, idLibro);
            psPrestamo.setString(2, cedula);
            psPrestamo.executeUpdate();

            JOptionPane.showMessageDialog(this, "Préstamo registrados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos después del registro exitoso
            tipo_sol.setText("");
            nombre_sol.setText("");
            cedula_sol.setText("");
            carrera_sol.setText("");
            telefono_sol.setText("");
            idLibroField.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar el préstamo:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
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
    
    private void registrarSolicitante() {
    String tipo = tipo_sol.getText().trim();
    String nombre = nombre_sol.getText().trim();
    String cedulaStr = cedula_sol.getText().trim(); // Convertimos a String para verificar
    String carrera = carrera_sol.getText().trim();
    String telefono = telefono_sol.getText().trim();

    // Validar que la cédula sea un entero
    int cedula = Integer.parseInt(cedulaStr);
    try {
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "La cédula debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (tipo.isEmpty() || nombre.isEmpty() || cedulaStr.isEmpty() || carrera.isEmpty() || telefono.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos del solicitante.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Connection conexion = null;
    PreparedStatement psSolicitante = null;

    try {
        conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
        String sqlSolicitante = "INSERT INTO solicitantes (Tipo, Nombre, Cedula, Carrera, Telefono) VALUES (?, ?, ?, ?, ?)";
        psSolicitante = conexion.prepareStatement(sqlSolicitante);
        psSolicitante.setString(1, tipo);
        psSolicitante.setString(2, nombre);
        psSolicitante.setInt(3, cedula);
        psSolicitante.setString(4, carrera);
        psSolicitante.setString(5, telefono);
        psSolicitante.executeUpdate();

        JOptionPane.showMessageDialog(this, "Solicitante registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al registrar el solicitante:\n");
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

        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        carrera_sol = new javax.swing.JTextField();
        telefono_sol = new javax.swing.JTextField();
        cedula_sol = new javax.swing.JTextField();
        nombre_sol = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        telefono_sol1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        panelBorder4 = new com.raven.swing.PanelBorder();
        jLabel10 = new javax.swing.JLabel();
        carrera_sol1 = new javax.swing.JTextField();
        telefono_sol2 = new javax.swing.JTextField();
        cedula_sol1 = new javax.swing.JTextField();
        nombre_sol1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        telefono_sol3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelBorder3 = new com.raven.swing.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        tituloLabel = new javax.swing.JTextField();
        autorLabel = new javax.swing.JTextField();
        cantidadLabel = new javax.swing.JTextField();
        especialidadLabel = new javax.swing.JTextField();
        edicionLabel = new javax.swing.JTextField();
        estanteLabel = new javax.swing.JTextField();
        panel = new com.raven.swing.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        idLibroField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
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
        panel1 = new com.raven.swing.PanelBorder();
        jLabel19 = new javax.swing.JLabel();

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Datos del solicitante");

        cedula_sol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedula_solActionPerformed(evt);
            }
        });

        jLabel5.setText("Nombre");

        jLabel6.setText("Cédula");

        jLabel7.setText("Carrera");

        jLabel8.setText("Teléfono");

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Tipo");

        panelBorder4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(127, 127, 127));
        jLabel10.setText("Datos del solicitante");

        cedula_sol1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedula_sol1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Nombre");

        jLabel12.setText("Cédula");

        jLabel13.setText("Carrera");

        jLabel14.setText("Teléfono");

        jButton2.setBackground(new java.awt.Color(153, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Tipo");

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre_sol1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(carrera_sol1))
                                .addGap(37, 37, 37)
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cedula_sol1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(telefono_sol2))
                                .addGap(37, 37, 37))
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11))
                                .addGap(202, 202, 202)
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(188, 188, 188)))
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefono_sol3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addContainerGap(28, Short.MAX_VALUE))))
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGap(46, 46, 46)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre_sol1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cedula_sol1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(carrera_sol1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono_sol2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(telefono_sol3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(carrera_sol))
                                .addGap(37, 37, 37)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cedula_sol, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(telefono_sol))
                                .addGap(37, 37, 37))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(202, 202, 202)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(188, 188, 188)))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefono_sol1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addContainerGap(28, Short.MAX_VALUE))))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cedula_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(carrera_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(telefono_sol1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1456, 0, -1, -1));

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

        cantidadLabel.setText("Copias:");
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
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cantidadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(tituloLabel))
                        .addGap(45, 45, 45)
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(autorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(especialidadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edicionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estanteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
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

        add(panelBorder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 810, 200));

        panel.setBackground(new java.awt.Color(20, 33, 100));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Prestamos de libros");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID Libro");

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
                            .addComponent(idLibroField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
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

        add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, -1));

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
        jButton3.setText("Registrar");
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
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20)
                            .addComponent(jLabel17)
                            .addComponent(carrera_sol2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(nombre_sol2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21)
                            .addComponent(cedula_sol8, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono_sol4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipo_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(67, 67, 67)))
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addGap(46, 46, 46)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre_sol2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cedula_sol8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(carrera_sol2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefono_sol4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(tipo_sol, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(panelBorder5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1140, 396));

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

        add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 1140, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void cedula_solActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedula_solActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedula_solActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cedula_sol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedula_sol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedula_sol1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        registrarPrestamo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cedula_sol8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedula_sol8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedula_sol8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        registrarSolicitante();
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void estanteLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estanteLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estanteLabelActionPerformed

    private void edicionLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edicionLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edicionLabelActionPerformed

    private void especialidadLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_especialidadLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_especialidadLabelActionPerformed

    private void cantidadLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadLabelActionPerformed

    private void autorLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autorLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autorLabelActionPerformed

    private void tituloLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tituloLabelActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
                registrarPrestamo();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField autorLabel;
    private javax.swing.JTextField cantidadLabel;
    private javax.swing.JTextField carrera_sol;
    private javax.swing.JTextField carrera_sol1;
    private javax.swing.JTextField carrera_sol2;
    private javax.swing.JTextField cedula_sol;
    private javax.swing.JTextField cedula_sol1;
    private javax.swing.JTextField cedula_sol8;
    private javax.swing.JTextField edicionLabel;
    private javax.swing.JTextField especialidadLabel;
    private javax.swing.JTextField estanteLabel;
    private javax.swing.JTextField idLibroField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre_sol;
    private javax.swing.JTextField nombre_sol1;
    private javax.swing.JTextField nombre_sol2;
    private com.raven.swing.PanelBorder panel;
    private com.raven.swing.PanelBorder panel1;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder3;
    private com.raven.swing.PanelBorder panelBorder4;
    private com.raven.swing.PanelBorder panelBorder5;
    private javax.swing.JTextField telefono_sol;
    private javax.swing.JTextField telefono_sol1;
    private javax.swing.JTextField telefono_sol2;
    private javax.swing.JTextField telefono_sol3;
    private javax.swing.JTextField telefono_sol4;
    private javax.swing.JTextField tipo_sol;
    private javax.swing.JTextField tituloLabel;
    // End of variables declaration//GEN-END:variables
}
