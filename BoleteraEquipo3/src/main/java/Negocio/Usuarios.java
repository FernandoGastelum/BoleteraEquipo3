/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import java.util.Date;
/**
 *
 * @author gaspa
 */
public class Usuarios {
    private int usuarioId;
    private String correo;
    private String contrasena; // En español sería "contraseña", pero por convención en Java usamos nombres sin caracteres especiales
    private String nombreCompleto;
    private String domicilio;
    private String fechaNacimiento;
    private int edad;
    private double saldo;

    // Constructor vacío
    public Usuarios() {}

    // Constructor con todos los parámetros
    public Usuarios(int usuarioId, String correo, String contrasena, String nombreCompleto, String domicilio, String fechaNacimiento, int edad, double saldo) {
        this.usuarioId = usuarioId;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.saldo = saldo;
    }

    // Getters y Setters
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método toString para imprimir los detalles del usuario
    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contrasena + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", edad=" + edad +
                ", saldo=" + saldo +
                '}';
    }
}

