package gt.edu.usac.ingenieria.controller.admin.listeners;

import gt.edu.usac.ingenieria.model.Bibliografia;
import gt.edu.usac.ingenieria.model.Usuario;
import gt.edu.usac.ingenieria.view.admin.AdminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ReporteUsersListener implements ActionListener {
    Usuario[] usuarios;
    Bibliografia[] bibliografias;
    AdminView view;
    public ReporteUsersListener(Usuario[] usuarios, Bibliografia[] bibliografias, AdminView view) {
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

        File reporte = new File(cwd + "/reportes/reporteUsuario.html");
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
                                <title>Reporte Usuario</title>
                            </head>
                            <body>
                            """
            );
            nuevaLinea.println("<h1>Reporte de Usuarios</h1>");

            nuevaLinea.println("<table>");
            String tabla =crearTabla();
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
                    <th>Usuario</th>
                    <th>Rol</th>
                    <th>ID</th>
                    <th>Cantidad Libros Prestados</th>
                    <th>Titulos</th>
                </tr>""");

        for (Usuario usuario : usuarios) {
            // Verifico que no se muestre el usuario admin
            if (usuario.getId() == 1) {
                continue;
            }
            tabla.append("<tr>");

            if (usuario.getLibrosPrestados().length != 0) {
                infoBasica(tabla, usuario);
                tabla.append("<td>");
                for (int id : usuario.getLibrosPrestados()) {
                    for (Bibliografia bibliografia : bibliografias) {
                        if (bibliografia.getId() == id) {
                            tabla.append(bibliografia.getTitulo()).append("</br>");
                        }
                    }
                }
                tabla.append("</td>");
            }
//            } else {
//                infoBasica(tabla, usuario);
//                tabla.append("<td>").append(" ").append("</td>");
//            }
            tabla.append("</tr>");
        }
        return tabla.toString();
    }

    private void infoBasica(StringBuilder tabla, Usuario usuario) {
        tabla.append("<td>").append(usuario.getUser()).append("</td>");
        tabla.append("<td>").append(usuario.getRol()).append("</td>");
        tabla.append("<td>").append(usuario.getId()).append("</td>");

        tabla.append("<td>").append(usuario.getLibrosPrestados().length).append("</td>");
    }
}
