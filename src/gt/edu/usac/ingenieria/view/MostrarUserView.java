package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class MostrarUserView extends JFrame{
    private JPanel mainPanel;
    private JTable usersTable;
    private JButton regresarButton;
    DefaultTableModel model = new DefaultTableModel(
            null,
            new String[]{"No.", "ID.", "Nombre", "Apellido", "User", "Rol", "Password"}
    );

    public MostrarUserView() {
        // Titulo de la ventana
        super("Ver Usuario");
        // Modelo de la tabla
        usersTable.setModel(model);
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void agregarUsuarioTabla(Object[] usuario) {
        model.addRow(usuario);
    }

    public void addRegresarListener(ActionListener listener) {
        regresarButton.addActionListener(listener);
    }
}
