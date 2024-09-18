/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaz.IConexion;
import Negocio.Eventos;
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
public class EventoDAO {
    private IConexion conexion;
    
    public EventoDAO(IConexion conexion){
        this.conexion=conexion;
    }
    public List<Eventos> consultar() {
        Connection bd = conexion.crearConexion();
        String buscarEvento = "select * from eventos";
        List<Eventos> eventos = new ArrayList<>();
        try {
            PreparedStatement busqueda = bd.prepareStatement(buscarEvento);
            
            ResultSet resultados = busqueda.executeQuery();
            
            while(resultados.next()){
                Eventos e = new Eventos();
                e.setNombre_evento(resultados.getString("nombre_evento"));
                e.setFecha_evento(resultados.getString("fecha_evento"));
                e.setVenue(resultados.getString("venue"));
                e.setCiudad(resultados.getString("ciudad"));
                e.setEstado(resultados.getString("estado"));
                e.setDescripcion(resultados.getString("descripcion"));
                eventos.add(e);
            }
            
        } catch (SQLException e) {
            System.out.println("No se pudo consultar"+e);
            
        }
        return eventos;
        
    }
    public boolean agregar(Eventos evento) {
        Connection bd = conexion.crearConexion();
        String insertar ="insert into eventos (nombre_evento, fecha_evento, venue, ciudad, estado, descripcion) VALUES (?,STR_TO_DATE(?, '%d-%m-%Y'),?,?,?,?)";
        try {
            PreparedStatement i = bd.prepareStatement(insertar);
            i.setString(1, evento.getNombre_evento());
            i.setString(2, evento.getFecha_evento());
            i.setString(3, evento.getVenue());
            i.setString(4, evento.getCiudad());
            i.setString(5, evento.getEstado());
            i.setString(6, evento.getDescripcion());
            
            i.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("No se pudo agregar el evento");
            return false;
        }
    }
}

