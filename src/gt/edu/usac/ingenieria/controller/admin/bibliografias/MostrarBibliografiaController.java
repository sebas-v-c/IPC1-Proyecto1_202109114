package gt.edu.usac.ingenieria.controller.admin.bibliografias;

import gt.edu.usac.ingenieria.controller.admin.AdminController;
import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.admin.AdminView;
import gt.edu.usac.ingenieria.view.admin.MostrarBibliografiasView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarBibliografiaController {
    Usuario[] usuarios;
    Bibliografia[] bibliografias;
    MostrarBibliografiasView view;

    public MostrarBibliografiaController(Usuario[] usuarios, Bibliografia[] bibliografias, MostrarBibliografiasView view) {
        this.usuarios = usuarios;
        this.bibliografias = bibliografias;
        this.view = view;
        view.addRegresarListener(new RegresarListener());
        rellenarTabla(bibliografias);
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
                        entrada[12] = "";
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

    private class RegresarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AdminView adminView = new AdminView();
            AdminController adminController = new AdminController(usuarios, bibliografias, adminView);
            view.dispose();
        }
    }

}
