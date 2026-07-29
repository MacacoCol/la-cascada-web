package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/la_cascada_web?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USUARIO = "root";
    private static final String PASSWORD = ""; // coloca aqui tu password de MySQL / phpMyAdmin

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver de MySQL (mysql-connector-j)", e);
        }
    }
}
