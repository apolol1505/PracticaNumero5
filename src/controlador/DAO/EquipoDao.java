/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.DAO;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.VacioException;
import controlador.ed.lista.exception.PosicionException;
import java.io.IOException;
import modelo.Equipo;

public class EquipoDao extends AdaptadorDAO<Equipo> {

    private Equipo equipo;

    public EquipoDao() {
        super(Equipo.class);
    }

    public Equipo getEquipo() {
        if (this.equipo == null) {
            this.equipo = new Equipo();
        }
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void guardar() throws IOException {
        equipo.setId(generateID());
        this.guardar(equipo);
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(equipo, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Equipo> ordenarNombre(ListaEnlazada<Equipo> lista,  Integer tipo) {
        try {
            Equipo[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Equipo key = matriz[i];
                int j = i - 1;
                switch(tipo){
                    case 0: 
                        
                }
                while (j >= 0 && (matriz[j].getNombre().compareToIgnoreCase(key.getNombre())) > 0) {
                    //lista.update(j+1, lista.get(j));
                    matriz[j + 1] = matriz[j];
                    j = j - 1;
                }
                //lista.update(j+1, key);
                matriz[j + 1] = key;
            }
            lista.toList(matriz);
        } catch (Exception e) {
        }
        return lista;
    }

    public ListaEnlazada<Equipo> ordenarID(ListaEnlazada<Equipo> lista, Integer tipo) {
        try {
            Equipo[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Equipo key = matriz[i];
                //Marca key = lista.get(i);
                int j = i - 1;
                switch (tipo) {
                    case 0:
                        while (j >= 0 && (matriz[j].getId() > key.getId())) {
                            //lista.update(j+1, lista.get(j));
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }

                        break;

                    case 1:
                        while (j >= 0 && (matriz[j].getId() < key.getId())) {
                            //lista.update(j+1, lista.get(j));
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }
                        break;

                }
            }
            lista.toList(matriz);

        } catch (Exception e) {
        }
        return lista;
    }
    
     public Equipo buscarPorNombre(String dato) throws VacioException, PosicionException{
        Equipo resultado = null;
        ListaEnlazada<Equipo> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Equipo aux = lista.get(i);
            if (aux.getNombre().toLowerCase().equals(dato.toLowerCase())) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }
}

