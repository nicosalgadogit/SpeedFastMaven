package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public boolean create(Repartidor repartidor) {
        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear repartidor: " + e.getMessage());
            return false;
        }
    }

    public List<Repartidor> readAll() {
        List<Repartidor> repartidores = new ArrayList<>();
        String sql = "SELECT * FROM repartidores";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                repartidores.add(new Repartidor(rs.getInt("id_repartidor"), rs.getString("nombre")));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar repartidores: " + e.getMessage());
        }

        return repartidores;
    }

    public boolean update(Repartidor repartidor) {
        String sql = "UPDATE repartidores SET nombre = ? WHERE id_repartidor = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            ps.setInt(2, repartidor.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar repartidor: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM repartidores WHERE id_repartidor = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar repartidor: " + e.getMessage());
            return false;
        }
    }
}