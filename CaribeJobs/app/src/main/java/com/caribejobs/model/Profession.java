package com.caribejobs.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Profession implements Serializable {
    public static ArrayList<Profession> professionsUtil = new ArrayList<>();
    private int professionid;
    private String professionname;

    public Profession(int professionid, String professionname) {
        this.professionid = professionid;
        this.professionname = professionname;
    }

    public int getProfessionid() {
        return professionid;
    }

    public void setProfessionid(int professionid) {
        this.professionid = professionid;
    }

    public String getProfessionname() {
        return professionname;
    }

    public void setProfessionname(String professionname) {
        this.professionname = professionname;
    }



    @Override
    public String toString() {
        return "Profession{" +
                "professionid=" + professionid +
                ", professionname='" + professionname + '\'' +
                '}';
    }
}
