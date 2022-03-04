package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.controller.listeners.GuardarListener;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.AdminView;
import gt.edu.usac.ingenieria.view.ModificarUsersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ModificarUsersController {
    private Usuario[] usuarios;
    private ModificarUsersView view;
    private Usuario usuario;

    public ModificarUsersController(Usuario[] usuarios, ModificarUsersView view) {
        this.usuarios = usuarios;
        this.view = view;
        view.addSalirListener(new SalirListener());
        view.setContentsIdComboBox(getIds(usuarios.length-1));
        view.addBuscarListener(new BuscarListener());
        view.addModificarListener(new ModificarUsersListener());
        view.addGuardarListener(new GuardarListener(usuarios, view, this));
        view.addEliminarListener(new EliminarListener());
    }

    private String[] getIds(int n){
        String[] ids = new String[n];
        for (int i = 1; i < n+1; i++) {
            ids[i-1] = String.valueOf(usuarios[i].getId());
        }
        return ids;
    }

    private class EliminarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boolean respuesta = view.confirmarAccion("¿Está seguro desea borrar el usuario?", "ELIMINAR USUARIO");
            if (respuesta) {
                eliminarUsuario();
                view.mostrarMensaje("Usuario Eliminado con éxito");
                view.removeAllRolComboBox();
                view.setGuardarButtonVisible(false);
                view.setEliminarButtonVisible(false);
                view.setModificarButtonVisible(false);
                view.setParametrosEditables(false);
                view.setUserContents("", "", "", "", "");
                view.removeAllIdComboBox();
                view.setContentsIdComboBox(getIds(usuarios.length-1));
            }
        }

        private void eliminarUsuario() {
            Usuario[] nuevosUsuarios = new Usuario[usuarios.length-1];
            for (int i = 1; i < usuarios.length-1; i++) {
                if (getUsuario().getId() == usuarios[i].getId()) {
                    continue;
                }
                nuevosUsuarios[i] = usuarios[i];
            }
            setUsuarios(nuevosUsuarios);
        }
    }

    private class ModificarUsersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.setParametrosEditables(true);
            view.setEliminarButtonVisible(false);
            view.setGuardarButtonVisible(true);
            view.setModificarButtonVisible(false);
            String rol = view.getSelectedRolComboBox();
            if (rol.equals("Estudiante")) {
                view.setContentsRolComboBox(new String[] {"Catedrático"});
            } else if (rol.equals("Catedrático")) {
                view.setContentsRolComboBox(new String[]{"Estudiante"});
            } else {
                view.removeAllRolComboBox();
                view.setContentsRolComboBox(new String[] {"Catedrático", "Estudiante"});
            }
        }
    }


    private class BuscarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String nombre = "";
            String apellido = "";
            String user = "";
            String password = "";
            String rol = "";
            long id = Long.parseLong(view.getSelectedIdComboBox());
            for (Usuario usuarioActual : usuarios) {
                if (usuarioActual.getId() == id) {
                    setUsuario(usuarioActual);
                    nombre = usuarioActual.getNombre();
                    apellido = usuarioActual.getApellido();
                    user = usuarioActual.getUser();
                    rol = usuarioActual.getRol();
                    password = usuarioActual.getPassword();
                    break;
                }
            }
            view.removeAllRolComboBox();
            view.setUserContents(nombre, apellido, user, rol, password);
            view.setParametrosEditables(false);
            view.setModificarButtonVisible(true);
            view.setEliminarButtonVisible(true);
            view.setGuardarButtonVisible(false);
        }
    }


    private class SalirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AdminView adminView = new AdminView();
            AdminController controller = new AdminController(usuarios, adminView);
            view.dispose();
        }
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

}
