package gt.edu.usac.ingenieria.model;

public class Libro extends Bibliografia{
    private int ISBN;

    public Libro(String autor, int anio, String titulo, String[] palabrasClave, String descripcion, String[] temas,
                 int copias, int ISBN, int edicion) {
        super(autor, anio, titulo, palabrasClave, descripcion, temas, copias, edicion);
        this.ISBN = ISBN;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

}
