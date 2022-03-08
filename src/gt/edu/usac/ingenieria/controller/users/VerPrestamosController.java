package gt.edu.usac.ingenieria.controller.users;

import gt.edu.usac.ingenieria.controller.users.listeners.LogoutListener;
import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.users.PrestamoLibrosView;
import gt.edu.usac.ingenieria.view.users.VerPrestamosView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerPrestamosController {
    private Info info;
    private VerPrestamosView view;
    private Bibliografia[] bibliografias;

    public VerPrestamosController(Info info, VerPrestamosView view) {
        this.info = info;
        this.view = view;
        this.bibliografias = info.getBibliografias();
        rellenarTabla(info.getUsuarioIngresado().getLibrosPrestados());
        view.addLogoutListener(new LogoutListener(info, view));
        view.addRegresarListener(new RegresarListener());
    }



    public void rellenarTabla(int[] librosEncontrados) {
        Object[] fila = new Object[11];
        for (int libroEncontrado : librosEncontrados) {
            Bibliografia bibliografia = bibliografias[0];
            for (Bibliografia value : bibliografias) {
                if (value.getId() == libroEncontrado) {
                    bibliografia = value;
                    break;
                }
            }
            String temas = String.join(",", bibliografia.getTemas());
            fila[0] = bibliografia.getId();
            fila[2] = bibliografia.getTitulo();
            fila[3] = bibliografia.getAutor();
            fila[4] = bibliografia.getAnio();
            fila[5] = bibliografia.getEdicion();
            fila[6] = bibliografia.getDescripcion();
            fila[7] = temas;

            if (bibliografia instanceof Libro) {
                fila[1] = "Libro";
                fila[8] = ((Libro) bibliografia).getISBN();
                fila[9] = "";
            } else if (bibliografia instanceof Tesis) {
                fila[1] = "Tesis";
                fila[8] = "";
                fila[9] = ((Tesis) bibliografia).getArea();
            } else {
                fila[1] = "Revista";
                fila[8] = "";
                fila[9] = "";
            }
            fila[10] = "Quitar Libro";

            view.agregarEntrada(fila);
        }
    }

    public void quitarLibroSeleccionado(Object valueAt) {
        int id = Integer.parseInt(valueAt.toString());
        boolean respuesta = view.confirmarAccion("¿Está seguro desea borrar la bibliografía?",
                "ELIMINAR BIBLIOGRAFÍA", new String[] {"Segurisimo", "No!"});
        if (respuesta) {
            for (int i = 0; i < info.getUsuarioIngresado().getLibrosPrestados().length; i++) {
                if (id == info.getUsuarioIngresado().getLibrosPrestados()[i]) {
                    info.getUsuarioIngresado().quitarLibro(id);
                }
            }
            int row = view.getSelectedRowIndex();

            for (int i = 0; i < info.getBibliografias().length; i++) {
                if (id == info.getBibliografias()[i].getId()) {
                    info.getBibliografias()[i].setDisponibles(
                            info.getBibliografias()[i].getDisponibles() + 1
                    );
                }
            }
            view.eliminarFila(row);
            view.mostrarMensaje("Bibliografia eliminada con exito");
        }
    }

    private class RegresarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PrestamoLibrosView nuevaView = new PrestamoLibrosView();
            PrestamoLibrosController controller = new PrestamoLibrosController(info, nuevaView);
            nuevaView.setController(controller);
            view.dispose();
        }
    }
}
