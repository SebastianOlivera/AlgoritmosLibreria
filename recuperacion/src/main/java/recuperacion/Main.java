package recuperacion;

import java.util.List;

import recuperacion.ArbolBB.TElementoAB;
import recuperacion.Recuperacion.*;
import recuperacion.Util.ManejadorArchivosGenerico;

public class Main {
    public static void main(String[] args) {

        // Crear un sistema de biblioteca
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        System.out.println("<<< ---------------------- [ CARGAR ALUMNOS ] ---------------------- >>>");
        // Cargar Alumnos
        String[] lineasAlumnos = ManejadorArchivosGenerico
                .leerArchivo("recuperacion\\src\\main\\java\\recuperacion\\Recuperacion\\Alumnos.txt");
        for (String linea : lineasAlumnos) {
            String[] partes = linea.split(",");
            String CI = partes[0];
            String nombre = partes[1];
            sistema.registrarAlumno(CI, nombre);
        }
        System.out.println("<<< ---------------------- [ CARGAR LIBROS ] ---------------------- >>>");
        // Cargar Libros
        String[] lineasLibros = ManejadorArchivosGenerico.leerArchivo("recuperacion\\src\\main\\java\\recuperacion\\Recuperacion\\Libros.txt");
        for (String linea : lineasLibros) {
            String[] partes = linea.split(",");
            String nombreLibro =partes[0];
            String categoria =partes[1];
            String autor = partes[2];
            sistema.registrarLibro(nombreLibro,categoria,autor);
        }
        System.out.println("<<< ---------------------- [ PRESTAMO ] ---------------------- >>>");
        // Prestamos Aceptados
        boolean prestamo1 = sistema.prestarLibro("93847561", "Calculo Avanzado");
        boolean prestamo2 = sistema.prestarLibro("75648392", "Fisica Moderna");
        boolean prestamo3 = sistema.prestarLibro("27483915", "Quimica Organica");

        System.out.println("<<< ---------------------- [ PRESTAMO LIBRO INEXISTENTE] ---------------------- >>>");
        // Prestamos Rechazados por inexistencia de libro
        boolean prestamo4 = sistema.prestarLibro("61574823", "libro No Existente");

        System.out.println("<<< ---------------------- [ PRESTAMO ALUMNO INEXISTENTE] ---------------------- >>>");
        // Prestamos Rechazados por alumno inexistente
        boolean prestamo5 = sistema.prestarLibro("9374618233322", "Literatura Espanola");

        System.out.println("<<< ---------------------- [ PRESTAMO ALUMNO TIENE LIBRO PRESTADO] ---------------------- >>>");
        // Prestamos Rechazados por alumno ya tiene libro prestado
        boolean prestamo6 = sistema.prestarLibro("93847561", "Mecanica Clasica");

        System.out.println("<<< ---------------------- [ PRESTAMO LIBRO NO DISPONIBLE] ---------------------- >>>");
        // Prestamos Rechazados por libro no disponible
        boolean prestamo7 = sistema.prestarLibro("84726359", "Calculo Avanzado");

        System.out.println("<<< ---------------------- [ DEVOLUCION ] ---------------------- >>>");
        // Devolver Libros
        boolean devolucion1 = sistema.devolverLibro("93847561");
        boolean devolucion2 = sistema.devolverLibro("75648392");

        System.out.println("<<< ---------------------- [ REGISTRAR NUEVO ALUMNO ] ---------------------- >>>");
        // Agregar alumno al sistema
        sistema.registrarAlumno("4666566", "Bruno Olivera");

        System.out.println("<<< ---------------------- [ REGISTRAR NUEVO LIBRO ] ---------------------- >>>");
        // Agregar libros al sistema
        sistema.registrarLibro("Estructuras De Datos Y Algoritmos", "Programacion", "Mark Weiss");

        System.out.println("<<< ---------------------- [ BUSQUEDA ] ---------------------- >>>");
        // Realizar búsquedas
        String busqueda = "Estructuras De";
        // Buscar libro
        List<Libro> resultados = sistema.buscarLibro(busqueda);
        System.out.println("Resultados de la busqueda para: " + busqueda);
        for (Libro libro : resultados) {
            System.out.println(libro);
        }
        // Prestar libro a alumno nuevo
        System.out.println("<<< ---------------------- [ PRESTAMO ALUMNO NUEVO LIBRO ] ---------------------- >>>");
        boolean prestamo8 = sistema.prestarLibro("93847561", "Estructuras De Datos Y Algoritmos");
    }
}
