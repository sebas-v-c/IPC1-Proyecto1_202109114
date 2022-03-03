package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.controller.listeners.CrearUserListener;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.AdminView;
import gt.edu.usac.ingenieria.view.CrearUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUserController {
    private String[] roles = {"Estudiante", "Catedrático"};
    private Usuario[] usuarios;
    private CrearUserView view;

    public CrearUserController(Usuario[] usuarios, CrearUserView view) {
        this.usuarios = usuarios;
        this.view = view;
        // TODO agregar clase crear usuario y cancelar usuario
        view.addCrearListener(new CrearUserListener(usuarios, view));
        view.addCancelarListener(new CancelarListener());
        view.setRolComboBox(roles);
        // TODO en crear usuario agregar un metodo para verificar que no sea contrasenia vacia
//        view.addCrearListener(new CrearUserListener());
    }

    private class CancelarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AdminView adminView = new AdminView();
            AdminController controller = new AdminController(usuarios, adminView);
            view.dispose();
        }
    }


}
