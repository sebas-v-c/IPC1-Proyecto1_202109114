package gt.edu.usac.ingenieria;

import gt.edu.usac.ingenieria.controller.PantallaInicioController;
import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

public class App {
    public static void main(String[] args) {
        Usuario[] usuarios = new Usuario[1];
        usuarios[0] = new Usuario("admin", "", 1, "admin");
        usuarios[0].setPassword("admin");
        usuarios[0].setUser("admin");

        Bibliografia[] bibliografias = new Bibliografia[0];


        PantallaInicioView view = new PantallaInicioView();

        PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, view);

        view.setVisible(true);
    }
}
