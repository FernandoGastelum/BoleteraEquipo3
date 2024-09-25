/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import Negocio.Transacciones;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface ITransacciones {
    public boolean agregar(Transacciones Transaccion);
    public boolean eliminar(int id);
    public boolean actualizar(Transacciones Transaccion);
    public Transacciones consultar(int id);
    public List<Transacciones> consultar();
}
