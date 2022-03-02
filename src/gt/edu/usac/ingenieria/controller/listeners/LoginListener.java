package gt.edu.usac.ingenieria.controller.listeners;

import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    Usuario[] usuarios;
    LoginView view;

    public LoginListener(Usuario[] usuarios, LoginView view){
        this.usuarios = usuarios;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String nombre;
        int carnet;
        Usuario usuarioActual;
        boolean usuarioCorrecto = false;

        // Lo rodeamos de un try para evitar cualquier error
        try {
            nombre = view.getUsuarioTextField();
            carnet = Integer.parseInt(view.getPasswordField());

            for (int i = 0; i < usuarios.length; i++) {
                // TODO modificar la logica para saber cuando el usuario no existe o se escribio mal
                if (usuarios[i] != null) {
                    if ((usuarios[i].getNombre().equals(nombre)) && (usuarios[i].getCarnet() == carnet)) {
                       // TODO abrir la siguiente ventana
                        usuarioActual = usuarios[i];
                        usuarioCorrecto = true;
//                            InfoUsuarioView infoView = new InfoUsuarioView("Información del usuario");
//                            InfoUsuarioController controller = new InfoUsuarioController(infoView, usuarios, usuarioActual);
//                            infoView.setVisible(true);
                        view.dispose();
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("");
        }
        if (!usuarioCorrecto) {
            view.limpiarCampos();
            view.mostrarMensajeError("Usuario y/o contraseña incorrectos");
        }
    }
}
