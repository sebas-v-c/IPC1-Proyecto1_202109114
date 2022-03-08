package gt.edu.usac.ingenieria.controller.admin.listeners;

import gt.edu.usac.ingenieria.controller.admin.users.ModificarUsersController;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.admin.ModificarUsersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuardarListener implements ActionListener {
    private Usuario[] usuarios;
    private ModificarUsersView view;
    private ModificarUsersController controller;
    private Usuario nuevoUsuario;

    public GuardarListener(Usuario[] usuarios, ModificarUsersView view, ModificarUsersController controller) {
        this.usuarios = usuarios;
        this.view = view;
        this.controller = controller;
        nuevoUsuario = controller.getUsuario();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        nuevoUsuario = controller.getUsuario();
        view.setContentsRolComboBox(new String[] {"Estudiante", "Catedrático"});
        String nombre = view.getNombreTextField();
        String usuario = view.getUserTextField();
        String apellido = view.getApellidoTextField();
        String rol = view.getSelectedRolComboBox();
        String password1 = view.getPasswordField1();
        String password2 = view.getPasswordField2();
        long id = nuevoUsuario.getId();

        try {

            verificarParametros(new String[] {
                    nombre, apellido, usuario, rol, password1, password2
            }, new String[] {
                    "Nombre", "Apeliido", "Usuario", "Rol", "Contraseña1", "Contraseña2"
            });
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setRol(rol);
            nuevoUsuario.setUser(usuario);
            nuevoUsuario.setPassword(password1);
            if (!elementExist(usuarios, nuevoUsuario)) {
                for (int i = 0; i < usuarios.length; i++) {
                    if (usuarios[i].getId() == nuevoUsuario.getId()) {
                        usuarios[i] = nuevoUsuario;
                    }
                }
                // Actualizamos la variable en la clase superior
                controller.setUsuarios(usuarios);
                view.mostrarMensaje("El usuario se ha actualizado con éxito");
            } else {
                view.mostrarMensaje("El nombre de usuario ya existe");
            }
        } catch (CrearUserListener.ParametroException e) {
            e.printStackTrace();
            view.mostrarMensaje(e.mensaje);
        }
        view.removeAllRolComboBox();
        view.setGuardarButtonVisible(false);
        view.setParametrosEditables(false);
        view.setUserContents("", "", "", "", "");
    }

    protected Usuario[] addElement(int n, Usuario[] usuarios, Usuario usuario) {
        Usuario[] newUsuarios = new Usuario[n + 1];
        // Copiar la array antigua en la nueva
        if (n >= 0) System.arraycopy(usuarios, 0, newUsuarios, 0, n);
        newUsuarios[n] = usuario;
        return newUsuarios;
    }


    protected boolean elementExist(Usuario[] usuarios, Usuario nuevoUsuario) {
        for (Usuario usuario: usuarios) {
            if ((usuario.getUser().equals(nuevoUsuario.getUser())) && !(usuario.getId() == nuevoUsuario.getId())) {
                return true;
            }
        }
        return false;
    }

    // Solo verifica que los parametros esten todos bien
    // Siempre es necesario colocar las passwords como parametros consecutivos
    protected void verificarParametros(String[] camposTexto, String[] tipos) throws CrearUserListener.ParametroException {
        for (int i = 0; i < camposTexto.length; i++) {
            if (camposTexto[i].equals("")) {
                throw new CrearUserListener.ParametroException("El parametro " + tipos[i] + " está vacio.");
            } else if (tipos[i].equals("Contraseña1")) {
                if (! camposTexto[i].equals(camposTexto[i + 1])) {
                    throw new CrearUserListener.ParametroException("Las contraseñas no coinciden, por favor vuelva a intentar");
                }
            }
        }
    }
}
