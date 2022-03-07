package gt.edu.usac.ingenieria.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CrearBibliografiaIndiviView extends JFrame {
    private JButton cargarButton;
    private JButton cancelarButton;
    private JComboBox<String> tipoComboBox;
    private JTextField autorTextField;
    private JTextField anioTextField;
    private JTextField tituloTextField;
    private JTextField palabrasCTextField;
    private JTextField descripcionTextField;
    private JTextField isbnTextField;
    private JTextField edicionTextField;
    private JTextField copiasTextField;
    private JTextField areaTextField;
    private JTextField categoriaTextField;
    private JTextField tamanioTextField;
    private JTextField temasTextField;
    private JPanel mainPanel;
    private JButton colocarButton;
    private JTextField ejemplaresTextField;


    public CrearBibliografiaIndiviView() {
        // Titulo de la ventana
        super("Log In");
        // Que hacer cuando se cierre la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void setIsbnText(int isbn) {
        isbnTextField.setText(String.valueOf(isbn));
    }

    public void setEjemplaresText(int ejemplares) {
        ejemplaresTextField.setText(String.valueOf(ejemplares));
    }

    public void setCategoriaText(String categoria) {
        categoriaTextField.setText(categoria);
    }

    public void setAreaText(String area) {
        areaTextField.setText(area);
    }

    public void setTamanioText(int tamanio) {
        tamanioTextField.setText(String.valueOf(tamanio));
    }

    public void setCopiasText() {
        copiasTextField.setText("");
    }

    public void setInfoBasica(String autor, int anio, String titulo, String palabrasClave, String descripcion,
                              int edicion, int copias, String temas) {
        autorTextField.setText(autor);
        anioTextField.setText(String.valueOf(anio));
        tituloTextField.setText(titulo);
        palabrasCTextField.setText(palabrasClave);
        descripcionTextField.setText(descripcion);
        edicionTextField.setText(String.valueOf(edicion));
        copiasTextField.setText(String.valueOf(copias));
        temasTextField.setText(temas);
    }

    public void addCargarButtonListener(ActionListener listener) {
        cargarButton.addActionListener(listener);
    }

    public void addCancelarButtonListener(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    public void addColocarButtonListener(ActionListener listener) {
        colocarButton.addActionListener(listener);
    }

    public void setTipoComboBox(String[] tipos) {
        for (String tipo : tipos) {
            tipoComboBox.addItem(tipo);
        }
    }

    public void limpiarCampos() {
        autorTextField.setText("");
        anioTextField.setText("");
        tituloTextField.setText("");
        palabrasCTextField.setText("");
        descripcionTextField.setText("");
        isbnTextField.setText("");
        edicionTextField.setText("");
        copiasTextField.setText("");
        areaTextField.setText("");
        categoriaTextField.setText("");
        tamanioTextField.setText("");
        temasTextField.setText("");
    }

    public void setMainFieldsEditable() {
        this.autorTextField.setEditable(true);
        this.anioTextField.setEditable(true);
        this.tituloTextField.setEditable(true);
        this.palabrasCTextField.setEditable(true);
        this.descripcionTextField.setEditable(true);
        this.temasTextField.setEditable(true);
        this.edicionTextField.setEditable(true);
    }

    public void setIsbnTextFieldEditable() {
        this.isbnTextField.setEditable(true);
    }


    public void setCopiasTextFieldEditable() {
        this.copiasTextField.setEditable(true);
    }

    public void setAreaTextFieldEditable() {
        this.areaTextField.setEditable(true);
    }

    public void setCategoriaTextFieldEditable() {
        this.categoriaTextField.setEditable(true);
    }

    public void setTamanioTextFieldEditable() {
        this.tamanioTextField.setEditable(true);
    }

    public void setEjemplaresTextFieldEditable() {
        this.ejemplaresTextField.setEditable(true);
    }

    public void bloquearCampos() {
        autorTextField.setEditable(false);
        anioTextField.setEditable(false);
        tituloTextField.setEditable(false);
        palabrasCTextField.setEditable(false);
        descripcionTextField.setEditable(false);
        isbnTextField.setEditable(false);
        edicionTextField.setEditable(false);
        copiasTextField.setEditable(false);
        areaTextField.setEditable(false);
        categoriaTextField.setEditable(false);
        tamanioTextField.setEditable(false);
        temasTextField.setEditable(false);
        ejemplaresTextField.setEditable(false);
    }

    public String getAutorTextField() {
        return autorTextField.getText();
    }

    public String getAnioTextField() {
        return anioTextField.getText();
    }

    public String getTituloTextField() {
        return tituloTextField.getText();
    }

    public String getPalabrasCTextField() {
        return palabrasCTextField.getText();
    }

    public String getDescripcionTextField() {
        return descripcionTextField.getText();
    }

    public String getIsbnTextField() {
        return isbnTextField.getText();
    }

    public String getEdicionTextField() {
        return edicionTextField.getText();
    }

    public String getCopiasTextField() {
        return copiasTextField.getText();
    }

    public String getAreaTextField() {
        return areaTextField.getText();
    }

    public String getCategoriaTextField() {
        return categoriaTextField.getText();
    }

    public String getTamanioTextField() {
        return tamanioTextField.getText();
    }

    public String getTemasTextField() {
        return temasTextField.getText();
    }

    public String getEjemplaresTextField() {
        return ejemplaresTextField.getText();
    }

    public String getTipoComboBox() {
        return (String) tipoComboBox.getSelectedItem();
    }

    public void setCargarButtonVisible(boolean b) {
        cargarButton.setVisible(b);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(mainPanel, mensaje);
    }

    public void presionarColocarButton() {
        colocarButton.doClick();
    }
}
