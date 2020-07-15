package com.caribejobs.model;

import java.io.Serializable;

public class Reference implements Serializable {
    private int referenceid;
    private String lastJob;
    private String firstname;
    private String lastname;
    private String lastname2;
    private String phonenumber;

    public Reference(String lastJob, String firstname, String lastname, String lastname2, String phonenumber) {
        this.lastJob = lastJob;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lastname2 = lastname2;
        this.phonenumber = phonenumber;
    }

    public Reference(int referenceid, String lastJob, String firstname, String lastname, String lastname2, String phonenumber) {
        this.referenceid = referenceid;
        this.lastJob = lastJob;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lastname2 = lastname2;
        this.phonenumber = phonenumber;
    }

    public Reference() {
    }

    public int getReferenceid() {
        return referenceid;
    }

    public void setReferenceid(int referenceid) {
        this.referenceid = referenceid;
    }

    public String getLastJob() {
        return lastJob;
    }

    public void setLastJob(String lastJob) {
        this.lastJob = lastJob;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname2() {
        return lastname2;
    }

    public void setLastname2(String lastname2) {
        this.lastname2 = lastname2;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Reference{" +
                "lastJob='" + lastJob + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", lastname2='" + lastname2 + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
