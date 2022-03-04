package gt.edu.usac.ingenieria.model;

public class Usuario {
    private String nombre;
    private String apellido;
    private final long id;
    private String rol;

    private String user;
    private String password;

    public Usuario(String nombre, String apellido, long id, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.rol = rol;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public long getId() {
        return id;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol(){
        return rol;
    }
}
