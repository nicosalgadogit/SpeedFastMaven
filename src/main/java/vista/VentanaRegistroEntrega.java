package vista;

import javax.swing.*;
import model.*;
import java.awt.*;
import java.util.List;

public class VentanaRegistroEntrega extends JFrame {

    private JComboBox<Pedido> cmbPedido;
    private JComboBox<Repartidor> cmbRepartidor;
    private EntregaDAO entregaDAO = new EntregaDAO();
    private VentanaListaEntregas ventanaLista;

    public VentanaRegistroEntrega(VentanaListaEntregas ventanaLista) {
        this.ventanaLista = ventanaLista;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Registrar Entrega");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        List<Pedido> pedidos = new PedidoDAO().readAll();
        List<Repartidor> repartidores = new RepartidorDAO().readAll();

        add(new JLabel("Pedido:"));
        cmbPedido = new JComboBox<>(pedidos.toArray(new Pedido[0]));
        add(cmbPedido);

        add(new JLabel("Repartidor:"));
        cmbRepartidor = new JComboBox<>(repartidores.toArray(new Repartidor[0]));
        add(cmbRepartidor);

        JButton btnGuardar = new JButton("Registrar");
        add(new JLabel());
        add(btnGuardar);

        btnGuardar.addActionListener(e -> guardar(pedidos, repartidores));

        setVisible(true);
    }

    private void guardar(List<Pedido> pedidos, List<Repartidor> repartidores) {
        if (pedidos.isEmpty() || repartidores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes tener al menos un pedido y un repartidor registrados.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pedido pedidoSeleccionado = (Pedido) cmbPedido.getSelectedItem();
        Repartidor repartidorSeleccionado = (Repartidor) cmbRepartidor.getSelectedItem();

        Entrega nuevaEntrega = new Entrega(pedidoSeleccionado.getId(), repartidorSeleccionado.getId());
        boolean exito = entregaDAO.create(nuevaEntrega);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");
            if (ventanaLista != null) ventanaLista.actualizarTabla();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar la entrega.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}