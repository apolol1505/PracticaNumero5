/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Jugador {

    private Integer id;
    private String nombre;
    private Double precio;
    private String alias;
    private Integer dorsal;
    private Integer id_jugador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getDorsal() {
        return dorsal;
    }

    public void setDorsal(Integer dorsal) {
        this.dorsal = dorsal;
    }

    public Integer getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(Integer id_jugador) {
        this.id_jugador = id_jugador;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Dorsal: " + dorsal + ", Alias: " + alias;
    }

}
