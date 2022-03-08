package gt.edu.usac.ingenieria.controller.users;

import gt.edu.usac.ingenieria.controller.LoginController;
import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.model.LibroDigital;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;
import gt.edu.usac.ingenieria.view.users.VerBibliotecaVirtualView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VerBibliotecaVirtualController {
    private Info info;
    private Bibliografia[] bibliografias;
    private Usuario[] usuarios;
    private VerBibliotecaVirtualView view;
    private String idSeleccionado;


    public VerBibliotecaVirtualController(Info info) {
        this.info = info;
        this.bibliografias = info.getBibliografias();
        this.usuarios = info.getUsuarios();
        this.view = new VerBibliotecaVirtualView(this);
        view.addSalirButtonListener(new SalirListener());
        view.addLogoutButton(new LogoutListener());
        view.addEliminarButtonListener(new EliminarListener());
        colocarLibros();
    }

    private void colocarLibros() {
        for (int id : info.getUsuarioIngresado().getLibrosPrestados()) {
            for (Bibliografia bibliografia : bibliografias) {
                if (id == bibliografia.getId() && bibliografia instanceof LibroDigital) {
                    view.agregarNuevoLibro(id, bibliografia.getTitulo());
                }
            }
        }
        view.actualizarScrollPane();
    }


    public void colocarComponentes(String id) {
        // TODO colocar componentes
        for (Bibliografia bibliografia : bibliografias) {
            if (String.valueOf(bibliografia.getId()).equals(id)) {
                view.setAtributos(bibliografia.getId(), bibliografia.getTitulo(),
                        bibliografia.getAutor(), String.valueOf(bibliografia.getAnio()),
                        String.valueOf(bibliografia.getEdicion()), bibliografia.getDescripcion(),
                        String.valueOf(((LibroDigital) bibliografia).getTamanio()));
            }
        }
    }

    public Info getInfo() {
        return info;
    }

    private class EliminarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO completar este codigo
            int id;
            try {
                boolean respuesta = view.confirmarAccion("¿Está seguro desea borrar el libro?",
                        "ELIMINAR USUARIO", new String[] {"Segurisimo", "No!"});
                if (!respuesta) {
                    throw new Exception();
                }

                id = Integer.parseInt(view.getIdLabelText());
                info.getUsuarioIngresado().quitarLibro(id);

                // Creo que este codigo no es necesario porque como es digital no hay por que sumarle cantidades
//                for (Bibliografia bibliografia : bibliografias) {
//                    if (bibliografia.getId() == id) {
//                        bibliografia.setDisponibles(bibliografia.getDisponibles());
//                    }
//                }
                view.eliminarLibro(id);

            } catch (Exception e) {
                view.mostrarMensaje("No se ha eliminado libro seleccionado");
            }
        }
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            LoginView loginView = new LoginView();
            LoginController controller = new LoginController(loginView, info.getUsuarios(), info.getBibliografias());
            view.dispose();
        }
    }


    private class SalirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            BibliotecaVirtualView virtualView = new BibliotecaVirtualView();
            BibliotecaController controller = new BibliotecaController(info, virtualView);
            virtualView.setController(controller);
            view.dispose();
        }
    }

    public void setView(VerBibliotecaVirtualView view) {
        this.view = view;
        view.addSalirButtonListener(new SalirListener());
        view.addLogoutButton(new LogoutListener());
        view.addEliminarButtonListener(new EliminarListener());
    }
}
