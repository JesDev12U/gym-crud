/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import javax.swing.JInternalFrame;

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
    CobrarMensualidad cobrarMensualidad;
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
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
        jMenuBar1 = new javax.swing.JMenuBar();
        btnMenuInscripcion = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
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

        jMenu2.setText("Cobros");

        jMenuItem1.setText("Mensualidad");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Semana");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Visita");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Inventario");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Venta");

        jMenuItem4.setText("Generar");
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Reporte");
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Kick boxing");

        jMenuItem6.setText("Inscripción");
        jMenu5.add(jMenuItem6);

        jMenuItem7.setText("Cobro");
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(549, Short.MAX_VALUE))
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if(!(inscripcionForm instanceof Inscripcion)){
            inscripcionForm = new Inscripcion();
        }
        centrarVentanaInterna(inscripcionForm);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(!(cobrarMensualidad instanceof CobrarMensualidad)){
            cobrarMensualidad = new CobrarMensualidad();
        }
        centrarVentanaInterna(cobrarMensualidad);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu btnMenuInscripcion;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JLabel labelFechaHora;
    // End of variables declaration//GEN-END:variables
}
