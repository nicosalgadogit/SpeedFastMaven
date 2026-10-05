package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean create(Pedido pedido) {
        String sql = "INSERT INTO pedidos (direccion_entrega, tipo, estado) VALUES (?, ?, ?)";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado().toString());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear pedido: " + e.getMessage());
            return false;
        }
    }

    public List<Pedido> readAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("direccion_entrega"),
                        rs.getString("tipo"),
                        EstadoPedido.valueOf(rs.getString("estado"))
                );
                pedidos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    public boolean update(Pedido pedido) {
        String sql = "UPDATE pedidos SET direccion_entrega = ?, tipo = ?, estado = ? WHERE id_pedido = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado().toString());
            ps.setInt(4, pedido.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
}