package gt.edu.usac.ingenieria.controller.listeners;

import gt.edu.usac.ingenieria.controller.admin.AdminController;
import gt.edu.usac.ingenieria.controller.UserController;
import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.AdminView;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    Usuario[] usuarios;
    LoginView view;
    Bibliografia[] bibliografias;

    public LoginListener(Usuario[] usuarios, Bibliografia[] bibliografias, LoginView view){
        this.usuarios = usuarios;
        this.view = view;
        this.bibliografias = bibliografias;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String user = view.getUsuarioTextField();
        String password = view.getPasswordField();
        boolean usuarioExiste = false;

        // Lo rodeamos de un try para evitar cualquier error
            for (Usuario usuario : usuarios) {
                if (usuario != null) {
                    if ((usuario.getUser().equals(user)) && (usuario.getPassword().equals(password)) &&
                            (usuario.getRol().equals("admin"))) {
                        // Activo el modo admin y saco 100 en el proyecto B)
                        view.dispose();
                        System.out.println(usuario.getUser());
                        System.out.println(usuario.getPassword());
                        usuarioExiste = true;
                        AdminView adminView = new AdminView();
                        AdminController controller = new AdminController(usuarios, bibliografias, adminView);
                        break;
                    } else if ((usuario.getUser().equals(user)) && (usuario.getPassword().equals(password))) {
                        // Usuario normal
                        usuarioExiste = true;
                        UserView userView = new UserView();
                        UserController controller = new UserController(usuarios, bibliografias, usuario, userView);
                        view.dispose();
                        break;
                    } else if (usuario.getUser().equals(user)) {
                        usuarioExiste = true;
                        view.mostrarMensajeError("El usuario y/o contraseña no coinciden por favor revise sus datos");
                        view.limpiarCampos();
                        break;
                    }
                }
            }

        if (!usuarioExiste) {
            view.limpiarCampos();
            view.mostrarMensajeError("El usuario no existe, ponerse en contacto con el administrador para solicitar" +
                    " un registro");
        }
    }
}
