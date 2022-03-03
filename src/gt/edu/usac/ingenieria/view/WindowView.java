package gt.edu.usac.ingenieria.view;

import javax.swing.*;

public class WindowView extends JFrame {

    public WindowView(String titulo) {
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
}
