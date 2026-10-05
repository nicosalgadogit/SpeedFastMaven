package vista;

import javax.swing.*;
import model.Pedido;
import model.EstadoPedido;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private ListaPedidos listapedidos;

    public VentanaPrincipal() {
        this.listapedidos = new ListaPedidos();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SpeedFast - Sistema de Pedidos");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnRegistrar = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listar Pedidos");
        JButton btnAsignar = new JButton("Asignar Repartidor / Iniciar Entrega");

        add(btnRegistrar);
        add(btnListar);
        add(btnAsignar);

        btnRegistrar.addActionListener(e -> new VentanaRegistroPedido(listapedidos));

        btnListar.addActionListener(e -> new VentanaListaPedidos(listapedidos));

        btnAsignar.addActionListener(e -> {
            List<Pedido> pedidos = listapedidos.obtenerPedidos();

            if (pedidos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay pedidos registrados todavía.");
                return;
            }

            String[] opciones = new String[pedidos.size()];
            for (int i = 0; i < pedidos.size(); i++) {
                opciones[i] = pedidos.get(i).toString();
            }

            String seleccion = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecciona un pedido para asignar repartidor:",
                    "Asignar Repartidor",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (seleccion == null) {
                return;
            }

            int indice = java.util.Arrays.asList(opciones).indexOf(seleccion);
            Pedido pedidoElegido = pedidos.get(indice);

            String nombreRepartidor = JOptionPane.showInputDialog(this, "Nombre del repartidor:");
            if (nombreRepartidor == null || nombreRepartidor.trim().isEmpty()) {
                return;
            }

            pedidoElegido.setEstado(EstadoPedido.EN_REPARTO);
            JOptionPane.showMessageDialog(this, "Repartidor " + nombreRepartidor + " asignado a " + pedidoElegido);
        });

        setVisible(true);
    }
}