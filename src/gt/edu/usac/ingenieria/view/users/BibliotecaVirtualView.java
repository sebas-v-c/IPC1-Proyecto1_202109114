package gt.edu.usac.ingenieria.view.users;

import gt.edu.usac.ingenieria.controller.users.BibliotecaController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaVirtualView extends JFrame {
    private JPanel mainPanel;
    private JTable tablaDigitales;
    private JTextField buscarTextField;
    private JComboBox<String> campoComboBox;
    private JButton buscarButton;
    private JLabel usuarioJLabel;
    private JButton logoutButton;
    private JButton regresarButton;
    private JButton verBibVirtualButton;
    private DefaultTableModel model = new DefaultTableModel(
            null,
            new String[]{"ID", "Autor", "Año", "Título", "Edición", "Palabras Clave", "Descripción", "Temas", "Acción"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };
    private BibliotecaController controller;

    public BibliotecaVirtualView() {
        // Titulo de la ventana
        super("Biblioteca Virtual");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        tablaDigitales.setModel(model);
        tablaDigitales.getTableHeader().setReorderingAllowed(false);
        tablaDigitales.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        tablaDigitales.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox()));
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public String[] getValoresColumnas(int indiceColumna, boolean esNumerico) {
        String[] valoresEncontrados = new String[tablaDigitales.getRowCount()];
        if (esNumerico) {
            for (int i = 0; i < tablaDigitales.getRowCount(); i++) {
                valoresEncontrados[i] = String.valueOf((int) tablaDigitales.getValueAt(i, indiceColumna));
            }
        } else {
            for (int i = 0; i < tablaDigitales.getRowCount(); i++) {
                valoresEncontrados[i] = (String) tablaDigitales.getValueAt(i, indiceColumna);
            }
        }
        return valoresEncontrados;
    }

    public int getCampoComboBox() {
        return campoComboBox.getSelectedIndex();
    }


    public void addVerBibliotecaListener(ActionListener listener) {
        verBibVirtualButton.addActionListener(listener);
    }

    public void addRegresarListener(ActionListener listener) {
        regresarButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void addBuscarListener(ActionListener listener) {
        buscarButton.addActionListener(listener);
    }

    public String getBuscarText() {
        return buscarTextField.getText();
    }

    public void setRolComboBox(String[] campos) {
        for (String campo : campos) {
            campoComboBox.addItem(campo);
        }
    }

    public String getRolComboBox() {
        return (String) campoComboBox.getSelectedItem();
    }

    public void agregarFila(Object[] usuario) {
        model.addRow(usuario);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }

    public void setController(BibliotecaController controller) {
        this.controller = controller;
    }

    public void deleteAllTable() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
    }

    public int obtenerIdFila(int fila) {
        return (int) tablaDigitales.getValueAt(fila, 0);
    }

    public void setBuscarText(String s) {
        buscarTextField.setText("");
    }

    public void setUsuarioText(String nombre) {
        usuarioJLabel.setText(nombre);
    }


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
                controller.setLibroSeleccionado(tablaDigitales.getValueAt(tablaDigitales.getSelectedRow(), 0));
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
}
