package gt.edu.usac.ingenieria.view.users;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UserView extends JFrame{
    private JPanel mainPanel;
    private JButton bibliotecaVirtualButton;
    private JButton prestamoDeLibroButton;
    private JButton logoutButton;
    private JLabel userInfoLabel;
    private JTextArea infoUsuario;

    public UserView() {
        // Titulo de la ventana
        super("Usuario");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
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

    public void setUserInfoLabel(String info) {
        userInfoLabel.setText(info);
    }
}
