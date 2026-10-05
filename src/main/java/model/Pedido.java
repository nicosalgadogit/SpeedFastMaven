package model;

public class Pedido {

    private int id;
    private String direccionEntrega;
    private String tipo;
    private EstadoPedido estado;

    public Pedido(int id, String direccionEntrega, String tipo) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido nuevoEstado) { this.estado = nuevoEstado; }

    @Override
    public String toString() {
        return "Pedido #" + id + " [" + direccionEntrega + "] (" + tipo + ") - Estado: " + estado;
    }
}