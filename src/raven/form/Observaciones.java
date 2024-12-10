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

public class Observaciones extends javax.swing.JPanel {

    public Observaciones() {
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
        
        
        busqueda_field.addActionListener(e -> buscarDatos(busqueda_field.getText()));
        
        busqueda_field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarDatos(busqueda_field.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarDatos(busqueda_field.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarDatos(busqueda_field.getText());
            }
        });
       
    }
    
    private void buscarDatos(String query) {
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
    
    public void mostrarTabla() {
    String sql = "SELECT Cedula, Fecha, Hora FROM entrada";
    Conexion cn = new Conexion();
    Connection conexion = cn.conectar();

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("Cedula");
    model.addColumn("Fecha");
    model.addColumn("Hora");

    table.setModel(model);

    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ArrayList<String> datos = new ArrayList<>();
            datos.add(rs.getString("Cedula"));
            datos.add(rs.getString("Fecha"));
            datos.add(rs.getString("Hora"));

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
    
    private void modificarTesis() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos del registro seleccionado
        String id = (String) table.getValueAt(selectedRow, 0);
        String titulo = (String) table.getValueAt(selectedRow, 1);
        String autor = (String) table.getValueAt(selectedRow, 2);
        String cedula = (String) table.getValueAt(selectedRow, 3);
        String fecha = (String) table.getValueAt(selectedRow, 4);
        String especialidad = (String) table.getValueAt(selectedRow, 5);
        String enumeracion = (String) table.getValueAt(selectedRow, 6);

        // Panel del formulario para modificar
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear los componentes del formulario
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(id, 8);
        JLabel tituloLabel = new JLabel("Titulo:");
        JTextField tituloField = new JTextField(titulo, 8);
        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(autor, 8);
        JLabel cedulaLabel = new JLabel("Cedula:");
        JTextField cedulaField = new JTextField(cedula, 8);
        JLabel fechaLabel = new JLabel("Fecha:");
        JTextField fechaField = new JTextField(fecha, 8);
        JLabel especialidadLabel = new JLabel("Especialidad:");
        JTextField especialidadField = new JTextField(especialidad, 8);
        JLabel enumeracionLabel = new JLabel("Enumeracion:");
        JTextField enumeracionField = new JTextField(enumeracion, 8);

        // Añadir los componentes al panel
        panel.add(idLabel);
        panel.add(idField);
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

        int result = JOptionPane.showConfirmDialog(null, panel, "Modificar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (tituloField.getText().isBlank() || autorField.getText().isBlank() || cedulaField.getText().isBlank() || fechaField.getText().isBlank() || especialidadField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Conectar a la base de datos y actualizar el registro
                    Conexion cn = new Conexion();
                    Connection conexion = cn.conectar();
                    String sql = "UPDATE Tesis SET Titulo = ?, Autor = ?, Cedula = ?, Fecha = ?, Especialidad = ?, Enumeracion = ? WHERE ID_Tesis = ?";
                    PreparedStatement ps2 = conexion.prepareStatement(sql);

                    ps2.setString(1, tituloField.getText());
                    ps2.setString(2, autorField.getText());
                    ps2.setString(3, cedulaField.getText());
                    ps2.setString(4, fechaField.getText());
                    ps2.setString(5, especialidadField.getText());
                    ps2.setString(6, enumeracionField.getText());
                    ps2.setInt(7, Integer.parseInt(idField.getText()));

                    int rowsAffected = ps2.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Registro actualizado", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                        table.setValueAt(tituloField.getText(), selectedRow, 1);
                        table.setValueAt(autorField.getText(), selectedRow, 2);
                        table.setValueAt(cedulaField.getText(), selectedRow, 3);
                        table.setValueAt(fechaField.getText(), selectedRow, 4);
                        table.setValueAt(especialidadField.getText(), selectedRow, 5);
                        table.setValueAt(enumeracionField.getText(), selectedRow, 6);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el registro con ID: " + idField.getText(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
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
   
     private void eliminarTesis(){
         
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear los componentes del formulario
        JLabel mensaje = new JLabel ("Ingrese el ID del registro que desea eliminar");
        JLabel tituloLabel = new JLabel("ID:");
        JTextField tituloField = new JTextField(8);
        panel.add(mensaje);
        panel.add(tituloLabel);
        panel.add(tituloField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                if (tituloField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "El campo ID está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Conexion cn = new Conexion();
                    Connection conexion = cn.conectar();

                    String Titulo = tituloField.getText();

                    String sql = "DELETE FROM Tesis WHERE ID_Tesis = ?";

                    PreparedStatement ps = conexion.prepareStatement(sql);
                    ps.setString(1, Titulo);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Registro eliminado", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un registro con ese título", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Limpiar el campo después de la eliminación exitosa
                    tituloField.setText("");

                    // Cerrar la conexión
                    conexion.close();
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
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
        jPanel1 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

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
            .addComponent(spTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        busqueda_field.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true));

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Observaciones");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(476, 476, 476)
                .addComponent(lb)
                .addContainerGap(520, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
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
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(busqueda_field, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busqueda_field, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
         agregarTesis();
        mostrarTabla();
    }//GEN-LAST:event_jLabel6MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JLayeredPane panel1;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
