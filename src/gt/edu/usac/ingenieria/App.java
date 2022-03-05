package gt.edu.usac.ingenieria;

import gt.edu.usac.ingenieria.controller.PantallaInicioController;
import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.PantallaInicioView;

public class App {
    public static void main(String[] args) {
        // TODO dejar solamente el usuario administrador
        Usuario[] usuarios = new Usuario[3];
        usuarios[0] = new Usuario("admin", "", 1, "admin");
        usuarios[0].setPassword("admin");
        usuarios[0].setUser("admin");
        usuarios[1] = new Usuario("Sebastian", "Vásquez", 3010299380101L, "Estudiante");
        usuarios[1].setPassword("Azopotamadre344");
        usuarios[1].setUser("zibas");
        usuarios[2] = new Usuario("Belén", "Izquierdo", 5014896305251L, "Catedrático");
        usuarios[2].setPassword("belen2004");
        usuarios[2].setUser("AB");

        Bibliografia[] bibliografias = new Bibliografia[3];
        bibliografias[0] = new Libro("HP Lovecraft", 1970, "La llamada de Cuthulu",
                new String[]{"Terror Cosimico", "Simon"}, "Pulpo qliao come gente",
                new String[]{"Homosexualidad", "Comunismo", "Como cocinar un pastel 3 leches"}, 20, 20,
                655453, 3);
        bibliografias[1] = new LibroDigital(
                "No se lol MIT",
                1992, "Structure and interpretation of computer programs",
                new String[]{"Matematica", "Dolor de cabezaaaa"},
                "Libor re piola con un monton de cosas piolas",
                new String[]{"Recursion", "Cocinar helados"},
                5, 557336);
        bibliografias[2] = new Revista(
                "Algun maje loco",
                1987,
                "Un libro loco sobre el amor, la guerra y recetas de cocina",
                new String[]{"Cocina", "Amor", "Guerra"},
                "Es solo un libro sobre como hacer tamales, no hay pdo",
                new String[] {"Cocina", "Tamales", "Depresion"},
                4687654,
                12,
                "Revista cientifica",
                45454545
        );
        System.out.println(bibliografias[2].getId());
        System.out.println(bibliografias[1].getId());
        System.out.println(bibliografias[0].getId());

        PantallaInicioView view = new PantallaInicioView();

        PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, view);

        view.setVisible(true);
    }
}
