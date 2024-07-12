package recuperacion.Tries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import recuperacion.Recuperacion.Libro;

public class TNodoTrieTest {

    private TNodoTrie nodoTrie = new TNodoTrie();

    @Test
    public void testInsertar() {
        Libro libro1 = new Libro("Libro1", "Autor1", "Categoría1");
        nodoTrie.insertar("palabra", libro1);
        
        List<Libro> resultados = nodoTrie.buscar("palabra");
        
        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals(libro1, resultados.get(0));
    }

    @Test
    public void testInsertarConMayusculas() {
        Libro libro1 = new Libro("Libro1", "Autor1", "Categoría1");
        nodoTrie.insertar("Palabra", libro1);
        
        List<Libro> resultados = nodoTrie.buscar("palabra");
        
        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals(libro1, resultados.get(0));
    }

    @Test
    public void testInsertarConCaracterInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            nodoTrie.insertar("palabra123", new Libro("Libro1", "Autor1", "Categoría1"));
        });
    }

    @Test
    public void testBuscarPalabraNoExistente() {
        Libro libro1 = new Libro("Libro1", "Autor1", "Categoría1");
        nodoTrie.insertar("existente", libro1);

        List<Libro> resultados = nodoTrie.buscar("noexistente");

        assertNotNull(resultados);
        assertTrue(resultados.isEmpty());
    }

    @Test
    public void testBuscarPrefijo() {
        Libro libro1 = new Libro("Libro1", "Autor1", "Categoría1");
        Libro libro2 = new Libro("Libro2", "Autor2", "Categoría2");

        nodoTrie.insertar("palabra", libro1);
        nodoTrie.insertar("palabras", libro2);

        List<Libro> resultados = nodoTrie.buscar("palabr");

        assertNotNull(resultados);
        assertEquals(2, resultados.size());
        assertTrue(resultados.contains(libro1));
        assertTrue(resultados.contains(libro2));
    }
}
