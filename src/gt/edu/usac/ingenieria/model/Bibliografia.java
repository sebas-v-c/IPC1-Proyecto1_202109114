package gt.edu.usac.ingenieria.model;

public class Bibliografia {
    private String autor;
    private int anio;
    private String titulo;
    private String[] palabrasClave;
    private String descripcion;
    private String[] temas;
    private int copias;
    private int disponibles;
    private static int bibliografiaId = 0;
    private final int ID;

    public Bibliografia(String autor, int anio, String titulo, String[] palabrasClave, String descripcion, String[] temas, int copias, int disponibles) {
        this.autor = autor;
        this.anio = anio;
        this.titulo = titulo;
        this.palabrasClave = palabrasClave;
        this.descripcion = descripcion;
        this.temas = temas;
        this.copias = copias;
        this.ID = bibliografiaId;
        bibliografiaId++;
    }

    public int getId() {
        return ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String[] getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String[] getTemas() {
        return temas;
    }

    public void setTemas(String[] temas) {
        this.temas = temas;
    }
}
