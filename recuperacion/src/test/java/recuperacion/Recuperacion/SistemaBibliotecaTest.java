package recuperacion.Recuperacion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import recuperacion.ArbolBB.TElementoAB;

public class SistemaBibliotecaTest {

    private SistemaBiblioteca sistema = new SistemaBiblioteca();

    @Test
    void testBuscarLibros() {
        sistema.registrarLibro("Algebra Lineal", "Matematica", "Humberto Weiss");
        sistema.registrarLibro("Algebra Lineal I", "Matematica", "Humberto Weiss");
        sistema.registrarLibro("Algebra Lineal II", "Matematica", "Humberto Weiss");
        sistema.registrarLibro("Algebra Lineal III", "Matematica", "Humberto Weiss");
        String busqueda = "Alge";
        List<Libro> resultados = sistema.buscarLibro(busqueda);
        for (Libro libro : resultados) {
            System.out.println(libro);
        }
        assertEquals("algebralineal", resultados.get(0).getNombre());
        assertEquals("algebralineali", resultados.get(1).getNombre());
        assertEquals("algebralinealii", resultados.get(2).getNombre());
        assertEquals("algebralinealiii", resultados.get(3).getNombre());
        assertEquals(4, resultados.size());
    }

    @Test
    void testBuscarLibrosPorCategoria() {
        sistema.registrarLibro("Algebra Lineal", "Matematica", "Humberto Weiss");
        sistema.registrarLibro("Algebra Lineal I", "Matematica", "Humberto Weiss");
        sistema.registrarLibro("Algebra Lineal II", "Matematica", "Humberto Weiss");
        sistema.registrarLibro("Algebra Lineal III", "Matematica", "Humberto Weiss");
        String busqueda = "Matematica";
        List<Libro> resultados = sistema.buscarLibro(busqueda);
        for (Libro libro : resultados) {
            System.out.println(libro);
        }
        assertEquals("matematica", resultados.get(0).getCategoria());
        assertEquals("matematica", resultados.get(1).getCategoria());
        assertEquals("matematica", resultados.get(2).getCategoria());
        assertEquals("matematica", resultados.get(3).getCategoria());
        assertEquals(4, resultados.size());
    }

    @Test
    void testDevolverLibro() {
        sistema.registrarAlumno("4553553", "Juan Perez");
        sistema.registrarLibro("Algebra Lineal", "Matematica", "Humberto Weiss");
        sistema.prestarLibro("4553553", "Algebra Lineal");
        boolean devolucion1 = sistema.devolverLibro("4553553");
        assertTrue(devolucion1);
        TElementoAB<Alumno> nodoAlumno = sistema.getArbolAlumnos().buscar("4553553");
        assertNull(nodoAlumno.getDatos().getLibroPrestado());

    }

    @Test
    void testPrestarLibro() {
        sistema.registrarAlumno("4553553", "Juan Perez");
        sistema.registrarLibro("Algebra Lineal", "Matematica", "Humberto Weiss");
        boolean prestamo1 = sistema.prestarLibro("4553553", "Algebra Lineal");
        assertTrue(prestamo1);
        TElementoAB<Alumno> nodoAlumno = sistema.getArbolAlumnos().buscar("4553553");
        assertEquals("algebralineal", nodoAlumno.getDatos().getLibroPrestado());

    }

    @Test
    void testRegistrarAlumno() {
        sistema.registrarAlumno("4553553", "Juan Perez");
        TElementoAB<Alumno> nodoAlumno = sistema.getArbolAlumnos().buscar("4553553");
        assertNotNull(nodoAlumno);
        assertEquals("Juan Perez", nodoAlumno.getDatos().getNombre());
        

    }

    @Test
    void testRegistrarLibro() {
        sistema.registrarLibro("Algebra Lineal", "Matematica", "Humberto Weiss");
        List<Libro> libros = sistema.buscarLibro("AlgebraLineal");
        assertFalse(libros.isEmpty());
        assertEquals("algebralineal", libros.get(0).getNombre());
    }
}
