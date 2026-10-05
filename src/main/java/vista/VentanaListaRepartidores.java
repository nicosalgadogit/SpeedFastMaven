package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;
import java.awt.*;
import java.util.List;

public class VentanaListaRepartidores extends JFrame {

    private RepartidorDAO repartidorDAO = new RepartidorDAO();
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private List<Repartidor> repartidoresActuales;

    public VentanaListaRepartidores() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Lista de Repartidores");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Nombre"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnNuevo = new JButton("Nuevo");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");
        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);

        btnNuevo.addActionListener(e -> new VentanaRegistroRepartidor(null, this));

        btnEditar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un repartidor primero.");
                return;
            }
            new VentanaRegistroRepartidor(repartidoresActuales.get(fila), this);
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un repartidor primero.");
                return;
            }
            Repartidor seleccionado = repartidoresActuales.get(fila);
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Eliminar a " + seleccionado.getNombre() + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = repartidorDAO.delete(seleccionado.getId());
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Repartidor eliminado.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar (puede tener entregas asociadas).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnActualizar.addActionListener(e -> actualizarTabla());

        actualizarTabla();
        setVisible(true);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        repartidoresActuales = repartidorDAO.readAll();

        for (Repartidor r : repartidoresActuales) {
            modeloTabla.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }
}