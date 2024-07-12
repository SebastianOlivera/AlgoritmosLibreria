package recuperacion.Recuperacion;

import java.util.List;
import recuperacion.ArbolAVL.*;
import recuperacion.ArbolBB.*;
import recuperacion.Tries.*;
import recuperacion.Util.ManejadorArchivosGenerico;

public class SistemaBiblioteca {
    private TArbolAVL<Alumno> arbolAlumnos;
    private TArbolTrie trieLibros;

    public SistemaBiblioteca() {
        this.arbolAlumnos = new TArbolAVL<>();
        this.trieLibros = new TArbolTrie();
    }

    public void registrarAlumno(String cedula, String nombre) {
        Alumno alumno = new Alumno(cedula, nombre);
        TElementoAVL<Alumno> elementoAlumno = new TElementoAVL<>(cedula, alumno);
        arbolAlumnos.insertar(elementoAlumno);
        System.out.println("Registrado alumno: " + nombre + ", " + cedula);
    }

    public void borrarAlumno(String cedula) {
        System.out.println("Intentando borrar alumno: " + cedula);
        TElementoAB<Alumno> nodoAlumno = arbolAlumnos.buscar(cedula);
        if (nodoAlumno != null) {
            Alumno alumno = nodoAlumno.getDatos();
            System.out.println("Alumno encontrado: " + alumno.getNombre());
            if (alumno.getLibroPrestado() == null) {
                arbolAlumnos.eliminar(cedula);
                System.out.println("Alumno borrado: " + alumno.getNombre());
            } else {
                System.out.println("Alumno tiene un libro prestado: ID = " + alumno.getLibroPrestado());
            }
        } else {
            System.out.println("Alumno no encontrado: " + cedula);
        }
    }

    public void registrarLibro(String nombreLibro, String categoria, String autor) {
        String nombreLibroFiltrado = ManejadorArchivosGenerico.filtrarPalabra(nombreLibro).toLowerCase();
        String categoriaFiltrado = ManejadorArchivosGenerico.filtrarPalabra(categoria).toLowerCase();
        String autorFiltrado = ManejadorArchivosGenerico.filtrarPalabra(autor).toLowerCase();
        Libro libro = new Libro(nombreLibroFiltrado, categoriaFiltrado, autorFiltrado);
        libro.setDisponible(true);
        trieLibros.insertar(libro.getNombre(), libro);
        trieLibros.insertar(libro.getCategoria(), libro);
        trieLibros.insertar(libro.getAutor(), libro);
        System.out.println("Registrado libro: " + nombreLibro);
    }

    public boolean prestarLibro(String cedula, String nombreLibro) {
        String nombreLibroFiltrado = ManejadorArchivosGenerico.filtrarPalabra(nombreLibro).toLowerCase();
        System.out.println("Intentando prestar libro: " + nombreLibro + " a Alumno: " + cedula);
        TElementoAB<Alumno> nodoAlumno = arbolAlumnos.buscar(cedula);
        if (nodoAlumno != null) {
            Alumno alumno = nodoAlumno.getDatos();
            System.out.println("Alumno encontrado: " + alumno.getNombre());
            if (alumno.getLibroPrestado() == null) {
                List<Libro> libros = trieLibros.buscar(nombreLibroFiltrado);
                if (!libros.isEmpty()) {
                    Libro libro = libros.get(0);
                    if (libro.isDisponible()) {
                        alumno.setLibroPrestado(nombreLibroFiltrado);
                        libro.setDisponible(false);
                        System.out.println("Libro prestado: " + libro.getNombre() + " a Alumno: " + alumno.getNombre());
                        return true;
                    } else {
                        System.out.println("Libro no disponible: " + nombreLibro);
                    }
                } else {
                    System.out.println("Libro no encontrado: " + nombreLibro);
                }
            } else {
                System.out.println("Alumno ya tiene un libro prestado: " + "ID = " + alumno.getLibroPrestado());
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
                List<Libro> libros = trieLibros.buscar(nombreLibro);
                if (!libros.isEmpty()) {
                    Libro libro = libros.get(0);
                    alumno.setLibroPrestado(null);
                    libro.setDisponible(true);
                    System.out.println("Libro devuelto: " + nombreLibro + " por Alumno: " + alumno.getNombre());
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

    public TArbolTrie getTrieLibros() {
        return trieLibros;
    }

    public List<Libro> buscarLibro(String criterio) {
        String criteriofiltrado = ManejadorArchivosGenerico.filtrarPalabra(criterio).toLowerCase();
        return trieLibros.buscar(criteriofiltrado);
    }
}
