package gt.edu.usac.ingenieria.controller.admin.bibliografias;

import gt.edu.usac.ingenieria.model.*;
import gt.edu.usac.ingenieria.view.admin.CrearBibliografiaIndiviView;
import gt.edu.usac.ingenieria.view.admin.CrearBibliografiaMasivaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CargarListener implements ActionListener {
    Usuario[] usuarios;
    Bibliografia[] bibliografias;
    CrearBibliografiaIndiviView indiviView;
    CrearBibliografiaMasivaView masivaView;
    CrearBibliografiaController controller;
    boolean actualizar;
    int id;

    public CargarListener(Usuario[] usuarios, Bibliografia[] bibliografias, CrearBibliografiaIndiviView indiviView, CrearBibliografiaController controller, boolean actualizar, int id) {
        this.usuarios = usuarios;
        this.bibliografias = bibliografias;
        this.indiviView = indiviView;
        this.controller = controller;
        this.actualizar = actualizar;
        this.id = id;
    }

    public CargarListener(Usuario[] usuarios, Bibliografia[] bibliografias, CrearBibliografiaMasivaView masivaView, CrearBibliografiaController controller) {
        this.usuarios = usuarios;
        this.bibliografias = bibliografias;
        this.masivaView = masivaView;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (indiviView != null) {
            CargaIndividual cargaIndiviual = new CargaIndividual();
            cargaIndiviual.cargar();
        } else {
            CargaMasiva cargaMasiva = new CargaMasiva();
            cargaMasiva.cargar();
        }
    }

    private class CargaIndividual {
        public void cargar() {
            String opcion = indiviView.getTipoComboBox();
            switch (opcion) {
                case "Libro" -> cargarLibro();
                case "Revista" -> cargarRevista();
                case "Tesis" -> cargarTesis();
                case "Libro Digital" -> cargarLibroDigital();
            }
        }

        private void cargarLibroDigital() {
            String autor = indiviView.getAutorTextField();
            int anio = -1;
            String titulo = indiviView.getTituloTextField();
            int edicion = -1;
            String palabrasClave = indiviView.getPalabrasCTextField();
            String descripcion = indiviView.getDescripcionTextField();
            String temas = indiviView.getTemasTextField();
            int tamanio = -1;

            try {
                anio = Integer.parseInt(indiviView.getAnioTextField());
                edicion = Integer.parseInt(indiviView.getEdicionTextField());
                tamanio = Integer.parseInt(indiviView.getTamanioTextField());
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
                e.printStackTrace();
            }

            try {
                verificarNumericos(new int[] {anio, edicion, tamanio},
                        new String[] {"Año", "Edición", "Tamaño"});
                verificarParametros(new String[] {autor, titulo, temas, descripcion, palabrasClave},
                        new String[] {"Autor", "Título", "Temas", "Descripción", "Palabras Clave"});

                if (existElement(titulo, edicion, autor) || actualizar) {
                    if (actualizar) {
                        for (Bibliografia bibliografia : bibliografias) {
                            if (bibliografia.getId() == id) {
                                System.out.println(bibliografia.getId());
                                bibliografia.setAutor(autor);
                                bibliografia.setAnio(anio);
                                bibliografia.setTitulo(titulo);
                                bibliografia.setPalabrasClave(palabrasClave.split("[,]", 0));
                                bibliografia.setDescripcion(descripcion);
                                bibliografia.setTemas(temas.split("[,]", 0));
                                bibliografia.setEdicion(edicion);
                                ((LibroDigital)bibliografia).setTamanio(tamanio);
                                break;
                            }
                        }
                    } else {
                        LibroDigital nuevoLibroDigital = new LibroDigital(
                                autor, anio, titulo, palabrasClave.split("[,]", 0),
                                descripcion, temas.split("[,]", 0),
                                edicion, tamanio
                        );
                        bibliografias = addElement(bibliografias.length, bibliografias, nuevoLibroDigital);
                    }

//                    for (String tema : temas.split("[,]", 0)) {
//                        System.out.println(tema);
//                    }

                    controller.setBibliografias(bibliografias);
                    indiviView.mostrarMensaje("Bibliografía creada con éxito");
                    indiviView.limpiarCampos();
                } else {
                    indiviView.mostrarMensaje("El título y la edición del libro ya existe");
                    indiviView.limpiarCampos();
                }
            } catch (ParametroException e) {
                e.printStackTrace();
                indiviView.mostrarMensaje(e.mensaje);
            }
        }

        private void cargarTesis() {
            String autor = indiviView.getAutorTextField();
            int anio = -1;
            String titulo = indiviView.getTituloTextField();
            String palabrasClave = indiviView.getPalabrasCTextField();
            String area = indiviView.getAreaTextField();
            String temas = indiviView.getTemasTextField();
            String descripcion = indiviView.getDescripcionTextField();
            int edicion = -1;
            int copias = -1;

            try {
                anio = Integer.parseInt(indiviView.getAnioTextField());
                edicion = Integer.parseInt(indiviView.getEdicionTextField());
                copias = Integer.parseInt(indiviView.getCopiasTextField());
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
                e.printStackTrace();
            }

            try {
                verificarNumericos(new int[] {anio, edicion, copias},
                        new String[] {"Año", "Edición", "Copias"});
                verificarParametros(new String[] {autor, titulo, area, temas, descripcion, palabrasClave},
                        new String[] {"Autor", "Título", "Área", "Temas", "Descripción", "Palabras Clave"});

                if (existElement(titulo, edicion, autor) || actualizar) {
                    if (actualizar) {
                        for (Bibliografia bibliografia : bibliografias) {
                            if (bibliografia.getId() == id) {
                                bibliografia.setAutor(autor);
                                bibliografia.setAnio(anio);
                                bibliografia.setTitulo(titulo);
                                bibliografia.setPalabrasClave(palabrasClave.split("[,]", 0));
                                bibliografia.setDescripcion(descripcion);
                                bibliografia.setTemas(temas.split("[,]", 0));
                                bibliografia.setEdicion(edicion);
                                bibliografia.setCopias(copias);
                                ((Tesis) bibliografia).setArea(area);
                                break;
                            }
                        }
                    } else {
                        Tesis nuevaTesis = new Tesis(
                                autor, anio, titulo, palabrasClave.split("[,]", 0),
                                descripcion, temas.split("[,]", 0),
                                copias, area, edicion
                        );
                    bibliografias = addElement(bibliografias.length, bibliografias, nuevaTesis);
                    }

                    controller.setBibliografias(bibliografias);
                    indiviView.mostrarMensaje("Bibliografía creada con éxito");
                    indiviView.limpiarCampos();
                } else {
                    indiviView.mostrarMensaje("El título y la edición del libro ya existe");
                    indiviView.limpiarCampos();
                }
            } catch (ParametroException e) {
                e.printStackTrace();
                indiviView.mostrarMensaje(e.mensaje);
            }
        }

        private void cargarRevista() {
            String autor = indiviView.getAutorTextField();
            int anio = -1;
            String titulo = indiviView.getTituloTextField();
            int edicion = -1;
            String descripcion = indiviView.getDescripcionTextField();
            String categoria = indiviView.getCategoriaTextField();
            int ejemplares = -1;
            String temas = indiviView.getTemasTextField();
            String palabrasClave = indiviView.getPalabrasCTextField();
            int copias = -1;

            try {
                anio = Integer.parseInt(indiviView.getAnioTextField());
                edicion = Integer.parseInt(indiviView.getEdicionTextField());
                ejemplares = Integer.parseInt(indiviView.getEjemplaresTextField());
                copias = Integer.parseInt(indiviView.getCopiasTextField());
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
                e.printStackTrace();
            }

            try {
                verificarNumericos(new int[] {anio, edicion, ejemplares, copias},
                        new String[] {"Año", "Edición", "Ejemplares", "Copias"});
                verificarParametros(new String[] {autor, titulo, descripcion, categoria, temas, palabrasClave},
                        new String[] {"Autor", "Título", "Descripción", "Categoría",  "Temas", "Palabras Clave"});

                if (existElement(titulo, edicion, autor) || actualizar) {
                    System.out.println(id);
                    if (actualizar) {
                        for (Bibliografia bibliografia : bibliografias) {
                            if (bibliografia.getId() == id) {
                                System.out.println(bibliografia.getId());
                                bibliografia.setAutor(autor);
                                bibliografia.setAnio(anio);
                                bibliografia.setTitulo(titulo);
                                bibliografia.setPalabrasClave(palabrasClave.split("[,]", 0));
                                bibliografia.setDescripcion(descripcion);
                                bibliografia.setTemas(temas.split("[,]", 0));
                                bibliografia.setEdicion(edicion);
                                bibliografia.setCopias(copias);
                                ((Revista) bibliografia).setCategoria(categoria);
                                ((Revista) bibliografia).setEjemplares(ejemplares);
                                break;
                            }
                        }
                    } else {
                        Revista nuevaRevista = new Revista(
                                autor, anio, titulo, palabrasClave.split("[,]", 0),
                                descripcion, temas.split("[,]", 0),
                                copias, categoria, ejemplares, edicion
                        );
                        bibliografias = addElement(bibliografias.length, bibliografias, nuevaRevista);
                    }
                    controller.setBibliografias(bibliografias);
                    indiviView.mostrarMensaje("Bibliografía creada con éxito");
                } else {
                    indiviView.mostrarMensaje("El título y la edición del libro ya existe");
                }
                indiviView.limpiarCampos();
            } catch (ParametroException e) {
                e.printStackTrace();
                indiviView.mostrarMensaje(e.mensaje);
            }
        }

        private void cargarLibro() {
            String autor = indiviView.getAutorTextField();
            int anio = -1;
            int isbn = -1;
            String titulo = indiviView.getTituloTextField();
            int edicion = -1;
            String palabrasClave = indiviView.getPalabrasCTextField();
            String descripcion = indiviView.getDescripcionTextField();
            String temas = indiviView.getTemasTextField();
            int copias = -1;

            try {
                anio = Integer.parseInt(indiviView.getAnioTextField());
                isbn = Integer.parseInt(indiviView.getIsbnTextField());
                edicion = Integer.parseInt(indiviView.getEdicionTextField());
                copias = Integer.parseInt(indiviView.getCopiasTextField());
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
                e.printStackTrace();
            }

            try {
                verificarNumericos(new int[] {anio, isbn, edicion, copias},
                        new String[] {"Año", "ISBN", "Edición", "Copias"});
                verificarParametros(new String[] {autor, titulo, palabrasClave, descripcion, temas},
                        new String[] {"Autor", "Título", "Palabras Clave", "Descripción", "Temas"});

                if (!elementExist(isbn, titulo, edicion)  || actualizar) {
                    if (actualizar) {
                        for (Bibliografia bibliografia : bibliografias) {
                            if (bibliografia.getId() == id) {
                                bibliografia.setAutor(autor);
                                bibliografia.setAnio(anio);
                                bibliografia.setTitulo(titulo);
                                bibliografia.setPalabrasClave(palabrasClave.split("[,]", 0));
                                bibliografia.setDescripcion(descripcion);
                                bibliografia.setTemas(temas.split("[,]", 0));
                                bibliografia.setEdicion(edicion);
                                bibliografia.setCopias(copias);
                                ((Libro) bibliografia).setISBN(isbn);
                                break;
                            }
                        }
                    } else {
                        Libro nuevoLibro = new Libro(autor,
                                anio, titulo, palabrasClave.split("[,]", 0),
                                descripcion, temas.split("[,]", 0), copias, isbn,
                                edicion);
                        bibliografias = addElement(bibliografias.length, bibliografias, nuevoLibro);
                    }
                    controller.setBibliografias(bibliografias);
                    indiviView.mostrarMensaje("Bibliografía creada con éxito");
                    indiviView.limpiarCampos();
                } else {
                    indiviView.mostrarMensaje("El ISBN y/o el Titulo del libro ya existe");
                    indiviView.limpiarCampos();
                }
            } catch (ParametroException e) {
                e.printStackTrace();
                indiviView.mostrarMensaje(e.mensaje);
            }
        }

        private boolean existElement(String titulo, int edicion, String autor) {
            for (Bibliografia bibliografia : bibliografias) {
                if (bibliografia instanceof Revista) {
                    if (bibliografia.getTitulo().equals(titulo) &&
                            bibliografia.getEdicion() == edicion && bibliografia.getAutor().equals(autor)) {
                        return false;
                    }
                }
            }
            return true;
        }
        private boolean elementExist(int isbn, String titulo, int edicion) {
            for (Bibliografia bibliografia : bibliografias) {
                if (bibliografia instanceof Libro) {
                    if (((Libro) bibliografia).getISBN() == isbn ||
                            (bibliografia.getTitulo().equals(titulo) && (bibliografia.getEdicion() == edicion))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    private class CargaMasiva {
        private void cargar() {
            String[] lineas = masivaView.getAreaText();
            try {
                for (int i = 0; i < lineas.length; i++) {
                    String[] lineaActual = lineas[i].split("[;]", 0);
                    if (lineaActual.length != 14) {
                        throw new ParametroException("Faltan o hay demasiados parametros en la fila " + i+1 + ", favor revisar");
                    }
                    if (!((lineaActual[0].equals("0")) || (lineaActual[0].equals("1")) || (lineaActual[0].equals("2")))) {
                        throw new ParametroException("Se debe de seleccionar un tipo de bibliografia valida");
                    }

                    switch (lineaActual[0]) {
                        case "0" -> cargarLibro(lineaActual, i+1);
                        case "1" -> cargarRevista(lineaActual, i+1);
                        case "2" -> cargarTesis(lineaActual, i+1);
                    }
                }
                masivaView.mostrarMensaje("Las entradas se han agregado :)");
                controller.setBibliografias(bibliografias);
                masivaView.setAreaText("");
            } catch (ParametroException e) {
                e.printStackTrace();
                masivaView.mostrarMensaje(e.mensaje);
            }
        }

        private void cargarTesis(String[] lineaActual, int i) {
            try {
                int anio = Integer.parseInt(lineaActual[2]);
                int edicion = Integer.parseInt(lineaActual[5]);
                int copias = Integer.parseInt(lineaActual[9]);

                Tesis nuevaTesis = new Tesis(
                        lineaActual[1],
                        anio,
                        lineaActual[4],
                        lineaActual[6].split("[,]", 0),
                        lineaActual[7],
                        lineaActual[9].split("[,]", 0),
                        copias,
                        lineaActual[12],
                        edicion
                );

                bibliografias = addElement(bibliografias.length, bibliografias, nuevaTesis);
            } catch (NumberFormatException e) {
                masivaView.mostrarMensaje("Error en los datos numericos en la entrada numero " + i);
            }
        }

        private void cargarRevista(String[] lineaActual, int i) {
            try {
                int anio = Integer.parseInt(lineaActual[2]);
                int edicion = Integer.parseInt(lineaActual[5]);
                int copias = Integer.parseInt(lineaActual[9]);
                int ejemplares = Integer.parseInt(lineaActual[11]);

                Revista nuevaRevista = new Revista(
                        lineaActual[1],
                        anio,
                        lineaActual[4],
                        lineaActual[6].split("[,]", 0),
                        lineaActual[7],
                        lineaActual[9].split("[,]", 0),
                        copias,
                        lineaActual[10],
                        ejemplares,
                        edicion
                );

                bibliografias = addElement(bibliografias.length, bibliografias, nuevaRevista);
            } catch (NumberFormatException e) {
                masivaView.mostrarMensaje("Error en los datos numericos en la entrada numero " + i);
            }
        }

        private void cargarLibro(String[] lineaActual, int i) {
            try {
                int anio = Integer.parseInt(lineaActual[2]);
                int edicion = Integer.parseInt(lineaActual[5]);
                int copias = Integer.parseInt(lineaActual[9]);
                int isbn = Integer.parseInt(lineaActual[3]);

                Libro nuevoLibro = new Libro(
                        lineaActual[1],
                        anio,
                        lineaActual[4],
                        lineaActual[6].split("[,]", 0),
                        lineaActual[7],
                        lineaActual[9].split("[,]", 0),
                        copias,
                        isbn,
                        edicion
                );

                bibliografias = addElement(bibliografias.length, bibliografias, nuevoLibro);
            } catch (NumberFormatException e) {
                masivaView.mostrarMensaje("Error en los datos numericos en la entrada numero " + i);
            }
        }
    }

    private Bibliografia[] addElement(int n, Bibliografia[] bibliografias, Bibliografia bibliografia) {
        Bibliografia[] newBibliografia = new Bibliografia[n + 1];
        // Copiar la array antigua en la nueva
        if (n >= 0) System.arraycopy(bibliografias, 0, newBibliografia, 0, n);
        newBibliografia[n] = bibliografia;
        return newBibliografia;
    }

    private void verificarNumericos(int[] datos, String[] tipos) throws ParametroException {
        for (int i = 0; i < datos.length; i++) {
            if (datos[i] < 0) {
                throw new ParametroException(tipos[i] + " Tiene que ser un dato numérico");
            }
        }
    }

    private void verificarParametros(String[] camposTexto, String[] tipos) throws ParametroException {
        for (int i = 0; i < camposTexto.length; i++) {
            if (camposTexto[i].equals("")) {
                throw new ParametroException("El parametro " + tipos[i] + " está vacio.");
            }
//            else if (tipos[i].equals("Contraseña1")) {
//                if (! camposTexto[i].equals(camposTexto[i + 1])) {
//                    throw new ParametroException("Las contraseñas no coinciden, por favor vuelva a intentar");
//                }
//            }
        }
    }

    public static class ParametroException extends Exception {
        final String mensaje;
        ParametroException(String mensaje) {
            super(mensaje);
            this.mensaje = mensaje;
        }
    }

}
