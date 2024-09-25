/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaz.IBoleto;
import Negocio.Boletos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaspa
 */
public class BoletoDAO implements IBoleto{

    private Conexion conexion;

    // Constructor que recibe la conexión a la base de datos
    public BoletoDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    // Método para agregar un nuevo boleto
    public boolean agregar(Boletos boleto) {
        Connection bd = conexion.crearConexion();
        String sql = "INSERT INTO Boletos (numero_serie, fila, asiento, numero_control, precio_original, evento_id, usuario_id, reventa, venta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setString(1, boleto.getNumeroSerie());
            statement.setString(2, boleto.getFila());
            statement.setString(3, boleto.getAsiento());
            statement.setInt(4, boleto.getNumeroControl());
            statement.setDouble(5, boleto.getPrecioOriginal());
            statement.setInt(6, boleto.getEventoId());
            statement.setInt(7, boleto.getUsuarioId());
            statement.setBoolean(8, boleto.isReventa());
            statement.setBoolean(9, boleto.isVenta());

            int filasInsertadas = statement.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un boleto por su ID
    public boolean eliminar(int boletoId) {
        Connection bd = conexion.crearConexion();
        String sql = "DELETE FROM Boletos WHERE boleto_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setInt(1, boletoId);

            int filasEliminadas = statement.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para consultar un boleto por su ID
    public Boletos consultar(int boletoId) {
        Connection bd = conexion.crearConexion();
        Boletos boleto = null;
        String sql = "SELECT * FROM Boletos WHERE boleto_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setInt(1, boletoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    boleto = new Boletos();
                    boleto.setBoletoId(resultSet.getInt("boleto_id"));
                    boleto.setNumeroSerie(resultSet.getString("numero_serie"));
                    boleto.setFila(resultSet.getString("fila"));
                    boleto.setAsiento(resultSet.getString("asiento"));
                    boleto.setNumeroControl(resultSet.getInt("numero_control"));
                    boleto.setPrecioOriginal(resultSet.getDouble("precio_original"));
                    boleto.setEventoId(resultSet.getInt("evento_id"));
                    boleto.setUsuarioId(resultSet.getInt("usuario_id"));
                    boleto.setReventa(resultSet.getBoolean("reventa"));
                    boleto.setVenta(resultSet.getBoolean("venta"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boleto;
    }

    // Método para consultar todos los boletos
    public List<Boletos> consultar() {
        Connection bd = conexion.crearConexion();
        List<Boletos> listaBoletos = new ArrayList<>();
        String sql = "SELECT * FROM Boletos";

        try (Statement statement = bd.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Boletos boleto = new Boletos();
                boleto.setBoletoId(resultSet.getInt("boleto_id"));
                boleto.setNumeroSerie(resultSet.getString("numero_serie"));
                boleto.setFila(resultSet.getString("fila"));
                boleto.setAsiento(resultSet.getString("asiento"));
                boleto.setNumeroControl(resultSet.getInt("numero_control"));
                boleto.setPrecioOriginal(resultSet.getDouble("precio_original"));
                boleto.setEventoId(resultSet.getInt("evento_id"));
                boleto.setUsuarioId(resultSet.getInt("usuario_id"));
                boleto.setReventa(resultSet.getBoolean("reventa"));
                boleto.setVenta(resultSet.getBoolean("venta"));

                listaBoletos.add(boleto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaBoletos;
    }

    // Método para actualizar un boleto existente
    public boolean actualizar(Boletos boleto) {
        Connection bd = conexion.crearConexion();
        String sql = "UPDATE Boletos SET numero_serie = ?, fila = ?, asiento = ?, numero_control = ?, precio_original = ?, evento_id = ?, usuario_id = ?, reventa = ?, venta = ? WHERE boleto_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setString(1, boleto.getNumeroSerie());
            statement.setString(2, boleto.getFila());
            statement.setString(3, boleto.getAsiento());
            statement.setInt(4, boleto.getNumeroControl());
            statement.setDouble(5, boleto.getPrecioOriginal());
            statement.setInt(6, boleto.getEventoId());
            statement.setInt(7, boleto.getUsuarioId());
            statement.setBoolean(8, boleto.isReventa());
            statement.setBoolean(9, boleto.isVenta());
            statement.setInt(10, boleto.getBoletoId());

            int filasActualizadas = statement.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
