package proyecto.retrovault.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:retrovault.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void inicializar() {
        String sql = """
            create table if not exists juegos (
                id          integer primary key autoincrement,
                titulo      text    not null,
                consola     text    not null,
                genero      text    not null,
                anio        integer not null,
                puntuacion  integer not null default 0,
                estado      text    not null default 'Pendiente'
            )
            """;
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al inicializar BD: " + e.getMessage());
        }
    }
}
