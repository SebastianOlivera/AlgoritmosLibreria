package recuperacion.ArbolAVL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TArbolAVLTest {

    private TArbolAVL<Integer> arbolAVL = new TArbolAVL<>();

    @Test
    public void testInsertarElemento() {
        TElementoAVL<Integer> elemento1 = new TElementoAVL<>(10, 10);
        TElementoAVL<Integer> elemento2 = new TElementoAVL<>(20, 20);
        TElementoAVL<Integer> elemento3 = new TElementoAVL<>(30, 30);

        assertTrue(arbolAVL.insertar(elemento1));
        assertTrue(arbolAVL.insertar(elemento2));
        assertTrue(arbolAVL.insertar(elemento3));

        assertEquals(20, arbolAVL.getRaiz().getEtiqueta());
        assertEquals(10, ((TElementoAVL<Integer>) arbolAVL.getRaiz().getHijoIzq()).getEtiqueta());
        assertEquals(30, ((TElementoAVL<Integer>) arbolAVL.getRaiz().getHijoDer()).getEtiqueta());
    }

    @Test
    public void testEliminarElemento() {
        TElementoAVL<Integer> elemento1 = new TElementoAVL<>(10, 10);
        TElementoAVL<Integer> elemento2 = new TElementoAVL<>(20, 20);
        TElementoAVL<Integer> elemento3 = new TElementoAVL<>(30, 30);

        arbolAVL.insertar(elemento1);
        arbolAVL.insertar(elemento2);
        arbolAVL.insertar(elemento3);

        assertNotNull(arbolAVL.buscar(20));
        arbolAVL.eliminar(20);
        assertNull(arbolAVL.buscar(20));

        assertNotNull(arbolAVL.buscar(10));
        arbolAVL.eliminar(10);
        assertNull(arbolAVL.buscar(10));

        assertNotNull(arbolAVL.buscar(30));
        arbolAVL.eliminar(30);
        assertNull(arbolAVL.buscar(30));
    }

    @Test
    public void testBalanceo() {
        TElementoAVL<Integer> elemento1 = new TElementoAVL<>(10, 10);
        TElementoAVL<Integer> elemento2 = new TElementoAVL<>(20, 20);
        TElementoAVL<Integer> elemento3 = new TElementoAVL<>(30, 30);

        arbolAVL.insertar(elemento1);
        arbolAVL.insertar(elemento2);
        arbolAVL.insertar(elemento3);

        assertEquals(20, arbolAVL.getRaiz().getEtiqueta());
        assertEquals(10, ((TElementoAVL<Integer>) arbolAVL.getRaiz().getHijoIzq()).getEtiqueta());
        assertEquals(30, ((TElementoAVL<Integer>) arbolAVL.getRaiz().getHijoDer()).getEtiqueta());
    }
}
