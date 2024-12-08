/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.form;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import raven.conexion.Conexion;
import raven.application.Application;

/**
 *
 * @author User
 */
public class ConsultasForm extends javax.swing.JPanel {

    /**
     * Creates new form ConsultasForm
     */
    public ConsultasForm() {
        initComponents();
        
        idLibroField.addActionListener(e -> buscarLibros(idLibroField.getText()));
        
        idLibroField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarLibros(idLibroField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarLibros(idLibroField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarLibros(idLibroField.getText());
            }
        });
        
        idTesisField.addActionListener(e -> buscarLibros(idTesisField.getText()));
        
        idTesisField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarTesis(idTesisField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarTesis(idTesisField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarTesis(idTesisField.getText());
            }
        });
        
        idPasantiasField.addActionListener(e -> buscarLibros(idPasantiasField.getText()));
        
        idPasantiasField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarPasantias(idPasantiasField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarPasantias(idPasantiasField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarPasantias(idPasantiasField.getText());
            }
        });
    }
    
    
    private void buscarPasantias(String query) {
        try {
            Connection conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
            String sql = "SELECT * FROM pasantias WHERE ID_Informe LIKE ? OR Especialidad LIKE ? OR Cedula LIKE ?"; 
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");
            
            
            ResultSet rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] titulos = {"ID", "Titulo", "Autor", "Cedula", "Fecha", "Especialidad"};
            
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            
            while (rs.next()) {
                Object[] fila = new Object[rsmd.getColumnCount()];
                fila[0] = rs.getInt("ID_Informe"); 
                fila[1] = rs.getString("Titulo");
                fila[2] = rs.getString("Autor");
                fila[3] = rs.getString("Cedula");
                fila[4] = rs.getString("Fecha");
                fila[5] = rs.getString("Especialidad");
                modelo.addRow(fila); 
            }

            table.setModel(modelo); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar buscar:\n" + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarLibros(String query) {
        try {
            Connection conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
            String sql = "SELECT * FROM libros WHERE ID_Libro = ? OR Especialidad LIKE ? OR Titulo LIKE ?"; 
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");
            
            
            ResultSet rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] titulos = {"ID", "Titulo", "Autor", "Editorial", "Edicion", "Especialidad", "Estante", "N_Estante","Copias"};
            
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            
            while (rs.next()) {
                Object[] fila = new Object[rsmd.getColumnCount()];
                fila[0] = rs.getInt("ID_Libro"); 
                fila[1] = rs.getString("Titulo");
                fila[2] = rs.getString("Autor");
                fila[3] = rs.getString("Editorial");
                fila[4] = rs.getString("Edicion");
                fila[5] = rs.getString("Especialidad");
                fila[6] = rs.getString("Estante");
                fila[7] = rs.getString("N_Estante");
                fila[8] = rs.getString("Copias");
                modelo.addRow(fila); 
            }

            table.setModel(modelo); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar buscar:\n" + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarTesis(String query) {
        try {
            Connection conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
            String sql = "SELECT * FROM tesis WHERE ID_Tesis LIKE ? OR Especialidad LIKE ? OR Cedula LIKE ? OR Enumeracion LIKE ?"; 
            PreparedStatement ps = conexion.prepareStatement(sql);
            
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");
            ps.setString(4, "%" + query + "%");
            
            ResultSet rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] titulos = {"ID", "Titulo", "Autor", "Cedula", "Fecha", "Especialidad", "Enumeracion"};
            
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            
            while (rs.next()) {
                Object[] fila = new Object[rsmd.getColumnCount()];
                fila[0] = rs.getInt("ID_Tesis"); 
                fila[1] = rs.getString("Titulo");
                fila[2] = rs.getString("Autor");
                fila[3] = rs.getString("Cedula");
                fila[4] = rs.getString("Fecha");
                fila[5] = rs.getString("Especialidad");
                fila[6] = rs.getString("Enumeracion");
                modelo.addRow(fila); 
            }

            table.setModel(modelo); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar buscar:\n" + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void mostrarPasantias() {
        String sql = "SELECT * FROM pasantias";
        Conexion cn = new Conexion();
        Connection conexion = cn.conectar();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Informe");
        model.addColumn("Titulo");
        model.addColumn("Autor");
        model.addColumn("Cedula");
        model.addColumn("Fecha");
        model.addColumn("Especialidad");
        
        

        table.setModel(model);

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArrayList<String> datos = new ArrayList<>();
                datos.add(rs.getString(1));
                datos.add(rs.getString(2));
                datos.add(rs.getString(3));
                datos.add(rs.getString(4));
                datos.add(rs.getString(5));
                datos.add(rs.getString(6));
               

                // Convierte el ArrayList a un array y luego agrega la fila al modelo
                model.addRow(datos.toArray(new String[0]));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < table.getColumnCount(); i++) {
            Class<?> col_class = table.getColumnClass(i);
            table.setDefaultEditor(col_class, null); // Esto hace que la celda no sea editable
        }

    }
    public void mostrarLibros() {
        String sql = "SELECT * FROM libros";
        Conexion cn = new Conexion();
        Connection conexion = cn.conectar();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Libro");
        model.addColumn("Titulo");
        model.addColumn("Autor");
        model.addColumn("Editorial");
        model.addColumn("Edicion");
        model.addColumn("Especialidad");
        model.addColumn("Estante");
        model.addColumn("N.Estante");
        model.addColumn("Copias");
        
        

        table.setModel(model);

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArrayList<String> datos = new ArrayList<>();
                datos.add(rs.getString(1));
                datos.add(rs.getString(2));
                datos.add(rs.getString(3));
                datos.add(rs.getString(4));
                datos.add(rs.getString(5));
                datos.add(rs.getString(6));
                datos.add(rs.getString(7));
                datos.add(rs.getString(8));
                datos.add(rs.getString(9));
               

                // Convierte el ArrayList a un array y luego agrega la fila al modelo
                model.addRow(datos.toArray(new String[0]));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < table.getColumnCount(); i++) {
            Class<?> col_class = table.getColumnClass(i);
            table.setDefaultEditor(col_class, null); // Esto hace que la celda no sea editable
        }

    }
    public void mostrarTesis() {
        
        
        String sql = "SELECT * FROM tesis";
        Conexion cn = new Conexion();
        Connection conexion = cn.conectar();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Tesis");
        model.addColumn("Titulo");
        model.addColumn("Autor");
        model.addColumn("Cedula");
        model.addColumn("Fecha");
        model.addColumn("Especialidad");
        model.addColumn("Enumeracion");
        

        table.setModel(model);

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ArrayList<String> datos = new ArrayList<>();
                datos.add(rs.getString(1));
                datos.add(rs.getString(2));
                datos.add(rs.getString(3));
                datos.add(rs.getString(4));
                datos.add(rs.getString(5));
                datos.add(rs.getString(6));
                datos.add(rs.getString(7));

                // Convierte el ArrayList a un array y luego agrega la fila al modelo
                model.addRow(datos.toArray(new String[0]));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < table.getColumnCount(); i++) {
            Class<?> col_class = table.getColumnClass(i);
            table.setDefaultEditor(col_class, null); // Esto hace que la celda no sea editable
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
        panel = new com.raven.swing.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        idLibroField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panel1 = new com.raven.swing.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        idTesisField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel2 = new com.raven.swing.PanelBorder();
        jLabel6 = new javax.swing.JLabel();
        idPasantiasField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        jButton1 = new javax.swing.JButton();

        panel.setBackground(new java.awt.Color(0, 51, 153));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Consulta de libros");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pulse aquí para ver los libros disponibles");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Libro - Especialidad");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(84, 84, 84))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel2))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(idLibroField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idLibroField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel1.setBackground(new java.awt.Color(0, 0, 153));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Consulta de tesis");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Tesis - Especialidad - Cedula");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pulse aquí para ver las tesis disponibles");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel3))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(idTesisField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(8, 8, 8)
                .addComponent(idTesisField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel2.setBackground(new java.awt.Color(0, 51, 153));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Consulta de pasantías");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ID Informe - Especialidad - Cedula");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pulse aquí para ver los informes disponibles");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addGap(0, 34, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(idPasantiasField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(45, 45, 45))))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idPasantiasField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBorder1.setBackground(new java.awt.Color(153, 153, 153));
        panelBorder1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Registros");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jButton1.setText("Anterior");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        mostrarTesis();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        mostrarLibros();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        mostrarPasantias();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Application.logout();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idLibroField;
    private javax.swing.JTextField idPasantiasField;
    private javax.swing.JTextField idTesisField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private com.raven.swing.PanelBorder panel;
    private com.raven.swing.PanelBorder panel1;
    private com.raven.swing.PanelBorder panel2;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
