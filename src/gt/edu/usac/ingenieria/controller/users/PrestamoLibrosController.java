package gt.edu.usac.ingenieria.controller.users;

import gt.edu.usac.ingenieria.controller.users.listeners.BuscarPrestarListener;
import gt.edu.usac.ingenieria.controller.users.listeners.BuscarVirtualListener;
import gt.edu.usac.ingenieria.controller.users.listeners.LogoutListener;
import gt.edu.usac.ingenieria.controller.users.listeners.RegresarListener;
import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;
import gt.edu.usac.ingenieria.view.users.PrestamoLibrosView;
import gt.edu.usac.ingenieria.view.users.VerPrestamosView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrestamoLibrosController {
    private Info info;
    private Usuario usuarioIngresado;
    private Usuario[] usuarios;
    private Bibliografia[] bibliografias;
    private PrestamoLibrosView view;
    private int[] librosNoDigitales;
    private final String[] modelos = {"libro", "revista", "tesis"};
    private String modelo;

    public PrestamoLibrosController(Info info, PrestamoLibrosView view) {
        this.info = info;
        this.usuarios = info.getUsuarios();
        this.usuarioIngresado = info.getUsuarioIngresado();
        this.bibliografias = info.getBibliografias();
        this.view = view;
        this.librosNoDigitales = buscarNoDigitales();
        modelo = modelos[0];
        rellenarTabla(librosNoDigitales, modelo);
        view.addRegresarListener(new RegresarListener(info, view));
        view.addLogoutListener(new LogoutListener(info, view));
        view.setCampoComboBox(modelo);
        view.setTipoComboBox(modelos);
        view.addBuscarListener(new BuscarPrestarListener(info, view, this));
        view.setUsuarioText(usuarioIngresado.getNombre());
        view.addFiltrarListener(new FiltrarListener());
        view.addVerPrestamosListener(new VerPrestamosListener());
    }

    private class VerPrestamosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            VerPrestamosView prestamosView = new VerPrestamosView();
            VerPrestamosController controller = new VerPrestamosController(info, prestamosView);
            prestamosView.setController(controller);
            view.dispose();
        }
    }


    private class FiltrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.deleteAllTable(modelo);
            setModelo(view.getTipoComboBox());
            view.cambiarModelo(modelo);
            rellenarTabla(librosNoDigitales, modelo);
        }
    }


    public int[] buscarNoDigitales() {
        int[] librosNoDigitales = new int[0];

        for (Bibliografia bibliografia : bibliografias) {
            librosNoDigitales = agregarAArreglo(bibliografia.getId(), librosNoDigitales);
        }
        return librosNoDigitales;
    }

    public int[] agregarAArreglo(int libroId, int[] librosDigitales) {
        int n = librosDigitales.length;
        int[] newLibrosDigitales = new int[n + 1];
        // Copiar la array antigua en la nueva
        System.arraycopy(librosDigitales, 0, newLibrosDigitales, 0, n);
        newLibrosDigitales[n] = libroId;
        return newLibrosDigitales;
    }

    public void rellenarTabla(int[] librosEncontrados, String modelo) {
        Object[] fila = new Object[12];
        view.cambiarModelo(modelo);
        for (int libroEncontrado : librosEncontrados) {
            Bibliografia bibliografia = bibliografias[0];
            for (Bibliografia value : bibliografias) {
                if (value.getId() == libroEncontrado) {
                    bibliografia = value;
                    break;
                }
            }
            if (modelo.equals("libro") && bibliografia instanceof Libro) {
                String palabrasClave = String.join(",", bibliografia.getPalabrasClave());
                String temas = String.join(",", bibliografia.getTemas());
                fila[0] = bibliografia.getId();
                fila[1] = bibliografia.getAutor();
                fila[2] = bibliografia.getAnio();
                fila[3] = bibliografia.getTitulo();
                fila[4] = ((Libro)bibliografia).getISBN();
                fila[5] = bibliografia.getEdicion();
                fila[6] = palabrasClave;
                fila[7] = bibliografia.getDescripcion();
                fila[8] = temas;
                fila[9] = bibliografia.getDisponibles();
                fila[10] = "Agregar Libro";
                view.agregarFila(fila, modelo);
            } else if (modelo.equals("revista")&& bibliografia instanceof Revista) {
                String palabrasClave = String.join(",", bibliografia.getPalabrasClave());
                String temas = String.join(",", bibliografia.getTemas());
                fila[0] = bibliografia.getId();
                fila[1] = bibliografia.getAutor();
                fila[2] = bibliografia.getAnio();
                fila[3] = bibliografia.getTitulo();
                fila[4] = bibliografia.getEdicion();
                fila[5] = palabrasClave;
                fila[6] = ((Revista)bibliografia).getCategoria();
                fila[7] = bibliografia.getDescripcion();
                fila[8] = temas;
                fila[9] = ((Revista) bibliografia).getEjemplares();
                fila[10] = bibliografia.getDisponibles();
                fila[11] = "Agregar Revista";
                view.agregarFila(fila, modelo);
            } else if (modelo.equals("tesis")&& bibliografia instanceof Tesis){
                String palabrasClave = String.join(",", bibliografia.getPalabrasClave());
                String temas = String.join(",", bibliografia.getTemas());
                fila[0] = bibliografia.getId();
                fila[1] = bibliografia.getAutor();
                fila[2] = bibliografia.getAnio();
                fila[3] = bibliografia.getTitulo();
                fila[4] = bibliografia.getEdicion();
                fila[5] = palabrasClave;
                fila[6] = bibliografia.getDescripcion();
                fila[7] = temas;
                fila[8] = ((Tesis)bibliografia).getArea();
                fila[9] = bibliografia.getDisponibles();
                fila[10] = "Agregar Tesis";
                view.agregarFila(fila, modelo);
            }

        }
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(int i) {
        this.modelo = modelos[i];
        this.view.setCampoComboBox(modelo);
    }

    public void setLibroSeleccionado(Object valueAt) {
        int id = Integer.parseInt(valueAt.toString());
        boolean existente = false;
        boolean noHay = false;

        for (int j = 0; j < bibliografias.length; j++) {
            int in = bibliografias[j].getDisponibles();
            System.out.println(in);
            if (bibliografias[j].getDisponibles() <= 0 && bibliografias[j].getId() == id) {
                view.mostrarMensaje("Ya no hay existencias de este libro");
                noHay = true;
                break;
            } else if (bibliografias[j].getId() == id) {
                this.bibliografias[j].setDisponibles(bibliografias[j].getDisponibles() -1 );
                info.setBibliografias(bibliografias);
            }
        }

        for (int i = 0; i < usuarioIngresado.getLibrosPrestados().length; i++) {
            if (usuarioIngresado.getLibrosPrestados()[i] == id) {
                view.mostrarMensaje("El libro ya está agregado en su biblioteca virtual!");
                existente = true;
                break;
            } else if (noHay) {
                break;
            }
        }
        if (!noHay) {
            if (!existente) {
                usuarioIngresado.agregarLibro(id);
                info.setUsuarioIngresado(usuarioIngresado);
                view.mostrarMensaje("El libro se ha agregado con éxito");
            }
        }
    }
}
