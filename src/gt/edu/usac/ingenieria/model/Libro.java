package gt.edu.usac.ingenieria.model;

public class Libro extends Bibliografia{
    private final int ISBN;
    private int edicion;

    public Libro(String autor, int anio, String titulo, String[] palabrasClave, String descripcion, String[] temas,
                 int copias, int disponibles, int ISBN, int edicion) {
        super(autor, anio, titulo, palabrasClave, descripcion, temas, copias, disponibles);
        this.ISBN = ISBN;
        this.edicion = edicion;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }
}
