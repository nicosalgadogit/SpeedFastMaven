package vista;

import model.Pedido;
import java.util.List;
import java.util.ArrayList;

public class ListaPedidos {

    private List<Pedido> pedidosPendientes = new ArrayList<>();

    public void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidosPendientes;
    }

}
