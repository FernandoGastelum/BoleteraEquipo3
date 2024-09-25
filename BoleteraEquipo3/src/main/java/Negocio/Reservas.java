/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import java.time.LocalDateTime;
/**
 *
 * @author gaspa
 */
public class Reservas {
    private int reservaId;
    private int usuarioId;
    private int boletoId;
    private LocalDateTime fechaReserva;
    private LocalDateTime tiempoExpiracion;

    // Constructor vacío
    public Reservas() {}

    // Constructor con todos los parámetros
    public Reservas(int reservaId, int usuarioId, int boletoId, LocalDateTime fechaReserva, LocalDateTime tiempoExpiracion) {
        this.reservaId = reservaId;
        this.usuarioId = usuarioId;
        this.boletoId = boletoId;
        this.fechaReserva = fechaReserva;
        this.tiempoExpiracion = tiempoExpiracion;
    }

    // Getters y Setters
    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(int boletoId) {
        this.boletoId = boletoId;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDateTime getTiempoExpiracion() {
        return tiempoExpiracion;
    }

    public void setTiempoExpiracion(LocalDateTime tiempoExpiracion) {
        this.tiempoExpiracion = tiempoExpiracion;
    }

    // Método toString para imprimir los detalles de la reserva
    @Override
    public String toString() {
        return "Reserva{" +
                "reservaId=" + reservaId +
                ", usuarioId=" + usuarioId +
                ", boletoId=" + boletoId +
                ", fechaReserva=" + fechaReserva +
                ", tiempoExpiracion=" + tiempoExpiracion +
                '}';
    }
}

