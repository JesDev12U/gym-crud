/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gym;

/**
 *
 * @author jesus
 */
public class Gym {

    public static void iniciarForm(javax.swing.JLabel lbl){
        lbl.setText("Campo obligatorio");
    }
    
    public static void validarAlfabeto(java.awt.event.KeyEvent evento){
        //Validacion A-Z a-z
        if(!(evento.getKeyChar() >= 65 && evento.getKeyChar() <= 90
                ||evento.getKeyChar() >= 97 && evento.getKeyChar() <= 122)){
            evento.consume();
        }
    }
    
    public static void validarCamposVacios(javax.swing.JTextField arrayTxt[], javax.swing.JLabel lbl){
        for(javax.swing.JTextField txt : arrayTxt){
            if(txt.getText().isEmpty()){
                lbl.setText("Campo obligatorio");
            } else{
                lbl.setText("");
            }
        }
    }
    
    public static void habilitarBoton(javax.swing.JTextField arrayTxt[], javax.swing.JButton btn){
        for(javax.swing.JTextField txt : arrayTxt){
            if(txt.getText().equals("")){
                btn.setEnabled(false);
            } else{
                btn.setEnabled(true);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
