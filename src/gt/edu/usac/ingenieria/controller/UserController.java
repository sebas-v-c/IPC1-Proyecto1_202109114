package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.PantallaInicioView;
import gt.edu.usac.ingenieria.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    Usuario[] usuarios;
    UserView view;
    Usuario usuarioLogueado;
    Bibliografia[] bibliografias;

    public UserController(Usuario[] usuarios, Bibliografia[] bibliografias, Usuario usuarioLoguado, UserView view) {
        this.usuarios = usuarios;
        this.view = view;
        this.usuarioLogueado = usuarioLoguado;
        this.bibliografias = bibliografias;
        view.addLogoutListener(new LogoutListener());
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PantallaInicioView pantallaInicioView = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, pantallaInicioView);
            view.dispose();

        }
    }

}
