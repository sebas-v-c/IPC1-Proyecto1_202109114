package gt.edu.usac.ingenieria.controller.users.listeners;

import gt.edu.usac.ingenieria.controller.users.VerBibliotecaVirtualController;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.view.users.VerBibliotecaVirtualView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    Info info;
    VerBibliotecaVirtualController controller;
    VerBibliotecaVirtualView view;

    public MyMouseListener(VerBibliotecaVirtualController controller, VerBibliotecaVirtualView view) {
        this.controller = controller;
        this.view = view;
        this.info = controller.getInfo();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JPanel panel = (JPanel)mouseEvent.getSource();
        Component[] components = panel.getComponents();
        String id = "";
        if (components[0] instanceof JLabel) {
            id = ((JLabel) components[0]).getText();
        }
        controller.colocarComponentes(id);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

}
