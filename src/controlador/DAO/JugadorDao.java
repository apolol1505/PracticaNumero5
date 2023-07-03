/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.DAO;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.VacioException;
import controlador.ed.lista.exception.PosicionException;
import controlador.ordenacion.Comparador;
import controlador.ordenacion.MergeSort;
import java.io.IOException;
import modelo.Jugador;

public class JugadorDao extends AdaptadorDAO<Jugador> {

    private Jugador jugador;

    public JugadorDao() {
        super(Jugador.class);
    }

    public Jugador getJugador() {
        if (this.jugador == null) {
            this.jugador = new Jugador();
        }
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void guardar() throws IOException {
        jugador.setId(generateID());
        this.guardar(jugador);
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(jugador, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Jugador> buscarPorNombre(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Jugador> resultado = new ListaEnlazada<>();
        ListaEnlazada<Jugador> lista = listar();
        String datoLowerCase = dato.toLowerCase(); // Convertir el dato de búsqueda a minúsculas

        for (int i = 0; i < lista.size(); i++) {
            Jugador aux = lista.get(i);
            if (aux.getNombre().toLowerCase().startsWith(datoLowerCase)) { // Comparar en minúsculas
                resultado.insertar(aux);
            }
        }

        return resultado;
    }

    public ListaEnlazada<Jugador> buscarPorAlias(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Jugador> resultado = new ListaEnlazada<>();
        ListaEnlazada<Jugador> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Jugador aux = lista.get(i);
            if (aux.getAlias().toLowerCase().startsWith(dato)) {
                resultado.insertar(aux);
            }
        }

        return resultado;
    }

    public ListaEnlazada<Jugador> buscarDorsal(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Jugador> resultado = new ListaEnlazada<>();
        ListaEnlazada<Jugador> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Jugador aux = lista.get(i);
            String dorsalStr = String.valueOf(aux.getDorsal());
            if (dorsalStr.toLowerCase().startsWith(dato.toLowerCase())) {
                resultado.insertar(aux);
            }
        }

        return resultado;
    }

    public ListaEnlazada<Jugador> buscarDorsalBinario(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Jugador> lista = listar();

        if (dato.isEmpty()) {
            return lista; // Devolver la lista completa si el campo de búsqueda está vacío
        }

        MergeSort<Jugador> mergeSort = new MergeSort<>();
        Comparador<Jugador> comparador = Comparador.comparing(j -> j.getDorsal());

        mergeSort.mergeSort(lista, comparador);

        // Búsqueda binaria
        int inicio = 0;
        int fin = lista.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            Jugador jugador = lista.get(medio);
            int comparacion = Integer.compare(Integer.parseInt(dato), jugador.getDorsal());
            if (comparacion == 0) {
                // Se encontró el jugador con el dorsal buscado
                ListaEnlazada<Jugador> resultado = new ListaEnlazada<>();
                resultado.insertar(jugador);
                return resultado;
            } else if (comparacion < 0) {
                fin = medio - 1; // El dorsal buscado es menor, buscar en la mitad izquierda
            } else {
                inicio = medio + 1; // El dorsal buscado es mayor, buscar en la mitad derecha
            }
        }

        return new ListaEnlazada<>(); // No se encontró ningún jugador con el dorsal buscado
    }

    public Jugador buscarAliasBinario(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Jugador> lista = listar();
        if (lista.isEmpty()) {
            throw new VacioException();
        } else {
            MergeSort<Jugador> mergeSort = new MergeSort<>();
            Comparador<Jugador> comparador = Comparador.comparing(Jugador::getAlias); // Comparador por alias

            mergeSort.mergeSort(lista, comparador); // Ordenar la lista por alias

            int inicio = 0;
            int fin = lista.getSize() - 1;
            int medio;

            // Convertir texto a minúsculas
            dato = dato.toLowerCase();

            while (inicio <= fin) {
                medio = (inicio + fin) / 2;
                Jugador jugador = lista.get(medio);
                if (jugador.getAlias().toLowerCase().startsWith(dato)) {
                    return jugador;
                } else if (jugador.getAlias().compareToIgnoreCase(dato) < 0) {
                    inicio = medio + 1;
                } else {
                    fin = medio - 1;
                }
            }

            // Si no se encontró el jugador  retornamos null
            return null;
        }
    }

    public Jugador buscarNombreBinario(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Jugador> lista = listar();
        if (lista.isEmpty()) {
            throw new VacioException();
        } else {
            MergeSort<Jugador> mergeSort = new MergeSort<>();
            Comparador<Jugador> comparador = Comparador.comparing(Jugador::getNombre); // Comparador por nombre

            mergeSort.mergeSort(lista, comparador); // Ordenar la lista por nombre

            int inicio = 0;
            int fin = lista.getSize() - 1;
            int medio;

            // Convertir texto a minúsculas
            dato = dato.toLowerCase();

            while (inicio <= fin) {
                medio = (inicio + fin) / 2;
                Jugador jugador = lista.get(medio);
                if (jugador.getNombre().toLowerCase().startsWith(dato)) {
                    return jugador;
                } else if (jugador.getNombre().compareToIgnoreCase(dato) < 0) {
                    inicio = medio + 1;
                } else {
                    fin = medio - 1;
                }
            }
            // Si no se encontró el jugador retornamos null
            return null;
        }
    }
}
