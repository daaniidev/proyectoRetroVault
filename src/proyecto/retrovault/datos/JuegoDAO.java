package proyecto.retrovault.datos;

import proyecto.retrovault.modelo.Juego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoDAO {

    public List<Juego> obtenerTodos() {
        List<Juego> lista = new ArrayList<>();
        String sql = "select * from juegos order by titulo";
        try (Connection c = DatabaseManager.getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                lista.add(mapear(r));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Juego> obtenerPorConsola(String consola) {
        List<Juego> lista = new ArrayList<>();
        String sql = "select * from juegos where consola = ? order by titulo";
        try (Connection c = DatabaseManager.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, consola);
            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    lista.add(mapear(r));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Juego> obtenerPorEstado(String estado) {
        List<Juego> lista = new ArrayList<>();
        String sql = "select * from juegos where estado = ? order by titulo";
        try (Connection c = DatabaseManager.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, estado);
            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    lista.add(mapear(r));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertar(Juego j) {
        String sql = "insert into juegos(titulo,consola,genero,anio,puntuacion,estado) values(?,?,?,?,?,?)";
        try (Connection c = DatabaseManager.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, j.getTitulo());
            ps.setString(2, j.getConsola());
            ps.setString(3, j.getGenero());
            ps.setInt(4, j.getAnio());
            ps.setInt(5, j.getPuntuacion());
            ps.setString(6, j.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    j.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Juego j) {
        String sql = "update juegos set titulo=?,consola=?,genero=?,anio=?,puntuacion=?,estado=? where id=?";
        try (Connection c = DatabaseManager.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, j.getTitulo());
            ps.setString(2, j.getConsola());
            ps.setString(3, j.getGenero());
            ps.setInt(4, j.getAnio());
            ps.setInt(5, j.getPuntuacion());
            ps.setString(6, j.getEstado());
            ps.setInt(7, j.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "delete from juegos where id = ?";
        try (Connection c = DatabaseManager.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int contarTotal() {
        return contarSQL("select count(*) from juegos");
    }

    public int contarCompletados() {
        return contarSQL("select count(*) from juegos where estado='Completado'");
    }

    public double puntuacionMedia() {
        String sql = "select avg(puntuacion) from juegos where puntuacion > 0";
        try (Connection c = DatabaseManager.getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            if (r.next()) {
                return Math.round(r.getDouble(1) * 10.0) / 10.0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private int contarSQL(String sql) {
        try (Connection c = DatabaseManager.getConnection(); Statement s = c.createStatement(); ResultSet r = s.executeQuery(sql)) {
            if (r.next()) {
                return r.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Juego mapear(ResultSet r) throws SQLException {
        Juego j = new Juego();
        j.setId(r.getInt("id"));
        j.setTitulo(r.getString("titulo"));
        j.setConsola(r.getString("consola"));
        j.setGenero(r.getString("genero"));
        j.setAnio(r.getInt("anio"));
        j.setPuntuacion(r.getInt("puntuacion"));
        j.setEstado(r.getString("estado"));
        return j;
    }
}
