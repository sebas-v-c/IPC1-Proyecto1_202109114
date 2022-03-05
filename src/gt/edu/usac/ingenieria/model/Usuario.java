package gt.edu.usac.ingenieria.model;

public class Usuario {
    private String nombre;
    private String apellido;
    private final long id;
    private String rol;

    private String user;
    private String password;

    private int[] librosPrestados = new int[] {};

    public Usuario(String nombre, String apellido, long id, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.rol = rol;
    }

    public void agregarLibro(int libroPrestadoId) {
        int n = librosPrestados.length;
        int[] newLibrosPrestados = new int[n + 1];
        // Copiar la array antigua en la nueva
        System.arraycopy(librosPrestados, 0, newLibrosPrestados, 0, n);
        newLibrosPrestados[n] = libroPrestadoId;
        librosPrestados = newLibrosPrestados;
    }

    public void quitarLibro(int libroPrestadoId) {
        int[] nuevosLibrosPrestados = new int[librosPrestados.length-1];
        int n = 0;
        for (int libroPrestado : librosPrestados) {
            if (libroPrestado == libroPrestadoId) {
                continue;
            }
            nuevosLibrosPrestados[n] = libroPrestado;
            n++;
        }

        librosPrestados = nuevosLibrosPrestados;
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
