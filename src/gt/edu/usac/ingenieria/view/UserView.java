package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame{
    private JPanel mainPanel;
    private JButton bibliotecaVirtualButton;
    private JButton prestamoDeLibroButton;
    private JButton logoutButton;
    private JTextArea infoUsuario;

    public UserView(String titulo) {
        // Titulo de la ventana
        super(titulo);
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
    }
    public void addBilbiotecaListener(ActionListener listener) {
       bibliotecaVirtualButton.addActionListener(listener);
    }

    public void addPrestamoListener(ActionListener listener) {
        prestamoDeLibroButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void setInfoUsuario(String info) {
        infoUsuario.setText(info);
    }
}
