/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import Negocio.Usuarios;
import java.util.List;

/**
 *
 * @author gaspa
 */
public interface IUsuarios {
    public boolean agregar(Usuarios usuario);
    public boolean eliminar(int id);
    public boolean actualizar(Usuarios usuario);
    public Usuarios consultar(int id);
    public List<Usuarios> consultar();
}
