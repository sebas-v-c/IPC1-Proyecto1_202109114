package gt.edu.usac.ingenieria.controller.users.listeners;

import gt.edu.usac.ingenieria.controller.users.BibliotecaController;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarVirtualListener implements ActionListener {
    Info info;
    BibliotecaVirtualView view;
    BibliotecaController controller;

    public BuscarVirtualListener(Info info, BibliotecaVirtualView view, BibliotecaController controller) {
        this.info = info;
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int criterio = view.getCampoComboBox();
        String[] columna;
        if (criterio == 0 || criterio == 2 || criterio == 4) {
            columna = view.getValoresColumnas(criterio, true);
        } else {
            columna = view.getValoresColumnas(criterio, false);
        }
        String busqueda = view.getBuscarText();
        int[] ids = new int[0];
//        int[] filasCoincidentes = new int[0];

        int n = 0;
        for (String elemento : columna) {
            if (elemento.equals(busqueda)) {
//                filasCoincidentes = controller.agregarAArreglo(n, filasCoincidentes);
                ids = controller.agregarAArreglo(view.obtenerIdFila(n), ids);
            } else {
                for (String cadena : elemento.split("[,]", 0)) {
                    if (cadena.equals(busqueda)) {
//                        filasCoincidentes = controller.agregarAArreglo(n, filasCoincidentes);
                        ids = controller.agregarAArreglo(view.obtenerIdFila(n), ids);
                    }
                }
            }
            n++;
        }

        view.deleteAllTable();

        if (ids.length == 0) {
            ids = controller.buscarDigitales();
        }
        controller.rellenarTabla(ids);
        view.setBuscarText("");
    }
}
