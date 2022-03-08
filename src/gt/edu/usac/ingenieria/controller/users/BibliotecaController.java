package gt.edu.usac.ingenieria.controller.users;

import gt.edu.usac.ingenieria.controller.users.listeners.BuscarVirtualListener;
import gt.edu.usac.ingenieria.controller.users.listeners.LogoutListener;
import gt.edu.usac.ingenieria.controller.users.listeners.RegresarListener;
import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.model.LibroDigital;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;

public class BibliotecaController {
    Info info;
    Usuario usuarioIngresado;
    Usuario[] usuarios;
    Bibliografia[] bibliografias;
    BibliotecaVirtualView view;
    int[] librosDigitales;

    public BibliotecaController(Info info, BibliotecaVirtualView view) {
        this.info = info;
        this.usuarios = info.getUsuarios();
        this.usuarioIngresado = info.getUsuarioIngresado();
        this.bibliografias = info.getBibliografias();
        this.view = view;
        this.librosDigitales = buscarDigitales();
        rellenarTabla(librosDigitales);
        view.addRegresarListener(new RegresarListener(info, view));
        view.addLogoutListener(new LogoutListener(info, view));
        view.setRolComboBox(new String[] {"ID", "Autor", "Año", "Título", "Edición","Palabras Clave",
                "Descripción", "Temas"});
        view.addBuscarListener(new BuscarVirtualListener(info, view, this));
        view.setUsuarioText(usuarioIngresado.getNombre());
    }

    public int[] buscarDigitales() {
        int[] librosDigitales = new int[0];

        for (Bibliografia bibliografia : bibliografias) {
            if (bibliografia instanceof LibroDigital) {
                librosDigitales = agregarAArreglo(bibliografia.getId(), librosDigitales);
            }
        }
        return librosDigitales;
    }

    public void rellenarTabla(int[] librosEncontrados) {
        Object[] fila = new Object[9];
        for (int libroEncontrado : librosEncontrados) {
            Bibliografia bibliografia = bibliografias[0];
            for (Bibliografia value : bibliografias) {
                if (value.getId() == libroEncontrado) {
                    bibliografia = value;
                    break;
                }
            }
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
            fila[8] = "Agregar Libro";

            view.agregarFila(fila);
        }
    }

    public int[] agregarAArreglo(int libroId, int[] librosDigitales) {
        int n = librosDigitales.length;
        int[] newLibrosDigitales = new int[n + 1];
        // Copiar la array antigua en la nueva
        System.arraycopy(librosDigitales, 0, newLibrosDigitales, 0, n);
        newLibrosDigitales[n] = libroId;
        return newLibrosDigitales;
    }

    public void setLibroSeleccionado(Object valueAt) {
        int id = Integer.parseInt(valueAt.toString());
        boolean existente = false;
        for (int i = 0; i < usuarioIngresado.getLibrosPrestados().length; i++) {
            if (usuarioIngresado.getLibrosPrestados()[i] == id) {
                view.mostrarMensaje("El libro ya está agregado en su biblioteca virtual!");
                existente = true;
                break;
            }
        }
        if (!existente) {
            usuarioIngresado.agregarLibro(id);
            info.setUsuarioIngresado(usuarioIngresado);
            view.mostrarMensaje("El libro se ha agregado con éxito");
        }
    }
}
