/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import Negocio.Eventos;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IEvento {
    public boolean agregar(Eventos evento);
    public boolean eliminar(int id);
    public boolean actualizar(Eventos evento);
    public Eventos consultar(int id);
    public List<Eventos> consultar();
}
