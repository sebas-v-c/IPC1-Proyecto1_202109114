package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicioController {
    private Usuario[] usuarios;
    private PantallaInicioView view;
    private Bibliografia[] bibliografias;

    public PantallaInicioController(Usuario[] usuarios, Bibliografia[] bibliografias, PantallaInicioView view){
        this.usuarios = usuarios;
        this.view = view;
        this.bibliografias = bibliografias;
        view.addLoginListener(new LoginListener());
        view.addAboutListener(new AboutListener());
    }

    private class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            LoginView loginView = new LoginView();
            LoginController controller = new LoginController(loginView, usuarios, bibliografias);
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
