package com.caribejobs.model;

import java.io.Serializable;

public class WorkZone implements Serializable {
    private String provincia;
    private String canton;

    public WorkZone(String provincia, String canton) {
        this.provincia = provincia;
        this.canton = canton;
    }

    public WorkZone() {

    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @Override
    public String toString() {
        return "WorkZone{" +
                "provincia='" + provincia + '\'' +
                ", canton='" + canton + '\'' +
                '}';
    }


}
