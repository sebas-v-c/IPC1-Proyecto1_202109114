package gt.edu.usac.ingenieria.view.users;

import gt.edu.usac.ingenieria.controller.users.BibliotecaController;
import gt.edu.usac.ingenieria.controller.users.PrestamoLibrosController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrestamoLibrosView extends JFrame{
    private JPanel mainPanel;
    private JTable tablaLibros;
    private JLabel usuarioJLabel;
    private JButton verPrestamosButton;
    private JButton reporteExistenciasButton;
    private JButton logoutButton;
    private JButton regresarButton;
    private JComboBox<String> tipoComboBox;
    private JButton filtrarButton;
    private JTextField buscarTextField;
    private JComboBox<String> campoComboBox;
    private JButton buscarButton;
    private DefaultTableModel modelLibro = new DefaultTableModel(
            null,
            new String[]{"ID", "Autor", "Año", "Título", "ISBN","Edición", "Palabras Clave", "Descripción", "Temas",
                    "Disponibles","Acción"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };

    private DefaultTableModel modelRevista = new DefaultTableModel(
            null,
            new String[]{"ID", "Autor", "Año", "Título", "Edición", "Palabras Clave", "Categoría", "Descripción",
                    "Temas", "Ejemplares", "Disponibles","Acción"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };

    private DefaultTableModel modelTesis = new DefaultTableModel(
            null,
            new String[]{"ID", "Autor", "Año", "Título", "Edición", "Palabras Clave", "Descripción", "Temas",
                    "Área", "Disponibles","Acción"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    };

    private PrestamoLibrosController controller;


    public PrestamoLibrosView() {
        // Titulo de la ventana
        super("Prestamo De Libros");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        tablaLibros.setModel(modelLibro);
        tablaLibros.getTableHeader().setReorderingAllowed(false);
        tablaLibros.getColumn("Acción").setCellRenderer(new PrestamoLibrosView.ButtonRenderer());
        tablaLibros.getColumn("Acción").setCellEditor(new PrestamoLibrosView.ButtonEditor(new JCheckBox()));
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public String[] getValoresColumnas(int indiceColumna, boolean esNumerico) {
        String[] valoresEncontrados = new String[tablaLibros.getRowCount()];
        if (esNumerico) {
            for (int i = 0; i < tablaLibros.getRowCount(); i++) {
                valoresEncontrados[i] = String.valueOf((int) tablaLibros.getValueAt(i, indiceColumna));
            }
        } else {
            for (int i = 0; i < tablaLibros.getRowCount(); i++) {
                valoresEncontrados[i] = (String) tablaLibros.getValueAt(i, indiceColumna);
            }
        }
        return valoresEncontrados;
    }

    public int getCampoComboBox() {
        return campoComboBox.getSelectedIndex();
    }

    public int getTipoComboBox() {
        return tipoComboBox.getSelectedIndex();
    }

    public void setTipoComboBox(String[] modelos) {
        for (String modelo : modelos) {
            tipoComboBox.addItem(modelo);
        }
    }

    public void addVerReporteExistencias(ActionListener listener) {
        reporteExistenciasButton.addActionListener(listener);
    }

    public void addVerPrestamosListener(ActionListener listener) {
        verPrestamosButton.addActionListener(listener);
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

    public void setCampoComboBox(String modelo) {
        campoComboBox.removeAllItems();

        if (modelo.equals("libro")) {
            String[] libro = new String[]{"ID", "Autor", "Año", "Título", "ISBN", "Edición", "Palabras Clave", "Descripción", "Temas",
                    "Disponibles"};
            for (String tipo : libro) {
                campoComboBox.addItem(tipo);
            }
        } else if (modelo.equals("revista")) {
            String[] revista = new String[]{"ID", "Autor", "Año", "Título", "Edición", "Palabras Clave", "Categoría", "Descripción",
                    "Temas", "Ejemplares", "Disponibles"};
            for (String tipo : revista) {
                campoComboBox.addItem(tipo);
            }
        } else {
            String[] tesis = new String[]{"ID", "Autor", "Año", "Título", "Edición", "Palabras Clave", "Descripción", "Temas",
                    "Área", "Disponibles"};
            for (String tipo : tesis) {
                campoComboBox.addItem(tipo);
            }
        }
    }

    public String getRolComboBox() {
        return (String) campoComboBox.getSelectedItem();
    }

    public void cambiarModelo(String modelo) {
        switch (modelo) {
            case "libro" -> {
                tablaLibros.setModel(modelLibro);
                tablaLibros.getColumn("Acción").setCellRenderer(new PrestamoLibrosView.ButtonRenderer());
                tablaLibros.getColumn("Acción").setCellEditor(new PrestamoLibrosView.ButtonEditor(new JCheckBox()));
            }
            case "revista" -> {
                tablaLibros.setModel(modelRevista);
                tablaLibros.getColumn("Acción").setCellRenderer(new PrestamoLibrosView.ButtonRenderer());
                tablaLibros.getColumn("Acción").setCellEditor(new PrestamoLibrosView.ButtonEditor(new JCheckBox()));
            }
            case "tesis" -> {
                tablaLibros.setModel(modelTesis);
                tablaLibros.getColumn("Acción").setCellRenderer(new PrestamoLibrosView.ButtonRenderer());
                tablaLibros.getColumn("Acción").setCellEditor(new PrestamoLibrosView.ButtonEditor(new JCheckBox()));
            }
        }
    }

    public void agregarFila(Object[] usuario, String modelo) {
        Object[] nuevoUsuario = new Object[0];
        for (Object dato : usuario) {
            if (dato != null) {
                nuevoUsuario = agregarAArreglo(dato, nuevoUsuario);
            }
        }
        switch (modelo) {
            case "libro" -> modelLibro.addRow(nuevoUsuario);
            case "revista" -> modelRevista.addRow(nuevoUsuario);
            case "tesis" -> modelTesis.addRow(nuevoUsuario);
        }
    }
    public Object[] agregarAArreglo(Object objeto, Object[] objetos) {
        int n = objetos.length;
        Object[] newObjetos = new Object[ n + 1];
        // Copiar la array antigua en la nueva
        System.arraycopy(objetos, 0, newObjetos, 0, n);
        newObjetos[n] = objeto;
        return newObjetos;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }

    public void setController(PrestamoLibrosController controller) {
        this.controller = controller;
    }

    public void deleteAllTable(String modelo) {
        switch (modelo) {
            case "libro" -> {
                modelLibro.getDataVector().removeAllElements();
                modelLibro.fireTableDataChanged();}
            case "revista" -> {
                modelRevista.getDataVector().removeAllElements();
                modelRevista.fireTableDataChanged();
            }
            case "tesis" -> {
                modelTesis.getDataVector().removeAllElements();
                modelTesis.fireTableDataChanged();
            }
        }
    }

    public int obtenerIdFila(int fila) {
        return (int) tablaLibros.getValueAt(fila, 0);
    }

    public void setBuscarText(String s) {
        buscarTextField.setText("");
    }

    public void setUsuarioText(String nombre) {
        usuarioJLabel.setText(nombre);
    }

    public int getRowCount() {
        return tablaLibros.getRowCount();
    }

    public void addFiltrarListener(ActionListener filtrarListener) {
        filtrarButton.addActionListener(filtrarListener);
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
                controller.setLibroSeleccionado(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0));
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
