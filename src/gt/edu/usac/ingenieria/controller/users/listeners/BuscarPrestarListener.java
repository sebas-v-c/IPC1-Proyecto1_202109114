package gt.edu.usac.ingenieria.controller.users.listeners;

import gt.edu.usac.ingenieria.controller.users.BibliotecaController;
import gt.edu.usac.ingenieria.controller.users.PrestamoLibrosController;
import gt.edu.usac.ingenieria.model.Info;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.users.BibliotecaVirtualView;
import gt.edu.usac.ingenieria.view.users.PrestamoLibrosView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarPrestarListener implements ActionListener {
    Info info;
    PrestamoLibrosView view;
    PrestamoLibrosController controller;
    Usuario usuarioIngresado;

    public BuscarPrestarListener(Info info, PrestamoLibrosView view, PrestamoLibrosController controller) {
        this.info = info;
        this.view = view;
        this.usuarioIngresado = info.getUsuarioIngresado();
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int modeloIndice = view.getTipoComboBox();
        int criterio = view.getCampoComboBox();
        String modelo = controller.getModelo();
//        controller.setModelo(modeloIndice);
        String[] columna;

        if (modelo.equals("libro") &&
                (criterio == 0 || criterio == 2 || criterio == 4 || criterio ==5 || criterio == 9)) {
            columna = view.getValoresColumnas(criterio, true);
            System.out.println("Libro numerico");
//            columna = verificarCriterios(new int[]{0, 2, 4, 5, 9}, criterio);
        } else if (modelo.equals("revista") &&
                (criterio ==  0 || criterio==2 || criterio==4 || criterio==9 || criterio==10)) {
            columna = view.getValoresColumnas(criterio, true);
            System.out.println("revista numerico");
//            columna = verificarCriterios(new int[]{0, 2, 4, 9, 10}, criterio);
        } else if (modelo.equals("tesis") &&
                (criterio == 0 || criterio == 2 || criterio == 4 || criterio == 9)) {
            columna = view.getValoresColumnas(criterio, true);
            System.out.println("tesis numerico");
//            columna = verificarCriterios(new int[]{0, 2, 4, 9}, criterio);
        } else {
            columna = view.getValoresColumnas(criterio, false);
            System.out.println("nada numerico");
        }
        for (String element :
                columna) {
            System.out.println(element);
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


        view.deleteAllTable(modelo);
        if (ids.length == 0) {
            ids = controller.buscarNoDigitales();
        }
        controller.rellenarTabla(ids, controller.getModelo());
        view.setBuscarText("");
    }

    private String[] verificarCriterios(int[] indiceDatosNum, int criterio) {
        String[] columna = new String[view.getRowCount()];
        for (int indice : indiceDatosNum) {
            if (indice == criterio) {
                columna = view.getValoresColumnas(criterio, true);
                return columna;
            }
        }
        columna = view.getValoresColumnas(criterio, false);
        return columna;
    }


}
