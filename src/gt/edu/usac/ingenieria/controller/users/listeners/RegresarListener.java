package gt.edu.usac.ingenieria.controller.users.listeners;

import gt.edu.usac.ingenieria.controller.users.BibliotecaController;
import gt.edu.usac.ingenieria.controller.users.PrestamoLibrosController;
import gt.edu.usac.ingenieria.controller.users.UserController;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;
import gt.edu.usac.ingenieria.view.users.PrestamoLibrosView;
import gt.edu.usac.ingenieria.view.users.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegresarListener implements ActionListener {
    Info info;
    BibliotecaVirtualView virtualView;
    PrestamoLibrosView prestamoView;

    public RegresarListener(Info info, BibliotecaVirtualView virtualView) {
        this.info = info;
        this.virtualView = virtualView;
    }

    public RegresarListener(Info info, PrestamoLibrosView prestamoView) {
        this.info = info;
        this.prestamoView = prestamoView;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        UserView userView = new UserView();
        UserController controller = new UserController(info, userView);
        if (virtualView != null) {
            virtualView.dispose();
        } else {
            prestamoView.dispose();
        }
    }
}
