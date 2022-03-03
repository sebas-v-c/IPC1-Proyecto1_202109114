package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CrearUserView extends JFrame{
    private JLabel id;
    private JPanel mainPanel;
    private JTextField idTextField;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField userTextField;
    private JComboBox<String> rolComboBox;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton crearButton;
    private JButton cancelarButton;

    public CrearUserView(String titulo) {
        // Titulo de la ventana
        super(titulo);
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
    }

    public String getIdTextField() {
        return idTextField.getText();
    }

    public String getNombreTextField() {
        return nombreTextField.getText();
    }

    public String getApellidoTextField() {
        return apellidoTextField.getText();
    }

    public String getUserTextField() {
        return userTextField.getText();
    }

    public void setRolComboBox(String[] roles) {
        for (String rol : roles) {
            rolComboBox.addItem(rol);
        }
    }

    // TODO devolver de la lista una cadena
    public JComboBox getRolComboBox() {
        return rolComboBox;
    }


    public String getPasswordField1() {
        return String.valueOf(passwordField1.getPassword());
    }

    public String getPasswordField2() {
        return String.valueOf(passwordField2.getPassword());
    }

    public void addCrearListener(ActionListener listener) {
        crearButton.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }
}
