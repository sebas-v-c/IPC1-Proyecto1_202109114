package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class MostrarBibliografiasView extends JFrame{
    private JPanel mainPanel;
    private JTable tablaBibliografias;
    private JButton regresarButton;
    DefaultTableModel model = new DefaultTableModel(
            null,
            new String[]{"ID", "Tipo", "Titulo","Autor", "Año", "Edición", "Descripción", "Palabras Clave", "Temas",
                    "ISBN", "Área", "Categoría", "Ejemplares","Copias", "Disponibles", "Tamaño"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public MostrarBibliografiasView() {
        // Titulo de la ventana
        super("Ver Usuario");
        // Modelo de la tabla
        tablaBibliografias.setModel(model);
        tablaBibliografias.getTableHeader().setReorderingAllowed(false);
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void agregarBibliografia(Object[] usuario) {
        model.addRow(usuario);
    }


    public void addRegresarListener(ActionListener listener) {
        regresarButton.addActionListener(listener);
    }
}
