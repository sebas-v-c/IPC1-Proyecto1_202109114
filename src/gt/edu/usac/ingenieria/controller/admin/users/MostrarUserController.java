package gt.edu.usac.ingenieria.controller.admin.users;

import gt.edu.usac.ingenieria.controller.admin.AdminController;
import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.AdminView;
import gt.edu.usac.ingenieria.view.MostrarUserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarUserController {

    private Usuario[] usuarios;
    private MostrarUserView view;
    private Bibliografia[] bibliografias;

    public MostrarUserController(Usuario[] usuarios, Bibliografia[] bibliografias,MostrarUserView view) {
        this.usuarios = usuarios;
        this.view = view;
        rellenarTabla();
        view.addRegresarListener(new RegresarListener());
    }

    private void rellenarTabla(){
        Object[] entrada = new Object[7];
        int num = 0;
        try {
            for (int i = 1; i < usuarios.length; i++) {
                if (usuarios[i] != null) {
                    entrada[0] = num;
                    entrada[1] = usuarios[i].getId();
                    entrada[2] = usuarios[i].getNombre();
                    entrada[3] = usuarios[i].getApellido();
                    entrada[4] = usuarios[i].getUser();
                    entrada[5] = usuarios[i].getRol();
                    entrada[6] = usuarios[i].getPassword();
                    view.agregarUsuarioTabla(entrada);
                    num++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RegresarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AdminView adminView = new AdminView();
            AdminController controller = new AdminController(usuarios, bibliografias, adminView);
            view.dispose();
        }
    }
}
