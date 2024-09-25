/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaz.ITransacciones;
import Negocio.Transacciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaspa
 */
public class TransaccionDAO implements ITransacciones{
    private Conexion conexion;

    // Constructor que recibe la conexión a la base de datos
    public TransaccionDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    // Método para obtener todas las transacciones de la base de datos
    public List<Transacciones> consultar() {
        Connection bd = conexion.crearConexion();
        List<Transacciones> listaTransacciones = new ArrayList<>();
        String buscarTransaccion = "SELECT * FROM Transacciones";

        try {
            PreparedStatement busqueda = bd.prepareStatement(buscarTransaccion);
            
            ResultSet resultados = busqueda.executeQuery();

            while (resultados.next()) {
                Transacciones transaccion = new Transacciones();
                transaccion.setTransaccionId(resultados.getInt("transaccion_id"));
                transaccion.setTipoTransaccion(resultados.getString("tipo_transaccion"));
                transaccion.setFechaHora(resultados.getTimestamp("fecha_hora").toLocalDateTime());
                transaccion.setCompradorId(resultados.getInt("comprador_id"));
                transaccion.setVendedorId(resultados.getInt("vendedor_id"));
                transaccion.setMonto(resultados.getDouble("monto"));
                transaccion.setBoletoId(resultados.getInt("boleto_id"));
                transaccion.setComision(resultados.getDouble("comision"));

                listaTransacciones.add(transaccion);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo consultar");
            e.printStackTrace(); 
        }

        return listaTransacciones;
    }

    // Método para obtener una transacción por su ID
    public Transacciones consultar(int transaccionId) {
        Connection bd = conexion.crearConexion();
        Transacciones transaccion = null;
        String buscarTransaccion = "SELECT transaccion_id, tipo_transaccion, fecha_hora, comprador_id, vendedor_id, monto, boleto_id, comision FROM Transacciones WHERE transaccion_id = ?";
        
        try (PreparedStatement busqueda = bd.prepareStatement(buscarTransaccion)) {
            busqueda.setInt(1, transaccionId);
            try (ResultSet resultSet = busqueda.executeQuery()) {
                if (resultSet.next()) {
                    transaccion = new Transacciones();
                    transaccion.setTransaccionId(resultSet.getInt("transaccion_id"));
                    transaccion.setTipoTransaccion(resultSet.getString("tipo_transaccion"));
                    transaccion.setFechaHora(resultSet.getTimestamp("fecha_hora").toLocalDateTime());
                    transaccion.setCompradorId(resultSet.getInt("comprador_id"));
                    transaccion.setVendedorId(resultSet.getInt("vendedor_id"));
                    transaccion.setMonto(resultSet.getDouble("monto"));
                    transaccion.setBoletoId(resultSet.getInt("boleto_id"));
                    transaccion.setComision(resultSet.getDouble("comision"));
                }
            }
        } catch (SQLException e) {
            System.out.println("No se pudo consultar");
            e.printStackTrace(); 
        }

        return transaccion;
    }

    // Método para insertar una nueva transacción
    public boolean agregar(Transacciones transaccion) {
        Connection bd = conexion.crearConexion();
        String buscarTransaccion = "INSERT INTO Transacciones (tipo_transaccion, fecha_hora, comprador_id, vendedor_id, monto, boleto_id, comision) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = bd.prepareStatement(buscarTransaccion)) {
            statement.setString(1, transaccion.getTipoTransaccion());
            statement.setTimestamp(2, Timestamp.valueOf(transaccion.getFechaHora()));
            statement.setInt(3, transaccion.getCompradorId());
            statement.setInt(4, transaccion.getVendedorId());
            statement.setDouble(5, transaccion.getMonto());
            statement.setInt(6, transaccion.getBoletoId());
            statement.setDouble(7, transaccion.getComision());

            int filasInsertadas = statement.executeUpdate();
            return filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción como corresponda
            return false;
        }
    }

    // Método para actualizar una transacción existente
    public boolean actualizar(Transacciones transaccion) {
        Connection bd = conexion.crearConexion();
        String sql = "UPDATE Transacciones SET tipo_transaccion = ?, fecha_hora = ?, comprador_id = ?, vendedor_id = ?, monto = ?, boleto_id = ?, comision = ? WHERE transaccion_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setString(1, transaccion.getTipoTransaccion());
            statement.setTimestamp(2, Timestamp.valueOf(transaccion.getFechaHora()));
            statement.setInt(3, transaccion.getCompradorId());
            statement.setInt(4, transaccion.getVendedorId());
            statement.setDouble(5, transaccion.getMonto());
            statement.setInt(6, transaccion.getBoletoId());
            statement.setDouble(7, transaccion.getComision());
            statement.setInt(8, transaccion.getTransaccionId());

            int filasActualizadas = statement.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción como corresponda
            return false;
        }
    }

    // Método para eliminar una transacción por su ID
    public boolean eliminar(int transaccionId) {
        Connection bd = conexion.crearConexion();
        String sql = "DELETE FROM Transacciones WHERE transaccion_id = ?";

        try (PreparedStatement statement = bd.prepareStatement(sql)) {
            statement.setInt(1, transaccionId);

            int filasEliminadas = statement.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción como corresponda
            return false;
        }
    }

    

    
}
