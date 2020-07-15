package com.caribejobs.model.address;

import java.util.ArrayList;

public class Provincia {
    private String name;
    private ArrayList<Canton> cantones = new ArrayList<>();

    public Provincia(String name, ArrayList<Canton> cantones) {
        this.name = name;
        this.cantones = cantones;
    }

    public Provincia() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Canton> getCantones() {
        return cantones;
    }

    public void setCantones(ArrayList<Canton> cantones) {
        this.cantones = cantones;
    }

    public void addCanton(Canton canton) {
        cantones.add(canton);
    }

    public ArrayList<String> getCantonesNames() {
        ArrayList<String> cantones = new ArrayList<>();
        for (Canton canton: this.cantones) {
            cantones.add(canton.getName());
        }
        return cantones;
    }
}
