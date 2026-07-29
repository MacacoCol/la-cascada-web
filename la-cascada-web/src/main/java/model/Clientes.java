package model;

import java.sql.Timestamp;

public class Clientes {

    private int id_cliente;
    private String nombre;
    private String documento;
    private String telefono;
    private String direccion;
    private String correo;
    private Timestamp fecha_registro;
    private boolean estado;

    public Clientes() {
    }

    public Clientes(int id_cliente, String nombre, String documento, String telefono,
                     String direccion, String correo, Timestamp fecha_registro, boolean estado) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
    }

    // Constructor util para insertar un cliente nuevo (sin id ni fecha, los genera la BD)
    public Clientes(String nombre, String documento, String telefono, String direccion, String correo) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Timestamp fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
