package model;

public class Entrega {
    private int id;
    private int idPedido;
    private int idRepartidor;
    private String fechaEntrega;
    private String direccionPedido;
    private String nombreRepartidor;

    public Entrega(int idPedido, int idRepartidor) {
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
    }

    public Entrega(int id, int idPedido, int idRepartidor, String fechaEntrega,
                   String direccionPedido, String nombreRepartidor) {
        this.id = id;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fechaEntrega = fechaEntrega;
        this.direccionPedido = direccionPedido;
        this.nombreRepartidor = nombreRepartidor;
    }

    public int getId() { return id; }
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public int getIdRepartidor() { return idRepartidor; }
    public void setIdRepartidor(int idRepartidor) { this.idRepartidor = idRepartidor; }
    public String getFechaEntrega() { return fechaEntrega; }
    public String getDireccionPedido() { return direccionPedido; }
    public String getNombreRepartidor() { return nombreRepartidor; }
}