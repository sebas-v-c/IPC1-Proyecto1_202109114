package gt.edu.usac.ingenieria.view.admin;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame{
    private JButton crearUserButton;
    private JButton mostrarUserButton;
    private JButton modificarUserButton;
    private JButton crearLibroButton;
    private JButton modificarLibroButton;
    private JButton mostrarLibroButton;
    private JButton reporteUsuariosButton;
    private JButton reporteLibrosButton;
    private JPanel mainPanel;
    private JButton salirButton;

    public AdminView() {
        // Titulo de la ventana
        super("Administrador");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }


    public void addCrearUserListener(ActionListener listener) {
        crearUserButton.addActionListener(listener);
    }

    public void addSalirListener(ActionListener listener){ salirButton.addActionListener(listener); }

    public void addMostrarUserListener(ActionListener listener) {
        mostrarUserButton.addActionListener(listener);
    }

    public void addModificarUserListener(ActionListener listener) {
        modificarUserButton.addActionListener(listener);
    }

    public void addCrearLibroListener(ActionListener listener) {
        crearLibroButton.addActionListener(listener);
    }

    public void addMostrarLibroListener(ActionListener listener) {
        mostrarLibroButton.addActionListener(listener);
    }

    public void addModificarLibroListener(ActionListener listener) {
        modificarLibroButton.addActionListener(listener);
    }

    public void addReporteUsersListener(ActionListener listener) {
        reporteUsuariosButton.addActionListener(listener);
    }

    public void addReporteLibrosListener(ActionListener listener) {
        reporteLibrosButton.addActionListener(listener);
    }

    // Verifica si se selecciono la primera opcion, o sea un 0. Hay que poner la primera opcion positiva!.
    public int confirmarAccion(String mensaje, String titulo, String[] opciones) {
        return JOptionPane.showOptionDialog(mainPanel, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
    }

    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(mainPanel, s);
    }
}
