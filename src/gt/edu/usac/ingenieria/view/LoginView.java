package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JTextField usuarioTextField;
    private static JPanel mainPanel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelarButton;

    public LoginView(String titulo) {
        // Titulo de la ventana
        super(titulo);
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
    }
    public String getUsuarioTextField() {
        return usuarioTextField.getText();
    }

    public String getPasswordField() {
        // getPassword devuelve un arreglo del chars. Por eso se tiene que Convertir a una string
        return String.valueOf(passwordField.getPassword());
    }

    // Limpiamos los JtextFiel y el PasswordField para que el usuario intene nuevamente
    public void limpiarCampos() {
        usuarioTextField.setText("");
        passwordField.setText("");
    }

    // Agregamos un listener del boton login
    public void addLoginListener(ActionListener listenForLoginButton) {
        loginButton.addActionListener(listenForLoginButton);
    }

    public void addCancelarListener(ActionListener listenForCerrarButton) {
        cancelarButton.addActionListener(listenForCerrarButton);
    }

    // Ventana con un pequeño mensaje de error
    public void mostrarMensajeError(String mensajeError) {
        JOptionPane.showMessageDialog(mainPanel, mensajeError);
    }
}
