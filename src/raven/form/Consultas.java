package raven.form;

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

public class Consultas extends javax.swing.JPanel {

    public Consultas() {
        initComponents();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/stock.png")), "Agregar tesis", "$200000", "Increased by 60%"));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        //  add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        mostrarTabla();
        
        
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
    
    public void mostrarTablaLibros() {
        
        
        String sql = "SELECT * FROM Libros";
        Conexion cn = new Conexion();
        Connection conexion = cn.conectar();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Libro");
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
    
    public void mostrarTablaTesis() {
        
        
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
    
    public void mostrarTablaPasantias() {
        
        
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
         
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear los componentes del formulario
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(8);
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
       
                

        JOptionPane.showConfirmDialog(null, panel, "Modificar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        try {
             
            if (tituloField.getText().isBlank() || autorField.getText().isBlank() || cedulaField.getText().isBlank() || fechaField.getText().isBlank() || especialidadField.getText().isBlank()
                    ) {
                JOptionPane.showMessageDialog(null, "Faltan campos por llenar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
            

                Conexion cn = new Conexion();
                Connection conexion = cn.conectar();
                Statement st = conexion.createStatement();

                String ID, Titulo, Autor, Cedula, Fecha, Enumeracion, Especialidad,  Resumen;
                ID = texto(idField);
                Titulo = texto(tituloField);
                Autor = texto(autorField);
                Cedula = texto(cedulaField);
                Fecha = texto(fechaField);
                Especialidad = texto(especialidadField);
                Enumeracion = texto(enumeracionField);
                
                int ID_Tesis = Integer.parseInt(ID);
               
                

                String sql = "UPDATE Tesis SET Titulo = ?, Autor = ?, Cedula = ?, Fecha = ?, Especialidad = ?, Enumeracion = ? WHERE ID_Tesis = ?";
                PreparedStatement ps2 = conexion.prepareStatement(sql);

                ps2.setString(1, Titulo);
                ps2.setString(2, Autor);
                ps2.setString(3, Cedula);
                ps2.setString(4, Fecha);
                ps2.setString(5, Especialidad);
                ps2.setString(6, Enumeracion);
                ps2.setInt(7, ID_Tesis);

                ps2.executeUpdate();

                // Ejecutar la actualización
        int rowsAffected = ps2.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro actualizado", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el registro con ID: " + ID_Tesis, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

        }   
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        
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
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JLayeredPane();
        panel1 = new javax.swing.JLayeredPane();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();
        panel2 = new javax.swing.JLayeredPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();
        busqueda_field = new javax.swing.JTextField();

        jLabel2.setText("jLabel2");

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Standard Table Design");

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
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

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

        panel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 265;
        gridBagConstraints.ipady = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 19, 0, 0);
        panel2.add(jLabel3, gridBagConstraints);

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        panel2.add(jLabel4, new java.awt.GridBagConstraints());

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 255;
        gridBagConstraints.ipady = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 17, 0, 0);
        panel2.add(jLabel5, gridBagConstraints);

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 255;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panel2.add(card1, gridBagConstraints);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        card2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card2MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 255;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panel2.add(card2, gridBagConstraints);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 255;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 1);
        panel2.add(card3, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(busqueda_field, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
                    .addGap(20, 20, 20)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(busqueda_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(580, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(625, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(69, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        
        mostrarTabla();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
     
        mostrarTabla();
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        
        mostrarTabla();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void card2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_card2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busqueda_field;
    private com.raven.component.Card card1;
    private com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JLayeredPane panel1;
    private javax.swing.JLayeredPane panel2;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
