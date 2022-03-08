package gt.edu.usac.ingenieria.controller.users.listeners;

import gt.edu.usac.ingenieria.controller.LoginController;
import gt.edu.usac.ingenieria.controller.users.UserController;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.view.LoginView;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;
import gt.edu.usac.ingenieria.view.users.PrestamoLibrosView;
import gt.edu.usac.ingenieria.view.users.UserView;
import gt.edu.usac.ingenieria.view.users.VerPrestamosView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener {
    Info info;
    BibliotecaVirtualView virtualView;
    PrestamoLibrosView prestamoView;
    VerPrestamosView verView;

    public LogoutListener(Info info, BibliotecaVirtualView virtualView) {
        this.info = info;
        this.virtualView = virtualView;
    }

    public LogoutListener(Info info, PrestamoLibrosView prestamoView) {
        this.info = info;
        this.prestamoView = prestamoView;
    }

    public LogoutListener(Info info, VerPrestamosView view) {
        this.info = info;
        this.verView = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        LoginView loginView= new LoginView();
        LoginController controller = new LoginController(loginView, info.getUsuarios(), info.getBibliografias());
        if (virtualView != null) {
            virtualView.dispose();
        } else if (prestamoView != null) {
            prestamoView.dispose();
        } else {
            verView.dispose();
        }
    }
}
