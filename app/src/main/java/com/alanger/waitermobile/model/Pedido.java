package com.alanger.waitermobile.model;

public class Pedido extends Mesa{

    private int idPedido;
    private String name;
    private int status;

    public Pedido(int id, int pos) {
        super(id, pos);
        this.name="";
    }
    public Pedido(){
        super(0,0);
        this.name="";
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
