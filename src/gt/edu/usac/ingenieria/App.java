package gt.edu.usac.ingenieria;

import gt.edu.usac.ingenieria.controller.PantallaInicioController;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

public class App {
    public static void main(String[] args) {
        Usuario[] usuarios = new Usuario[100];
        usuarios[0] = new Usuario("admin", "", 1, "admin");
        PantallaInicioView view = new PantallaInicioView("Welcome");

        PantallaInicioController controller = new PantallaInicioController(usuarios, view);

        view.setVisible(true);
    }
}
