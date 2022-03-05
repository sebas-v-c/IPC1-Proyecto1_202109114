package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ModificarUsersView extends JFrame{
    private JPanel mainPanel;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField userTextField;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton salirButton;
    private JComboBox<String> idComboBox;
    private JButton guardarButton;
    private JButton modificarButton;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JComboBox<String> rolComboBox;

    public ModificarUsersView() {
        // Titulo de la ventana
        super("Eliminar Usuarios");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setParametrosEditables(false);
        setGuardarButtonVisible(false);
        setEliminarButtonVisible(false);
        setModificarButtonVisible(false);
        // ComboBox de busqueda de usuarios
        idComboBox.setEditable(true);

        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void setEliminarButtonVisible(boolean visible) {
        eliminarButton.setVisible(visible);
    }

    public void setModificarButtonVisible(boolean visible) {
        modificarButton.setVisible(visible);
    }

    public void setParametrosEditables(boolean editable) {
        // Poner como no editables todos los textfields
        nombreTextField.setEditable(editable);
        apellidoTextField.setEditable(editable);
        userTextField.setEditable(editable);
        nombreTextField.setEditable(editable);
        passwordField1.setEditable(editable);
        passwordField2.setEditable(editable);
    }

    public void setGuardarButtonVisible(boolean visible) {
        guardarButton.setVisible(visible);
    }

    public void addGuardarListener(ActionListener listener) {
        guardarButton.addActionListener(listener);
    }

    public String getSelectedIdComboBox() {
        return (String) idComboBox.getSelectedItem();
    }


    public String getSelectedRolComboBox() {
        return (String) rolComboBox.getSelectedItem();
    }

    public void setContentsRolComboBox(String[] roles) {
        for (String rol : roles) {
            rolComboBox.addItem(rol);
        }
    }

    public JComboBox<String> getIdComboBox(){
        return idComboBox;
    }

    public void setContentsIdComboBox(String[] roles) {
        for (String rol : roles) {
            idComboBox.addItem(rol);
        }
    }

    public void addBuscarListener(ActionListener listener) {
        buscarButton.addActionListener(listener);
    }

    public void addModificarListener(ActionListener listener) {
        modificarButton.addActionListener(listener);
    }

    public void addEliminarListener(ActionListener listener) {
        eliminarButton.addActionListener(listener);
    }


    public void addSalirListener(ActionListener listener) {
        salirButton.addActionListener(listener);
    }

    public void setUserContents(String nombre, String apellido, String usuario, String rol, String password) {
        nombreTextField.setText(nombre);
        apellidoTextField.setText(apellido);
        userTextField.setText(usuario);
        nombreTextField.setText(nombre);
        nombreTextField.setText(nombre);
        passwordField1.setText(password);
        passwordField2.setText(password);
        setContentsRolComboBox(new String[] {rol});
    }

    public String getNombreTextField() {
        return nombreTextField.getText();
    }

    public String getUserTextField() {
        return userTextField.getText();
    }

    public String getApellidoTextField() {
        return apellidoTextField.getText();
    }

    public String getPasswordField1() {
        return String.valueOf(passwordField1.getPassword());
    }

    public String getPasswordField2() {
        return String.valueOf(passwordField2.getPassword());
    }

    // Ventana con un pequeño mensaje de error
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }

    public void removeAllRolComboBox() {
        rolComboBox.removeAllItems();
    }

    public void removeAllIdComboBox() {
        idComboBox.removeAllItems();
    }

    // Verifica si se selecciono la primera opcion, o sea un 0. Hay que poner la primera opcion positiva!.
    public boolean confirmarAccion(String mensaje, String titulo, String[] opciones) {
        int reply = JOptionPane.showOptionDialog(mainPanel, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
        return reply == 0;
    }
}
