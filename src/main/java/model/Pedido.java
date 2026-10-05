package model;

public class Pedido {
    private int id;
    private String direccionEntrega;
    private String tipo;
    private EstadoPedido estado;

    public Pedido(int id, String direccionEntrega, String tipo, EstadoPedido estado) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    @Override
    public String toString() {
        return id + " - " + direccionEntrega + " (" + tipo + ")";
    }
}