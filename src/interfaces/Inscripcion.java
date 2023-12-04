/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package interfaces;
import gym.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author jesus
 */
public class Inscripcion extends javax.swing.JInternalFrame {
    final double costoInscripcionMensual = 3000;
    final double costoInscripcionSemanal = 1500;
    final double costoVisita = 500;
    private javax.swing.JTextField arrayTxt[];
    /**
     * Creates new form Inscripcion
     */
    public Inscripcion() {
        initComponents();
        javax.swing.JLabel arrayLbl[] = new javax.swing.JLabel[3];
        arrayLbl[0] = labelCampoReq;
        arrayLbl[1] = labelCampoReq1;
        arrayLbl[2] = labelCampoReq2;
        arrayTxt = new javax.swing.JTextField[3];
        arrayTxt[0] = txtNomCliente;
        arrayTxt[1] = txtApPatCliente;
        arrayTxt[2] = txtApMatCliente;
        btnRealizarIns.setEnabled(false);
        Gym.iniciarForm(arrayLbl);
        labelCarga.setVisible(false);
    }
    
    private void habilitarBoton(){
        if(txtNomCliente.getText().isEmpty() || txtApPatCliente.getText().isEmpty()
                || txtApMatCliente.getText().isEmpty()){
            btnRealizarIns.setEnabled(false);
        } else{
            btnRealizarIns.setEnabled(true);
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
        jLabel2 = new javax.swing.JLabel();
        txtNomCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbxFormaPago = new javax.swing.JComboBox<>();
        btnRealizarIns = new javax.swing.JButton();
        labelCampoReq = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtApPatCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtApMatCliente = new javax.swing.JTextField();
        labelCampoReq1 = new javax.swing.JLabel();
        labelCampoReq2 = new javax.swing.JLabel();
        labelCarga = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbxTipoCliente = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Inscripción");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Inscripcion");

        jLabel2.setText("Nombre del cliente");

        txtNomCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomClienteKeyTyped(evt);
            }
        });

        jLabel3.setText("Forma de pago");

        cmbxFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta", " " }));

        btnRealizarIns.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        btnRealizarIns.setText("Realizar inscripción");
        btnRealizarIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarInsActionPerformed(evt);
            }
        });

        labelCampoReq.setForeground(new java.awt.Color(255, 0, 0));

        jLabel4.setText("Apellido paterno");

        txtApPatCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApPatClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApPatClienteKeyTyped(evt);
            }
        });

        jLabel5.setText("Apellido materno");

        txtApMatCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApMatClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApMatClienteKeyTyped(evt);
            }
        });

        labelCampoReq1.setForeground(new java.awt.Color(255, 0, 0));

        labelCampoReq2.setForeground(new java.awt.Color(255, 0, 0));

        labelCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargando.gif"))); // NOI18N

        jLabel6.setText("Tipo de cliente");

        cmbxTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mensual", "Semanal", "Visita" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApMatCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApPatCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCampoReq1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelCampoReq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCampoReq2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(113, 113, 113)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRealizarIns, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(labelCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(90, 90, 90))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelCampoReq1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNomCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCampoReq, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtApPatCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtApMatCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelCampoReq2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRealizarIns, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cmbxTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cmbxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomClienteKeyTyped
        Gym.validarAlfabeto(evt);
        Gym.validarCantidadCaracteres(txtNomCliente, evt, 30);
    }//GEN-LAST:event_txtNomClienteKeyTyped

    private void txtNomClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomClienteKeyReleased
        Gym.validarCamposVacios(txtNomCliente, labelCampoReq );
        habilitarBoton();
    }//GEN-LAST:event_txtNomClienteKeyReleased

    private void txtApPatClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApPatClienteKeyReleased
        Gym.validarCamposVacios(txtApPatCliente, labelCampoReq1);
        habilitarBoton();
    }//GEN-LAST:event_txtApPatClienteKeyReleased

    private void txtApPatClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApPatClienteKeyTyped
        Gym.validarAlfabeto(evt);
        Gym.validarCantidadCaracteres(txtApPatCliente, evt, 15);
    }//GEN-LAST:event_txtApPatClienteKeyTyped

    private void txtApMatClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApMatClienteKeyReleased
        Gym.validarCamposVacios(txtApMatCliente, labelCampoReq2);
        habilitarBoton();
    }//GEN-LAST:event_txtApMatClienteKeyReleased

    private void txtApMatClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApMatClienteKeyTyped
        Gym.validarAlfabeto(evt);
        Gym.validarCantidadCaracteres(txtApMatCliente, evt, 15);
    }//GEN-LAST:event_txtApMatClienteKeyTyped

    /*private void proceso(){
        try {
            btnRealizarIns.setEnabled(false);
            Thread.sleep(3000);
            btnRealizarIns.setEnabled(true);
        } catch (InterruptedException ex) {}
    }*/
    
    private int ejecutarInscripcionMensual(){ 
        int filasAfectadas = 0;
        Connection conexion = null;
        try{
            if(MySQLConnection.conectarBD()){
                conexion = MySQLConnection.getConexion();
                conexion.setAutoCommit(false);
                int idPago = Gym.registrarPago("Inscripcion Mensual", cmbxFormaPago, costoInscripcionMensual);
                String insertInscripcionQuery = "INSERT INTO Inscripciones (ID_Ins, Fec_Ins, ID_Pago) " + 
                        "VALUES (DEFAULT, ?, ?)";
                PreparedStatement statementInscripcion = conexion.prepareStatement(insertInscripcionQuery, Statement.RETURN_GENERATED_KEYS);
                statementInscripcion.setDate(1, MySQLConnection.getFechaActual());
                statementInscripcion.setInt(2, idPago);
                statementInscripcion.executeUpdate();

                //Obtener el ID_Ins generado
                ResultSet generatedKeysInscripcion = statementInscripcion.getGeneratedKeys();
                int idIns = -1;
                if(generatedKeysInscripcion.next()){
                    idIns = generatedKeysInscripcion.getInt(1);
                }
                //Obtener el ultimo ID del Cliente
                int sigID = Gym.obtenerUltimoID("ID_Cli", "Clientes", "3");
                
                String idCliente = "Cl";
                if(sigID < 10){
                    idCliente += "00" + sigID;
                } else if(sigID < 100){
                    idCliente += "0" + sigID;
                } else{
                    idCliente += sigID;
                }
                
                String insertClientesQuery = "INSERT INTO Clientes (ID_Cli, Nom_Cli, ApPat_Cli, ApMat_Cli, Est_Cli, ID_Ins) " + 
                        "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statementClientes = conexion.prepareStatement(insertClientesQuery);
                statementClientes.setString(1, idCliente);
                statementClientes.setString(2, txtNomCliente.getText());
                statementClientes.setString(3, txtApPatCliente.getText());
                statementClientes.setString(4, txtApMatCliente.getText());
                statementClientes.setString(5, "Activo");
                statementClientes.setInt(6, idIns);
                statementClientes.executeUpdate();

                String insertMensualidadQuery = "INSERT INTO Mensualidades (ID_Men, ID_Cli, ID_Pago) " + 
                        "VALUES (DEFAULT, ?, ?)";
                PreparedStatement statementMensualidad = conexion.prepareCall(insertMensualidadQuery);
                statementMensualidad.setString(1, idCliente);
                statementMensualidad.setInt(2, idPago);
                statementMensualidad.executeUpdate();
                
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
    
    private int ejecutarInscripcionSemanal(){
        int filasAfectadas = 0;
        Connection conexion = null;
        try{
            if(MySQLConnection.conectarBD()){
                conexion = MySQLConnection.getConexion();
                conexion.setAutoCommit(false);
                int idPago = Gym.registrarPago("Inscripcion semanal", cmbxFormaPago, costoInscripcionSemanal);
                String insertClienteSemanalQuery = "INSERT INTO ClienteSemanal (ID_Sem, Nom_Sem, ApPat_Sem, ApMat_Sem, Est_Sem, ID_Pago) " + 
                        "VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statementClienteSemanal = conexion.prepareStatement(insertClienteSemanalQuery);
                int sigID = Gym.obtenerUltimoID("ID_Sem", "ClienteSemanal", "4");
                LocalDate fechaActual = LocalDate.now();
                int year = fechaActual.getYear() % 100;
                String idClienteSemanal = "S" + year;
                if(sigID < 10){
                    idClienteSemanal += "0" + sigID;
                } else{
                    idClienteSemanal += sigID;
                }
                //System.out.println(idClienteSemanal + " " + year + " " + sigID);
                statementClienteSemanal.setString(1, idClienteSemanal);
                statementClienteSemanal.setString(2, txtNomCliente.getText());
                statementClienteSemanal.setString(3, txtApPatCliente.getText());
                statementClienteSemanal.setString(4, txtApMatCliente.getText());
                statementClienteSemanal.setString(5, "Activo");
                statementClienteSemanal.setInt(6, idPago);
                statementClienteSemanal.executeUpdate();
                
                conexion.commit();
                conexion.setAutoCommit(true);
                filasAfectadas = 1;
            }
        }catch (SQLException e) {
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
    
    private int ejecutarInscripcionVisita(){
        int filasAfectadas = 0;
        Connection conexion = null;
        try{
            if(MySQLConnection.conectarBD()){
                conexion = MySQLConnection.getConexion();
                conexion.setAutoCommit(false);
                int idPago = Gym.registrarPago("Inscripcion visita", cmbxFormaPago, costoVisita);
                String insertVisitaQuery = "INSERT INTO Visitas (ID_Vis, Nom_Vis, ApPat_Vis, ApMat_Vis, ID_Pago) " + 
                        "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statementVisita = conexion.prepareStatement(insertVisitaQuery);
                int sigID = Gym.obtenerUltimoID("ID_Vis", "Visitas", "4");
                LocalDate fechaActual = LocalDate.now();
                int year = fechaActual.getYear() % 100;
                String idVisita = "V" + year;
                if(sigID < 10){
                    idVisita += "0" + sigID;
                } else{
                    idVisita += sigID;
                }
                statementVisita.setString(1, idVisita);
                statementVisita.setString(2, txtNomCliente.getText());
                statementVisita.setString(3, txtApPatCliente.getText());
                statementVisita.setString(4, txtApMatCliente.getText());
                statementVisita.setInt(5, idPago);
                statementVisita.executeUpdate();
                
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
    
    private void btnRealizarInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarInsActionPerformed
        new Thread(){
            @Override
            public void run(){
                labelCarga.setVisible(true);
                int resultado = 0;
                switch(cmbxTipoCliente.getSelectedIndex()){
                    case 0 ->{
                        resultado = ejecutarInscripcionMensual();
                        if (resultado > 0) {
                            JOptionPane.showMessageDialog(null, 
                                "Inscripción Mensual realizada correctamente", 
                                "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, 
                                "Error al hacer la inscripcion mensual", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 1 ->{
                        resultado = ejecutarInscripcionSemanal();
                        if(resultado > 0){
                            JOptionPane.showMessageDialog(null, "Inscripcion semanal realizada correctamente", 
                                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(null, "Error al hacer la inscripcion semanal",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 2 ->{
                        resultado = ejecutarInscripcionVisita();
                        if(resultado > 0){
                            JOptionPane.showMessageDialog(null, "Inscripcion de visita realizada correctamente", 
                                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(null, "Error al hacer la inscripcion de visita", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                

                MySQLConnection.cerrarConexion();
                labelCarga.setVisible(false);
                
            }
        }.start();
        
    }//GEN-LAST:event_btnRealizarInsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRealizarIns;
    private javax.swing.JComboBox<String> cmbxFormaPago;
    private javax.swing.JComboBox<String> cmbxTipoCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel labelCampoReq;
    private javax.swing.JLabel labelCampoReq1;
    private javax.swing.JLabel labelCampoReq2;
    private javax.swing.JLabel labelCarga;
    private javax.swing.JTextField txtApMatCliente;
    private javax.swing.JTextField txtApPatCliente;
    private javax.swing.JTextField txtNomCliente;
    // End of variables declaration//GEN-END:variables
}
