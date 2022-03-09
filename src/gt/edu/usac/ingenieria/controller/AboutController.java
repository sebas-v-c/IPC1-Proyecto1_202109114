package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.AboutView;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutController {
    Usuario[] usuarios;
    Bibliografia[] bibliografias;
    AboutView aboutView;

    public AboutController(Usuario[] usuarios, Bibliografia[] bibliografias, AboutView aboutView) {
        this.aboutView = aboutView;
        this.bibliografias = bibliografias;
        this.usuarios = usuarios;
        aboutView.addRegresarListener(new RegresarListener());
    }

    private class RegresarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PantallaInicioView view = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, view);
            aboutView.dispose();
        }
    }
}
