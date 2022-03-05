package gt.edu.usac.ingenieria.model;

public class Revista extends Bibliografia{
    private String categoria;
    private int ejemplares;

    public Revista(String autor, int anio, String titulo, String[] palabrasClave, String descripcion, String[] temas,
                   int copias, int disponibles, String categoria, int ejemplares) {
        super(autor, anio, titulo, palabrasClave, descripcion, temas, copias, disponibles);
        this.categoria = categoria;
        this.ejemplares = ejemplares;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }
}
