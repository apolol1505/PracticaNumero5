/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.DAO.EquipoDao;
import controlador.ed.lista.exception.PosicionException;
import java.io.IOException;
import modelo.Equipo;

public class Main {

    public static void main(String[] args) throws IOException, PosicionException {
        EquipoDao md = new EquipoDao();
        // Agregar 10 equipos
        for (int i = 1; i <= 10; i++) {
            Equipo equipo = new Equipo();
            equipo.setId(i);
            // Asignar nombres a los equipos
            switch (i) {
                case 1:
                    equipo.setNombre("Arsenal");
                    break;
                case 2:
                    equipo.setNombre("Chelsea");
                    break;
                case 3:
                    equipo.setNombre("Manchester United");
                    break;
                case 4:
                    equipo.setNombre("Liverpool");
                    break;
                case 5:
                    equipo.setNombre("Manchester City");
                    break;
                case 6:
                    equipo.setNombre("Tottenham Hotspur");
                    break;
                case 7:
                    equipo.setNombre("Leicester City");
                    break;
                case 8:
                    equipo.setNombre("Brighton");
                    break;
                case 9:
                    equipo.setNombre("Newcastle");
                    break;
                case 10:
                    equipo.setNombre("Aston Villa");
                    break;
                default:
                    equipo.setNombre("Equipo " + i);
                    break;
            }
            //guardar equipos
            equipo.setEstado(true);
            md.setEquipo(equipo);
            md.guardar();
        }
    }
}
