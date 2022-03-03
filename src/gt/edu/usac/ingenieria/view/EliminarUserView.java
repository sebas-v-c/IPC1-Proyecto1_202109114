package gt.edu.usac.ingenieria.view;

import javax.swing.*;

public class EliminarUserView extends JFrame{
    private JPanel mainPanel;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField userTextField;
    private JTextField rolTextField;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton cancelarButton;
    private JComboBox comboBox1;

    public EliminarUserView() {
        // Titulo de la ventana
        super("Eliminar Usuarios");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Poner como no editables todos los textfields
        nombreTextField.setEditable(false);
        apellidoTextField.setEditable(false);
        userTextField.setEditable(false);
        rolTextField.setEditable(false);
        nombreTextField.setEditable(false);

        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

}
