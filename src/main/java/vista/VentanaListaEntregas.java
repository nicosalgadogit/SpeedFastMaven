package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;
import java.awt.*;
import java.util.List;

public class VentanaListaEntregas extends JFrame {

    private EntregaDAO entregaDAO = new EntregaDAO();
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private List<Entrega> entregasActuales;

    public VentanaListaEntregas() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Lista de Entregas");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Pedido (dirección)", "Repartidor", "Fecha"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnNuevo = new JButton("Nueva");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");
        panelBotones.add(btnNuevo);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.SOUTH);

        btnNuevo.addActionListener(e -> new VentanaRegistroEntrega(this));

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una entrega primero.");
                return;
            }
            Entrega seleccionada = entregasActuales.get(fila);
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Eliminar esta entrega?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = entregaDAO.delete(seleccionada.getId());
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Entrega eliminada.");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la entrega.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnActualizar.addActionListener(e -> actualizarTabla());

        actualizarTabla();
        setVisible(true);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        entregasActuales = entregaDAO.readAll();

        for (Entrega en : entregasActuales) {
            modeloTabla.addRow(new Object[]{
                    en.getId(), en.getDireccionPedido(), en.getNombreRepartidor(), en.getFechaEntrega()
            });
        }
    }
}