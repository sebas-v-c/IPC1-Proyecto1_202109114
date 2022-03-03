package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicioController {
    private Usuario[] usuarios;
    private PantallaInicioView view;

    public PantallaInicioController(Usuario[] usuarios, PantallaInicioView view){
        this.usuarios = usuarios;
        this.view = view;
        view.addLoginListener(new LoginListener());
        view.addAboutListener(new AboutListener());
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            LoginView loginView = new LoginView();
            LoginController controller = new LoginController(loginView, usuarios);
            view.dispose();
        }
    }


    private class AboutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO llamar pantalla de about

        }
    }
}
