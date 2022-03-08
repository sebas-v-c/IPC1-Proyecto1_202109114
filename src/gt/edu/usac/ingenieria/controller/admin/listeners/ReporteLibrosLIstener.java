package gt.edu.usac.ingenieria.controller.admin.listeners;

import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.admin.AdminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ReporteLibrosLIstener implements ActionListener {
    private Usuario[] usuarios;
    private Bibliografia[] bibliografias;
    private AdminView view;
    public ReporteLibrosLIstener(Usuario[] usuarios, Bibliografia[] bibliografias, AdminView view) {
        this.usuarios = usuarios;
        this.bibliografias = bibliografias;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String cwd = System.getProperty("user.dir");
        File dir = new File(cwd + "/reportes");
        if (!dir.exists() && !dir.isDirectory()) {
            try {
                dir.mkdir();
            } catch (Exception e) {
                view.mostrarMensaje("Ha habido un error creando el archivo");
            }
        }

        File reporte = new File(cwd + "/reportes/reporteLibros.html");
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
                                <title>Reporte Libros</title>
                            </head>
                            <body>
                            """
            );
            nuevaLinea.println("<h1>Reporte de Libros</h1>");

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
                    <th>Cantidad Disponible</th>
                    <th>Copias Totales</th>
                    <th>A quien se prestó</th>
                </tr>""");

        for (Usuario usuario : usuarios) {
            // Verifico que no se muestre el usuario admin
            if (usuario.getId() == 1) {
                continue;
            }
            if (usuario.getLibrosPrestados().length != 0) {
                for (int id : usuario.getLibrosPrestados()) {
                    for (Bibliografia bibliografia : bibliografias) {
                        if (bibliografia.getId() == id) {
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
                            if (bibliografia instanceof LibroDigital) {
                                tabla.append("<td>").append("&infin;").append("</td>");
                                tabla.append("<td>").append("&infin;").append("</td>");
                            } else {
                                tabla.append("<td>").append(bibliografia.getDisponibles()).append("</td>");
                                tabla.append("<td>").append(bibliografia.getCopias()).append("</td>");
                            }
                            tabla.append("<td>").append(usuario.getUser()).append("</td>");
                            tabla.append("</tr>");
                        }
                    }
                }
            }
        }

        return tabla.toString();
    }
}
