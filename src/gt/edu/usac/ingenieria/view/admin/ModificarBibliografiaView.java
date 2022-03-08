package gt.edu.usac.ingenieria.view.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class ModificarBibliografiaView extends JFrame{
    private JPanel mainPanel;
    private JTable tabla;
    private JTextField buscarTextField;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JButton salirButton;
    private DefaultTableModel model = new DefaultTableModel(
            null,
            new String[]{"ID", "Tipo", "Titulo","Autor", "Año", "Edición", "Descripción", "Palabras Clave", "Temas",
                    "ISBN", "Área", "Categoría", "Ejemplares","Copias", "Disponibles", "Tamaño"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ModificarBibliografiaView() {
        // Titulo de la ventana
        super("Eliminar Usuarios");
        // Que hacer cuando se cierre la ventanausuario
        tabla.setModel(model);
        tabla.getTableHeader().setReorderingAllowed(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setEliminarButtonVisible(true);
        setModificarButtonVisible(true);
        tabla.setCellSelectionEnabled(false);
        tabla.setRowSelectionAllowed(true);
        // ComboBox de busqueda de usuarios

        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void setBuscarText(String text) {
        buscarTextField.setText(text);
    }

    public int getRowCount() {
        return tabla.getRowCount();
    }

    public String getBuscarTextField() {
        String id = buscarTextField.getText();
        setBuscarText("");
        return id;
    }

    public void addBuscarListener(ActionListener listener) {
        buscarButton.addActionListener(listener);
    }

    public void addModificarListener(ActionListener listener) {
        modificarButton.addActionListener(listener);
    }

    public void addEliminarListener(ActionListener listener) {
        eliminarButton.addActionListener(listener);
    }

    public void addSalirListener(ActionListener listener) {
        salirButton.addActionListener(listener);
    }

    public void setEliminarButtonVisible(boolean visible) {
        eliminarButton.setVisible(visible);
    }
    public void setModificarButtonVisible(boolean visible) {
        modificarButton.setVisible(visible);
    }

    public void agregarBibliografia(Object[] bibliografia) {
        model.addRow(bibliografia);
    }

    public int getIdDeLibroSeleccionado() {
        int fila = tabla.getSelectedRow();
        return (int) tabla.getValueAt(fila, 0);
    }

    public boolean confirmarAccion(String mensaje, String titulo, String[] opciones) {
        int reply = JOptionPane.showOptionDialog(mainPanel, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
        return reply == 0;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }


    public void eliminarBibliografia() {
        model.removeRow(tabla.getSelectedRow());
    }


    public String getTipoFilaSeleccionada() {
        int fila = tabla.getSelectedRow();
        return (String) tabla.getValueAt(fila, 1);
    }

    public int getIdDeLaFila(int row) {
        int id = (int) tabla.getValueAt(row, 0);
        return id;
    }

    public void highLight(int row) {
        tabla.setRowSelectionInterval(row,row);
    }


}
