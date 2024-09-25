/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaz.IReservas;
import Negocio.Reservas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaspa
 */
public class ReservaDAO implements IReservas{

    private Conexion conexion;

    // Constructor que recibe la conexión a la base de datos
    public ReservaDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    // Método para agregar una nueva reserva
    public boolean agregar(Reservas reserva) {
        Connection bd = conexion.crearConexion();
        String sql = "INSERT INTO Reservas (usuario_id, boleto_id, fecha_reserva, tiempo_expiracion) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setInt(1, reserva.getUsuarioId());
            statement.setInt(2, reserva.getBoletoId());
            statement.setTimestamp(3, Timestamp.valueOf(reserva.getFechaReserva()));
            statement.setTimestamp(4, Timestamp.valueOf(reserva.getTiempoExpiracion()));

            int filasInsertadas = statement.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar una reserva por su ID
    public boolean eliminar(int reservaId) {
        Connection bd = conexion.crearConexion();
        String sql = "DELETE FROM Reservas WHERE reserva_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setInt(1, reservaId);

            int filasEliminadas = statement.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para consultar una reserva por su ID
    public Reservas consultar(int reservaId) {
        Connection bd = conexion.crearConexion();
        Reservas reserva = null;
        String sql = "SELECT reserva_id, usuario_id, boleto_id, fecha_reserva, tiempo_expiracion FROM Reservas WHERE reserva_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setInt(1, reservaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = new Reservas();
                    reserva.setReservaId(resultSet.getInt("reserva_id"));
                    reserva.setUsuarioId(resultSet.getInt("usuario_id"));
                    reserva.setBoletoId(resultSet.getInt("boleto_id"));
                    reserva.setFechaReserva(resultSet.getTimestamp("fecha_reserva").toLocalDateTime());
                    reserva.setTiempoExpiracion(resultSet.getTimestamp("tiempo_expiracion").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserva;
    }

    // Método para consultar todas las reservas
    public List<Reservas> consultar() {
        Connection bd = conexion.crearConexion();
        List<Reservas> listaReservas = new ArrayList<>();
        String sql = "SELECT reserva_id, usuario_id, boleto_id, fecha_reserva, tiempo_expiracion FROM Reservas";

        try (Statement statement = bd.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Reservas reserva = new Reservas();
                reserva.setReservaId(resultSet.getInt("reserva_id"));
                reserva.setUsuarioId(resultSet.getInt("usuario_id"));
                reserva.setBoletoId(resultSet.getInt("boleto_id"));
                reserva.setFechaReserva(resultSet.getTimestamp("fecha_reserva").toLocalDateTime());
                reserva.setTiempoExpiracion(resultSet.getTimestamp("tiempo_expiracion").toLocalDateTime());

                listaReservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaReservas;
    }

    // Método para actualizar una reserva existente
    public boolean actualizar(Reservas reserva) {
        Connection bd = conexion.crearConexion();
        String sql = "UPDATE Reservas SET usuario_id = ?, boleto_id = ?, fecha_reserva = ?, tiempo_expiracion = ? WHERE reserva_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setInt(1, reserva.getUsuarioId());
            statement.setInt(2, reserva.getBoletoId());
            statement.setTimestamp(3, Timestamp.valueOf(reserva.getFechaReserva()));
            statement.setTimestamp(4, Timestamp.valueOf(reserva.getTiempoExpiracion()));
            statement.setInt(5, reserva.getReservaId());

            int filasActualizadas = statement.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
