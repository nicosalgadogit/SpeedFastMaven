package model;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

    private final List<Pedido> pedidosPendientes = new ArrayList<>();
    private volatile boolean sinMasPedidos = false;

    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
        System.out.println("[ZonaDeCarga] Pedido agregado: " + p);
        notifyAll();
    }

    public synchronized Pedido retirarPedido() {
        while (pedidosPendientes.isEmpty() && !sinMasPedidos) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        if (pedidosPendientes.isEmpty()) {
            return null;
        }
        return pedidosPendientes.remove(0);
    }

    public synchronized void marcarSinMasPedidos() {
        sinMasPedidos = true;
        notifyAll();
    }
}