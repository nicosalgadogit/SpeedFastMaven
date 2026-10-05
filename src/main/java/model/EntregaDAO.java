package model;

import java.sql.*;

public class EntregaDAO {

    public void guardar(Entrega entrega) {
        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha_entrega) VALUES (?, ?, NOW())";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());

            ps.executeUpdate();
            System.out.println("Entrega registrada en la base de datos.");

        } catch (SQLException e) {
            System.out.println("Error al guardar la entrega: " + e.getMessage());
        }
    }
}