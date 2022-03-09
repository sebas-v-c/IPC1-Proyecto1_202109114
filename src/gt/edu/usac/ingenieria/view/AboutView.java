package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AboutView extends JFrame{
    private JButton regresarButton;
    private JPanel mainPanel;

    public AboutView() {
        // Titulo de la ventana
        super("About");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void addRegresarListener(ActionListener listener) {
        regresarButton.addActionListener(listener);
    }
}
