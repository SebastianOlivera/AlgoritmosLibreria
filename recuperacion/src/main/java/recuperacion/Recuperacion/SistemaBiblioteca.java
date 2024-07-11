package recuperacion.Recuperacion;

import java.util.HashMap;
import java.util.List;
import recuperacion.ArbolAVL.*;
import recuperacion.ArbolBB.*;
import recuperacion.Tries.*;
import recuperacion.Util.ManejadorArchivosGenerico;


public class SistemaBiblioteca {
    private TArbolAVL<Alumno> arbolAlumnos;
    private TArbolTrie trieLibros;
    private HashMap<String, Libro> diccionarioLibros;
    private HashMap<String, String> prestamos;

    public SistemaBiblioteca() {
        this.arbolAlumnos = new TArbolAVL<>();
        this.trieLibros = new TArbolTrie();
        this.diccionarioLibros = new HashMap<>();
        this.prestamos = new HashMap<>();
    }

    public void registrarAlumno(String cedula, String nombre) {
        Alumno alumno = new Alumno(cedula, nombre);
        TElementoAVL<Alumno> elementoAlumno = new TElementoAVL<>(cedula, alumno);
        arbolAlumnos.insertar(elementoAlumno);
        System.out.println("Registrado alumno: " + nombre + ", " + cedula);
    }

    public void registrarLibro(String nombreLibro, String categoria, String autor) {
        String nombreLibroFiltrado = ManejadorArchivosGenerico.filtrarPalabra(nombreLibro).toLowerCase();
        String categoriaFiltrado = ManejadorArchivosGenerico.filtrarPalabra(categoria).toLowerCase();
        String autorFiltrado = ManejadorArchivosGenerico.filtrarPalabra(autor).toLowerCase();
        Libro libro = new Libro(nombreLibroFiltrado, categoriaFiltrado, autorFiltrado);
        libro.setDisponible(true);  // Asegurar que el libro esté disponible
        trieLibros.insertar(libro.getNombre().toLowerCase(), libro);
        trieLibros.insertar(libro.getCategoria().toLowerCase(), libro);
        trieLibros.insertar(libro.getAutor().toLowerCase(), libro);
        diccionarioLibros.put(nombreLibroFiltrado, libro);
        System.out.println("Registrado libro: " + nombreLibro);
    }

    public boolean prestarLibro(String cedula, String nombreLibro) {
        String nombreLibroFiltrado = ManejadorArchivosGenerico.filtrarPalabra(nombreLibro).toLowerCase();
        System.out.println("Intentando prestar libro: " + nombreLibro + " a " + cedula);
        TElementoAB<Alumno> nodoAlumno = arbolAlumnos.buscar(cedula);
        if (nodoAlumno != null) {
            Alumno alumno = nodoAlumno.getDatos();
            System.out.println("Alumno encontrado: " + alumno.getNombre());
            if (alumno.getLibroPrestado() == null) {
                Libro libro = diccionarioLibros.get(nombreLibroFiltrado);
                if (libro != null && libro.isDisponible()) {
                    alumno.setLibroPrestado(nombreLibroFiltrado);
                    libro.setDisponible(false);
                    prestamos.put(nombreLibroFiltrado, cedula);
                    System.out.println("Libro prestado: " + libro.getNombre() + " a " + alumno.getNombre());
                    return true;
                } else {
                    System.out.println("Libro no disponible: " + nombreLibro);
                }
            } else {
                System.out.println("Alumno ya tiene un libro prestado: " + "ID = "+alumno.getLibroPrestado());
            }
        } else {
            System.out.println("Alumno no encontrado: " + cedula);
        }
        return false;
    }

    public boolean devolverLibro(String cedula) {
        System.out.println("Intentando devolver libro de " + cedula);
        TElementoAB<Alumno> nodoAlumno = arbolAlumnos.buscar(cedula);
        if (nodoAlumno != null) {
            Alumno alumno = nodoAlumno.getDatos();
            System.out.println("Alumno encontrado: " + alumno.getNombre());
            if (alumno.getLibroPrestado() != null) {
                String nombreLibro = alumno.getLibroPrestado();
                Libro libro = diccionarioLibros.get(nombreLibro);
                if (libro != null) {
                    alumno.setLibroPrestado(null);
                    libro.setDisponible(true);
                    prestamos.remove(nombreLibro);
                    System.out.println("Libro devuelto: " + nombreLibro + " por " + alumno.getNombre());
                    return true;
                }
            } else {
                System.out.println("Alumno no tiene libro prestado.");
            }
        } else {
            System.out.println("Alumno no encontrado: " + cedula);
        }
        return false;
    }

    public TArbolAVL<Alumno> getArbolAlumnos() {
        return arbolAlumnos;
    }

    public List<Libro> buscarLibro(String criterio) {
        return trieLibros.buscar(criterio.toLowerCase());
    }

    
}