package recuperacion.Recuperacion;

public class Alumno {

    private String CI;
    private String Nombre;
    private String libroPrestado;

    public Alumno(String cedula, String nombre) {
        CI = cedula;
        Nombre = nombre;
    }

    public String getCI() {
        return CI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setLibroPrestado(String libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

    public String getLibroPrestado() {
        return libroPrestado;
    }
}
