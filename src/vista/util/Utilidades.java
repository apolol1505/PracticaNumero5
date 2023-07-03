/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.util;

import controlador.DAO.AdaptadorDAO;
import controlador.DAO.EquipoDao;
import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import javax.swing.JComboBox;
import modelo.Equipo;

public class Utilidades {

    public static void cargarEquipo(JComboBox cbx, EquipoDao md) throws VacioException, PosicionException {
        cbx.removeAllItems();
        ListaEnlazada<Equipo> lista = md.ordenarNombre(md.listar(), AdaptadorDAO.ASCENDENTE);
        for (int i = 0; i < lista.size(); i++) {
            cbx.addItem(lista.get(i).getNombre());
        }
    }
}
