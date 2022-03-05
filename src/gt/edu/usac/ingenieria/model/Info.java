package gt.edu.usac.ingenieria.model;

public class Info {
    private Bibliografia[] bibliografias;
    private Usuario[] usuarios;
    private Usuario usuarioIngresado;

    public Info(Bibliografia[] bibliografias, Usuario[] usuarios, Usuario usuarioIngresado) {
        this.usuarios = usuarios;
        this.usuarioIngresado = usuarioIngresado;
        this.bibliografias = bibliografias;
    }

    public Bibliografia[] getBibliografias() {
        return bibliografias;
    }

    public void setBibliografias(Bibliografia[] bibliografias) {
        this.bibliografias = bibliografias;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioIngresado() {
        return usuarioIngresado;
    }

    public void setUsuarioIngresado(Usuario usuarioIngresado) {
        this.usuarioIngresado = usuarioIngresado;
    }
}
