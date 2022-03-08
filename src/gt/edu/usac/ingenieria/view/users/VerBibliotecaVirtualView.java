package gt.edu.usac.ingenieria.view.users;

import gt.edu.usac.ingenieria.controller.users.VerBibliotecaVirtualController;
import gt.edu.usac.ingenieria.controller.users.listeners.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VerBibliotecaVirtualView extends JFrame{
    private JButton logoutButton;
    private JButton salirButton;
    private JPanel mainPanel;
    private JLabel tituloLabel;
    private JLabel autorLabel;
    private JLabel anioLabel;
    private JLabel edicionLabel;
    private JLabel descripcionLabel;
    private JLabel tamanioLabel;
    private JButton eliminarButton;
    private JScrollPane scrollPane;
    private JPanel containerPanel;
    private JLabel idLabel;

    private JPanel[] listaLibros = new JPanel[]{};
    private JPanel copia;
    private VerBibliotecaVirtualController controller;

    public VerBibliotecaVirtualView(VerBibliotecaVirtualController controller) {
        // Titulo de la ventana
        super("Eliminar Usuarios");
        this.controller = controller;
        // Que hacer cuando se cierre la ventanausuario
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ComboBox de busqueda de usuarios
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        copia = containerPanel;
        scrollPane.setViewportView(containerPanel);

        this.setContentPane(mainPanel);
//        this.setPreferredSize(new Dimension(300, 400));
        this.pack();
        this.setVisible(true);
    }

    public void agregarNuevoLibro(int id, String titulo) {
//        FlowLayout tempLayout = new FlowLayout(FlowLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel idLabel = new JLabel(String.valueOf(id));
        JLabel tituloLabel = new JLabel(titulo);
        panel.add(idLabel);
        panel.add(tituloLabel);
        panel.addMouseListener(new MyMouseListener(controller, this));
        panel.setBackground(Color.green);

        listaLibros = agregarAArreglo(panel, listaLibros);
    }

    public void eliminarLibro(int id) {
        listaLibros = quitarLibro(id, listaLibros);
        actualizarScrollPane();
    }

    public void actualizarScrollPane() {
        for (Component panel : containerPanel.getComponents()) {
            containerPanel.remove(panel);
        }

        System.out.println(listaLibros.length + " Tamanio listado de libros");

        if (listaLibros != null) {
            for (JPanel panel : listaLibros) {
                containerPanel.add(panel);
                containerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
            }
        }
    }

    public void addEliminarButtonListener(ActionListener listener) {
        eliminarButton.addActionListener(listener);
    }
    public void addSalirButtonListener(ActionListener listener) {
        salirButton.addActionListener(listener);
    }
    public void addLogoutButton(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public String getIdLabelText() {
        return idLabel.getText();
    }

    public void setAtributos(int id, String titulo, String autor, String anio, String edicion, String descripcion, String tamanio) {
        idLabel.setText(String.valueOf(id));
        tituloLabel.setText(titulo);
        autorLabel.setText(autor);
        anioLabel.setText(anio);
        edicionLabel.setText(edicion);
        descripcionLabel.setText(descripcion);
        tamanioLabel.setText(tamanio);
    }


    public JPanel[] agregarAArreglo(JPanel objeto, JPanel[] objetos) {
        int n = objetos.length;
        JPanel[] newObjetos = new JPanel[ n + 1];
        // Copiar la array antigua en la nueva
        System.arraycopy(objetos, 0, newObjetos, 0, n);
        newObjetos[n] = objeto;
        return newObjetos;
    }


    private JPanel[] quitarLibro(int jpanelID, JPanel[] lista) {
        JPanel[] nuevaLista = new JPanel[lista.length-1];
        int n = 0;
        String id = "";
        String idJpanel = String.valueOf(jpanelID);


        for (JPanel panel : lista) {
            Component[] components = panel.getComponents();
            if ( components[0] instanceof JLabel ) {
                id = ((JLabel) components[0]).getText();
            }
            if (idJpanel.equals(id)) {
                continue;
            }
            nuevaLista[n] = panel;
            n++;
        }

        return nuevaLista;
    }

    public void setController(VerBibliotecaVirtualController controller) {
        this.controller = controller;
    }

    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(mainPanel, s);
    }

    public boolean confirmarAccion(String mensaje, String titulo, String[] opciones) {
        int reply = JOptionPane.showOptionDialog(mainPanel, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
        return reply == 0;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
