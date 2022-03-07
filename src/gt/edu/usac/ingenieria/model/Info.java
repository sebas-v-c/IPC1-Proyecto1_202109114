package gt.edu.usac.ingenieria.model;

public class Info {
    private Bibliografia[] bibliografias;
    private Usuario[] usuarios;
    private Usuario usuarioIngresado;
    private Bibliografia[] carrito;

    public Info(Bibliografia[] bibliografias, Usuario[] usuarios, Usuario usuarioIngresado, Bibliografia[] carrito) {
        this.usuarios = usuarios;
        this.usuarioIngresado = usuarioIngresado;
        this.bibliografias = bibliografias;
        this.carrito = carrito;
    }


    public Bibliografia[] getBibliografias() {
        return bibliografias;
    }

    public void setBibliografias(Bibliografia[] bibliografias) {
        this.bibliografias = bibliografias;
    }

    public void setCarrito(Bibliografia[] carrito) {
        this.carrito = carrito;
    }

    public Bibliografia[] getCarrito() {
        return carrito;
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

    public Bibliografia[] agregarElemento(Bibliografia[] bibliografias, Bibliografia bibliografia) {
        int n = bibliografias.length;
        Bibliografia[] newBibliografia = new Bibliografia[n + 1];
        // Copiar la array antigua en la nueva
        System.arraycopy(bibliografias, 0, newBibliografia, 0, n);
            newBibliografia[n] = bibliografia;
            return newBibliografia;
    }

    public Bibliografia[] eliminarEntrada(Bibliografia[] bib, Bibliografia eliminable) {
        Bibliografia[] nuevaBibliografia = new Bibliografia[bib.length-1];
        int n = 0;
        for (Bibliografia bibliografiaActual : bib) {
            if (eliminable.getId() == bibliografiaActual.getId()) {
                continue;
            }
            nuevaBibliografia[n] = bibliografiaActual;
            n++;
        }
        return nuevaBibliografia;
    }
}
