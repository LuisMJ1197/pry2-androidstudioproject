package com.caribejobs.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserProfession implements Serializable {
    private Profession profession;
    private int experienceyears;
    private String details;
    private double costperhour;
    private boolean aNegociar = false;
    private ArrayList<ReferencePicture> referencePictures = new ArrayList<>();

    public UserProfession(Profession profession, int experienceyears, String details, double costperhour) {
        this.profession = profession;
        this.experienceyears = experienceyears;
        this.details = details;
        this.costperhour = costperhour;
    }

    public UserProfession() {

    }

    public UserProfession(Profession profession, Integer experienceyears, String details) {
        this.profession = profession;
        this.experienceyears = experienceyears;
        this.details = details;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public int getExperienceyears() {
        return experienceyears;
    }

    public void setExperienceyears(int experienceyears) {
        this.experienceyears = experienceyears;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getCostperhour() {
        return costperhour;
    }

    public void setCostperhour(double costperhour) {
        this.costperhour = costperhour;
    }

    public boolean isaNegociar() {
        return aNegociar;
    }

    public void setaNegociar(boolean aNegociar) {
        this.aNegociar = aNegociar;
    }

    public ArrayList<ReferencePicture> getReferencePictures() {
        return referencePictures;
    }

    public void setReferencePictures(ArrayList<ReferencePicture> referencePictures) {
        this.referencePictures = referencePictures;
    }

    public void addReferencePicture(ReferencePicture referencePicture) {
        this.referencePictures.add(referencePicture);
    }

    @Override
    public String toString() {
        return "UserProfession{" +
                "profession=" + profession +
                ", experienceyears=" + experienceyears +
                ", details='" + details + '\'' +
                ", costperhour=" + costperhour +
                '}';
    }
}
