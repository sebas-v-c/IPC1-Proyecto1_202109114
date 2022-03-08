package gt.edu.usac.ingenieria.controller.admin.listeners;

import gt.edu.usac.ingenieria.controller.admin.users.CrearUserController;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.admin.CrearUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearUserListener implements ActionListener {
    protected Usuario[] usuarios;
    protected final CrearUserView view;
    protected final CrearUserController controller;

    public CrearUserListener(Usuario[] usuarios, CrearUserView view, CrearUserController controller) {
        this.usuarios = usuarios;
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String nombre = view.getNombreTextField();
        String usuario = view.getUserTextField();
        String apellido = view.getApellidoTextField();
        long id = 0;
        String idString = view.getIdTextField();
        String rol = view.getRolComboBox();
        String password1 = view.getPasswordField1();
        String password2 = view.getPasswordField2();
        Usuario nuevoUsuario;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ignored) {
            System.out.println("NumberFormatException");
        }

        try {
            if (id == 0) {
                view.limpiarID();
                throw new ParametroException("El ID solo puede contener datos numéricos");
            }

            verificarParametros(new String[] {
                    nombre, apellido, usuario, rol, idString, password1, password2
            }, new String[] {
                    "Nombre", "Apeliido", "Usuario", "Rol", "ID", "Contraseña1", "Contraseña2"
            });
            nuevoUsuario = new Usuario(nombre, apellido, id, rol);
            nuevoUsuario.setUser(usuario);
            nuevoUsuario.setPassword(password1);
//            verificarParametros(usuario, "User");
//            verificarParametros(apellido, "Apellido");
//            verificarParametros(apellido, "Apellido");
//            verificarParametros(String.valueOf(id), "ID");
//            verificarParametros(rol, "Rol");
//            verificarParametros(password1, "Contraseña");
            if (!elementExist(usuarios, nuevoUsuario)) {
                usuarios = addElement(usuarios.length, usuarios, nuevoUsuario);
                // Actualizamos la variable en la clase superior
                controller.setUsuarios(usuarios);
                view.mostrarMensaje("Usuario creado con éxito");
                view.limpiarCampos();
            } else {
                view.mostrarMensaje("El ID o el nombre de usuario ya existen");
                view.limpiarID();
                view.limpiarUser();
            }
        } catch (ParametroException e) {
            e.printStackTrace();
            view.mostrarMensaje(e.mensaje);
        }
    }

    private Usuario[] addElement(int n, Usuario[] usuarios, Usuario usuario) {
        Usuario[] newUsuarios = new Usuario[n + 1];
        // Copiar la array antigua en la nueva
        if (n >= 0) System.arraycopy(usuarios, 0, newUsuarios, 0, n);
        newUsuarios[n] = usuario;
        return newUsuarios;
    }


    private boolean elementExist(Usuario[] usuarios, Usuario nuevoUsuario) {
        for (Usuario usuario: usuarios) {
            if (usuario.getId() == nuevoUsuario.getId() || usuario.getUser().equals(nuevoUsuario.getUser())) {
                return true;
            }
        }
        return false;
    }

    // Solo verifica que los parametros esten todos bien
    // Siempre es necesario colocar las passwords como parametros consecutivos
    private void verificarParametros(String[] camposTexto, String[] tipos) throws ParametroException {
        for (int i = 0; i < camposTexto.length; i++) {
            if (camposTexto[i].equals("")) {
                throw new ParametroException("El parametro " + tipos[i] + " está vacio.");
            } else if (tipos[i].equals("Contraseña1")) {
                if (! camposTexto[i].equals(camposTexto[i + 1])) {
                    throw new ParametroException("Las contraseñas no coinciden, por favor vuelva a intentar");
                }
            }
        }
    }

    public static class ParametroException extends Exception {
        final String mensaje;
        ParametroException(String mensaje) {
            super(mensaje);
            this.mensaje = mensaje;
        }
//
//        private String getMensaje() {
//            return mensaje;
//        }
    }
}
