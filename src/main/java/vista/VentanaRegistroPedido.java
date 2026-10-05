package vista;

import javax.swing.*;
import model.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JComboBox<EstadoPedido> cmbEstado;
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private Pedido pedidoAEditar;
    private VentanaListaPedidos ventanaLista;

    public VentanaRegistroPedido(Pedido pedidoAEditar, VentanaListaPedidos ventanaLista) {
        this.pedidoAEditar = pedidoAEditar;
        this.ventanaLista = ventanaLista;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(pedidoAEditar == null ? "Registrar Pedido" : "Editar Pedido");
        setSize(320, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>(new String[]{"Comida", "Encomienda", "Express"});
        add(cmbTipo);

        add(new JLabel("Estado:"));
        cmbEstado = new JComboBox<>(EstadoPedido.values());
        add(cmbEstado);

        JButton btnGuardar = new JButton(pedidoAEditar == null ? "Registrar" : "Guardar cambios");
        add(btnGuardar);

        if (pedidoAEditar != null) {
            txtDireccion.setText(pedidoAEditar.getDireccionEntrega());
            cmbTipo.setSelectedItem(pedidoAEditar.getTipo());
            cmbEstado.setSelectedItem(pedidoAEditar.getEstado());
        }

        btnGuardar.addActionListener(e -> guardar());

        setVisible(true);
    }

    private void guardar() {
        String direccion = txtDireccion.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();
        EstadoPedido estado = (EstadoPedido) cmbEstado.getSelectedItem();

        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La dirección es obligatoria.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean exito;
        if (pedidoAEditar == null) {
            Pedido nuevo = new Pedido(0, direccion, tipo, estado);
            exito = pedidoDAO.create(nuevo);
        } else {
            pedidoAEditar.setDireccionEntrega(direccion);
            pedidoAEditar.setTipo(tipo);
            pedidoAEditar.setEstado(estado);
            exito = pedidoDAO.update(pedidoAEditar);
        }

        if (exito) {
            JOptionPane.showMessageDialog(this, "Pedido guardado correctamente.");
            if (ventanaLista != null) ventanaLista.actualizarTabla();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}