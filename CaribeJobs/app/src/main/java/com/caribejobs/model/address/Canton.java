package com.caribejobs.model.address;

import java.util.ArrayList;

public class Canton {
    private String name;
    private ArrayList<String> distritos = new ArrayList<>();

    public Canton(String name, ArrayList<String> distritos) {
        this.name = name;
        this.distritos = distritos;
    }

    public Canton() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDistritos() {
        return distritos;
    }

    public void setDistrito(ArrayList<String> distritos) {
        this.distritos = distritos;
    }

    public void addDistrito(String distrito) {
        distritos.add(distrito);
    }

    @Override
    public String toString() {
        return "Canton{" +
                "name='" + name + '\'' +
                ", distrito=" + distritos +
                '}';
    }
}
