package gt.edu.usac.ingenieria.controller;

import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.CrearUserView;

public class CrearUserController {
    private String[] roles = {"Estudiante", "Catedrático"};
    private Usuario[] usuarios;
    private CrearUserView view;

    public CrearUserController(Usuario[] usuarios, CrearUserView view) {
        this.usuarios = usuarios;
        this.view = view;
//        view.addCancelarListener(new CancelarListener());
//        view.addCrearListener(new CrearUserListener());
    }

}
