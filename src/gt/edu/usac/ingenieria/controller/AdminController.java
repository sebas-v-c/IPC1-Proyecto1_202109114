package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    Usuario[] usuarios;
    AdminView view;

    public AdminController(Usuario[] usuarios, AdminView view) {
        this.usuarios = usuarios;
        this.view = view;
        //TODO crear todos los botones
        view.addCrearUserListener(new CrearUserListener());
        view.addSalirListener(new SalirListener());
        view.addMostrarUserListener(new MostrarUserListener());
        view.addModificarUserListener(new ModificarUserListener());
//        view.addModificarUserListener();
    }

    private class ModificarUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ModificarUsersView modificarUsersView = new ModificarUsersView();
            ModificarUsersController controller = new ModificarUsersController(usuarios, modificarUsersView);
            view.dispose();
        }
    }


    private class MostrarUserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            MostrarUserView userView = new MostrarUserView();
            MostrarUserController controller = new MostrarUserController(usuarios, userView);
            view.dispose();
        }
    }


    private class SalirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PantallaInicioView pantallaInicioView = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, pantallaInicioView);
            view.dispose();
        }
    }


    private class CrearUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CrearUserView crearUserView = new CrearUserView();
            CrearUserController controller = new CrearUserController(usuarios, crearUserView);
            view.dispose();
        }
    }

}
