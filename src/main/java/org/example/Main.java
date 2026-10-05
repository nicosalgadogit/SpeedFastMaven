package org.example;

import vista.VentanaPrincipal;
import model.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try {
            Connection conexion = ConexionBD.getConexion();
            System.out.println("Conexión exitosa a MySQL");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        new VentanaPrincipal();
    }
}