package gt.edu.usac.ingenieria.controller.admin.users;

import gt.edu.usac.ingenieria.controller.admin.AdminController;
import gt.edu.usac.ingenieria.controller.admin.listeners.CrearUserListener;
import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.admin.AdminView;
import gt.edu.usac.ingenieria.view.admin.CrearUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUserController {
    private String[] roles = {"Estudiante", "Catedrático"};
    private Usuario[] usuarios;
    private CrearUserView view;
    Bibliografia[] bibliografias;

    public CrearUserController(Usuario[] usuarios, Bibliografia[] bibliografias, CrearUserView view) {
        this.usuarios = usuarios;
        this.view = view;
        this.bibliografias = bibliografias;
        view.addCrearListener(new CrearUserListener(usuarios, view, this));
        view.addCancelarListener(new CancelarListener());
        view.setRolComboBox(roles);
    }

    private class CancelarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AdminView adminView = new AdminView();
            AdminController controller = new AdminController(usuarios, bibliografias, adminView);
            view.dispose();
        }
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }
}
