/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author Equipo3
 */
public class Boletos {
    private int boletoId;
    private String numeroSerie;
    private String fila;
    private String asiento;
    private int numeroControl;
    private double precioOriginal;
    private int eventoId;
    private int usuarioId;
    private boolean reventa;
    private boolean venta;

    // Constructor vacío
    public Boletos() {}

    // Constructor con todos los parámetros
    public Boletos(int boletoId, String numeroSerie, String fila, String asiento, int numeroControl, double precioOriginal, int eventoId, int usuarioId, boolean reventa, boolean venta) {
        this.boletoId = boletoId;
        this.numeroSerie = numeroSerie;
        this.fila = fila;
        this.asiento = asiento;
        this.numeroControl = numeroControl;
        this.precioOriginal = precioOriginal;
        this.eventoId = eventoId;
        this.usuarioId = usuarioId;
        this.reventa = reventa;
        this.venta = venta;
    }

    // Getters y Setters
    public int getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(int boletoId) {
        this.boletoId = boletoId;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public int getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(int numeroControl) {
        this.numeroControl = numeroControl;
    }

    public double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isReventa() {
        return reventa;
    }

    public void setReventa(boolean reventa) {
        this.reventa = reventa;
    }

    public boolean isVenta() {
        return venta;
    }

    public void setVenta(boolean venta) {
        this.venta = venta;
    }

    // Método toString para imprimir los detalles del boleto
    @Override
    public String toString() {
        return "Boleto [boletoId=" + boletoId + ", numeroSerie=" + numeroSerie + ", fila=" + fila + ", asiento=" + asiento
                + ", numeroControl=" + numeroControl + ", precioOriginal=" + precioOriginal + ", eventoId=" + eventoId
                + ", usuarioId=" + usuarioId + ", reventa=" + reventa + ", venta=" + venta + "]";
    }
}

