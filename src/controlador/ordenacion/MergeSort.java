/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ordenacion;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;

public class MergeSort<T> {

    public void mergeSort(ListaEnlazada<T> lista, Comparador<T> comparador) throws VacioException, PosicionException {
        mergeSort(lista, comparador, 0, lista.size() - 1);
    }

    private void mergeSort(ListaEnlazada<T> lista, Comparador<T> comparador, int inicio, int fin) throws VacioException, PosicionException {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;

            mergeSort(lista, comparador, inicio, medio);
            mergeSort(lista, comparador, medio + 1, fin);

            merge(lista, comparador, inicio, medio, fin);
        }
    }

    private void merge(ListaEnlazada<T> lista, Comparador<T> comparador, int inicio, int medio, int fin) throws VacioException, PosicionException {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;

        ListaEnlazada<T> listaIzquierda = new ListaEnlazada<>();
        ListaEnlazada<T> listaDerecha = new ListaEnlazada<>();

        for (int i = 0; i < n1; ++i) {
            listaIzquierda.agregarDato(lista.get(inicio + i));
        }

        for (int j = 0; j < n2; ++j) {
            listaDerecha.agregarDato(lista.get(medio + 1 + j));
        }

        int i = 0, j = 0;
        int k = inicio;

        while (i < n1 && j < n2) {
            T elementoIzquierda = listaIzquierda.get(i);
            T elementoDerecha = listaDerecha.get(j);

            if (comparador.comparar(elementoIzquierda, elementoDerecha) <= 0) {
                lista.set(k, elementoIzquierda);
                i++;
            } else {
                lista.set(k, elementoDerecha);
                j++;
            }
            k++;
        }

        while (i < n1) {
            lista.set(k, listaIzquierda.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            lista.set(k, listaDerecha.get(j));
            j++;
            k++;
        }
    }
}
