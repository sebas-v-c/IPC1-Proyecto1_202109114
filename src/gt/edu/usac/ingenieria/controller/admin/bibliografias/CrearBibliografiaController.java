package gt.edu.usac.ingenieria.controller.admin.bibliografias;

import gt.edu.usac.ingenieria.controller.admin.AdminController;
import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.AdminView;
import gt.edu.usac.ingenieria.view.CrearBibliografiaIndiviView;
import gt.edu.usac.ingenieria.view.CrearBibliografiaMasivaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearBibliografiaController {
    private Usuario[] usuarios;
    private Bibliografia[] bibliografias;
    private CrearBibliografiaIndiviView viewIndividual;
    private CrearBibliografiaMasivaView viewMasiva;
    int id;

    public CrearBibliografiaController(Usuario[] usuarios, Bibliografia[] bibliografias, CrearBibliografiaIndiviView view, String[] tipos) {
        this.bibliografias = bibliografias;
        this.usuarios = usuarios;
        this.viewIndividual = view;
        view.addCancelarButtonListener(new CancelarListener());
        view.addColocarButtonListener(new ColocarListener());
        if (tipos.length == 1) {
            view.addCargarButtonListener(new CargarListener(usuarios, bibliografias, view, this, true, 0));
        } else {
            view.addCargarButtonListener(new CargarListener(usuarios, bibliografias, view, this, false, 0));
        }
        view.bloquearCampos();
        view.setCargarButtonVisible(false);
        view.setTipoComboBox(tipos);
        view.presionarColocarButton();
    }
    public CrearBibliografiaController(Usuario[] usuarios, Bibliografia[] bibliografias, CrearBibliografiaMasivaView view) {
        this.bibliografias = bibliografias;
        this.usuarios = usuarios;
        this.viewMasiva = view;
        view.addCancelarButtonListener(new CancelarListener());
        view.addCargarButtonListener(new CargarListener(usuarios, bibliografias, view, this));
    }

    private class ColocarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String seleccion = viewIndividual.getTipoComboBox();
            viewIndividual.bloquearCampos();
            viewIndividual.setMainFieldsEditable();
            // switch mejorado
            switch (seleccion) {
                case "Libro" -> {
                    viewIndividual.setIsbnTextFieldEditable();
                    viewIndividual.setCopiasTextFieldEditable();
                }
                case "Revista" -> {
                    viewIndividual.setCategoriaTextFieldEditable();
                    viewIndividual.setEjemplaresTextFieldEditable();
                    viewIndividual.setCopiasTextFieldEditable();
                }
                case "Tesis" -> {
                    viewIndividual.setAreaTextFieldEditable();
                    viewIndividual.setCopiasTextFieldEditable();
                }
                case "Libro Digital" -> {
                    viewIndividual.setTamanioTextFieldEditable();
                }
            }

            viewIndividual.setCargarButtonVisible(true);
        }
    }

    public void setCamposViewIndividual(Bibliografia bibliografia) {
        viewIndividual.setInfoBasica(bibliografia.getAutor(), bibliografia.getAnio(),
                bibliografia.getTitulo(), String.join(",", bibliografia.getPalabrasClave()),
                bibliografia.getDescripcion(), bibliografia.getEdicion(), bibliografia.getCopias(),
                String.join(",", bibliografia.getTemas()));

        if (bibliografia instanceof LibroDigital) {
            viewIndividual.setTamanioText(((LibroDigital) bibliografia).getTamanio());
            viewIndividual.setCopiasText();
        } else if (bibliografia instanceof Libro) {
            viewIndividual.setIsbnText(((Libro) bibliografia).getISBN());
        } else if (bibliografia instanceof Tesis) {
            viewIndividual.setAreaText(((Tesis) bibliografia).getArea());
        } else if (bibliografia instanceof Revista) {
            viewIndividual.setCategoriaText(((Revista) bibliografia).getCategoria());
            viewIndividual.setEjemplaresText(((Revista) bibliografia).getEjemplares());
        }


        viewIndividual.addCargarButtonListener(new CargarListener(usuarios, bibliografias, viewIndividual, this, true, bibliografia.getId()));
    }


    private class CancelarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AdminView adminView = new AdminView();
            AdminController controller = new AdminController(usuarios, bibliografias, adminView);
            if (viewIndividual != null) {
                viewIndividual.dispose();
            } else {
                viewMasiva.dispose();
            }
        }
    }

    public void setBibliografias(Bibliografia[] bibliografias) {
        this.bibliografias = bibliografias;
    }

}
