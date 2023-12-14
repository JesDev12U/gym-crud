/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces.altasbajas;

import javax.swing.JTable;
import gym.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import configtablas.RenderTabla;
import interfaces.inventario.Inventario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class AltasBajas {
    private static String idTable;
    private static String nomTable;
    private static String apPatTable;
    private static String apMatTable;
    private static String estTable;
    private static String table;
    private static JButton btn;
    private static JButton[] botones;
    private static String[] nomCols;
    
    public AltasBajas(String idTable, String nomTable, String apPatTable, String apMatTable, String estTable, String table){
        botonesInicio();
        AltasBajas.idTable = idTable;
        AltasBajas.nomTable = nomTable;
        AltasBajas.apPatTable = apPatTable;
        AltasBajas.apMatTable = apMatTable;
        AltasBajas.estTable = estTable;
        AltasBajas.table = table;
    }
    
    private void botonesInicio(){
        btn = new JButton("Realizar operación");
        btn.setName("btn");
        botones = new JButton[1];
        botones[0] = btn;
        nomCols = new String[1];
        nomCols[0] = "Operación";
    }
    
    public static void mostrarTabla(String where, JTable tabla){
        if(MySQLConnection.conectarBD()){
            String query = "SELECT " + idTable + " AS 'ID', CONCAT(" + nomTable + ", ' ',"
                    + " " + apPatTable + ", ' ', " + apMatTable + ") AS 'Nombre completo', " + estTable + "\n" +
            "FROM " + table + "\n" +
            "WHERE " + estTable + " = '" + where + "'";
            Statement st;
            Connection conexion = MySQLConnection.getConexion();
            DefaultTableModel model;
            tabla.setDefaultRenderer(Object.class, new RenderTabla());
            model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            model.addColumn("ID");
            model.addColumn("Nombre completo");
            if(nomCols.length != 0){
                for(String col : nomCols){
                    model.addColumn(col);
                }
            }
            tabla.setModel(model);
            Object[] datos = new Object[2 + nomCols.length];
            try{
                st = conexion.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    int i = 2;
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    if(botones.length != 0){
                        for(JButton btn : botones){
                            datos[i] = btn;
                            i++;
                        }
                    }
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
    
    public static void inHab(java.awt.event.MouseEvent evt, JTable tabla, JLabel labelCarga, JButton btnRefrescar, String estado){
        int seleccion = tabla.rowAtPoint(evt.getPoint());
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tabla.getRowHeight();
        
        if(row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() 
                && column >= 0){
            Object value = tabla.getValueAt(row, column);
            if(value instanceof JButton){
                new Thread(){
                    @Override
                    public void run(){
                        labelCarga.setVisible(true);
                        ((JButton)value).doClick();
                        JButton boton = (JButton)value;
                        String id = String.valueOf(tabla.getValueAt(seleccion, 0));
                        if(boton.getName().equals("btn")){
                            int filasAfectadas = 0;
                            Connection conexion = null;
                            if(MySQLConnection.conectarBD()){
                                try {
                                    conexion = MySQLConnection.getConexion();
                                    conexion.setAutoCommit(false);
                                    String query = "UPDATE " + table + " SET " + estTable + " = ? WHERE " + idTable + " = ?";
                                    PreparedStatement st = conexion.prepareStatement(query);
                                    st.setString(1, estado);
                                    st.setString(2, id);
                                    st.executeUpdate();
                                    conexion.commit();
                                    conexion.setAutoCommit(true);
                                    filasAfectadas = 1;
                                    
                                } catch (Exception ex) {
                                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if(filasAfectadas > 0){
                                    JOptionPane.showMessageDialog(null, "Operación realizada correctamente", 
                                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            } else{
                                JOptionPane.showMessageDialog(null, "Error al realizar la operación",
                                   "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            btnRefrescar.doClick();
                        }
                        labelCarga.setVisible(false);
                    }
                }.start();
            }
        }
    }
}
