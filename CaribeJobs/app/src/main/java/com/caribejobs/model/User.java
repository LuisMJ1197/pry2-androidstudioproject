package com.caribejobs.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String firstname;
    private String lastname;
    private String lastname2;
    private String email;
    private String phonenumber1;
    private String phonenumber2;
    private String profilePicture;
    private String birthday;
    private Address address = new Address();
    private ArrayList<WorkZone> workZones = new ArrayList<>();
    private ArrayList<UserProfession> professions = new ArrayList<>();
    private ArrayList<Reference> references = new ArrayList<>();
    private Schedule schedule = new Schedule();
    public static User userLogged = null;


    public User(String username, String firstname, String lastname,
                String lastname2, String email, String phonenumber1, String phonenumber2,
                String profilePicture, String birthday) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lastname2 = lastname2;
        this.email = email;
        this.phonenumber1 = phonenumber1;
        this.phonenumber2 = phonenumber2;
        this.profilePicture = profilePicture;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {

    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber1() {
        return phonenumber1;
    }

    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }

    public String getPhonenumber2() {
        return phonenumber2;
    }

    public void setPhonenumber2(String phonenumber2) {
        this.phonenumber2 = phonenumber2;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(String provincia, String canton, String distrito) {
        this.address = new Address(provincia, canton, distrito);
    }

    public ArrayList<UserProfession> getProfessions() {
        return professions;
    }

    public void setProfessions(ArrayList<UserProfession> professions) {
        this.professions = professions;
    }

    public void addProfession(UserProfession profession) {
        this.professions.add(profession);
    }

    public ArrayList<WorkZone> getWorkZones() {
        return workZones;
    }

    public void setWorkZones(ArrayList<WorkZone> workZones) {
        this.workZones = workZones;
    }

    public static User getUserLogged() {
        return userLogged;
    }

    public static void setUserLogged(User userLogged) {
        User.userLogged = userLogged;
    }

    public void addWorkZone(WorkZone workZone) {
        this.workZones.add(workZone);
    }

    public ArrayList<Reference> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<Reference> references) {
        this.references = references;
    }

    public void addReference(Reference reference) {
        this.references.add(reference);
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", lastname2='" + lastname2 + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber1='" + phonenumber1 + '\'' +
                ", phonenumber2='" + phonenumber2 + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }

    public void setAttribute(String type, String value) {
        switch (type) {
            case "email":
                setEmail(value);
                break;
            case "phonenumber1":
                setPhonenumber1(value);
                break;
            case "phonenumber2":
                setPhonenumber2(value);
                break;
        }
    }
}
