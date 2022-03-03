package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.controller.listeners.LoginListener;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    Usuario[] usuarios;
    LoginView view;

    public LoginController(LoginView view, Usuario[] usuarios) {
        this.usuarios = usuarios;
        this.view = view;
        view.addLoginListener(new LoginListener(usuarios, view));
        view.addCancelarListener(new cerrarListener());
    }

    private class cerrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO llamar a la pantalla de bienvenida
            PantallaInicioView pantallaInicioView = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, pantallaInicioView);
            view.dispose();
        }
    }

}
