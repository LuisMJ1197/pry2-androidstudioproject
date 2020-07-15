package com.caribejobs.model.address;

import java.util.ArrayList;

public class Utility {
    public static ArrayList<Provincia> provincias = new ArrayList<>();
    private static Utility instance;

    public Utility() {
        init();
    }

    public static Utility getInstance() {
        if (instance == null) {
            instance = new Utility();
        }
        return instance;
    }

    public static void init() {
    }

    public ArrayList<String> getProvinciasName() {
        ArrayList<String> pr = new ArrayList<>();
        for (Provincia provincia: provincias) {
            pr.add(provincia.getName());
        }
        return pr;
    }
}
