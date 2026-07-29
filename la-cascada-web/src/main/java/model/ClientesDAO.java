package model;

import conexion.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientesDAO {

    // Inserta un nuevo cliente
    public boolean guardar(Clientes cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nombre, documento, telefono, direccion, correo) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDocumento());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());

            return ps.executeUpdate() > 0;
        }
    }

    // Devuelve todos los clientes activos
    public ArrayList<Clientes> listar() throws SQLException {
        ArrayList<Clientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE estado = 1 ORDER BY id_cliente DESC";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Clientes c = new Clientes();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setDocumento(rs.getString("documento"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setCorreo(rs.getString("correo"));
                c.setFecha_registro(rs.getTimestamp("fecha_registro"));
                c.setEstado(rs.getBoolean("estado"));
                lista.add(c);
            }
        }
        return lista;
    }

    // Busca un cliente por id (util para editar)
    public Clientes buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Clientes c = new Clientes();
                    c.setId_cliente(rs.getInt("id_cliente"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDocumento(rs.getString("documento"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setDireccion(rs.getString("direccion"));
                    c.setCorreo(rs.getString("correo"));
                    c.setFecha_registro(rs.getTimestamp("fecha_registro"));
                    c.setEstado(rs.getBoolean("estado"));
                    return c;
                }
            }
        }
        return null;
    }

    // Actualiza los datos de un cliente existente
    public boolean actualizar(Clientes cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, documento = ?, telefono = ?, "
                   + "direccion = ?, correo = ? WHERE id_cliente = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDocumento());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());
            ps.setInt(6, cliente.getId_cliente());

            return ps.executeUpdate() > 0;
        }
    }

    // Borrado logico (no elimina la fila, solo marca estado = 0)
    public boolean eliminar(int id) throws SQLException {
        String sql = "UPDATE clientes SET estado = 0 WHERE id_cliente = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
