package com.alanger.waitermobile.model;

import java.io.Serializable;

public class Pedido extends Mesa implements Serializable {

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
    public String getNombre() {
        return name;
    }

    @Override
    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
