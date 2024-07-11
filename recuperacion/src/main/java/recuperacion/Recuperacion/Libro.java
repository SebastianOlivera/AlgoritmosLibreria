package recuperacion.Recuperacion;

public class Libro {
    private String nombre;
    private String categoria;
    private String autor;
    private boolean disponible;
    
    public Libro(String nombre, String categoria, String autor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean b) {
        this.disponible = b;
    }

    @Override
    public String toString() {
        return "Libro: "+nombre + "|| Categoria: " + categoria + "|| Autor: " + autor + "";
    }
}
