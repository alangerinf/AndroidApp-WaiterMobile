package com.alanger.waitermobile.model;

import java.io.Serializable;

public class Mesa implements Serializable {

    private int id;
    private int pos;
    private String name;
    private boolean isOccupied;

    public Mesa(int id, int pos) {
        this.name="";
        this.isOccupied=false;
        this.id=id;
        this.pos=pos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
