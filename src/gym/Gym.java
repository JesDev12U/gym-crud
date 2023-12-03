/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gym;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.sql.*;

/**
 *
 * @author jesus
 */
public class Gym {
    public TableRowSorter filtroTabla;
    
    public static void inicioFiltroTablaCobros(JTable tablaClientes, JTextField txtFolio, JButton btnRealizarCobro){
        tablaClientes.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent Mouse_evt){
                //JTable table = (JTable) Mouse_evt.getSource();
                //Point point = Mouse_evt.getPoint();
                //int row = table.rowAtPoint(point);
                if(Mouse_evt.getClickCount() == 1){
                    txtFolio.setText(tablaClientes.getValueAt(tablaClientes.getSelectedRow(), 0).toString());
                    btnRealizarCobro.setEnabled(true);
                }
            }
        });
    }
    
    public void filtrarTabla(JTextField txtFiltro){
        int columnaTabla = 1; //Columna del nombre
        filtroTabla.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), columnaTabla));
    }
    
    public void procesoFiltro(JTextField txtFiltro, JTable tablaClientes){
        txtFiltro.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(final KeyEvent e){
                String txt = (txtFiltro.getText());
                txtFiltro.setText(txt);
                filtrarTabla(txtFiltro);
            }
        });
        filtroTabla = new TableRowSorter(tablaClientes.getModel());
        tablaClientes.setRowSorter(filtroTabla);
    }
    
    public static void iniciarForm(javax.swing.JLabel arrayLbl[]) {
        for(javax.swing.JLabel lbl : arrayLbl){
            lbl.setText("Campo obligatorio");
        }
    }

    public static void validarAlfabeto(java.awt.event.KeyEvent evento) {
        //Validacion A-Z a-z
        if (!((evento.getKeyChar() >= 65 && evento.getKeyChar() <= 90)
                || (evento.getKeyChar() >= 97 && evento.getKeyChar() <= 122)
                || (evento.getKeyChar() == 32))) {
            evento.consume();
        }
    }

    public static void validarCamposVacios(javax.swing.JTextField txt, javax.swing.JLabel lbl) {
        if (txt.getText().isEmpty()) {
            lbl.setText("Campo obligatorio");
        } else {
            lbl.setText("");
        }
    }
    
    public static void validarCantidadCaracteres(javax.swing.JTextField txt, java.awt.event.KeyEvent evento
            , int cantidad){
        if(txt.getText().length() >= cantidad){
            evento.consume();
        }
    }
    
    public static int obtenerUltimoID(String id, String tabla) throws SQLException{
        Connection conexion = MySQLConnection.getConexion();
        Statement statementIDCliente = conexion.createStatement();
        ResultSet resultSetIDCliente = statementIDCliente.executeQuery(
        "SELECT MAX(CONVERT(SUBSTRING(" + id + ", 3), UNSIGNED INTEGER)) AS MaxID FROM " + tabla);
        int sigID = 1;
        if(resultSetIDCliente.next()){
            sigID = resultSetIDCliente.getInt("MaxID");
            sigID++;
        }
        return sigID;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
