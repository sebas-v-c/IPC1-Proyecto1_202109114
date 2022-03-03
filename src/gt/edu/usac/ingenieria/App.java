package gt.edu.usac.ingenieria;

import gt.edu.usac.ingenieria.controller.PantallaInicioController;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

public class App {
    public static void main(String[] args) {
        // TODO dejar solamente el usuario administrador
        Usuario[] usuarios = new Usuario[3];
        usuarios[0] = new Usuario("admin", "", 1, "admin");
        usuarios[0].setPassword("admin");
        usuarios[0].setUser("admin");
        usuarios[1] = new Usuario("Sebastian", "Vásquez", 3010299380101L, "estudiante");
        usuarios[1].setPassword("Azopotamadre344");
        usuarios[1].setUser("zibas");
        usuarios[2] = new Usuario("Belén", "Izquierdo", 5014896305251L, "catedratico");
        usuarios[2].setPassword("belen2004");
        usuarios[2].setUser("AB");
        PantallaInicioView view = new PantallaInicioView();

        PantallaInicioController controller = new PantallaInicioController(usuarios, view);

        view.setVisible(true);
    }
}
