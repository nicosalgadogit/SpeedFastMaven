package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void guardar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (direccion_entrega, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado().toString());

            ps.executeUpdate();
            System.out.println("Pedido guardado en la base de datos.");

        } catch (SQLException e) {
            System.out.println("Error al guardar el pedido: " + e.getMessage());
        }
    }

    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("direccion_entrega"),
                        rs.getString("tipo")
                );
                p.setEstado(EstadoPedido.valueOf(rs.getString("estado")));
                pedidos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }
}