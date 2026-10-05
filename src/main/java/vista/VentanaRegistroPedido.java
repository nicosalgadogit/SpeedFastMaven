package vista;

import javax.swing.*;
import model.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private ListaPedidos listapedidos;

    public VentanaRegistroPedido(ListaPedidos listapedidos) {
        this.listapedidos = listapedidos;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Registro de pedidos");
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("ID del Pedido:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel("Direccion del Pedido:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Tipo del Pedido:"));
        cmbTipo = new JComboBox<>(new String[]{"Comida", "Encomienda", "Express"});
        add(cmbTipo);

        JButton btnRegistrar = new JButton("Registrar");
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {

            String idTexto = txtId.getText();
            String direccion = txtDireccion.getText();
            String tipo = cmbTipo.getSelectedItem().toString();

            if (idTexto.trim().isEmpty() || direccion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Favor de completar todos los campos", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(idTexto.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Pedido nuevoPedido = new Pedido(id, direccion, tipo);
            new PedidoDAO().guardar(nuevoPedido);
            JOptionPane.showMessageDialog(this, "Pedido guardado correctamente.");

            txtId.setText("");
            txtDireccion.setText("");
        });

        setVisible(true);
    }
}