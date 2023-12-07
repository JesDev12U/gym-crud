/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package interfaces;
import java.sql.*;
import gym.*;
import configtablas.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jesus
 */
public final class Inventario extends javax.swing.JInternalFrame {
    private JButton btnMod;
    private JButton btnDel;
    private JButton[] botones;
    private String[] nomCols;
    /**
     * Creates new form Inventario
     */
    public Inventario() {
        initComponents();
        //labelCampoVacio.setVisible(false);
        btnAgregar.setEnabled(false);
        labelCarga.setVisible(false);
        botonesModDel();
        mostrarDatosTabla("Est_Prod = 1", botones, nomCols, tablaInventario);
    }
    
    private void botonesModDel(){
        btnMod = new JButton("Modificar");
        btnDel = new JButton("Eliminar");
        btnMod.setName("btnModificar");
        btnDel.setName("btnEliminar");
        botones = new JButton[2];
        botones[0] = btnMod;
        botones[1] = btnDel;
        nomCols = new String[2];
        nomCols[0] = "Modificar";
        nomCols[1] = "Eliminar";
    }

    private void habilitarBoton(){
        btnAgregar.setEnabled(!txtNom.getText().isEmpty());
    }
    
    public void mostrarDatosTabla(String where, JButton botones[], String nomCol[], JTable tabla){
        if(MySQLConnection.conectarBD()){
            String query = "SELECT ID_Prod, T_Prod, Nom_Prod, Pre_Prod, UE_Prod FROM Inventario WHERE " + where;
            Statement st;
            Connection conexion = MySQLConnection.getConexion();
            DefaultTableModel model;
            tabla.setDefaultRenderer(Object.class, new RenderTabla());
            /*JButton btnMod = new JButton("Modificar");
            btnMod.setName("btnModificar");
            JButton btnDel = new JButton("Eliminar");
            btnDel.setName("btnEliminar");*/
            int[] columnas = {1, 2, 3, 4};
            if(botones.length != 0){
                model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        for(int elem : columnas){
                            if(column == elem){
                                return true;
                            }
                        }
                        return false;
                    }
                };
            } else{
                model = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
            }
            model.addColumn("ID");
            model.addColumn("Tipo");
            model.addColumn("Nombre");
            model.addColumn("Precio");
            model.addColumn("Unidades existentes");
            /*model.addColumn("Modificar");
            model.addColumn("Eliminar");*/
            if(nomCol.length != 0){
                for(String col : nomCol){
                    model.addColumn(col);
                }
            }
            tabla.setModel(model);
            Object[] datos = new Object[5 + nomCol.length];
            try{
                st = conexion.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    int i = 5;
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    /*datos[5] = btnMod;
                    datos[6] = btnDel;*/
                    if(botones.length != 0){
                        for(JButton btn : botones){
                            datos[i] = btn;
                            i++;
                        }
                    }
                    model.addRow(datos);
                }
                tabla.setModel(model);
                tabla.setRowHeight(30);
            } catch(SQLException e){
                System.out.println("Error al ejecutar la transacción de inserción.");
                e.printStackTrace();
                if (conexion != null) {
                    try {
                        conexion.rollback(); // Hacer rollback en caso de error
                        conexion.setAutoCommit(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbxTipoProducto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        spinnerPrecio = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        spinnerUE = new javax.swing.JSpinner();
        btnAgregar = new javax.swing.JButton();
        labelCampoVacio = new javax.swing.JLabel();
        labelCarga = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Inventario");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Inventario");

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo producto", "Nombre", "Precio", "Unidades Existentes", "Modificar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Agregar producto");

        jLabel3.setText("Nombre");

        txtNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomKeyTyped(evt);
            }
        });

        jLabel4.setText("Tipo de producto");

        cmbxTipoProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bebida", "Suplemento" }));

        jLabel5.setText("Precio");

        spinnerPrecio.setModel(new javax.swing.SpinnerNumberModel(10.0d, 1.0d, 9999.0d, 1.0d));

        jLabel6.setText("Unidades Existentes");

        spinnerUE.setModel(new javax.swing.SpinnerNumberModel(10, 1, 9999, 1));
        spinnerUE.setValue(5);

        btnAgregar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        labelCampoVacio.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelCampoVacio.setForeground(new java.awt.Color(255, 0, 0));
        labelCampoVacio.setText("Campo obligatorio");

        labelCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargando.gif"))); // NOI18N

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(169, 169, 169)
                                .addComponent(btnRefrescar)
                                .addGap(75, 75, 75))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelCampoVacio))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(spinnerUE, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbxTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))
                        .addComponent(labelCarga)
                        .addGap(20, 20, 20)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(275, 275, 275))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnRefrescar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(labelCampoVacio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbxTipoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(spinnerPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(spinnerUE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCarga)
                        .addGap(37, 37, 37))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyReleased
        Gym.validarCamposVacios(txtNom, labelCampoVacio);
        habilitarBoton();
    }//GEN-LAST:event_txtNomKeyReleased

    private void txtNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomKeyTyped
        Gym.validarAlfabeto(evt);
    }//GEN-LAST:event_txtNomKeyTyped

    private int agregarProducto() throws SQLException{
        int filasAfectadas = 0;
        if(MySQLConnection.conectarBD()){
            Connection conexion = MySQLConnection.getConexion();
            String query = "INSERT INTO Inventario (ID_Prod, T_Prod, Nom_Prod, Pre_Prod, UE_Prod, Est_Prod) VALUES (DEFAULT, ?, ?, ?, ?, ?)";
            conexion.setAutoCommit(false);
            PreparedStatement st = conexion.prepareStatement(query);
            st.setString(1, cmbxTipoProducto.getSelectedItem().toString());
            st.setString(2, txtNom.getText());
            st.setDouble(3, Double.parseDouble(spinnerPrecio.getValue().toString()));
            st.setInt(4, Integer.parseInt(spinnerUE.getValue().toString()));
            st.setBoolean(5, true);
            st.executeUpdate();
            conexion.commit();
            conexion.setAutoCommit(true);
            filasAfectadas = 1;
        }
        return filasAfectadas;
    }
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        new Thread(){
            @Override
            public void run(){
                labelCarga.setVisible(true);
                int filasAfectadas = 0;
                try {
                    filasAfectadas = agregarProducto();
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(filasAfectadas > 0){
                    JOptionPane.showMessageDialog(null, 
                        "Producto almacenado correctamente", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null, 
                        "Error al almacenar el producto", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                btnRefrescar.doClick();
                labelCarga.setVisible(false);
            }
        }.start();
    }//GEN-LAST:event_btnAgregarActionPerformed
    
    /*private int obtenerIdProducto(String nombre) throws SQLException{
        if(MySQLConnection.conectarBD()){
            Connection conexion = MySQLConnection.getConexion();
            String query = "SELECT ID_Prod FROM Inventario WHERE Nom_Prod = '" + nombre + "'";
            Statement st = conexion.prepareStatement(query);
            ResultSet resultSet = st.executeQuery(query);
            int rs = 0;
            while(resultSet.next()){
                rs = resultSet.getInt("ID_Prod");
            }
            return rs;
        }
        return -1;
    }*/
    
    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        int seleccion = tablaInventario.rowAtPoint(evt.getPoint());
        int column = tablaInventario.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tablaInventario.getRowHeight();
        
        if(row < tablaInventario.getRowCount() && row >= 0 && column < tablaInventario.getColumnCount() 
                && column >= 0){
            Object value = tablaInventario.getValueAt(row, column);
            if(value instanceof JButton){
                new Thread(){
                    @Override
                    public void run(){
                        labelCarga.setVisible(true);
                        ((JButton)value).doClick();
                        JButton boton = (JButton)value;
                        int id = Integer.parseInt(String.valueOf(tablaInventario.getValueAt(seleccion, 0)));
                        if(boton.getName().equals("btnModificar")){
                            int filasAfectadas = 0;
                            Connection conexion = null;
                            if(MySQLConnection.conectarBD()){
                                try {
                                    conexion = MySQLConnection.getConexion();
                                    conexion.setAutoCommit(false);
                                    
                                    String t_prod = String.valueOf(tablaInventario.getValueAt(seleccion, 1));
                                    String nombre = String.valueOf(tablaInventario.getValueAt(seleccion, 2));
                                    double precio = Double.parseDouble(String.valueOf(tablaInventario.getValueAt(seleccion, 3)));
                                    int unidadesE = Integer.parseInt(String.valueOf(tablaInventario.getValueAt(seleccion, 4)));
                                    String query = "UPDATE Inventario SET T_Prod = ?, Nom_Prod = ?, Pre_Prod = ?, UE_Prod = ? WHERE ID_Prod = ?";
                                    PreparedStatement st = conexion.prepareStatement(query);
                                    st.setString(1, t_prod);
                                    st.setString(2, nombre);
                                    st.setDouble(3, precio);
                                    st.setInt(4, unidadesE);
                                    st.setInt(5, id);
                                    st.executeUpdate();
                                    conexion.commit();
                                    conexion.setAutoCommit(true);
                                    filasAfectadas = 1;
                                    
                                } catch (Exception ex) {
                                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if(filasAfectadas > 0){
                                    JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", 
                                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            } else{
                                JOptionPane.showMessageDialog(null, "Error al actualizar el producto",
                                   "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            btnRefrescar.doClick();
                        }
                        if(boton.getName().equals("btnEliminar")){
                            int filasAfectadas = 0;
                            if(MySQLConnection.conectarBD()){
                                Connection conexion = MySQLConnection.getConexion();
                                String query = "UPDATE Inventario SET Est_Prod = 0 WHERE ID_Prod = ?";
                                try {
                                    conexion.setAutoCommit(false);
                                    PreparedStatement st = conexion.prepareStatement(query);
                                    st.setInt(1, id);
                                    st.executeUpdate();
                                    conexion.commit();
                                    conexion.setAutoCommit(true);
                                    filasAfectadas = 1;
                                } catch (SQLException ex) {
                                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if(filasAfectadas > 0){
                                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente",
                                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            } else{
                                JOptionPane.showMessageDialog(null, "Error al eliminar el producto",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            btnRefrescar.doClick();
                        }
                        labelCarga.setVisible(false);
                    }
                }.start();
            }
        }
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        mostrarDatosTabla("Est_Prod = 1", botones, nomCols, tablaInventario);
    }//GEN-LAST:event_btnRefrescarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JComboBox<String> cmbxTipoProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCampoVacio;
    private javax.swing.JLabel labelCarga;
    private javax.swing.JSpinner spinnerPrecio;
    private javax.swing.JSpinner spinnerUE;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
