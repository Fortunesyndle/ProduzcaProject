package raven.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.model.Model_Card;

import com.raven.swing.ScrollBar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
import rojeru_san.componentes.RSDateChooser;

public class ReportesSalidas extends javax.swing.JPanel {

    public ReportesSalidas() {
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
        
        dateChooser = new RSDateChooser(); 
        dateChooser.addPropertyChangeListener((PropertyChangeEvent evt) -> {
        if ("date".equals(evt.getPropertyName())) { 
        filtrarPorFecha(); 
            }
        });
       puntualidadComboBox.addActionListener(e -> filtrarPorPuntualidad(puntualidadComboBox.getSelectedItem().toString()));
        
        
    }
    
    
    public void mostrarTabla() {
    String sql = "SELECT Cedula, Nombres, Apellidos, Fecha, Hora, Puntualidad FROM salida";
    Conexion cn = new Conexion();
    Connection conexion = cn.conectar();

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("Cedula");
    model.addColumn("Nombres");
    model.addColumn("Apellidos");
    model.addColumn("Fecha");
    model.addColumn("Hora");
    model.addColumn("Puntualidad");

    table.setModel(model);

    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ArrayList<String> datos = new ArrayList<>();
            datos.add(rs.getString("Cedula"));
            datos.add(rs.getString("Nombres"));
            datos.add(rs.getString("Apellidos"));
            datos.add(rs.getString("Fecha"));
            datos.add(rs.getString("Hora"));
            datos.add(rs.getString("Puntualidad"));

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
    
    private void buscarRegistros(String query) {
    try {
        Connection conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
        String sql = "SELECT Cedula, Nombres, Apellidos, Fecha, Hora, Puntualidad FROM entrada WHERE Cedula LIKE ? OR Nombres LIKE ? OR Apellidos LIKE ? OR Puntualidad LIKE ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        
        ps.setString(1, "%" + query + "%");
        ps.setString(2, "%" + query + "%");
        ps.setString(3, "%" + query + "%");
        ps.setString(4, "%" + query + "%");
        
        ResultSet rs = ps.executeQuery();
        
        String[] titulos = {"Cedula", "Nombres", "Apellidos", "Fecha", "Hora", "Puntualidad"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        while (rs.next()) {
            Object[] fila = new Object[6];
            fila[0] = rs.getString("Cedula");
            fila[1] = rs.getString("Nombres");
            fila[2] = rs.getString("Apellidos");
            fila[3] = rs.getDate("Fecha");
            fila[4] = rs.getTime("Hora");
            fila[5] = rs.getString("Puntualidad");
            modelo.addRow(fila);
        }

        table.setModel(modelo);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al intentar buscar:\n" + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
    }
}


     private void filtrarPorFecha() { 
         if (dateChooser.getDatoFecha() != null) { 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String query = sdf.format(dateChooser.getDatoFecha()); buscarDatosPorFecha(query); 
        } else { 
         JOptionPane.showMessageDialog(this, "Selecciona una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE); 
         } 
     }

    private void buscarDatosPorFecha(String fecha) {
    Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
        String sql = "SELECT Cedula, Nombres, Apellidos, Fecha, Hora, Puntualidad FROM entrada WHERE DATE(Fecha) = DATE(?)";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, fecha);

        rs = ps.executeQuery();

        String[] titulos = {"Cédula", "Nombres", "Apellidos", "Fecha", "Hora", "Puntualidad"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        while (rs.next()) {
            Object[] fila = new Object[6];
            fila[0] = rs.getString("Cedula");
            fila[1] = rs.getString("Nombres");
            fila[2] = rs.getString("Apellidos");
            fila[3] = rs.getDate("Fecha");
            fila[4] = rs.getTime("Hora");
            fila[5] = rs.getString("Puntualidad");
            modelo.addRow(fila);
        }

        table.setModel(modelo);

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron registros para la fecha seleccionada.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al intentar buscar:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
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

    

    private void filtrarPorPuntualidad(String puntualidad) {
        buscarDatosPorPuntualidad(puntualidad);
    }

    private void buscarDatosPorPuntualidad(String puntualidad) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().conectar(); // Asegúrate de que este método esté bien definido
            String sql = "SELECT Cedula, Nombres, Apellidos, Fecha, Hora, Puntualidad FROM salida";

            if (!puntualidad.equals("Todos")) {
                sql += " WHERE Puntualidad = ?";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, puntualidad);
            } else {
                ps = conexion.prepareStatement(sql);
            }

            rs = ps.executeQuery();

            String[] titulos = {"Cédula", "Nombres", "Apellidos", "Fecha", "Hora", "Puntualidad"};
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("Cedula");
                fila[1] = rs.getString("Nombres");
                fila[2] = rs.getString("Apellidos");
                fila[3] = rs.getDate("Fecha");
                fila[4] = rs.getTime("Hora");
                fila[5] = rs.getString("Puntualidad");
                modelo.addRow(fila);
            }

            table.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar buscar:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión:\n" + e.getMessage(), "Error en la operación", JOptionPane.ERROR_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        busqueda_field = new javax.swing.JTextField();
        puntualidadComboBox = new javax.swing.JComboBox<>();
        dateChooser = new rojeru_san.componentes.RSDateChooser();

        jLabel2.setText("jLabel2");

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(0, 0, 102));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Salidas");
        panelBorder1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

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

        panelBorder1.add(spTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1140, 394));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Reporte de salidas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(475, 475, 475)
                .addComponent(lb)
                .addContainerGap(529, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(lb)
                .addContainerGap())
        );

        busqueda_field.setForeground(new java.awt.Color(102, 102, 102));
        busqueda_field.setText("  Cedula...");
        busqueda_field.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true));

        puntualidadComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "               ", "Puntual", "Temprano" }));
        puntualidadComboBox.setToolTipText("");
        puntualidadComboBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 2, true));
        puntualidadComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntualidadComboBoxActionPerformed(evt);
            }
        });

        dateChooser.setFuente(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateChooser.setPlaceholder("Seleccionar Fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 1136, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busqueda_field, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(puntualidadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(busqueda_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(puntualidadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void puntualidadComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntualidadComboBoxActionPerformed
        // TODO add your handling code here:
        filtrarPorPuntualidad(puntualidadComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_puntualidadComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda_field;
    private rojeru_san.componentes.RSDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JLayeredPane panel1;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JComboBox<String> puntualidadComboBox;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
