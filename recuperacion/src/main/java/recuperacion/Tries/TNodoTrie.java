package recuperacion.Tries;

import java.util.ArrayList;
import java.util.List;

import recuperacion.Recuperacion.Libro;

public class TNodoTrie implements INodoTrie {
    private final static int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;
    private List<Libro> libros;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
        libros = new ArrayList<>();
    }

    @Override
    public void insertar(String unaPalabra, Libro libro) {
        unaPalabra = unaPalabra.toLowerCase(); // Convertir a minúsculas
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = getIndice(unaPalabra.charAt(c));
            if ((indice < 0) || indice >= CANT_CHR_ABECEDARIO) {
                System.out.println(indice);
                throw new IllegalArgumentException("Caracter inválido en la palabra: " + unaPalabra.charAt(c));
            }
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
        nodo.libros.add(libro);
    }

    private int getIndice(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 26; // Assuming CANT_CHR_ABECEDARIO is at least 52
        } else if (c == ' ') {
            return -2; // Special case for space, can be ignored or handled differently
        } else {
            return -1; // Invalid character
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                for (Libro libro : nodo.libros) {
                    System.out.println(s + " -> " + libro);
                }
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }
    
    public List<Libro> buscar(String s) {
        TNodoTrie nodo = buscarNodoTrie(s);
        List<Libro> resultado = new ArrayList<>();
        if (nodo != null) {
            buscarLibros(nodo, resultado);
        }
        return resultado;
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (int c = 0; c < s.length(); c++) {
            int indice = getIndice(s.charAt(c));
            if (nodo.hijos[indice] == null) {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo;
    }

    private void buscarLibros(TNodoTrie nodo, List<Libro> resultado) {
        if (nodo.esPalabra) {
            resultado.addAll(nodo.libros);
        }
        for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
            if (nodo.hijos[c] != null) {
                buscarLibros(nodo.hijos[c], resultado);
            }
        }
    }
}
