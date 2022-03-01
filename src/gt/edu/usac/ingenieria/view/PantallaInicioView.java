package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PantallaInicioView extends JFrame{
    private JButton aboutButton;
    private JButton loginButton;
    private JPanel mainPanel;

    public PantallaInicioView(String titulo) {
        // Titulo de la ventana
        super(titulo);
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
    }

    public void addLoginListener(ActionListener listenForLoginButton) {
        loginButton.addActionListener(listenForLoginButton);
    }

    public void addAboutListener(ActionListener listenForAboutButton) {
        aboutButton.addActionListener(listenForAboutButton);
    }
}
