package com.caribejobs.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Address implements Serializable {
    private String provincia = "No establecida";
    private String canton = "No establecido";
    private String distrito = "No establecido";

    public Address(String provincia, String canton, String distrito) {
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
    }

    public Address() {

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

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @NonNull
    @Override
    public Address clone() {
        return new Address(this.provincia, this.canton, this.distrito);
    }

    public void setAttribute(String type, String value) {
        switch (type){
            case "provincia":
                setProvincia(value);
                break;
            case "canton":
                setCanton(value);
                break;
            case "distrito":
                setDistrito(value);
                break;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "provincia='" + provincia + '\'' +
                ", canton='" + canton + '\'' +
                ", distrito='" + distrito + '\'' +
                '}';
    }
}
