package vista;

import javax.swing.*;
import model.*;
import java.awt.*;

public class VentanaRegistroRepartidor extends JFrame {

    private JTextField txtNombre;
    private RepartidorDAO repartidorDAO = new RepartidorDAO();
    private Repartidor repartidorAEditar;
    private VentanaListaRepartidores ventanaLista;

    public VentanaRegistroRepartidor(Repartidor repartidorAEditar, VentanaListaRepartidores ventanaLista) {
        this.repartidorAEditar = repartidorAEditar;
        this.ventanaLista = ventanaLista;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(repartidorAEditar == null ? "Registrar Repartidor" : "Editar Repartidor");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        JButton btnGuardar = new JButton(repartidorAEditar == null ? "Registrar" : "Guardar cambios");
        add(new JLabel());
        add(btnGuardar);

        if (repartidorAEditar != null) {
            txtNombre.setText(repartidorAEditar.getNombre());
        }

        btnGuardar.addActionListener(e -> guardar());

        setVisible(true);
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean exito;
        if (repartidorAEditar == null) {
            exito = repartidorDAO.create(new Repartidor(0, nombre));
        } else {
            repartidorAEditar.setNombre(nombre);
            exito = repartidorDAO.update(repartidorAEditar);
        }

        if (exito) {
            JOptionPane.showMessageDialog(this, "Repartidor guardado correctamente.");
            if (ventanaLista != null) ventanaLista.actualizarTabla();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el repartidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}