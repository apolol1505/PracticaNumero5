/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ordenacion;

public interface Comparador<T> {
    int comparar(T elemento1, T elemento2);

    interface KeyExtractor<T, U> {
        U extractKey(T elemento);
    }

    static <T, U extends Comparable<U>> Comparador<T> comparing(KeyExtractor<T, U> keyExtractor) {
        return (elemento1, elemento2) -> keyExtractor.extractKey(elemento1).compareTo(keyExtractor.extractKey(elemento2));
    }
}


