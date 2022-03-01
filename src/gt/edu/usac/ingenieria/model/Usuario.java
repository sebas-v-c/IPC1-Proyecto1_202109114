package gt.edu.usac.ingenieria.model;

public class Usuario {
    private final String nombre;
    private final String apellido;
    private final long id;
    private final String rol;

    private String user;
    private String password;

    public Usuario(String nombre, String apellido, long id, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public long getId() {
        return id;
    }

}
