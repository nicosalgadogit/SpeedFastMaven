package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public boolean create(Entrega entrega) {
        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha_entrega) VALUES (?, ?, NOW())";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear entrega: " + e.getMessage());
            return false;
        }
    }

    public List<Entrega> readAll() {
        List<Entrega> entregas = new ArrayList<>();
        String sql = "SELECT e.id_entrega, e.id_pedido, e.id_repartidor, e.fecha_entrega, " +
                "p.direccion_entrega, r.nombre " +
                "FROM entregas e " +
                "JOIN pedidos p ON e.id_pedido = p.id_pedido " +
                "JOIN repartidores r ON e.id_repartidor = r.id_repartidor";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Entrega e = new Entrega(
                        rs.getInt("id_entrega"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor"),
                        rs.getString("fecha_entrega"),
                        rs.getString("direccion_entrega"),
                        rs.getString("nombre")
                );
                entregas.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar entregas: " + e.getMessage());
        }

        return entregas;
    }

    public boolean update(Entrega entrega) {
        String sql = "UPDATE entregas SET id_pedido = ?, id_repartidor = ? WHERE id_entrega = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setInt(3, entrega.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar entrega: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM entregas WHERE id_entrega = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar entrega: " + e.getMessage());
            return false;
        }
    }
}