/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package interfaces;
import gym.*;
import java.io.IOException;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jesus
 */
public class ReporteVentas extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReporteVentas
     */
    public ReporteVentas() {
        initComponents();
        mostrarDatosTabla();
        labelCarga.setVisible(false);
    }
    
    private void mostrarDatosTabla(){
        if(MySQLConnection.conectarBD()){
            String query = "SELECT v.ID_Venta, v.ID_Pago, p.Form_Pago, v.Hor_Venta, d.ID_Prod, \n" +
            "i.T_Prod, i.Nom_Prod, i.Pre_Prod, d.Cantidad_V, d.Importe_V\n" +
            "FROM Ventas v\n" +
            "INNER JOIN DetalleDeLaVenta d ON v.ID_Venta = d.ID_Venta\n" +
            "INNER JOIN Pagos p on v.ID_Pago = p.ID_Pago\n" +
            "INNER JOIN Inventario i on i.ID_Prod = d.ID_Prod";
            Statement st;
            Connection conexion = MySQLConnection.getConexion();
            DefaultTableModel model;
            model = new DefaultTableModel(){
               @Override
               public boolean isCellEditable(int row, int column){
                   return false;
               }
            };
            model.addColumn("ID_Venta");
            model.addColumn("ID_Pago");
            model.addColumn("Form_Pago");
            model.addColumn("Hor_Venta");
            model.addColumn("ID_Prod");
            model.addColumn("T_Prod");
            model.addColumn("Nom_Prod");
            model.addColumn("Pre_Prod");
            model.addColumn("Cantidad_V");
            model.addColumn("Importe_V");
            tabla.setModel(model);
            Object[] datos = new Object[10];
            try{
                st = conexion.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    datos[0] = rs.getInt(1);
                    datos[1] = rs.getInt(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getTimestamp(4);
                    datos[4] = rs.getInt(5);
                    datos[5] = rs.getString(6);
                    datos[6] = rs.getString(7);
                    datos[7] = rs.getDouble(8);
                    datos[8] = rs.getInt(9);
                    datos[9] = rs.getDouble(10);
                    model.addRow(datos);
                }
                tabla.setModel(model);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnExcel = new javax.swing.JToggleButton();
        btnRefrescar = new javax.swing.JButton();
        labelCarga = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de ventas");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Reporte de ventas");

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/excel.png"))); // NOI18N
        btnExcel.setText("Exportar a Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        labelCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargando.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(labelCarga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(btnRefrescar)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExcel)
                            .addComponent(btnRefrescar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(labelCarga)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        mostrarDatosTabla();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        new Thread(){
            @Override
            public void run(){
                labelCarga.setVisible(true);
                ExportarExcel excel;
                try{
                    excel = new ExportarExcel();
                    excel.exportarExcel(tabla);
                } catch(IOException e){
                    System.out.println(e);
                }
                labelCarga.setVisible(false);
            }
        }.start();
    }//GEN-LAST:event_btnExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnExcel;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCarga;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
