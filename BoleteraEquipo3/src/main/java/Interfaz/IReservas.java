/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import Negocio.Reservas;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IReservas {
    public boolean agregar(Reservas reserva);
    public boolean eliminar(int id);
    public boolean actualizar(Reservas reserva);
    public Reservas consultar(int id);
    public List<Reservas> consultar();
}
