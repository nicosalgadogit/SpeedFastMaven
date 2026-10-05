package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SpeedFast - Sistema de Gestión");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnPedidos = new JButton("Gestionar Pedidos");
        JButton btnRepartidores = new JButton("Gestionar Repartidores");
        JButton btnEntregas = new JButton("Gestionar Entregas");

        add(btnPedidos);
        add(btnRepartidores);
        add(btnEntregas);

        btnPedidos.addActionListener(e -> new VentanaListaPedidos());
        btnRepartidores.addActionListener(e -> new VentanaListaRepartidores());
        btnEntregas.addActionListener(e -> new VentanaListaEntregas());

        setVisible(true);
    }
}