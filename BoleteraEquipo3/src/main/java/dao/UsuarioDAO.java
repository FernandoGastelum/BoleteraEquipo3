/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaz.IConexion;
import Interfaz.IUsuarios;
import Negocio.Eventos;
import Negocio.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaspa
 */
public class UsuarioDAO implements IUsuarios{
    private IConexion conexion;
    
    public UsuarioDAO(Conexion conexion){
        this.conexion=conexion;
    }
    
    @Override
    public boolean agregar(Usuarios usuario) {
        Connection bd = conexion.crearConexion();
        String insertar ="insert into Usuarios (correo, contraseña, nombre_completo, domicilio, fecha_nacimiento, edad) VALUES (?,?,?,?,STR_TO_DATE(?, '%d-%m-%Y'),?)";
        try {
            PreparedStatement i = bd.prepareStatement(insertar);
            i.setString(1, usuario.getCorreo());
            i.setString(2, usuario.getContrasena());
            i.setString(3, usuario.getNombreCompleto());
            i.setString(4, usuario.getDomicilio());
            i.setString(5, usuario.getFechaNacimiento());
            i.setInt(6, usuario.getEdad());
            
            i.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo agregar el usuario");
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Usuarios usuario) {
        Connection bd = conexion.crearConexion();
        String actualizar ="UPDATE Usuarios SET correo = ?, contraseña = ?, nombre_completo= ?, domicilio = ?, fecha_nacimiento= STR_TO_DATE(?, '%d-%m-%Y'), edad = ? WHERE usuario_id = ?";
        try {
            PreparedStatement i = bd.prepareStatement(actualizar);
            i.setString(1, usuario.getCorreo());
            i.setString(2, usuario.getContrasena());
            i.setString(3, usuario.getNombreCompleto());
            i.setString(4, usuario.getDomicilio());
            i.setString(5, usuario.getFechaNacimiento());
            i.setInt(6, usuario.getEdad());
            i.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.out.println("No se pudo actualizar");
            return false;
        }
    }

    @Override
    public Usuarios consultar(int id) {
        Connection bd = conexion.crearConexion();
        String buscarUsuario = "SELECT * FROM Usuarios WHERE usuario_id = (?)";
        try {
            PreparedStatement busqueda = bd.prepareStatement(buscarUsuario);
            busqueda.setInt(1, id);
            
            ResultSet resultados = busqueda.executeQuery();
            
            if(resultados.next()){
                Usuarios u = new Usuarios();
                u.setUsuarioId(resultados.getInt("ID"));
                u.setCorreo(resultados.getString("Correo"));
                u.setContrasena(resultados.getString("Contrasena"));
                u.setNombreCompleto(resultados.getString("Nombre"));
                u.setDomicilio(resultados.getString("Domicilio"));
                u.setFechaNacimiento(resultados.getString("Fecha de nacimiento"));
                u.setEdad(resultados.getInt("Edad"));
                u.setSaldo(resultados.getFloat("Saldo"));
                return u;
            }
            else{
            }
            
        } catch (SQLException e) {
            System.out.println("No se pudo consultar");
            
            
        }
        return null;
    }

    @Override
    public List<Usuarios> consultar() {
        Connection bd = conexion.crearConexion();
        String buscarUsuario = "SELECT * FROM Usuarios";
        List<Usuarios> usuarios = new ArrayList<>();
        try {
            PreparedStatement busqueda = bd.prepareStatement(buscarUsuario);
            
            ResultSet resultados = busqueda.executeQuery();
            
            while(resultados.next()){
                Usuarios u = new Usuarios();
                u.setUsuarioId(resultados.getInt("usuario_id"));
                u.setCorreo(resultados.getString("correo"));
                u.setContrasena(resultados.getString("contraseña"));
                u.setNombreCompleto(resultados.getString("nombre_completo"));
                u.setDomicilio(resultados.getString("domicilio"));
                u.setFechaNacimiento(resultados.getString("fecha_nacimiento"));
                u.setEdad(resultados.getInt("edad"));
                u.setSaldo(resultados.getFloat("saldo"));
                usuarios.add(u);
            }
            
            
        } catch (SQLException e) {
            System.out.println("No se pudo consultar");
            
        }
        return usuarios;
    }
 
    
}
