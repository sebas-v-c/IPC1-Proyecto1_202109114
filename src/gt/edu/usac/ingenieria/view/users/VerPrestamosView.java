package gt.edu.usac.ingenieria.view.users;

import gt.edu.usac.ingenieria.controller.users.VerPrestamosController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerPrestamosView extends JFrame{
    private JPanel mainPanel;
    private JButton logoutButton;
    private JButton regresarButton;
    private JTable tablaLibros;
    private DefaultTableModel model = new DefaultTableModel(
            null,
            new String[]{"ID", "Tipo", "Titulo","Autor", "Año", "Edición", "Descripción", "Temas",
                    "ISBN", "Área", "Acción"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };

    private VerPrestamosController controller;

    public VerPrestamosView() {
        // Titulo de la ventana
        super("Prestamo De Libros");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        tablaLibros.setModel(model);
        tablaLibros.getTableHeader().setReorderingAllowed(false);
        tablaLibros.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        tablaLibros.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox()));
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public int getRowCount() {
        return tablaLibros.getRowCount();
    }

    public void agregarEntrada(Object[] prestamo) {
        model.addRow(prestamo);
    }


    public int getIdDeLibroSeleccionado() {
        int fila = tablaLibros.getSelectedRow();
        return (int) tablaLibros.getValueAt(fila, 0);
    }

    public int getSelectedRowIndex() {
        return tablaLibros.getSelectedRow();
    }

    public void eliminarFila(int row) {
        model.removeRow(row);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addRegresarListener(ActionListener listener) {
        regresarButton.addActionListener(listener);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }

    public boolean confirmarAccion(String mensaje, String titulo, String[] opciones) {
        int reply = JOptionPane.showOptionDialog(mainPanel, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
        return reply == 0;
    }

    // Codigo para renderisar botones dentro de la tabla
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                controller.quitarLibroSeleccionado(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0));
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    public void setController(VerPrestamosController controller) {
        this.controller = controller;
    }
}
