/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import Negocio.Boletos;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IBoleto {
    public boolean agregar(Boletos boleto);
    public boolean eliminar(int id);
    public boolean actualizar(Boletos boleto);
    public Boletos consultar(int id);
    public List<Boletos> consultar();
}
