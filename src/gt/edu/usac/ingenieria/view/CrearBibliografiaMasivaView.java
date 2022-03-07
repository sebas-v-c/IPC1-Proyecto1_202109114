package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CrearBibliografiaMasivaView extends JFrame{
    private JTextArea textArea;
    private JButton cargarButton;
    private JButton cancelarButton;
    private JPanel mainPanel;

    public CrearBibliografiaMasivaView() {
        // Titulo de la ventana
        super("Log In");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void addCargarButtonListener(ActionListener listener) {
        cargarButton.addActionListener(listener);
    }

    public void addCancelarButtonListener(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    public void setAreaText(String text){
        this.textArea.setText(text);
    }

    public String[] getAreaText() {
        return textArea.getText().split("\\r?\\n");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }

}
