package gt.edu.usac.ingenieria.controller.admin.bibliografias;

import gt.edu.usac.ingenieria.controller.admin.AdminController;
import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.admin.AdminView;
import gt.edu.usac.ingenieria.view.admin.CrearBibliografiaIndiviView;
import gt.edu.usac.ingenieria.view.admin.ModificarBibliografiaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarBibliografiaController {
    Bibliografia[] bibliografias;
    Usuario[] usuarios;
    ModificarBibliografiaView view;

    public ModificarBibliografiaController(Bibliografia[] bibliografias, Usuario[] usuarios, ModificarBibliografiaView view) {
        this.bibliografias = bibliografias;
        this.usuarios = usuarios;
        this.view = view;
        rellenarTabla(bibliografias);
        view.addEliminarListener(new EliminarListener());
        view.addSalirListener(new SalirListener());
        view.addModificarListener(new ModificarListener());
        view.addBuscarListener(new BuscarListener());
    }

    private class BuscarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                int id = Integer.parseInt(view.getBuscarTextField());
                int length = view.getRowCount();
                for (int i = 0; i < length; i++) {
                    if (id == view.getIdDeLaFila(i)) {
                        view.highLight(i);
                    }
                }

            } catch (NumberFormatException e) {
                view.mostrarMensaje("Tiene que se un dato numerico");
            }
        }
    }

    private void rellenarTabla(Bibliografia[] bibliografias) {
        Object[] entrada = new Object[16];
        try {
            for (int i = 0; i < bibliografias.length; i++) {
                if (bibliografias[i] != null) {
                    if (bibliografias[i] instanceof Libro) {
                        entrada[0] = bibliografias[i].getId();
                        entrada[1] = "Libro";
                        informacionBasica(entrada, i, bibliografias);
                        entrada[9] = ((Libro) bibliografias[i]).getISBN();
                        entrada[10] = "";
                        entrada[11] = "";
                        entrada[12] = "";
                        entrada[13] = bibliografias[i].getCopias();
                        entrada[14] = bibliografias[i].getDisponibles();
                        entrada[15] = "";
                    } else if (bibliografias[i] instanceof Revista) {
                        entrada[0] = bibliografias[i].getId();
                        entrada[1] = "Revista";
                        informacionBasica(entrada, i, bibliografias);
                        entrada[9] = "";
                        entrada[10] = "";
                        entrada[11] = ((Revista) bibliografias[i]).getCategoria();
                        entrada[12] = ((Revista) bibliografias[i]).getEjemplares();
                        entrada[13] = bibliografias[i].getCopias();
                        entrada[14] = bibliografias[i].getDisponibles();
                        entrada[15] = "";
                    } else if (bibliografias[i] instanceof Tesis) {
                        entrada[0] = bibliografias[i].getId();
                        entrada[1] = "Tesis";
                        informacionBasica(entrada, i, bibliografias);
                        entrada[9] = "";
                        entrada[10] = ((Tesis) bibliografias[i]).getArea();
                        entrada[11] = "";
                        entrada[12] = "";
                        entrada[13] = bibliografias[i].getCopias();
                        entrada[14] = bibliografias[i].getDisponibles();
                        entrada[15] = "";
                    } else if (bibliografias[i] instanceof LibroDigital) {

                        entrada[0] = bibliografias[i].getId();
                        entrada[1] = "Libro Digital";
                        informacionBasica(entrada, i, bibliografias);
                        entrada[9] = "";
                        entrada[10] = "";
                        entrada[11] = "";
                        entrada[12] = "";
                        entrada[13] = "";
                        entrada[14] = "";
                        entrada[15] = ((LibroDigital) bibliografias[i]).getTamanio();
                    }

                    view.agregarBibliografia(entrada);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void informacionBasica(Object[] entrada, int i, Bibliografia[] bibliografias) {
        String palabrasClave = String.join(",", bibliografias[i].getPalabrasClave());
        String temas = String.join(",", bibliografias[i].getTemas());
        System.out.println(temas);
        entrada[2] = bibliografias[i].getTitulo();
        entrada[3] = bibliografias[i].getAutor();
        entrada[4] = bibliografias[i].getAnio();
        entrada[5] = bibliografias[i].getEdicion();
        entrada[6] = bibliografias[i].getDescripcion();
        entrada[7] = palabrasClave;
        entrada[8] = temas;
    }

    private class EliminarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boolean respuesta = view.confirmarAccion("¿Está seguro desea borrar la bibliografía?",
                    "ELIMINAR USUARIO", new String[] {"Segurisimo", "No!"});
            if (respuesta) {
                int id = view.getIdDeLibroSeleccionado();
                eliminarUsuario(id);
                view.eliminarBibliografia();
                view.mostrarMensaje("Bibliografia eliminada con exito");
            }
        }


        private void eliminarUsuario(int id) {
            Bibliografia[] nuevasBibliografias = new Bibliografia[bibliografias.length-1];
            int n = 0;
            for (Bibliografia bibliografiaActual : bibliografias) {
                if (bibliografiaActual.getId() == id) {
                    continue;
                }
                nuevasBibliografias[n] = bibliografiaActual;
                n++;
            }
            setBibliografias(nuevasBibliografias);
        }
    }

    private void setBibliografias(Bibliografia[] nuevaBibliografia) {
        this.bibliografias = nuevaBibliografia;
    }

    private class SalirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AdminView adminView = new AdminView();
            AdminController controller = new AdminController(usuarios, bibliografias, adminView);
            view.dispose();
        }
    }

    private class ModificarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CrearBibliografiaIndiviView crearView = new CrearBibliografiaIndiviView();
            CrearBibliografiaController controller = new CrearBibliografiaController(usuarios, bibliografias, crearView, new String[]{view.getTipoFilaSeleccionada()});
            for (Bibliografia bibliografia : bibliografias) {
                if (bibliografia.getId() == view.getIdDeLibroSeleccionado()) {
                    controller.setCamposViewIndividual(bibliografia);
                }
            }
            view.dispose();
        }
    }
}
