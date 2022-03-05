package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.controller.listeners.LoginListener;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    Usuario[] usuarios;
    LoginView view;
    Bibliografia[] bibliografias;

    public LoginController(LoginView view, Usuario[] usuarios, Bibliografia[] bibliografia) {
        this.usuarios = usuarios;
        this.view = view;
        this.bibliografias = bibliografias;
        view.addLoginListener(new LoginListener(usuarios, bibliografias, view));
        view.addCancelarListener(new cerrarListener());
    }

    private class cerrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PantallaInicioView pantallaInicioView = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, pantallaInicioView);
            view.dispose();
        }
    }

}
