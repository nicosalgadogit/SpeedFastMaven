package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pedido;
import model.PedidoDAO;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private ListaPedidos listapedidos;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public VentanaListaPedidos(ListaPedidos listapedidos) {
        this.listapedidos = listapedidos;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Lista de pedidos");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarTabla());
        add(btnActualizar, BorderLayout.SOUTH);

        actualizarTabla();
        setVisible(true);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);

        List<Pedido> pedidos = new PedidoDAO().listarTodos();
        for (Pedido p : pedidos) {
            Object[] fila = {p.getId(), p.getDireccionEntrega(), p.getTipo(), p.getEstado()};
            modeloTabla.addRow(fila);
        }
    }
}