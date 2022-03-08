package gt.edu.usac.ingenieria.controller.users;

import gt.edu.usac.ingenieria.controller.PantallaInicioController;
import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.model.LibroDigital;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.PantallaInicioView;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;
import gt.edu.usac.ingenieria.view.users.PrestamoLibrosView;
import gt.edu.usac.ingenieria.view.users.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    private Info info;
    private Usuario[] usuarios;
    private UserView view;
    private Usuario usuarioLogueado;
    private Bibliografia[] bibliografias;

    public UserController(Info info, UserView view) {
        this.info =info;
        this.usuarios = info.getUsuarios();
        this.view = view;
        this.usuarioLogueado = info.getUsuarioIngresado();
        this.bibliografias = info.getBibliografias();
        view.addLogoutListener(new LogoutListener());
        view.addBilbiotecaListener(new BibliotecaListener());
        view.addPrestamoListener(new PrestamoListener());
        System.out.println(usuarioLogueado.getLibrosPrestados().length);
        view.setUserInfoLabel("<html>Bienvenido "+ usuarioLogueado.getUser() + "!<br/>" +
                "Nombre completo: "+ usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido() + "<br/>" +
                "Tienes actualmente " + usuarioLogueado.getLibrosPrestados().length + " libros en su libreria");
    }

    public int[] buscarDigitales(int[] libros) {
        int[] librosDigitales = new int[0];

        int n = 0;
        for (Bibliografia bibliografia : bibliografias) {
            try {
                if (bibliografia.getId() == libros[n] && (bibliografia instanceof LibroDigital)) {
                    librosDigitales = agregarAArreglo(bibliografia.getId(), librosDigitales);
                    n++;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return librosDigitales;
    }

    public int[] buscarNoDigitales(int[] libros) {
        int[] librosNoDigitales = new int[0];

        int n = 0;
        for (Bibliografia bibliografia : bibliografias) {
            try {
                if (bibliografia.getId() == libros[n] && !(bibliografia instanceof LibroDigital)) {
                    librosNoDigitales = agregarAArreglo(bibliografia.getId(), librosNoDigitales);
                    n++;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {}
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

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PantallaInicioView pantallaInicioView = new PantallaInicioView();
            PantallaInicioController controller = new PantallaInicioController(usuarios, bibliografias, pantallaInicioView);
            view.dispose();

        }
    }

    private class PrestamoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PrestamoLibrosView librosView = new PrestamoLibrosView();
            PrestamoLibrosController controller = new PrestamoLibrosController(info, librosView);
            librosView.setController(controller);
            view.dispose();
        }
    }

    private class BibliotecaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            BibliotecaVirtualView bibliotecaView = new BibliotecaVirtualView();
            BibliotecaController controller = new BibliotecaController(info, bibliotecaView);
            bibliotecaView.setController(controller);
            view.dispose();
        }
    }
}
