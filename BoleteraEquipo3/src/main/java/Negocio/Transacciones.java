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
public class Transacciones {
    private int transaccionId;
    private String tipoTransaccion; // Puede ser 'compra' o 'reventa'
    private LocalDateTime fechaHora;
    private Integer compradorId; // Puede ser null
    private Integer vendedorId; // Puede ser null
    private double monto;
    private int boletoId;
    private double comision;

    // Constructor vacío
    public Transacciones() {}

    // Constructor con todos los parámetros
    public Transacciones(int transaccionId, String tipoTransaccion, LocalDateTime fechaHora, Integer compradorId, Integer vendedorId, double monto, int boletoId, double comision) {
        this.transaccionId = transaccionId;
        this.tipoTransaccion = tipoTransaccion;
        this.fechaHora = fechaHora;
        this.compradorId = compradorId;
        this.vendedorId = vendedorId;
        this.monto = monto;
        this.boletoId = boletoId;
        this.comision = comision;
    }

    // Getters y Setters
    public int getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(int transaccionId) {
        this.transaccionId = transaccionId;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(Integer compradorId) {
        this.compradorId = compradorId;
    }

    public Integer getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Integer vendedorId) {
        this.vendedorId = vendedorId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(int boletoId) {
        this.boletoId = boletoId;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    // Método toString para imprimir los detalles de la transacción
    @Override
    public String toString() {
        return "Transaccion{" +
                "transaccionId=" + transaccionId +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", fechaHora=" + fechaHora +
                ", compradorId=" + compradorId +
                ", vendedorId=" + vendedorId +
                ", monto=" + monto +
                ", boletoId=" + boletoId +
                ", comision=" + comision +
                '}';
    }
}

