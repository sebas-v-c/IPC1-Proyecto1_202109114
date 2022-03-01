package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Usuario;
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
            //TODO llamar pantalla de login

        }
    }


    private class AboutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO llamar pantalla de about

        }
    }
}
