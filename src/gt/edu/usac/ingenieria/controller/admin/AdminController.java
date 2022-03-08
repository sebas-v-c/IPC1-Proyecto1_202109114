package gt.edu.usac.ingenieria.controller.admin;

import gt.edu.usac.ingenieria.controller.admin.bibliografias.CrearBibliografiaController;
import gt.edu.usac.ingenieria.controller.admin.bibliografias.ModificarBibliografiaController;
import gt.edu.usac.ingenieria.controller.admin.bibliografias.MostrarBibliografiaController;
import gt.edu.usac.ingenieria.controller.admin.users.CrearUserController;
import gt.edu.usac.ingenieria.controller.admin.users.ModificarUsersController;
import gt.edu.usac.ingenieria.controller.admin.users.MostrarUserController;
import gt.edu.usac.ingenieria.controller.PantallaInicioController;
import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.*;
import gt.edu.usac.ingenieria.view.admin.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    Usuario[] usuarios;
    AdminView view;
    Bibliografia[] bibliografias;

    public AdminController(Usuario[] usuarios, Bibliografia[] bibliografias, AdminView view) {
        this.usuarios = usuarios;
        this.view = view;
        this.bibliografias = bibliografias;
        //TODO crear todos los botones
        view.addCrearUserListener(new CrearUserListener());
        view.addSalirListener(new SalirListener());
        view.addMostrarUserListener(new MostrarUserListener());
        view.addModificarUserListener(new ModificarUserListener());
        view.addCrearLibroListener(new CrearLibroListener());
        view.addMostrarLibroListener(new MostrarLibrosListener());
        view.addModificarLibroListener(new ModificarLibroListener());
//        view.addModificarUserListener();
    }

    private class ModificarLibroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ModificarBibliografiaView modView = new ModificarBibliografiaView();
            ModificarBibliografiaController controller = new ModificarBibliografiaController(bibliografias, usuarios, modView);
            view.dispose();
        }
    }


    private class MostrarLibrosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            MostrarBibliografiasView bibliografiasView = new MostrarBibliografiasView();
            MostrarBibliografiaController controller = new MostrarBibliografiaController(usuarios, bibliografias, bibliografiasView);
            view.dispose();
        }
    }


    // TODO quickfix como dicen los chavos
    private class CrearLibroListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int opcion = view.confirmarAccion("¿Desea realizar una carga indiviual o una carga masiva de libros?",
                    "Cargar Libros", new String[]{"Masiva", "Individual"});

            if (opcion == 0) {
                // TODO llamar view y controller de carga masiva
                CrearBibliografiaMasivaView masivaView = new CrearBibliografiaMasivaView();
                CrearBibliografiaController controller = new CrearBibliografiaController(usuarios, bibliografias, masivaView);
                view.dispose();
            } else {
                System.out.println("Individual");
                CrearBibliografiaIndiviView indiviView = new CrearBibliografiaIndiviView();
                CrearBibliografiaController controller;
                controller = new CrearBibliografiaController(usuarios, bibliografias, indiviView,
                        new String[] {"Libro", "Revista", "Tesis", "Libro Digital"});
                view.dispose();
            }
        }
    }

    private class ModificarUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ModificarUsersView modificarUsersView = new ModificarUsersView();
            new ComboBoxController(modificarUsersView.getIdComboBox());
            ModificarUsersController controller = new ModificarUsersController(usuarios, bibliografias, modificarUsersView);
            view.dispose();
        }
    }


    private class MostrarUserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            MostrarUserView userView = new MostrarUserView();
            MostrarUserController controller = new MostrarUserController(usuarios, bibliografias,userView);
            view.dispose();
        }
    }


    private class SalirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PantallaInicioView pantallaInicioView = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, pantallaInicioView);
            view.dispose();
        }
    }


    private class CrearUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CrearUserView crearUserView = new CrearUserView();
            CrearUserController controller = new CrearUserController(usuarios, bibliografias, crearUserView);
            view.dispose();
        }
    }

}
