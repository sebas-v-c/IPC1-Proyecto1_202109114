package gt.edu.usac.ingenieria.model;

public class LibroDigital extends Bibliografia{
    private int tamanio;
    private int edicion;

    public LibroDigital(String autor, int anio, String titulo, String[] palabrasClave, String descripcion,
                        String[] temas, int edicion, int tamanio) {
        super(autor, anio, titulo, palabrasClave, descripcion, temas, -1, -1);
        this.tamanio = tamanio;
        this.edicion = edicion;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
