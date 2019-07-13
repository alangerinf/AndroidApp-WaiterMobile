package com.alanger.waiter.model;

import java.io.Serializable;

public class PedidosResumen implements Serializable {


    private int    unidades;
    private String nombre;
    private float  importeTotal;
    private boolean isEntragado;

    public PedidosResumen(){
        this.unidades=0;
        this.nombre="";
        this.importeTotal=0;
        this.isEntragado=false;
    }

    public PedidosResumen(int unidades,String nombre,float importeTotal,boolean isEntragado){
        this.unidades=unidades;
        this.nombre=nombre;
        this.importeTotal=importeTotal;
        this.isEntragado=isEntragado;
    }


    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public boolean isEntragado() {
        return isEntragado;
    }

    public void setEntragado(boolean entragado) {
        isEntragado = entragado;
    }
}
