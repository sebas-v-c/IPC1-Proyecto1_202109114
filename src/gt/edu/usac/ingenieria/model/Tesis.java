package gt.edu.usac.ingenieria.model;

public class Tesis extends Bibliografia{
    private String area;
    public Tesis(String autor, int anio, String titulo, String[] palabrasClave, String descripcion, String[] temas,
                 int copias, int disponibles, String area) {
        super(autor, anio, titulo, palabrasClave, descripcion, temas, copias, disponibles);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
