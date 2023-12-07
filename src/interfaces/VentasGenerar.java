/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package interfaces;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import gym.*;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class VentasGenerar extends javax.swing.JInternalFrame {
    private final Inventario inventario;
    private JButton[] botones;
    private String[] nomCols;
    /**
     * Creates new form VentasGenerar
     */
    public VentasGenerar() {
        initComponents();
        botonesInicio();
        inventario = new Inventario();
        inventario.mostrarDatosTabla("Est_Prod = 1 AND UE_Prod > 0", botones, nomCols, tablaInventario);
        mostrarTotal();
        labelCarga.setVisible(false);
    }

    private void botonesInicio(){
        botones = new JButton[0];
        nomCols = new String[0];
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomProd = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbxFormaPago = new javax.swing.JComboBox<>();
        btnVender = new javax.swing.JButton();
        labelCarga = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Generar venta");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Ventas");

        jLabel2.setText("Nombre del producto");

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo producto", "Nombre", "Precio", "Unidades existentes"
            }
        ));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        tablaVentas = new javax.swing.JTable(){
            public boolean isCellEditable(){
                return false;
            }
        };
        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo producto", "Nombre", "Precio", "Cantidad", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaVentas);

        jLabel3.setText("Total a pagar");

        labelTotal.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotal.setText("0.00");

        jLabel6.setText("Forma de pago");

        cmbxFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta", " " }));

        btnVender.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnVender.setText("Vender");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        labelCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargando.gif"))); // NOI18N

        jLabel7.setText("Tipo de producto");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomProd, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(labelCarga))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefrescar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefrescar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNomProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(labelTotal))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(labelCarga)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(btnVender)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private double calcularSubTotal(double precio, int cantidad){
        return precio * cantidad;
    }
    
    private double calcularTotal(){
        int cantidad = 0;
        double precio = 0;
        double total = 0;
        for (int i = 0; i < tablaVentas.getRowCount(); i++) {
            cantidad = Integer.parseInt(String.valueOf(tablaVentas.getValueAt(i, 4)));
            precio = Double.parseDouble(String.valueOf(tablaVentas.getValueAt(i, 3)));
            total += calcularSubTotal(precio, cantidad);
        }
        return total;
    }
    
    private void mostrarTotal(){
        labelTotal.setText("$" + String.format("%.2f", calcularTotal()));
    }
    
    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        Object unidadesE =  tablaInventario.getValueAt(tablaInventario.getSelectedRow(), 4);
        int uE = Integer.parseInt(String.valueOf(unidadesE));
        String input = JOptionPane.showInputDialog("Teclee la cantidad a vender");
        try {
            int cantidadVenta = Integer.parseInt(input);
            if(cantidadVenta > uE || cantidadVenta < 1) {
                JOptionPane.showMessageDialog(null, "Cantidad no valida", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                TableModel modelo1 = tablaInventario.getModel();
                int[] filas = tablaInventario.getSelectedRows();
                DefaultTableModel modelo2 = (DefaultTableModel) tablaVentas.getModel();
                boolean encontrado = false;

                // Recorremos la tabla de ventas para verificar si el producto ya está presente
                for (int i = 0; i < modelo2.getRowCount(); i++) {
                    if (modelo1.getValueAt(filas[0], 0).equals(modelo2.getValueAt(i, 0))) {
                        // Si el producto ya está en la tabla, actualizamos la cantidad
                        int cantidadExistente = (int) modelo2.getValueAt(i, 4);
                        modelo2.setValueAt(cantidadExistente + cantidadVenta, i, 4);
                        encontrado = true;
                        break;
                    }
                }

                // Si el producto no está en la tabla, agregamos una nueva fila
                if (!encontrado) {
                    Object[] row = new Object[6];
                    for (int i = 0; i < filas.length; i++) {
                        row[0] = modelo1.getValueAt(filas[i], 0);
                        row[1] = modelo1.getValueAt(filas[i], 1);
                        row[2] = modelo1.getValueAt(filas[i], 2);
                        row[3] = modelo1.getValueAt(filas[i], 3);
                        row[4] = cantidadVenta;
                        row[5] = String.format("%.2f", calcularSubTotal(
                                Double.parseDouble(String.valueOf(row[3])), cantidadVenta));
                        modelo2.addRow(row);
                    }
                }
                btnRefrescar.doClick();
                mostrarTotal();
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Teclee un numero!", "Error", JOptionPane.ERROR_MESSAGE);
            btnRefrescar.doClick();
        }
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        inventario.mostrarDatosTabla("Est_Prod = 1 AND UE_Prod > 0", botones, nomCols, tablaInventario);
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        Object[] opciones = {"Cambiar cantidad", "Quitar"};
        
        int seleccion = JOptionPane.showOptionDialog(
            null, // Componente padre, en este caso lo dejamos en null
            "Selecciona una opción:", // Mensaje a mostrar
            "Opciones", // Título del diálogo
            JOptionPane.YES_NO_OPTION, // Tipo de opciones (en este caso, dos botones)
            JOptionPane.QUESTION_MESSAGE, // Tipo de mensaje (en este caso, una pregunta)
            null, // Icono personalizado (en este caso, lo dejamos en null)
            opciones, // Opciones a mostrar
            opciones[0] // Opción por defecto seleccionada
        );
        
        if(seleccion == 0){
            Object unidadesE =  tablaInventario.getValueAt(tablaVentas.getSelectedRow(), 4);
            int uE = Integer.parseInt(String.valueOf(unidadesE));
            String input = JOptionPane.showInputDialog("Teclee la nueva cantidad");
            try{
                int cantidad = Integer.parseInt(input);
                if(cantidad > uE || cantidad < 1){
                    JOptionPane.showMessageDialog(null, "Cantidad no valida", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    tablaVentas.setValueAt(cantidad, tablaVentas.getSelectedRow(), 4);
                    JOptionPane.showMessageDialog(null, "Cantidad modificada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    Object precio = tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 3);
                    Object cantidadProd = tablaVentas.getValueAt(tablaVentas.getSelectedRow(), 4);
                    tablaVentas.setValueAt(String.format("%.2f", calcularSubTotal(Double.parseDouble(String.valueOf(precio)),
                            Integer.parseInt(String.valueOf(cantidadProd)))), tablaVentas.getSelectedRow(), 5);
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Teclee un numero!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(seleccion == 1){
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el producto seleccionado?");
            if(confirmacion == JOptionPane.YES_OPTION){
                DefaultTableModel model = (DefaultTableModel)tablaVentas.getModel();
                model.removeRow(tablaVentas.getSelectedRow());
            }
        }
        mostrarTotal();
    }//GEN-LAST:event_tablaVentasMouseClicked

    private int realizarVenta(){
        int filasAfectadas = 0;
        if(MySQLConnection.conectarBD()){
            Connection conexion = null;
            try{
                conexion = MySQLConnection.getConexion();
                conexion.setAutoCommit(false);
                Date fecActual = new Date();
                Timestamp timestamp = new Timestamp(fecActual.getTime());
                int idPago = Gym.registrarPago("Venta", cmbxFormaPago, calcularTotal());
                String queryVenta = "INSERT INTO Ventas (ID_Venta, ID_Pago, Hor_Venta) VALUES (DEFAULT, ?, ?)";
                PreparedStatement stVentas = conexion.prepareStatement(queryVenta, Statement.RETURN_GENERATED_KEYS);
                stVentas.setInt(1, idPago);
                stVentas.setTimestamp(2, timestamp);
                stVentas.executeUpdate();
                conexion.commit();
                conexion.setAutoCommit(true);
                ResultSet generatedKeys = stVentas.getGeneratedKeys();
                int idVenta = 0;
                if(generatedKeys.next()){
                    idVenta = generatedKeys.getInt(1);
                }
                for (int i = 0; i < tablaVentas.getRowCount(); i++) {
                    conexion.setAutoCommit(false);
                    String queryDetalleVenta = "INSERT INTO DetalleDeLaVenta (ID_Venta, ID_Prod, Cantidad_V, Importe_V) "
                        + "VALUES (?, ?, ?, ?)";
                    PreparedStatement stDVentas = conexion.prepareStatement(queryDetalleVenta);
                    stDVentas.setInt(1, idVenta);
                    stDVentas.setInt(2, Integer.parseInt(String.valueOf(tablaVentas.getValueAt(i, 0))));
                    stDVentas.setInt(3, Integer.parseInt(String.valueOf(tablaVentas.getValueAt(i, 4))));
                    stDVentas.setDouble(4, Double.parseDouble(String.valueOf(tablaVentas.getValueAt(i, 5))));
                    stDVentas.executeUpdate();
                    String queryUE = "SELECT UE_Prod FROM Inventario WHERE ID_Prod = " + String.valueOf(tablaVentas.getValueAt(i, 0));
                    Statement stUE = conexion.createStatement();
                    ResultSet resUE = stUE.executeQuery(queryUE);
                    int ue = 0;
                    while(resUE.next()){
                        ue = resUE.getInt("UE_Prod");
                    }
                    ue -= Integer.parseInt(String.valueOf(tablaVentas.getValueAt(i, 4)));
                    String queryInventario = "UPDATE Inventario SET UE_Prod = ? WHERE ID_Prod = ?";
                    PreparedStatement stInv = conexion.prepareStatement(queryInventario);
                    stInv.setInt(1, ue);
                    stInv.setInt(2, Integer.parseInt(String.valueOf(tablaVentas.getValueAt(i, 0))));
                    stInv.executeUpdate();
                    conexion.commit();
                    conexion.setAutoCommit(true);
                }
                filasAfectadas = 1;
            } catch(SQLException e){
                System.out.println(e);
            }
        }
        return filasAfectadas;
    }
    
    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        new Thread(){
            @Override
            public void run(){
                labelCarga.setVisible(true);
                int filasAfectadas = realizarVenta();
                if(filasAfectadas > 0){
                    JOptionPane.showMessageDialog(null, 
                        "Venta realizada correctamente", 
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null, 
                        "Error al realizar la venta", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                btnRefrescar.doClick();
                labelCarga.setVisible(false);
            }
        }.start();
    }//GEN-LAST:event_btnVenderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnVender;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbxFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelCarga;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtNomProd;
    // End of variables declaration//GEN-END:variables
}
