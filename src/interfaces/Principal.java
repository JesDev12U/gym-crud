/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;

/**
 *
 * @author jesus
 */
public class Principal extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm, fechaFormateada;
    SimpleDateFormat formato;
    Calendar calendario;
    Date fechaActual;
    Thread t1;
    Inscripcion inscripcionForm;
    Cobrar cobrarMensualidad;
    Inventario inventario;
    VentasGenerar ventasGenerar;
    InventarioDesHab inventarioDesHab;
    ReporteVentas reporteVentas;
    InscripcionKick inscripcionKick;
    CobrarKick cobrarKick;
    /**
     * Creates new form Principal
     */
    public Principal() {
        formato = new SimpleDateFormat("dd/MM/yyyy");
        initComponents();
        t1 = new Thread(this);
        t1.start();
        setLocationRelativeTo(null);
        setTitle("Gym");
        setVisible(true);
        setResizable(false);
        labelBienvenida.setVisible(false);
        btnReintentarConexion.setVisible(false);
        desHabMenus(false);
        hiloConexion();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void desHabMenus(boolean desHab){
        btnMenuInscripcion.setEnabled(desHab);
        btnMenuCobros.setEnabled(desHab);
        btnMenuInventario.setEnabled(desHab);
        btnMenuVenta.setEnabled(desHab);
        btnMenuKick.setEnabled(desHab);
        btnMenuBajas.setEnabled(desHab);
    }
    
    private void hiloConexion(){
        new Thread(){
            @Override
            public void run(){
                comprobarConexion();
            }
        }.start();
    }
    
    private void comprobarConexion(){
        labelEstadoConexion.setText("Realizando conexión a la base de datos, por favor espere...");
        labelCargando.setVisible(true);
        if(gym.MySQLConnection.conectarBD()){
            labelEstadoConexion.setText("Conexión éxitosa a la base de datos");
            labelCargando.setVisible(false);
            labelBienvenida.setVisible(true);
            btnReintentarConexion.setVisible(false);
            desHabMenus(true);
        } else{
            labelBienvenida.setVisible(false);
            labelCargando.setVisible(false);
            labelEstadoConexion.setText("Fallo en la conexión");
            btnReintentarConexion.setVisible(true);
            desHabMenus(false);
        }
    }
    
    private void calcula(){
        fechaActual = new Date();
        calendario = new GregorianCalendar();
        calendario.setTime(fechaActual);
        //Operador ternario
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        fechaFormateada = formato.format(fechaActual);
        if(ampm.equals("PM")){
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            //Para agregar el cero a la izquierda si es que es la hora de una cifra
            //Ej. 09:00:00 AM
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY)  > 9 ? "" + 
                    calendario.get(Calendar.HOUR_OF_DAY) : "0" + 
                    calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + 
                calendario.get(Calendar.MINUTE) : "0" + 
                calendario.get(Calendar.MINUTE);
        
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + 
                calendario.get(Calendar.SECOND) : "0" + 
                calendario.get(Calendar.SECOND);
    }
    
    @Override
    public void run(){
        Thread ct = Thread.currentThread();
        while(ct == t1){
            calcula();
            labelFechaHora.setText(fechaFormateada + " | " + hora + ":" + 
                    minutos + ":" + segundos + " " + ampm);
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                
            }
        }
    }
    
    public void centrarVentanaInterna(JInternalFrame internalFrame){
        int x = (desktopPane.getWidth() / 2) - internalFrame.getWidth() / 2;
        int y = (desktopPane.getWidth() / 2) - internalFrame.getWidth() / 2;
        
        if(internalFrame.isShowing()){
            internalFrame.setLocation(x, y);
        } else {
            desktopPane.add(internalFrame);
            internalFrame.setLocation(x, y);
            internalFrame.show();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelFechaHora = new javax.swing.JLabel();
        desktopPane = new javax.swing.JDesktopPane();
        labelEstadoConexion = new javax.swing.JLabel();
        labelCargando = new javax.swing.JLabel();
        labelBienvenida = new javax.swing.JLabel();
        btnReintentarConexion = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnMenuInscripcion = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        btnMenuCobros = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        btnMenuInventario = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        btnMenuVenta = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        btnMenuKick = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        btnMenuBajas = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelFechaHora.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        desktopPane.setPreferredSize(new java.awt.Dimension(920, 380));

        labelEstadoConexion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        labelEstadoConexion.setText("Realizando conexión a la base de datos, por favor espere...");

        labelCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cargando.gif"))); // NOI18N

        labelBienvenida.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        labelBienvenida.setText("Seleccione una opción del menú de la parte superior de la pantalla");

        btnReintentarConexion.setText("Reintentar conexión");
        btnReintentarConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReintentarConexionActionPerformed(evt);
            }
        });

        desktopPane.setLayer(labelEstadoConexion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(labelCargando, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(labelBienvenida, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktopPane.setLayer(btnReintentarConexion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(labelEstadoConexion)
                        .addGap(33, 33, 33)
                        .addComponent(labelCargando))
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(labelBienvenida))
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(btnReintentarConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(labelEstadoConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(labelCargando)))
                .addGap(54, 54, 54)
                .addComponent(labelBienvenida)
                .addGap(46, 46, 46)
                .addComponent(btnReintentarConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        btnMenuInscripcion.setText("Inscripción");

        jMenuItem8.setText("Inscripción");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        btnMenuInscripcion.add(jMenuItem8);

        jMenuBar1.add(btnMenuInscripcion);

        btnMenuCobros.setText("Cobros");

        jMenuItem1.setText("Cobros");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        btnMenuCobros.add(jMenuItem1);

        jMenuBar1.add(btnMenuCobros);

        btnMenuInventario.setText("Inventario");

        jMenuItem9.setText("Inventario");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        btnMenuInventario.add(jMenuItem9);

        jMenuItem2.setText("Inhabilitados");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        btnMenuInventario.add(jMenuItem2);

        jMenuBar1.add(btnMenuInventario);

        btnMenuVenta.setText("Venta");

        jMenuItem4.setText("Generar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        btnMenuVenta.add(jMenuItem4);

        jMenuItem5.setText("Reporte");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        btnMenuVenta.add(jMenuItem5);

        jMenuBar1.add(btnMenuVenta);

        btnMenuKick.setText("Kick boxing");

        jMenuItem6.setText("Inscripción");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        btnMenuKick.add(jMenuItem6);

        jMenuItem7.setText("Cobro");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        btnMenuKick.add(jMenuItem7);

        jMenuBar1.add(btnMenuKick);

        btnMenuBajas.setText("Dar de baja");
        jMenuBar1.add(btnMenuBajas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(!(cobrarMensualidad instanceof Cobrar)){
            cobrarMensualidad = new Cobrar();
        }
        centrarVentanaInterna(cobrarMensualidad);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if(!(inventario instanceof Inventario)){
            inventario = new Inventario();
        }
        centrarVentanaInterna(inventario);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if(!(ventasGenerar instanceof VentasGenerar)){
            ventasGenerar = new VentasGenerar();
        }
        centrarVentanaInterna(ventasGenerar);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnReintentarConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReintentarConexionActionPerformed
        hiloConexion();
    }//GEN-LAST:event_btnReintentarConexionActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if(!(inscripcionForm instanceof Inscripcion)){
            inscripcionForm = new Inscripcion();
        }
        centrarVentanaInterna(inscripcionForm);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(!(inventarioDesHab instanceof InventarioDesHab)){
            inventarioDesHab = new InventarioDesHab();
        }
        centrarVentanaInterna(inventarioDesHab);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if(!(reporteVentas instanceof ReporteVentas)){
            reporteVentas = new ReporteVentas();
        }
        centrarVentanaInterna(reporteVentas);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if(!(inscripcionKick instanceof InscripcionKick)){
            inscripcionKick = new InscripcionKick();
        }
        centrarVentanaInterna(inscripcionKick);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if(!(cobrarKick instanceof CobrarKick)){
            cobrarKick = new CobrarKick();
        }
        centrarVentanaInterna(new CobrarKick());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new GraphiteLookAndFeel());
                    new Principal().setVisible(true);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu btnMenuBajas;
    private javax.swing.JMenu btnMenuCobros;
    private javax.swing.JMenu btnMenuInscripcion;
    private javax.swing.JMenu btnMenuInventario;
    private javax.swing.JMenu btnMenuKick;
    private javax.swing.JMenu btnMenuVenta;
    private javax.swing.JButton btnReintentarConexion;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JLabel labelBienvenida;
    private javax.swing.JLabel labelCargando;
    private javax.swing.JLabel labelEstadoConexion;
    private javax.swing.JLabel labelFechaHora;
    // End of variables declaration//GEN-END:variables
}
