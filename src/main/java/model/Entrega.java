package model;

public class Entrega {
    private int idPedido;
    private int idRepartidor;

    public Entrega(int idPedido, int idRepartidor) {
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
    }

    public int getIdPedido() { return idPedido; }
    public int getIdRepartidor() { return idRepartidor; }
}