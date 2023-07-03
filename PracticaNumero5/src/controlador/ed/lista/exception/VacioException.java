/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ed.lista.exception;

public class VacioException extends Exception {

    public VacioException() {
        super("La lista se encuentra vacia");
    }

    public VacioException(String message) {
        super(message);
    }
}
