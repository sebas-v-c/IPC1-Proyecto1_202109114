package gt.edu.usac.ingenieria.controller.listeners;

import gt.edu.usac.ingenieria.controller.CrearUserController;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.CrearUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUserListener implements ActionListener {
    private Usuario[] usuarios;
    private CrearUserView view;

    public CrearUserListener(Usuario[] usuarios, CrearUserView view) {
        this.usuarios = usuarios;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//        try {
//            long id = Long.parseLong(view.getIdTextField());
//
//        } catch (Par)

    }

    private boolean estaVacio(String cadena) {

    }
}
