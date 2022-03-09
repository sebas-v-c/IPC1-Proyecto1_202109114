package gt.edu.usac.ingenieria.controller.users.listeners;

import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.users.PrestamoLibrosView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ReporteExistenciasListener implements ActionListener {
    Info info;
    Usuario[] usuarios;
    Bibliografia[] bibliografias;
    PrestamoLibrosView view;

    public ReporteExistenciasListener(Info info, PrestamoLibrosView view) {
        this.info = info;
        this.usuarios = info.getUsuarios();
        this.bibliografias = info.getBibliografias();
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String directorioActual = System.getProperty("user.dir");
        File nuevoDirectorio = new File(directorioActual + "/reportes");
        if (!nuevoDirectorio.isDirectory()  && !nuevoDirectorio.exists()) {
            try {
                nuevoDirectorio.mkdir();
            } catch (Exception e) {
                view.mostrarMensaje("Ha habido un error creando el archivo");
            }
        }

        File reporte = new File(directorioActual + "/reportes/reporteExistencias.html");
        reporte.delete();
        FileWriter escribir;
        PrintWriter nuevaLinea;

        try {
            reporte.createNewFile();
            escribir = new FileWriter(reporte, true);
            nuevaLinea = new PrintWriter(escribir);
            nuevaLinea.println(
                    """
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <style>
                                    table, th, td {
                                        border: 1px solid black;
                                    }
                                </style>
                                <meta charset="UTF-8">
                                <title>Reporte Existencias</title>
                            </head>
                            <body>
                            """
            );
            nuevaLinea.println("<h1>Reporte de Libros en Existencia</h1>");

            nuevaLinea.println("<table>");
            String tabla = crearTabla();
            nuevaLinea.println(tabla);
            nuevaLinea.println("</table>");
            //cerrar etiquetas
            nuevaLinea.println("</body>\n" + "</html>");
            escribir.close();
            view.mostrarMensaje("El reporte se ha creado con éxito");
        } catch (Exception e) {
            view.mostrarMensaje("Ha habido un error escribiendo en el archivo");
        }
    }

    private String crearTabla() {
        StringBuilder tabla = new StringBuilder();
        tabla.append("""
                <tr>
                    <th>Tipo De Bibliografía</th>
                    <th>Titulo</th>
                    <th>Palabras Clave</th>
                    <th>Temas</th>
                    <th>Cantidad Disponible</th>
                </tr>""");

        for (Bibliografia bibliografia : bibliografias) {
            if (bibliografia.getDisponibles() == 0 || bibliografia instanceof LibroDigital) {
                continue;
            }
                tabla.append("<tr>");
                if (bibliografia instanceof Libro) {
                    tabla.append("<td>").append("Libro").append("</td>");
                } else if (bibliografia instanceof Revista) {
                    tabla.append("<td>").append("Revista").append("</td>");
                } else if (bibliografia instanceof Tesis) {
                    tabla.append("<td>").append("Tesis").append("</td>");
                } else if (bibliografia instanceof LibroDigital) {
                    tabla.append("<td>").append("Libro Digital").append("</td>");
                }
                tabla.append("<td>").append(bibliografia.getTitulo()).append("</td>");
            tabla.append("<td>").append(String.join("</br>",bibliografia.getPalabrasClave())).append("</td>");
            tabla.append("<td>").append(String.join(",",bibliografia.getTemas())).append("</td>");
            if (bibliografia instanceof LibroDigital) {
                    tabla.append("<td>").append("&infin;").append("</td>");
                } else {
                    tabla.append("<td>").append(bibliografia.getDisponibles()).append("</td>");
                }
                tabla.append("</tr>");
        }
        return tabla.toString();
    }
}
