package com.alanger.waitermobile.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mesa implements Serializable {

    private int idMesa;
    private int posicion;
    private String nombre;
    private int estado;

    private List<Pedido> pedidoList;

    private List<PedidosResumen> pedidosResumenList;

    public Mesa(int idMesa, int posicion) {
        this.nombre ="";
        this.setEstado(0);
        this.idMesa = idMesa;
        this.posicion = posicion;
        setPedidoList(new ArrayList<>());
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public List<PedidosResumen> getPedidosResumenList() {
        return pedidosResumenList;
    }

    public void setPedidosResumenList(List<PedidosResumen> pedidosResumenList) {
        this.pedidosResumenList = pedidosResumenList;
    }
}
