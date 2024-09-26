/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package dao;

import Interfaz.IConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 
@author Equipo3*/
public class Conexion implements IConexion{

    private String cadena = "jdbc:mysql://localhost:3306/sistemareventaboletos";
    private String usuario = "root";
    private String contraseña = "1234";

    @Override
    public Connection crearConexion() {
        try {
            Connection c = DriverManager.getConnection(cadena, usuario, contraseña);
            return c;
        } catch (SQLException ex) {
            System.out.println("Hubo un error de conexion");
        }
        return null;
    }
}
