package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.PantallaInicioView;
import gt.edu.usac.ingenieria.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    private Info info;
    private Usuario[] usuarios;
    private UserView view;
    private Usuario usuarioLogueado;
    private Bibliografia[] bibliografias;

    public UserController(Info info, UserView view) {
        this.info =info;
        this.usuarios = info.getUsuarios();
        this.view = view;
        this.usuarioLogueado = info.getUsuarioIngresado();
        this.bibliografias = info.getBibliografias();
        view.addLogoutListener(new LogoutListener());
        view.addBilbiotecaListener(new BibliotecaListener());
        view.addPrestamoListener(new PrestamoListener());
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PantallaInicioView pantallaInicioView = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, pantallaInicioView);
            view.dispose();

        }
    }

    private class PrestamoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    private class BibliotecaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}
