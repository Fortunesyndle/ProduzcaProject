package raven.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.model.Model_Card;

import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
import raven.application.Application;
import raven.conexion.Conexion;

public class ListaEmpleados extends javax.swing.JPanel {

    public ListaEmpleados() {
        initComponents();
        //  add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        mostrarTabla();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
        busqueda_field.addActionListener(e -> buscarRegistros(busqueda_field.getText()));
        
        busqueda_field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarRegistros(busqueda_field.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarRegistros(busqueda_field.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarRegistros(busqueda_field.getText());
            }
        });
       
    }
    
    
    private void buscarRegistros(String query) {
    try {
        Connection conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
        String sql = "SELECT Cedula, Nombres, Apellidos, Edad, Genero, Direccion, Turno, Telefono, Email, Cargo, Entrada, Salida "
                   + "FROM empleados "
                   + "WHERE Cedula LIKE ? OR Cargo LIKE ? OR Turno LIKE ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        
        ps.setString(1, "%" + query + "%");
        ps.setString(2, "%" + query + "%");
        ps.setString(3, "%" + query + "%");
        
        ResultSet rs = ps.executeQuery();
        
        String[] titulos = {"Cedula", "Nombres", "Apellidos", "Edad", "Genero", "Direccion", "Turno", "Telefono", "Email", "Cargo", "Entrada", "Salida"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        while (rs.next()) {
            Object[] fila = new Object[12];
            fila[0] = rs.getString("Cedula");
            fila[1] = rs.getString("Nombres");
            fila[2] = rs.getString("Apellidos");
            fila[3] = rs.getInt("Edad");
            fila[4] = rs.getString("Genero");
            fila[5] = rs.getString("Direccion");
            fila[6] = rs.getString("Turno");
            fila[7] = rs.getString("Telefono");
            fila[8] = rs.getString("Email");
            fila[9] = rs.getString("Cargo");
            fila[10] = rs.getString("Entrada");
            fila[11] = rs.getString("Salida");
            modelo.addRow(fila);
        }

        table.setModel(modelo);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al intentar buscar:\n" + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    
    public void mostrarTabla() {
        
        
        String sql = "SELECT * FROM empleados";
        Conexion cn = new Conexion();
        Connection conexion = cn.conectar();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Cedula");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Edad");
        model.addColumn("Genero");
        model.addColumn("Direccion");
        model.addColumn("Turno");
        model.addColumn("Telefono");
        model.addColumn("Email");
        model.addColumn("Cargo");
        model.addColumn("H. Entrada");
        model.addColumn("H. Salida");
        
       

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
                datos.add(rs.getString(10));
                datos.add(rs.getString(11));
                datos.add(rs.getString(12));

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
    
    private void agregarTesis() { 
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear los componentes del formulario
        JLabel tituloLabel = new JLabel("Titulo:");
        JTextField tituloField = new JTextField(8);
        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(8);
        JLabel cedulaLabel = new JLabel("Cedula:");
        JTextField cedulaField = new JTextField(8);
        JLabel fechaLabel = new JLabel("Fecha:");
        JTextField fechaField = new JTextField(8);
        JLabel especialidadLabel = new JLabel("Especialidad:");
        JTextField especialidadField = new JTextField(8);
        JLabel enumeracionLabel = new JLabel("Enumeracion:");
        JTextField enumeracionField = new JTextField(8);

        // Añadir los componentes al panel
        panel.add(tituloLabel);
        panel.add(tituloField);
        panel.add(autorLabel);
        panel.add(autorField);
        panel.add(cedulaLabel);
        panel.add(cedulaField);
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(especialidadLabel);
        panel.add(especialidadField);
        panel.add(enumeracionLabel);
        panel.add(enumeracionField);
       
                

        JOptionPane.showConfirmDialog(null, panel, "Agregar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        try {
            if (tituloField.getText().isBlank() || autorField.getText().isBlank() || cedulaField.getText().isBlank() || fechaField.getText().isBlank() || especialidadField.getText().isBlank()
                    ) {
                JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                   Conexion cn = new Conexion();
                   Connection conexion = cn.conectar();
                   Statement st = conexion.createStatement();

                String Titulo, Autor, Cedula, Fecha, Enumeracion, Especialidad,  Resumen;

                Titulo = texto(tituloField);
                Autor = texto(autorField);
                Cedula = texto(cedulaField);
                Fecha = texto(fechaField);
                Especialidad = texto(especialidadField);
                Enumeracion = texto(enumeracionField);
                

                String sqla2 = "insert into Tesis (Titulo, Autor, Cedula, Fecha, Especialidad, Enumeracion) values (?,?,?,?,?,?)";

                PreparedStatement ps2 = conexion.prepareStatement(sqla2);

              
                ps2.setString(1, Titulo);
                ps2.setString(2, Autor);
                ps2.setString(3, Cedula);
                ps2.setString(4, Fecha);
                ps2.setString(5, Especialidad);
                ps2.setString(6, Enumeracion);

                ps2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro realizado", "EXITO", JOptionPane.INFORMATION_MESSAGE);

                blank(tituloField);
                blank(autorField);
                blank(cedulaField);
                blank(fechaField);
                blank(especialidadField);
                blank(enumeracionField);

            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        
        }
    }
    
    private void modificarEmpleado() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un registro primero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener datos existentes y convertirlos adecuadamente
    String cedulaStr = table.getValueAt(selectedRow, 0).toString(); // Convertir a String
    String nombres = (String) table.getValueAt(selectedRow, 1);
    String apellidos = (String) table.getValueAt(selectedRow, 2);
    String edadStr = table.getValueAt(selectedRow, 3).toString(); // Convertir a String
    String genero = (String) table.getValueAt(selectedRow, 4);
    String direccion = (String) table.getValueAt(selectedRow, 5);
    String turno = (String) table.getValueAt(selectedRow, 6);
    String telefono = (String) table.getValueAt(selectedRow, 7);
    String email = (String) table.getValueAt(selectedRow, 8);
    String cargo = (String) table.getValueAt(selectedRow, 9);
    String entrada = (String) table.getValueAt(selectedRow, 10);
    String salida = (String) table.getValueAt(selectedRow, 11);

    // Convertir cedula y edad a int
    int cedula, edad;
    try {
        cedula = Integer.parseInt(cedulaStr);
        edad = Integer.parseInt(edadStr); // Conversión correcta
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "La cédula y la edad deben ser números válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        return; // Salir si la conversión falla
    }

    // Crear campos de entrada
    JTextField[] fields = {
        new JTextField(nombres),
        new JTextField(apellidos),
        new JTextField(String.valueOf(edad)),
        new JTextField(genero),
        new JTextField(direccion),
        new JTextField(turno),
        new JTextField(telefono),
        new JTextField(email),
        new JTextField(cargo),
        new JTextField(entrada),
        new JTextField(salida)
    };

    // Crear panel para el diálogo
    JPanel panel = new JPanel(new GridLayout(12, 2));
    String[] labels = {"Nombres:", "Apellidos:", "Edad:", "Género:", "Dirección:", "Turno:", "Teléfono:", "Email:", "Cargo:", "H.Entrada:", "H.Salida"};
    
    for (int i = 0; i < labels.length; i++) {
        panel.add(new JLabel(labels[i]));
        panel.add(fields[i]);
    }

    // Mostrar diálogo de modificación
    int result = JOptionPane.showConfirmDialog(this, panel, "Modificar Empleado", JOptionPane.OK_CANCEL_OPTION);
    
    if (result == JOptionPane.OK_OPTION) {
        try (Connection conexion = new Conexion().conectar(); 
             PreparedStatement ps = conexion.prepareStatement("UPDATE empleados SET Nombres = ?, Apellidos = ?, Edad = ?, Genero = ?, Direccion = ?, Turno = ?, Telefono = ?, Email = ?, Cargo = ?, Entrada = ?, Salida = ? WHERE Cedula = ?")) {

            // Asignar valores a la consulta
            ps.setString(1, fields[0].getText());
            ps.setString(2, fields[1].getText());
            ps.setInt(3, Integer.parseInt(fields[2].getText())); // Convertir a entero
            ps.setString(4, fields[3].getText());
            ps.setString(5, fields[4].getText());
            ps.setString(6, fields[5].getText());
            ps.setString(7, fields[6].getText());
            ps.setString(8, fields[7].getText());
            ps.setString(9, fields[8].getText());
            ps.setString(10, fields[9].getText());
            ps.setString(11, fields[10].getText());
            ps.setInt(12, cedula);

            // Ejecutar actualización
            ps.executeUpdate();

            // Actualizar la tabla con los nuevos datos
            table.setValueAt(fields[0].getText(), selectedRow, 1);
            table.setValueAt(fields[1].getText(), selectedRow, 2);
            table.setValueAt(Integer.parseInt(fields[2].getText()), selectedRow, 3);
            table.setValueAt(fields[3].getText(), selectedRow, 4);
            table.setValueAt(fields[4].getText(), selectedRow, 5);
            table.setValueAt(fields[5].getText(), selectedRow, 6);
            table.setValueAt(fields[6].getText(), selectedRow, 7);
            table.setValueAt(fields[7].getText(), selectedRow, 8);
            table.setValueAt(fields[8].getText(), selectedRow, 9);
            table.setValueAt(fields[9].getText(), selectedRow, 10);
            table.setValueAt(fields[10].getText(), selectedRow, 11);

            JOptionPane.showMessageDialog(this, "Empleado modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el empleado:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }
}



    
    public static boolean eliminar(String id) {
    Conexion cn = new Conexion();
    PreparedStatement ps = null;
    Connection conexion = cn.conectar(); // Asegúrate de obtener la conexión

    String sql = "DELETE FROM tesis WHERE ID_Tesis = ?"; // Usa un parámetro en la consulta SQL

    try {
        ps = conexion.prepareStatement(sql); // Corrección: Sin comillas dobles
        ps.setString(1, id); // Establece el valor del parámetro
        ps.executeUpdate(); // Usa executeUpdate para operaciones de modificación

        
        return true;
    } catch (Exception e) {
        System.out.println(e.toString());
        return false;
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
   
private void eliminarEmpleado() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un registro primero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener la cédula del empleado a eliminar
    String cedulaStr = table.getValueAt(selectedRow, 0).toString(); // Convertir a String
    int cedula;
    try {
        cedula = Integer.parseInt(cedulaStr); // Convertir a entero
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "La cédula debe ser un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        return; // Salir si la conversión falla
    }

    // Confirmar eliminación
    int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return; // Salir si no se confirma la eliminación
    }

    // Conectar a la base de datos y eliminar el registro
    try (Connection conexion = new Conexion().conectar(); 
         PreparedStatement ps = conexion.prepareStatement("DELETE FROM empleados WHERE Cedula = ?")) {

        ps.setInt(1, cedula);
        ps.executeUpdate();


        JOptionPane.showMessageDialog(this, "Empleado eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al eliminar el empleado:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JLayeredPane();
        panel1 = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        busqueda_field = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        modificarbtn = new javax.swing.JButton();
        eliminarbtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));
        add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 36, 498, -1));

        panel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));
        add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 6, 1132, -1));

        panelBorder1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Detalles de las tesis");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email", "User Type", "Joined"
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
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(spTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        add(panelBorder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 1160, -1));

        busqueda_field.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
        add(busqueda_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 610, 30));

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 60, 102, 128));

        modificarbtn.setBackground(new java.awt.Color(0, 0, 153));
        modificarbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        modificarbtn.setForeground(new java.awt.Color(255, 255, 255));
        modificarbtn.setText("Modificar");
        modificarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarbtnActionPerformed(evt);
            }
        });
        add(modificarbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        eliminarbtn.setBackground(new java.awt.Color(0, 0, 153));
        eliminarbtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        eliminarbtn.setForeground(new java.awt.Color(255, 255, 255));
        eliminarbtn.setText("Eliminar");
        eliminarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarbtnActionPerformed(evt);
            }
        });
        add(eliminarbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Listado de empleados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(489, Short.MAX_VALUE)
                .addComponent(lb)
                .addGap(477, 477, 477))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1150, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
         agregarTesis();
        mostrarTabla();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void modificarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarbtnActionPerformed
        // TODO add your handling code here:
        modificarEmpleado();
    }//GEN-LAST:event_modificarbtnActionPerformed

    private void eliminarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarbtnActionPerformed
        // TODO add your handling code here:
        eliminarEmpleado();
        mostrarTabla();
    }//GEN-LAST:event_eliminarbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda_field;
    private javax.swing.JButton eliminarbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb;
    private javax.swing.JButton modificarbtn;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JLayeredPane panel1;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
