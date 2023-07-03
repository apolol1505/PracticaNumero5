/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.modeloTabla;

import controlador.DAO.EquipoDao;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Jugador;

public class ModeloTablaJugador extends AbstractTableModel {

    ListaEnlazada<Jugador> datos = new ListaEnlazada<>();

    public ListaEnlazada<Jugador> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Jugador> datos) {
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Jugador a = null;
        try {
            a = datos.get(arg0);
        } catch (Exception e) {

        }
        switch (arg1) {
            case 0:
                return (arg0 + 1);
            case 1:
                return (a != null) ? a.getNombre() : "No definido";
            case 2:
                return (a != null) ? a.getAlias() : "No definido";
            case 3:
                return (a != null) ? "$" + a.getPrecio() : "$0.0";
            case 4:
                return (a != null) ? a.getDorsal() : "No definido";
            case 5:
                return (a != null) ? new EquipoDao().obtener(a.getId_jugador()) : "No definido";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Nombre";
            case 2:
                return "Alias";
            case 3:
                return "Valor (Millones)";
            case 4:
                return "Dorsal";
            case 5:
                return "Equipo";
            default:
                return null;
        }
    }

}
