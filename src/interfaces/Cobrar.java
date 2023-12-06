/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package interfaces;

import gym.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author jesus
 */
public class Cobrar extends javax.swing.JInternalFrame {
    final double costoMensual = 100;
    final double costoSemanal = 60;
    /**
     * Creates new form CobrarMensualidad
     */
    public Cobrar() {
        initComponents();
        labelCarga.setVisible(false);
        btnRealizarCobro.setEnabled(false);
        Gym.inicioFiltroTablaCobros(tablaClientes, txtFolio, btnRealizarCobro);
        mostrarDatosTabla();
        //tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
    }
    
    private void mostrarDatosTabla(){
        if(MySQLConnection.conectarBD()){
            String sql = "SELECT \n" +
            "    COALESCE(C.ID_Cli, CS.ID_Sem) AS ID_Cliente,\n" +
            "    CONCAT(COALESCE(C.Nom_Cli, CS.Nom_Sem), ' ', COALESCE(C.ApPat_Cli, CS.ApPat_Sem), ' ', COALESCE(C.ApMat_Cli, CS.ApMat_Sem)) AS Nombre_Completo,\n" +
            "    COALESCE(PM.Desc_Pago, 'Pago semanal') AS Descripcion_Pago,\n" +
            "    COALESCE(LP.Fec_Pago, 'N/A') AS Ultimo_Pago\n" +
            "FROM \n" +
            "    (\n" +
            "    SELECT \n" +
            "        ID_Cli, \n" +
            "        ID_Pago,\n" +
            "        'Pago mensual' AS Desc_Pago\n" +
            "    FROM \n" +
            "        Mensualidades\n" +
            "    UNION ALL\n" +
            "    SELECT \n" +
            "        ID_Sem AS ID_Cli, \n" +
            "        ID_Pago,\n" +
            "        'Pago semanal' AS Desc_Pago\n" +
            "    FROM \n" +
            "        ClienteSemanal\n" +
            "    UNION ALL\n" +
            "    SELECT \n" +
            "        ID_CliSem AS ID_Cli,\n" +
            "        ID_Pago,\n" +
            "        'Pago semanal' AS Desc_Pago\n" +
            "    FROM \n" +
            "        pagosemanales\n" +
            "    ) AS PM\n" +
            "LEFT JOIN \n" +
            "    Clientes C ON PM.ID_Cli = C.ID_Cli\n" +
            "LEFT JOIN \n" +
            "    ClienteSemanal CS ON PM.ID_Cli = CS.ID_Sem\n" +
            "LEFT JOIN \n" +
            "    Pagos P ON PM.ID_Pago = P.ID_Pago\n" +
            "LEFT JOIN (\n" +
            "    SELECT \n" +
            "        ID_Cli,\n" +
            "        MAX(Fec_Pago) AS Fec_Pago\n" +
            "    FROM \n" +
            "        (\n" +
            "        SELECT \n" +
            "            ID_Cli,\n" +
            "            ID_Pago\n" +
            "        FROM \n" +
            "            Mensualidades\n" +
            "        UNION ALL\n" +
            "        SELECT \n" +
            "            ID_Sem AS ID_Cli,\n" +
            "            ID_Pago\n" +
            "        FROM \n" +
            "            ClienteSemanal\n" +
            "        UNION ALL\n" +
            "        SELECT \n" +
            "            ID_CliSem AS ID_Cli,\n" +
            "            ID_Pago\n" +
            "        FROM \n" +
            "            pagosemanales\n" +
            "        ) AS PM\n" +
            "    LEFT JOIN Pagos ON PM.ID_Pago = Pagos.ID_Pago\n" +
            "    GROUP BY \n" +
            "        ID_Cli\n" +
            "    ) AS LP ON (C.ID_Cli = LP.ID_Cli OR CS.ID_Sem = LP.ID_Cli)\n" +
            "WHERE \n" +
            "    (\n" +
            "    (PM.Desc_Pago = 'Pago mensual' OR PM.Desc_Pago = 'Pago semanal') AND \n" +
            "    COALESCE(LP.Fec_Pago, '1900-01-01') <\n" +
            "        CASE\n" +
            "            WHEN PM.Desc_Pago = 'Pago mensual' THEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH)\n" +
            "            WHEN PM.Desc_Pago = 'Pago semanal' THEN DATE_SUB(CURDATE(), INTERVAL 1 WEEK)\n" +
            "            ELSE CURDATE()\n" +
            "        END\n" +
            "    )\n" +
            "    AND (C.Est_Cli = 'Activo' OR CS.Est_Sem = 'Activo') -- Condición para clientes activos\n" +
            "GROUP BY \n" +
            "    ID_Cliente\n" +
            "ORDER BY \n" +
            "    ID_Cliente;";
            Statement st;
            Connection conexion = MySQLConnection.getConexion();
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            model.addColumn("Número de Folio");
            model.addColumn("Nombre");
            model.addColumn("Descripción del pago");
            model.addColumn("Último pago");
            tablaClientes.setModel(model);
            String [] datos = new String[4];
            try{
                st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    model.addRow(datos);
                }
                tablaClientes.setModel(model);
            } catch (SQLException e) {
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

        btnGClientes = new javax.swing.ButtonGroup();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnRealizarCobro = new javax.swing.JButton();
        labelCarga = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbxFormaPago = new javax.swing.JComboBox<>();
        txtMonto = new javax.swing.JTextField();
        txtDescPago = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cobros");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Cobros");

        jLabel2.setText("Nombre");

        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroKeyTyped(evt);
            }
        });

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número de folio", "Nombre", "Descripción del pago", "Último pago"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        jLabel3.setText("Número de folio");

        txtFolio.setEnabled(false);

        jLabel4.setText("Monto a pagar");

        btnRealizarCobro.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        btnRealizarCobro.setText("Registrar cobro");
        btnRealizarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCobroActionPerformed(evt);
            }
        });

        labelCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargando.gif"))); // NOI18N

        jLabel5.setText("Forma de pago");

        cmbxFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        txtMonto.setEnabled(false);

        txtDescPago.setText("Pago ");
        txtDescPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescPagoKeyTyped(evt);
            }
        });

        jLabel7.setText("Pago");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnRealizarCobro)
                        .addGap(30, 30, 30)
                        .addComponent(labelCarga)
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(209, 209, 209)
                        .addComponent(jButton1)
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescPago, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtDescPago, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(cmbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRealizarCobro)))
                    .addComponent(labelCarga))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyTyped
        Gym gym = new Gym();
        //gym.procesoFiltro(txtDescPago, tablaClientes, 2);
        gym.procesoFiltro(txtFiltro, tablaClientes, 1);
    }//GEN-LAST:event_txtFiltroKeyTyped

    private int realizarCobroMensual(){
        int filasAfectadas = 0;
        Connection conexion = null;
        try {
            if(MySQLConnection.conectarBD()){
                conexion = MySQLConnection.getConexion();
                conexion.setAutoCommit(false);
                double monto = Double.parseDouble(txtMonto.getText());
                int idPago = Gym.registrarPago("Pago mensual", cmbxFormaPago, monto);
                String folio = txtFolio.getText();
                String query = "INSERT INTO mensualidades VALUES (DEFAULT, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1, folio);
                statement.setInt(2, idPago);
                statement.executeUpdate();
                conexion.commit();
                conexion.setAutoCommit(true);
                filasAfectadas = 1;
            }
        } catch (SQLException e) {
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
        return filasAfectadas;
    }
    
    private int realizarCobroSemanal(){
        int filasAfectadas = 0;
        Connection conexion = null;
        try {
            if(MySQLConnection.conectarBD()){
                conexion = MySQLConnection.getConexion();
                conexion.setAutoCommit(false);
                double monto = Double.parseDouble(txtMonto.getText());
                int idPago = Gym.registrarPago("Pago semanal", cmbxFormaPago, monto);
                String folio = txtFolio.getText();
                String query = "INSERT INTO pagosemanales (ID_PSem, ID_CliSem, ID_Pago) VALUES (DEFAULT, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1, folio);
                statement.setInt(2, idPago);
                statement.executeUpdate();
                conexion.commit();
                conexion.setAutoCommit(true);
                filasAfectadas = 1;
            }
        } catch (SQLException e) {
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
        return filasAfectadas;
    }
    
    private void btnRealizarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCobroActionPerformed
        new Thread(){
            @Override
            public void run(){
                labelCarga.setVisible(true);
                /*realizarCobroMensual();
                mostrarDatosTabla(); //Actualiza la tabla de clientes*/
                char idFolioChar = txtFolio.getText().charAt(0);
                switch(idFolioChar){
                    case 'C' -> {
                        int ejecutar = realizarCobroMensual();
                        if(ejecutar > 0){
                            JOptionPane.showMessageDialog(null,
                                    "Cobro realizado correctamente", "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(null,
                                    "Error al realizar el cobro", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 'S' -> {
                        int ejecutar = realizarCobroSemanal();
                        if(ejecutar > 0){
                            JOptionPane.showMessageDialog(null,
                                    "Cobro realizado correctamente", "Aviso",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(null,
                                    "Error al realizar el cobro", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                mostrarDatosTabla();
                labelCarga.setVisible(false);
                btnRealizarCobro.setEnabled(false);
            }
        }.start();
    }//GEN-LAST:event_btnRealizarCobroActionPerformed

    private long calcularDiasDiferencia(String fechaSeleccionada){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Parsear la fecha a java.sql.Date
            java.sql.Date fechaFormateada = new java.sql.Date(formatoFecha.parse(fechaSeleccionada).getTime());

            // Obtener la fecha actual como java.sql.Date
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            // Calcular la diferencia en milisegundos
            long millisecondsDifference = currentDate.getTime() - fechaFormateada.getTime();
            long daysDifference = millisecondsDifference / (1000 * 60 * 60 * 24);
            
            //System.out.println("Diferencia en días: " + daysDifference);
            return daysDifference;
        } catch(ParseException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        int seleccionar = tablaClientes.rowAtPoint(evt.getPoint());
        String fechaSeleccionada = String.valueOf(tablaClientes.getValueAt(seleccionar, 3));
        long dias = calcularDiasDiferencia(fechaSeleccionada);
        String id = String.valueOf(tablaClientes.getValueAt(seleccionar, 0));
        char comienzoId = id.charAt(0);
        if(comienzoId == 'C'){
            txtMonto.setText(String.valueOf((dias * (costoMensual * 0.10)) + costoMensual));
        } else{
            txtMonto.setText(String.valueOf((dias * (costoSemanal * 0.10)) + costoSemanal));
        }
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void txtDescPagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescPagoKeyTyped
        Gym gym = new Gym();
        gym.procesoFiltro(txtDescPago, tablaClientes, 2);
        //gym.procesoFiltro(txtFiltro, tablaClientes, 1);
    }//GEN-LAST:event_txtDescPagoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarDatosTabla();
        btnRealizarCobro.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGClientes;
    private javax.swing.JButton btnRealizarCobro;
    private javax.swing.JComboBox<String> cmbxFormaPago;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCarga;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtDescPago;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
