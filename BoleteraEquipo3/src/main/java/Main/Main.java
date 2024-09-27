/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Presentacion.frmLogin;
import javax.swing.JFrame;

/**
 *
 * @author oribi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // Crear un JFrame para contener el JPanel frmLogin
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 600); // Tamaño de la ventana, ajusta según tus necesidades
        
        // Crear una instancia de frmLogin (que es un JPanel)
        frmLogin loginPanel = new frmLogin();
        
        // Agregar el JPanel al JFrame
        frame.add(loginPanel);
        
        // Centrar la ventana y hacerla visible
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
    }
}
